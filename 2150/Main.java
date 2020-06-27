/*
    baekjoon online judge
    problem number 2150
    https://www.acmicpc.net/problem/2150

    Strongly Connected Component
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
import java.util.Collections;
import java.util.Comparator;

public class Main{
    static int V;
    static int E;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        List<List<Integer>> g = new ArrayList<>();
        for(int i=0;i<V;i++){
            g.add(new ArrayList<>());
        }
        
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            g.get(u).add(v);
        }

        // get visit order
        Stack<Integer> order = new Stack<>();
        dfs(g, order);

        // make traspose graph
        List<List<Integer>> t = transpose(g);

        // dfs with reverse order
        // get scc
        List<List<Integer>> res = new ArrayList<>(); 
        dfs(t, order, res);

        for(List<Integer> scc : res){
            Collections.sort(scc);
        }

        Collections.sort(res, Comparator.comparingInt(e -> e.get(0)));

        bw.write(Integer.toString(res.size())+"\n");
        for(List<Integer> scc : res){
            for(int node : scc){
                bw.write(Integer.toString(node+1));
                bw.write(" ");
            }
            bw.write("-1\n");
        }

        bw.close();
        br.close();
    }

    static void dfs(List<List<Integer>> g, Stack<Integer> order){
        boolean[] visited = new boolean[g.size()];

        for(int i=0;i<g.size();i++){
            if(visited[i])
                continue;

            _dfs(i, g, visited, order);
        }
    }

    static void _dfs(int cur, List<List<Integer>> g, boolean[] visited, Stack<Integer> order){
        if(visited[cur])
            return;
        visited[cur] = true;

        List<Integer> adj = g.get(cur);
        for(int node : adj){
            if(visited[node])
                continue;

            _dfs(node, g, visited, order);
        }

        order.add(cur);
    }

    static void dfs(List<List<Integer>> g, Stack<Integer> order, List<List<Integer>> sccList){
        boolean[] visited = new boolean[g.size()];

        while(!order.isEmpty()){
            int first = order.pop();
            if(visited[first])
                continue;

            List<Integer> scc = new ArrayList<>();
            _dfs(first, g, visited, scc);
            sccList.add(scc);
        }
    }

    static void _dfs(int cur, List<List<Integer>> g, boolean[] visited, List<Integer> scc){
        if(visited[cur])
            return;
        visited[cur] = true;
        scc.add(cur);

        List<Integer> adj = g.get(cur);
        for(int node : adj){
            if(visited[node])
                continue;

            _dfs(node, g, visited, scc);
        }
    }

    static List<List<Integer>> transpose(List<List<Integer>> g){
        List<List<Integer>> t = new ArrayList<>();

        for(int i=0;i<g.size();i++){
            t.add(new ArrayList<>());
        }

        for(int i=0;i<g.size();i++){
            for(int node : g.get(i)){
                t.get(node).add(i);
            }
        }

        return t;
    }
}
