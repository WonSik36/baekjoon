/*
    baekjoon online judge
    problem number 9351
    https://www.acmicpc.net/problem/9351

    High Reference: https://www.crocus.co.kr/1023
    Tree + Dynamic Programming
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        /* make graph */
        int N = Integer.parseInt(br.readLine());
        List<Node> nodeList = new ArrayList<Node>();
        nodeList.add(new Node(0,0));    // add dummy value
        List<List<Node>> g = new ArrayList<List<Node>>();
        for(int i=0;i<=N;i++){
            g.add(new ArrayList<Node>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int num=1;num<=N;num++){
            int weight = Integer.parseInt(st.nextToken());
            nodeList.add(new Node(num, weight));
        }

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            g.get(start).add(nodeList.get(end));
            g.get(end).add(nodeList.get(start));
        }


        /* dynamic programming */
        int[][] memo = new int[N+1][2];

        int max = Max(dp(1, 1, true, nodeList, g, memo), dp(1, 1, false, nodeList, g, memo));
        bw.write(Integer.toString(max)+"\n");

        /* Tracking */
        List<Integer> resList = new ArrayList<Integer>();
        if(memo[1][0] > memo[1][1])
            trace(1, 1, false,  g, memo, resList);
        else
            trace(1, 1, true,  g, memo, resList);
        Collections.sort(resList);

        for(int num : resList){
            bw.write(Integer.toString(num));
            bw.write(" ");
        }
        bw.write("\n");
        
        bw.close();
        br.close();
    }

    static void trace(int cur, int prev, boolean checked, List<List<Node>> g, int[][] memo, List<Integer> resList){
        List<Node> adj = g.get(cur);
        
        if(checked){
            resList.add(cur);

            for(int i=0;i<adj.size();i++){
                int next = adj.get(i).num;
                if(prev == next)
                    continue;                
                
                trace(next, cur, false,  g, memo, resList);
            }

        }else{
            for(int i=0;i<adj.size();i++){
                int next = adj.get(i).num;
                if(prev == next)
                    continue;
                
                
                if(memo[next][0] > memo[next][1])
                    trace(next, cur, false,  g, memo, resList);
                else
                    trace(next, cur, true,  g, memo, resList);
            }
        }
    }

    static int dp(int cur, int prev, boolean checked, List<Node> nodeList, List<List<Node>> g, int[][] memo){
        if(checked && memo[cur][1] != 0)
            return memo[cur][1];
        else if(!checked && memo[cur][0] != 0)
            return memo[cur][0];

        int sum = 0;
        List<Node> adj = g.get(cur);
        
        if(checked){
            sum += nodeList.get(cur).weight;

            for(int i=0;i<adj.size();i++){
                int next = adj.get(i).num;
                if(prev == next)
                    continue;
                sum += dp(next, cur, false, nodeList, g, memo);
            }

            memo[cur][1] = sum;
        }else{
            for(int i=0;i<adj.size();i++){
                int next = adj.get(i).num;
                if(prev == next)
                    continue;
                sum += Max(dp(next, cur, true, nodeList, g, memo), dp(adj.get(i).num, cur, false, nodeList, g, memo));
            }

            memo[cur][0] = sum;
        }

        return sum;
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }
}

class Node {
    public int num;
    public int weight;

    public Node(int num, int weight){
        this.num = num;
        this.weight = weight;
    }
}