/*
    baekjoon online judge
    problem number 9656
    https://www.acmicpc.net/problem/9656

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
        int[] memo = new int[N+3];
        Arrays.fill(memo, -1);

        memo[1] = 0;
        memo[2] = 1;
        memo[3] = 0;

        int res = _dp(N, memo);

        if(res == 0)
            return false;
        else   
            return true;
    }

    static int _dp(int N, int[] memo){
        if(memo[N] != -1){
            if(memo[N] == 0)
                return 0;
            else
                return 1;
        }

        if(_dp(N-1, memo) + _dp(N-3, memo) == 2)
            return memo[N] = 0;
        else
            return memo[N] = 1;
    }
}
