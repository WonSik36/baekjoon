/*
    baekjoon online judge
    problem number 2003
    https://www.acmicpc.net/problem/2003

    Two Pointers Algorithm
    reference: https://m.blog.naver.com/kks227/220795165570
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int cnt = 0, sum = 0;
        int s = 0, e = 0;
        while(true){
            if(sum >= M){
                sum -= arr[s++];
            }else if(e == N){       // order of condition is very important
                break;
            }else{
                sum += arr[e++];
            }

            if(sum == M)
                cnt++;
        }

        bw.write(Integer.toString(cnt)+"\n");

        bw.close();
        br.close();
    }
}
