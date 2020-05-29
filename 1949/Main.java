/*
    baekjoon online judge
    problem number 1949
    https://www.acmicpc.net/problem/1949

    Tree with Dynamic Programming
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{
    static int N;
    static int[] arr;
    static int[][] memo;
    static List<List<Integer>> g;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // init
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        memo = new int[N+1][2];
        for(int i=0;i<=N;i++){
            Arrays.fill(memo[i], -1);
        }
        g = new ArrayList<>();
        for(int i=0;i<=N;i++){
            g.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            g.get(start).add(end);
            g.get(end).add(start);
        }

        int result = Max(dp(1, 0, true), dp(1, 0, false));
        bw.write(Integer.toString(result)+"\n");

        bw.close();
        br.close();
    }

    static int dp(int cur, int prev, boolean checked){
        if(checked && memo[cur][1] != -1){
            return memo[cur][1];
        }else if(!checked && memo[cur][0] != -1){
            return memo[cur][0];
        }

        int sum = 0;
        if(checked)
            sum += arr[cur];

        List<Integer> adj = g.get(cur);
        for(int i=0;i<adj.size();i++){
            int tmp = adj.get(i);

            if(tmp == prev)
                continue;

            if(checked)
                sum += dp(tmp, cur, false);
            else
                sum += Max(dp(tmp, cur, false), dp(tmp, cur, true));
        }

        if(checked)
            memo[cur][1] = sum;
        else
            memo[cur][0] = sum;

        return sum;
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }
}
