/*
    baekjoon online judge
    problem number 2533
    https://www.acmicpc.net/problem/2533

    Tree with Dynamic Programming
    I firstly thought it just need to calculate number of node in 2N level and 2N-1 level
    But below case, it doesn't work
    0 0 0
    \ | /
      0
      |
      0
    / | \
    0 0 0

    so it need to use dynamic programming
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
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> g = new ArrayList<>();
        for(int i=0;i<=N;i++){
            g.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            g.get(start).add(end);
            g.get(end).add(start);
        }

        int[][] memo = new int[N+1][2];
        for(int i=0;i<memo.length;i++)
            Arrays.fill(memo[i], -1);

        int result = Min(dp(1, 0, true, g, memo), dp(1, 0, false, g, memo));

        bw.write(Integer.toString(result)+"\n");

        bw.close();
        br.close();
    }

    static int dp(int cur, int prev, boolean checked, List<List<Integer>> g, int[][] memo){
        if(checked && memo[cur][1] != -1)
            return memo[cur][1];
        else if(!checked && memo[cur][0] != -1)
            return memo[cur][0];

        int result = 0;
        if(checked)
            result = 1;

        List<Integer> adj = g.get(cur);
        for(int i=0;i<adj.size();i++){
            int child = adj.get(i);

            if(child == prev)
                continue;

            if(checked){
                int min = Min(dp(child, cur, true, g, memo), dp(child, cur, false, g, memo));
                result += min;
            }else{
                result += dp(child, cur, true, g, memo);
            }
        }

        if(checked)
            memo[cur][1] = result;
        else
            memo[cur][0] = result;

        return result;
    }

    static int Min(int a, int b){
        return a<b?a:b;
    }
}
