/*
    baekjoon online judge
    problem number 4196
    https://www.acmicpc.net/problem/4196

    SCC(Strongly Connected Componenet) & Topological Sort
    reference: https://www.crocus.co.kr/953
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

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] arr = new int[M][2];
            for(int j=0;j<M;j++){
                st = new StringTokenizer(br.readLine());

                arr[j][0] = Integer.parseInt(st.nextToken()) - 1;
                arr[j][1] = Integer.parseInt(st.nextToken()) - 1;
            }

            int res = execute(N, M, arr);

            bw.write(Integer.toString(res));
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int execute(int N, int M, int[][] arr){
        // make graph
        // init graph
        List<List<Integer>> g = new ArrayList<>();
        for(int i=0;i<N;i++){
            g.add(new ArrayList<>());
        }
        // add edge
        for(int i=0;i<arr.length;i++){
            g.get(arr[i][0]).add(arr[i][1]);
        }


        // dfs
        Stack<Integer> order = dfs(g);

        // make transpose graph
        List<List<Integer>> t = transpose(g);

        // dfs and get scc
        int[] scc = dfs(t, order);

        // get indegree
        int[] indegree = dfs(g, scc);

        int cnt = 0;
        for(int i=0;i<indegree.length;i++){
            if(indegree[i] == 0)
                cnt++;
        }

        return cnt;
    }

    static void printGraph(List<List<Integer>> g){
        for(int i=0;i<g.size();i++){
            System.out.printf("ADJ[%d]:", i);
            for(int node : g.get(i)){
                System.out.printf("%d ", node);
            }
            System.out.println();
        }
    }

    static void printArr(int[] arr){
        for(int a : arr){
            System.out.printf("%d ", a);
        }
        System.out.println();
    }

    static Stack<Integer> dfs(List<List<Integer>> g){
        boolean[] visited = new boolean[g.size()];
        Stack<Integer> order = new Stack<>();
        
        for(int i=0;i<g.size();i++){
            if(visited[i])
                continue;

            _dfs(i, g, visited, order);
        }

        return order;
    } 

    static void _dfs(int cur, List<List<Integer>> g, boolean[] visited, Stack<Integer> order){
        visited[cur] = true;

        List<Integer> adj = g.get(cur);
        for(int node : adj){
            if(visited[node])
                continue;

            _dfs(node, g, visited, order);
        }

        order.add(cur);
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

    static int[] dfs(List<List<Integer>> t, Stack<Integer> order){
        boolean[] visited = new boolean[t.size()];
        int[] scc = new int[t.size()];
        int sccIdx = 0;

        while(!order.isEmpty()){
            int last = order.pop();
            if(visited[last])
                continue;

            _dfs(last, t, visited, scc, sccIdx);
            sccIdx++;
        }

        return scc;
    }

    static void _dfs(int cur, List<List<Integer>> t, boolean[] visited, int[] scc, final int sccIdx){
        visited[cur] = true;
        scc[cur] = sccIdx;

        List<Integer> adj = t.get(cur);
        for(int node : adj){
            if(visited[node])
                continue;

            _dfs(node, t, visited, scc, sccIdx);
        }
    }

    static int[] dfs(List<List<Integer>> g, int[] scc){
        int maxIdx = Arrays.stream(scc).max().orElse(-1);
        int[] indegree = new int[maxIdx+1];

        for(int i=0;i<g.size();i++){
            for(int node : g.get(i)){
                if(scc[i] != scc[node])
                    indegree[scc[node]]++;
            }
        }

        return indegree;
    }
}
