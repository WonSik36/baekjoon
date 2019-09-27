/*
    baekjoon online judge
    problem number 13302
    https://www.acmicpc.net/problem/13302
    using backtrace
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Deprecated{
    static final int[] SPENDDAYS = {1,3,5};
    static final int[] COST = {10000,25000,37000};
    static final int[] COUPON = {0,1,2};
    static final int TYPENUM = 3;
    static int MIN_PRICE = Integer.MAX_VALUE;
    static int CNT = 0;
    public static void main(String[] args)throws IOException{
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

        calculateDays(N, days);

        bw.write(Integer.toString(MIN_PRICE)+"\n");
        bw.write(Integer.toString(CNT)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void calculateDays(int N, int[] abDays){
        _calculateDays(0,N,abDays,0,0);
    }

    public static void _calculateDays(int day, int N, int[] abDays, int price, int couponNum){
        if((price >= MIN_PRICE) || (day > N)){
            CNT++;
            return;
        }

        if(day == N){
            CNT++;
            MIN_PRICE = price;
            return;
        }

        if(isAbsentDays(day+1, abDays)){
            _calculateDays(day+1,N,abDays,price,couponNum);
            return;
        }

        if(couponNum>=3){
            _calculateDays(day+1,N,abDays,price,couponNum-3);
        }

        int gap = getGapDay2AbDay(day+1, abDays);

        for(int i=0;i<TYPENUM;i++){
            if(gap <= SPENDDAYS[i]+1)
                _calculateDays(day+SPENDDAYS[i],N,abDays,price+COST[i],couponNum+COUPON[i]);
        }

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

    public static int getGapDay2AbDay(int day, int[] days){
        int gap = 0;

        for(int i=0;i<days.length;i++){
            gap = days[i] - day;
            if(gap > 0)
                break;
        }

        if(gap > 5)
            return 5;
        else if(gap < 0)
            return 0;
        else
            return gap;
    }
}
