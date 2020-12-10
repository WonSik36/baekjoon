/*
    baekjoon online judge
    problem number 2240
    https://www.acmicpc.net/problem/2240

    Dynamic Programming
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
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][][] memo = new int[T+1][W+1][2];

        int[][] trees = new int[T][2];
        for(int i=0;i<T;i++){
            int t = Integer.parseInt(br.readLine()) - 1;
            trees[i][t] = 1;
        }

        for(int i=1; i <= T; i++) {
            for(int j=0;j <= W; j++) {
                if(j == 0) {
                    memo[i][j][0] = memo[i-1][j][0] + trees[i-1][0];
                } else {
                    memo[i][j][0] = Math.max(memo[i-1][j][0], memo[i-1][j-1][1]) + trees[i-1][0];
                    memo[i][j][1] = Math.max(memo[i-1][j][1], memo[i-1][j-1][0]) + trees[i-1][1];
                }
            }
        }

        int max = 0;
        for(int i=0;i<=W;i++) {
            for(int j=0;j<2;j++) {
                max = Math.max(max, memo[T][i][j]);
            }
        }

        bw.write(Integer.toString(max)+"\n");
        
        bw.close();
        br.close();
    }
}
