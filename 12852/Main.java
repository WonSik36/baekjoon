/*
    baekjoon online judge
    problem number 12852
    https://www.acmicpc.net/problem/12852

    Dynamic Programming + Shortest Path Reverse Tracking
    reference: http://blog.devjoshua.me/2020/01/19/boj-12852/
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static int[] memo;
    static int[] before;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        memo = new int[N+1];
        before = new int[N+1];

        int min = dp(N);
        // printArr(memo);
        // printArr(before);

        int[] path = new int[min+1];
        int start = 1;
        for(int i=0;i<=min;i++){
            path[path.length-1-i] = start;
            start = before[start];
        }

        bw.write(Integer.toString(min)+"\n");
        for(int i=0;i<path.length;i++){
            bw.write(Integer.toString(path[i]));
            bw.write(" ");
        }
        bw.write("\n");

        bw.close();
        br.close();
    }

    static int dp(int N){
        Arrays.fill(memo, Integer.MAX_VALUE);
        memo[N] = 0;

        for(int i=N;i>1;i--){
            if(i % 3 == 0 && memo[i/3] > memo[i]+1){
                memo[i/3] = memo[i]+1;
                before[i/3] = i;
            }
            if(i % 2 == 0 && memo[i/2] > memo[i]+1){
                memo[i/2] = memo[i]+1;
                before[i/2] = i;
            }
            if(memo[i-1] > memo[i]+1){
                memo[i-1] = memo[i]+1;
                before[i-1] = i;
            }
        }

        return memo[1];
    }

    static void printArr(int[] arr){
        for(int i=1;i<arr.length;i++){
            System.out.printf("%d ",arr[i]);
        }
        System.out.println();
    }

    static int Min(int a, int b){
        return a<b?a:b;
    }
}
