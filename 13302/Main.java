/*
    baekjoon online judge
    problem number 13302
    https://www.acmicpc.net/problem/13302
    using dynaimc programming
    https://www.acmicpc.net/board/view/25480
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] abDays = new int[M];
        // this line is very important when M is 0
        if(M>0){
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++){
                abDays[i] = Integer.parseInt(st.nextToken());
            }
        }
        int price = calculateDays(N, abDays);

        bw.write(Integer.toString(price)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // plan: row for days, column for coupon numbers
    public static int calculateDays(int N, int[] abDays){
        int[][] plan = new int[110][50];
        for(int i=0;i<plan.length;i++)
            Arrays.fill(plan[i], 10000000);
        plan[0][0] = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<48;j++){
                // if day is absent days than skip it
                if(isAbsentDays(i+1, abDays))
                    plan[i+1][j] = MIN(plan[i+1][j],plan[i][j]);
                // calculate 3 cases
                plan[i+1][j] = MIN(plan[i+1][j], plan[i][j]+10000);
                plan[i+3][j+1] = MIN(plan[i+3][j+1], plan[i][j]+25000);
                plan[i+5][j+2] = MIN(plan[i+5][j+2], plan[i][j]+37000);
            
                // if have enough coupon also calculate it
                if(j >= 3)
                    plan[i+1][j-3] = MIN(plan[i+1][j-3], plan[i][j]);
            }
        }

        int price = minPrice(plan[N]);
        return price;
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
        int min = 10000000;

        for(int v: arr){
            if(min>v)
                min = v;
        }
        return min;
    }

    public static int MIN(int a, int b){
        return a<b?a:b;
    }
}
