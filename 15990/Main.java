/*
    baekjoon online judge
    problem number 15990
    https://www.acmicpc.net/problem/15990

    Dynmaic Programming
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final int LENGTH = 100000;
    static final int MOD = 1000000009;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[][] memo = new int[4][LENGTH+1];
        memo[1][1] = 1;
        memo[2][2] = 1;
        memo[1][3] = 1;
        memo[2][3] = 1;
        memo[3][3] = 1;

        for(int i=4;i<=LENGTH;i++){
            memo[1][i] = (memo[2][i-1] + memo[3][i-1]) % MOD;
            memo[2][i] = (memo[1][i-2] + memo[3][i-2]) % MOD;
            memo[3][i] = (memo[1][i-3] + memo[2][i-3]) % MOD;
        }

        // printArr(memo, 10);

        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());

            int res = ((memo[1][N] + memo[2][N]) % MOD + memo[3][N]) % MOD;
            bw.write(Integer.toString(res)+"\n");
        }

        bw.close();
        br.close();
    }

    static void printArr(int[][] memo, int len){
        for(int i=1;i<=len;i++){
            System.out.printf("i:%d -> %d\n", i, memo[1][i]+memo[2][i]+memo[3][i]);
        }
    }
}
