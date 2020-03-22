/*
    baekjoon online judge
    problem number 2315
    https://www.acmicpc.net/problem/2315

    Dynamic Programming

    High reference: 
    https://justicehui.github.io/ps/2019/01/15/BOJ2315/
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static int N;
    static int M;
    static long[][][] memo;
    static int[] pos;
    static int[] cost;
    static long[] sum;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memo = new long[N+1][N+1][2];
        pos = new int[N+1];
        cost = new int[N+1];
        sum = new long[N+1];

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());

            pos[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + cost[i];
        }

        long result = dp(M,M,0);

        bw.write(Long.toString(result)+"\n");
        bw.close();
        br.close();
    }

    static long dp(int left, int right, int leftOrRight){
        if(left==1 && right==N)
            return 0;

        if(memo[left][right][leftOrRight] != 0)
            return memo[left][right][leftOrRight];

        long result = Long.MAX_VALUE;
        int curPos = leftOrRight==0?left:right;
        long energyPerSec = sum[N] - (sum[right] - sum[left-1]);

        if(left > 1){
            result = Min(result, dp(left-1,right, 0) + (pos[curPos] - pos[left-1])*energyPerSec);
        }
        
        if(right < N){
            result = Min(result, dp(left,right+1, 1) + (pos[right+1] - pos[curPos])*energyPerSec);
        }

        memo[left][right][leftOrRight] = result;
        return result;
    }

    static long Min(long a, long b){
        return a<b?a:b;
    }
}
