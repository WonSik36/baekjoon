/*
    baekjoon online judge
    problem number 4811
    https://www.acmicpc.net/problem/4811

    DFS & Dynamic Programming
    reference: https://lmcoa15.tistory.com/84
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static long[][] memo = new long[31][31];
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while(true){
            int N = Integer.parseInt(br.readLine());

            if(N == 0)
                break;

            if(memo[N][0] == 0){
                memo[N][0] = dp(N,0);
            }

            bw.write(Long.toString(memo[N][0]));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    static long dp(int w, int h){
        if(memo[w][h] != 0){
            return memo[w][h];
        }

        if(w == 0)
            return 1;

        memo[w][h] = dp(w-1,h+1);
        if(h > 0)
            memo[w][h] += dp(w, h-1);

        return memo[w][h];
    }
}
