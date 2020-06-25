/*
    baekjoon online judge
    problem number 17435
    https://www.acmicpc.net/problem/17435

    LCA Algorithm
    Sparse Table
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static final int MAX_DEPTH = 500000;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[] func = Arrays.stream(br.readLine().split(" ")).mapToInt((x)->Integer.parseInt(x)-1).toArray();

        /* make sparse table */
        int maxIndex = (int)Math.ceil(Math.log(MAX_DEPTH) / Math.log(2));
        int[][] memo = new int[maxIndex][N];
        System.arraycopy(func, 0, memo[0], 0, func.length);
        for(int i=0;i<maxIndex-1;i++){
            for(int j=0;j<N;j++){
                memo[i+1][j] = memo[i][memo[i][j]];
            }
        }

        StringTokenizer st = null;
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;

            for(int j=0;j<maxIndex && n > 0; j++, n /= 2){
                if(n % 2 == 1){
                    x = memo[j][x];
                }
            }

            bw.write(Integer.toString(x+1));
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
