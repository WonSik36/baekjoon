/*
    baekjoon online judge
    problem number 1806
    https://www.acmicpc.net/problem/1806

    Two Pointers Algorithm
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
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int l = 0, r = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        while(true){
            if(sum >= S){
                sum -= arr[l++];
            }else if(r == N){
                break;
            }else{
                sum += arr[r++];
            }

            // System.out.printf("l: %d, r: %d, sum: %d\n", l,r,sum);
            
            if(sum >= S)
                minLen = Math.min(minLen, r-l);

            // System.out.printf("minLen: %d\n", minLen);
        }
        
        if(minLen == Integer.MAX_VALUE)
            bw.write("0\n");
        else
            bw.write(Integer.toString(minLen)+"\n");

        bw.close();
        br.close();
    }
}
