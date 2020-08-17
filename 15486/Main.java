/*
    baekjoon online judge
    problem number 15486
    https://www.acmicpc.net/problem/15486

    Dynamic Programming
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
        
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] period = new int[N];
        int[] payment = new int[N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            period[i] = Integer.parseInt(st.nextToken());
            payment[i] = Integer.parseInt(st.nextToken());
        }

        int[] memo = new int[N+1];
        for(int i=0;i<N;i++){
            if(i>0)
                memo[i] = Math.max(memo[i], memo[i-1]);

            if(i+period[i] > N)
                continue;

            memo[i+period[i]] = Math.max(memo[i+period[i]], memo[i]+payment[i]);
        }
        memo[N] = Math.max(memo[N], memo[N-1]);
        
        bw.write(Integer.toString(memo[N])+"\n");

        bw.close();
        br.close();
    }
}
