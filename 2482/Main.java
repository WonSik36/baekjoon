/*
    baekjoon online judge
    problem number 2482
    https://www.acmicpc.net/problem/2482

    Dynamic Programming
    reference: https://jaimemin.tistory.com/556
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final int MOD = 1000000003;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[][] memo = new int[N+1][N+1];

        for(int i=1;i<=3;i++){
            memo[i][1] = i;
        }

        for(int i=4;i<=N;i++){

            memo[i][1] = i;

            for(int j=2;j<=N/2;j++){
                memo[i][j] = (memo[i-2][j-1] + memo[i-1][j]) % MOD;
            }
        }

        // printArr(memo);
        bw.write(Integer.toString(memo[N][K])+"\n");
        bw.close();
        br.close();
    }

    static void printArr(int[][] memo){
        for(int i=1;i<memo.length;i++){
            for(int j=1;j<memo[i].length;j++){
                System.out.print(memo[i][j]+" ");
            }
            System.out.println();
        }
    }
}
