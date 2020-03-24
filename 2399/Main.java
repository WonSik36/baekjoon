/*
    baekjoon online judge
    problem number 2399
    https://www.acmicpc.net/problem/2399
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
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long sum = 0;

        for(int i=0;i<N;i++){
            for(int j=i;j<N;j++){
                sum += Math.abs(arr[i] - arr[j]);
            }
        }

        sum *= 2;

        bw.write(Long.toString(sum)+"\n");
        bw.close();
        br.close();
    }
}
