/*
    baekjoon online judge
    problem number 13302
    https://www.acmicpc.net/problem/13302
    using dynaimc programming
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static boolean DEBUG = false;
    static final int[] SPENDDAYS = {1,1,3,5};
    static final int[] COST = {0,10000,25000,37000};
    static final int[] COUPON = {0,0,1,2};
    static final int TYPENUM = 4;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] days = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++){
            days[i] = Integer.parseInt(st.nextToken());
        }

        int price = calculateDays(N, days);

        bw.write(Integer.toString(price)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // N: vacation days, days: absent days to resort
    // using dynamic programming
    public static int calculateDays(int N, int[] abDays){
        int price = 0;  // result of price
        int[][] plan = new int[N+1][TYPENUM];   // plan of vacations 0:coupon, 1:1day, 2:3days, 3:5days. which has total costs
        int[][] coupon = new int[N+1][TYPENUM];  // coupon numbers

        for(int i=1;i<=N;i++){
            for(int kind=0;kind<TYPENUM;kind++){
                if(isAbsentDays(i, abDays)){
                    plan[i][kind] = plan[i-1][kind];
                    coupon[i][kind] = coupon[i-1][kind];
                }
                else if(kind == 0)
                    calculateCoupon(i,plan,coupon);
                else
                    calculate(i,kind,plan,coupon);
            }
        }

        print2DArray(plan);
        print2DArray(coupon);

        price = minPrice(plan[N]);
        return price;
    }

    public static void calculateCoupon(int day, int[][] plan, int[][] coupon){
        int min = Integer.MAX_VALUE;
        int idx = 0;
        for(int i=0;i<TYPENUM;i++){
            print("day: %d, type:%d, coupon:%d, price: %d\n",day-1,i,coupon[day-1][i],plan[day-1][i]);
            if((coupon[day-1][i]>=3) && (plan[day-1][i] < min)){
                min = plan[day-1][i];
                idx = i;
            }
        }

        // System.out.println("calculate coupon day: "+day+" min: "+min);
        if(min == Integer.MAX_VALUE){
            plan[day][0] = Integer.MAX_VALUE;
            return;    
        }

        plan[day][0] = min;
        coupon[day][0] = coupon[day-1][idx] - 3;
    }

    public static void calculate(int day, int kind, int[][] plan, int[][] coupon){
        int pastDay = day-SPENDDAYS[kind];
        if(pastDay < 0){
            plan[day][kind] = Integer.MAX_VALUE;
            return;
        }

        int min = Integer.MAX_VALUE;
        int idx = 0;
        int value = 0;
        for(int i=0;i<TYPENUM;i++){
            if(plan[pastDay][i] - 3400*coupon[pastDay][i] < min){
                min = plan[pastDay][i] - 3400*coupon[pastDay][i];
                idx = i;
                value = plan[pastDay][i];
            }
        }

        if(min == Integer.MAX_VALUE)
            throw new RuntimeException("Min price value is Integer MAX");

        plan[day][kind] = value + COST[kind];
        coupon[day][kind] = coupon[pastDay][idx] + COUPON[kind];
    }

    public static boolean isAbsentDays(int day, int[] days){
        for(int i=0;i<days.length;i++){
            if(days[i] == day)
                return true;
            else if(days[i] > day)
                break;
        }
        return false;
    }

    public static int minPrice(int[] arr){
        int min = Integer.MAX_VALUE;

        for(int v: arr){
            if(min>v)
                min = v;
        }
        return min;
    }

    public static void print(int num){
        if(DEBUG)
            System.out.println(num);
    }

    public static void print(String str){
        if(DEBUG)
            System.out.print(str);
    }

    public static void print(String str, int... ints){
        if(DEBUG){
            String[] spl = str.split("%d");
            String output = spl[0];
            int idx = 1;
            for(int number:ints){
                output += number;
                if(idx < spl.length)
                    output += spl[idx++];
            }
            System.out.print(output);
        }
    }

    public static void printArray(int[] arr){
        if(DEBUG){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
    }

    public static void print2DArray(int[][] arr){
        for(int i=0;i<arr.length;i++){
            printArray(arr[i]);
        }
    }
}
