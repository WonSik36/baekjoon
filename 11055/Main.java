/*
    baekjoon online judge
    problem number 11055
    https://www.acmicpc.net/problem/11055

    LIS(Longest Increasing Subsequence)
    deep reference: https://m.blog.naver.com/occidere/220793914361
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

        int max = memo[0];
        for(int i=0;i<N;i++){
            for(int j=0;j<i;j++){
                if(arr[j] < arr[i] && memo[i] < memo[j]+arr[i])
                    memo[i] = memo[j]+arr[i];

                max = Math.max(max, memo[i]);
            }
        }

        bw.write(Integer.toString(max)+"\n");

        bw.close();
        br.close();
    }
}
