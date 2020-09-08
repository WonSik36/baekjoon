/*
    baekjoon online judge
    problem number 9657
    https://www.acmicpc.net/problem/9657

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

        boolean res = dp(N);

        if(res){
            bw.write("SK\n");
        }else{
            bw.write("CY\n");
        }

        bw.close();
        br.close();
    }

    static boolean dp(int N){
        int[] memo = new int[N+4];
        Arrays.fill(memo, -1);

        memo[1] = 1;
        memo[2] = 0;
        memo[3] = 1;
        memo[4] = 1;

        int res = _dp(N, memo);

        if(res == 0)
            return false;
        else   
            return true;
    }

    static int _dp(int N, int[] memo){
        if(memo[N] != -1){
            return memo[N];
        }

        if(_dp(N-1, memo) + _dp(N-3, memo) + _dp(N-4, memo) == 3)
            return memo[N] = 0;
        else
            return memo[N] = 1;
    }
}
