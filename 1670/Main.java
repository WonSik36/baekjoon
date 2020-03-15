/*
    baekjoon online judge
    problem number 1670
    https://www.acmicpc.net/problem/1670

    Dynamice Programming
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static long[] memo;
    static final long mod = 987654321;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        memo = new long[N+1];
        init(memo);
        memo[0] = 1;

        long result = dp(N);

        bw.write(Long.toString(result)+"\n");
        bw.close();
        br.close();
    }

    static void init(long[] arr){
        for(int i=0;i<arr.length;i++)
            arr[i] = -1;
    }

    static long dp(int N){
        if(memo[N] != -1)
            return memo[N];

        memo[N] = 0;
        for(int i=2;i<=N;i+=2){
            memo[N] = (memo[N] + dp(N-i) * dp(i-2))%mod;
        }

        return memo[N];
    }
}
