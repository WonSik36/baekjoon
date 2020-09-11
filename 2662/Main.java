/*
    baekjoon online judge
    problem number 2662
    https://www.acmicpc.net/problem/2662

    Dynamic Programming + Shortest Path Reverse Tracking
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.lang.StringBuilder;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] roi = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            for(int j=0;j<M;j++){
                roi[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // printArr(roi);

        int[][] memo = new int[N+1][M+1];
        int[][] budget = new int[N+1][M+1];
        for(int i=1;i<=M;i++){
            for(int j=1;j<=N;j++){
                memo[j][i] = memo[j][i-1];
                budget[j][i] = 0;

                for(int k=1;k<=j;k++){
                    if(memo[j][i] < memo[j-k][i-1] + roi[k-1][i-1]){
                        memo[j][i] = memo[j-k][i-1] + roi[k-1][i-1];
                        budget[j][i] = k;
                    }
                }
            }
        }

        // printArr(memo);
        // printArr(budget);

        bw.write(Integer.toString(memo[N][M]));
        bw.write("\n");
        bw.write(getBudgets(N,M,budget));
        bw.write("\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static String getBudgets(int row, int col, int[][] budget){
        StringBuilder sb = new StringBuilder();
        int i = row, j = col;
        while(j > 0){
            sb.insert(0, " ");
            sb.insert(0, budget[i][j]);
            i -= budget[i][j];
            j--;
        }

        return sb.toString();
    }

    static void printArr(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.printf("%d ", arr[i][j]);
            }
            System.out.println();
        }
    }
}
