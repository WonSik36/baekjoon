/*
    baekjoon online judge
    problem number 12996
    https://www.acmicpc.net/problem/12996

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
    static final int MOD = 1000000007;
    static final int[][] cases = {
        {-1,0,0}, {0,-1,0}, {0,0,-1}, 
        {0,-1,-1}, {-1,0,-1}, {-1,-1,0},
        {-1,-1,-1}
    };
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] arr = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int S = Integer.parseInt(st.nextToken());
        for(int i=0;i<3;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int res = dp(S, arr);
        bw.write(Integer.toString(res)+"\n");

        bw.close();
        br.close();
    }

    static int dp(int S, int[] arr){
        int[][][][] memo = new int[S+1][S+1][S+1][S+1];
        for(int i=0;i<memo.length;i++){
            for(int j=0;j<memo[i].length;j++){
                for(int k=0;k<memo[i][j].length;k++){
                    Arrays.fill(memo[i][j][k], -1);
                }
            }
        }

        return _dp(S,arr,memo);
    }

    static int _dp(int S, int[] arr, int[][][][] memo){
        if(memo[S][arr[0]][arr[1]][arr[2]] != -1)
            return memo[S][arr[0]][arr[1]][arr[2]];

        if(S > arr[0]+arr[1]+arr[2])
            return 0;

        if(S==0){
            if(arr[0]==0 && arr[1]==0 && arr[2]==0)
                return 1;
            else
                return 0;
        }

        int sum = 0;

        for(int i=0;i<cases.length;i++){
            for(int j=0;j<cases[i].length;j++){
                arr[j] += cases[i][j];
            }

            if(S >= 0 && arr[0] >= 0 && arr[1] >= 0 && arr[2] >= 0)
                sum = (sum + _dp(S-1, arr, memo)) % MOD;

            for(int j=0;j<cases[i].length;j++){
                arr[j] -= cases[i][j];
            }
        }

        return memo[S][arr[0]][arr[1]][arr[2]] = sum;
    }
}
