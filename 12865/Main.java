/*
    baekjoon online judge
    problem number 12865
    https://www.acmicpc.net/problem/12865
    Knapsack Problem
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
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] weight = new int[N+1];
        int[] value = new int[N+1];
        int[][] dp = new int[N+1][W+1];

        for(int i=1;i<=N;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }
        // i means ith item
        for(int i=1;i<=N;i++){
            // j means current weight
            for(int j=1;j<=W;j++){
                if(j<weight[i])
                    dp[i][j] = dp[i-1][j];
                else{
                    dp[i][j] = Max(dp[i-1][j-weight[i]]+value[i],dp[i-1][j]);
                }
            }
        }
        //printArray(dp);
        bw.write(Integer.toString(dp[N][W]));
        bw.flush();
        bw.close();
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }

    static void printArray(int[][] arr)throws IOException{
        for(int i=1;i<arr.length;i++){
            for(int j=1;j<arr[0].length;j++)
                System.out.print(arr[i][j]+" ");
            System.out.println();
        }
    }
}