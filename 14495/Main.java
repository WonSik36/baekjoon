/*
    baekjoon online judge
    problem number 14495
    https://www.acmicpc.net/problem/14495
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());

        long[] memo = new long[N+3];
        memo[1] = 1;
        memo[2] = 1;
        memo[3] = 1;

        for(int i=4;i<=N;i++){
            memo[i] = memo[i-1] + memo[i-3];
        }

        bw.write(Long.toString(memo[N])+"\n");

        bw.close();
        br.close();
    }
}
