/*
    baekjoon online judge
    problem number 1240
    https://www.acmicpc.net/problem/1240

    Depth First Search
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Arrays;
import java.lang.StringBuilder;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // init
        List<List<Node>> g = new ArrayList<>();
        for(int i=0;i<=N;i++){
            g.add(new ArrayList<>());
        }

        int[][] memo = new int[N+1][N+1];
        for(int i=0;i<=N;i++){
            Arrays.fill(memo[i], -1);
        }

        // make graph
        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            g.get(start).add(new Node(end, dist));
            g.get(end).add(new Node(start, dist));
        }

        // answer question
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            bw.write(Integer.toString(dp(start, end, g, memo)));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    static int dp(int start, int end, List<List<Node>> g, int[][] memo){
        if(memo[start][end] != -1)
            return memo[start][end];

        boolean[] visited = new boolean[g.size()];
        Stack<Node> st = new Stack<>();
        st.push(new Node(start, 0));
        memo[start][start] = 0;

        while(!st.isEmpty()){
            Node last = st.pop();
            // System.out.println(last.toString());

            visited[last.num] = true;

            List<Node> adj = g.get(last.num);
            for(int i=0;i<adj.size();i++){
                Node tmp = adj.get(i);

                if(visited[tmp.num])
                    continue;

                int dist = last.dist+tmp.dist;
                st.push(new Node(tmp.num, dist));
                memo[start][tmp.num] = dist;
                memo[tmp.num][start] = dist;
                // System.out.printf("memo[%d][%d] = %d\n", last.num, tmp.num, dist);
            }
        }

        return memo[start][end];
    }
}

class Node {
    public int num;
    public int dist;

    public Node(int num, int dist){
        this.num = num;
        this.dist = dist;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("num: ");
        sb.append(num);
        sb.append(", dist: ");
        sb.append(dist);

        return sb.toString();
    }
}