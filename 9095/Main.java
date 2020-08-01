/*
    baekjoon online judge
    problem number 9095
    https://www.acmicpc.net/problem/9095

    Dynamic Programming
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final int MAX_LEN = 11;
    static int[] memo = new int[MAX_LEN];

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));        
        
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 4;

        for(int i=4;i<MAX_LEN;i++){
            memo[i] = memo[i-1] + memo[i-2] + memo[i-3];
        }

        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());

            bw.write(Integer.toString(memo[N]));
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
