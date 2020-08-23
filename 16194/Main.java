/*
    baekjoon online judge
    problem number 16194
    https://www.acmicpc.net/problem/16194

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
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] memo = new int[N];
        System.arraycopy(arr, 0, memo, 0, N);

        for(int i=0;i<N;i++){
            memo[i] = arr[i];
            for(int j=0;j<i;j++){
                memo[i] = Math.min(memo[i], memo[i-1-j] + arr[j]);
            }
        }

        bw.write(Integer.toString(memo[N-1])+"\n");

        bw.close();
        br.close();
    }
}
