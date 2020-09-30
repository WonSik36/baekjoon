/*
    baekjoon online judge
    problem number 1758
    https://www.acmicpc.net/problem/1758
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import static java.util.Comparator.comparingInt;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[N];

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr, comparingInt((Integer x) -> x).reversed());

        long sum = 0;
        for(int i=0;i<N;i++){
            if(arr[i] - i > 0)
            sum += (arr[i] - i);
        }

        bw.write(Long.toString(sum)+"\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
