/*
    baekjoon online judge
    problem number 14916
    https://www.acmicpc.net/problem/14916

    Dynamic Programming
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
        
        int N = Integer.parseInt(br.readLine());

        int[] memo = new int[100001];
        Arrays.fill(memo, -1);
        memo[2] = 1;
        memo[4] = 2;
        memo[5] = 1;

        for(int i=6; i<=100000; i++) {
            if(memo[i-5] != -1) {
                memo[i] = memo[i-5] + 1;

                if(memo[i-2] != -1)
                    memo[i] = Math.min(memo[i], memo[i-2] + 1);
            }
            if(memo[i] == -1 && memo[i-2] != -1)
                memo[i] = memo[i-2] + 1;
        }

        bw.write(Integer.toString(memo[N]));
        bw.write("\n");

        bw.close();
        br.close();
    }
}
