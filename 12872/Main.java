/*
    baekjoon online judge
    problem number 12872
    https://www.acmicpc.net/problem/12872

    Dynamic Programming
    high reference: https://sgc109.tistory.com/66

    dp's each stage should represent process of choosing music
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static final long MOD = 1000000007;
    static int N;
    static int M;
    static int P;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        long res = dp(P, N);
        bw.write(Long.toString(res)+"\n");

        bw.close();
        br.close();
    }

    static long dp(int p, int n){
        long[][] memo = new long[P+1][N+1];
        for(int i=0;i<=P;i++){
            Arrays.fill(memo[i], -1);
        }
        memo[0][0] = 1;

        return _dp(p, n, memo);
    }

    static long _dp(int p, int n, long[][] memo){
        if(memo[p][n] != -1){
            // System.out.printf("p:%d n:%d => %d\n", p,n,memo[p][n]);
            return memo[p][n];
        }
        
        if(p == 0)
            return memo[p][n] = 0;

        long res = 0;
        if(n > 0)
            res = (res + (N - n + 1) * _dp(p-1, n-1, memo)) % MOD;
        if(n > M)
            res = (res + (n - M) * _dp(p-1, n, memo)) % MOD;

        // System.out.printf("p:%d n:%d => %d\n", p,n,res);
        return memo[p][n] = res;
    }
}
