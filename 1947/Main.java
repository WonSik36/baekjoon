/*
    baekjoon online judge
    problem number 1947
    https://www.acmicpc.net/problem/1947

    Dynamic Programming
    high reference: https://j3sung.tistory.com/63
    memo[i] = (i-1)*(memo[i-1]+memo[i-2])
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final long mod = 1000000000;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        if(N==1){
            bw.write("0\n");
        }else if(N==2){
            bw.write("1\n");
        }else{
            long[] memo = new long[N+1];
            memo[0] = 0;
            memo[1] = 0;
            memo[2] = 1;

            for(int i=3;i<=N;i++){
                memo[i] = ((i-1)*(memo[i-1]+memo[i-2]))%mod;
            }
            bw.write(Long.toString(memo[N])+"\n");
        }

        bw.close();
        br.close();
    }
}
