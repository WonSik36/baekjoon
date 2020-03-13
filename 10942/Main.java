/*
    baekjoon online judge
    problem number 10942
    https://www.acmicpc.net/problem/10942

    Dynamic Programming

    reference: https://mygumi.tistory.com/176
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static boolean[][] memo;
    static boolean[][] visited;
    static int[] arr;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        memo = new boolean[N][N];
        visited = new boolean[N][N];

        // initialize
        for(int i=0;i<N;i++){
            memo[i][i] = true;
            visited[i][i] = true;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(dp(start-1, end-1))
                bw.write("1\n");
            else
                bw.write("0\n");
        }

        bw.close();
        br.close();
    }

    static boolean dp(int start, int end){
        if(visited[start][end])
            return memo[start][end];
        visited[start][end] = true;
            
        if(arr[start] == arr[end]){
            if(start+1 == end){
                memo[start][end] = true;
                return memo[start][end];
            }else{
                memo[start][end] = dp(start+1,end-1);
                return memo[start][end];
            }
        }else{
            memo[start][end] = false;
            return memo[start][end];
        }
    }
}
