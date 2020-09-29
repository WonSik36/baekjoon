/*
    baekjoon online judge
    problem number 17212
    https://www.acmicpc.net/problem/17212

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
    static private final int DUMMY = -1;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[] memo = new int[N+8];
        Arrays.fill(memo, DUMMY);
        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 1;
        memo[5] = 1;
        memo[7] = 1;

        int coin = minCoin(N, memo);
        bw.write(Integer.toString(coin)+"\n");

        bw.close();
        br.close();
    }

    static int minCoin(int N, int[] memo){
        if(memo[N] != DUMMY)
            return memo[N];

        int _minCoin = Integer.MAX_VALUE;
        if(N > 1)
            _minCoin = Math.min(_minCoin, minCoin(N-1, memo)+1);
        if(N > 2)
            _minCoin = Math.min(_minCoin, minCoin(N-2, memo)+1);
        if(N > 5)
            _minCoin = Math.min(_minCoin, minCoin(N-5, memo)+1);
        if(N > 7)
            _minCoin = Math.min(_minCoin, minCoin(N-7, memo)+1);

        return memo[N] = _minCoin;
    }
}
