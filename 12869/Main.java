/*
    baekjoon online judge
    problem number 12869
    https://www.acmicpc.net/problem/12869

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
        int[] arr = new int[3];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        bw.write(Integer.toString(dp(arr)));

        bw.close();
        br.close();
    }

    static int dp(int[] arr){
        int[][][] memo = new int[61][61][61];
        for(int i=0;i<memo.length;i++){
            for(int j=0;j<memo[i].length;j++){
                Arrays.fill(memo[i][j], -1);
            }
        }
        memo[0][0][0] = 0;

        return _dp(arr, memo);
    }

    static int _dp(int[] arr, int[][][] memo){
        // System.out.printf("visit %d %d %d\n", arr[0], arr[1], arr[2]);

        if(memo[arr[0]][arr[1]][arr[2]] != -1){
            return memo[arr[0]][arr[1]][arr[2]];
        }

        int min = Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            if(arr[i] == 0)
                continue;
            int[] input = new int[3];
            input[i] = minus(arr[i], 9);
            input[(i+1)%3] = minus(arr[(i+1)%3], 3);
            input[(i+2)%3] = minus(arr[(i+2)%3], 1);
            Arrays.sort(input);
            min = Math.min(min, _dp(input, memo));
        }
        for(int i=2;i>=0;i--){
            if(arr[i] == 0)
                continue;
            int[] input = new int[3];
            input[i] = minus(arr[i], 9);
            input[(i+2)%3] = minus(arr[(i+2)%3], 3);
            input[(i+1)%3] = minus(arr[(i+1)%3], 1);
            Arrays.sort(input);
            min = Math.min(min, _dp(input, memo));
        }

        // System.out.printf("result %d %d %d = %d\n", arr[0], arr[1], arr[2], min+1);

        return memo[arr[0]][arr[1]][arr[2]] = min+1;
    }

    static int minus(int a, int b){
        return a>b ? a-b : 0;
    }
}
