/*
    baekjoon online judge
    problem number 2228
    https://www.acmicpc.net/problem/2228

    high reference:
    https://ksj14.tistory.com/entry/BeakJoon2228-%EA%B5%AC%EA%B0%84-%EB%82%98%EB%88%84%EA%B8%B0

    1. Use sum array for reducing sum operations
    2. Think step by step. f(N) will be made by f(N-1)
    3. If you want to access Memoization, access it through a function.
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final int MIN_INF = -1000000000;
    static int N;
    static int M;
    static int[] sum;
    static int[][] memo;
    static boolean[][] visited;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sum = new int[N+1];
        memo = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for(int i=1;i<=N;i++){
            sum[i] = Integer.parseInt(br.readLine())+sum[i-1];
        }

        int result = dp(N,M);

        bw.write(Integer.toString(result)+"\n");
        bw.close();
        br.close();
    }

    static int dp(int N, int M){
        if(M<=0)
            return 0;
        if(N<=0)
            return MIN_INF;

        if(visited[N][M])
            return memo[N][M];
        visited[N][M] = true;


        int max = dp(N-1,M); // does not contain Nth number
        
        // contain Nth number
        for(int i=N;i>0;i--){
            max = Max(max, dp(i-2,M-1)+(sum[N] - sum[i-1]));
        }

        return memo[N][M] = max;
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }
}
