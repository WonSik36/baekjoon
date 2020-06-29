/*
    baekjoon online judge
    problem number 3977
    https://www.acmicpc.net/problem/3977

    SCC(Strongly Connected Componenet) & Topological Sort
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
import java.util.Stack;
import java.util.Comparator;

public class Main{
    static final String CONFUSED = "Confused\n";
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            if(i != 0)
                br.readLine();

            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] arr = new int[M][2];

            for(int j=0;j<M;j++){
                st = new StringTokenizer(br.readLine());

                arr[j][0] = Integer.parseInt(st.nextToken());
                arr[j][1] = Integer.parseInt(st.nextToken());
            }

            int[] res = solve(N,M,arr);
            if(i != 0)
                bw.write("\n");

            if(res == null){
                bw.write(CONFUSED);
            }else{
                for(int j=0;j<res.length;j++){
                    bw.write(Integer.toString(res[j]));
                    bw.write("\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int[] solve(int N, int M, int[][] arr){
        List<List<Integer>> g = new ArrayList<>();
        for(int i=0;i<N;i++){
            g.add(new ArrayList<>());
        }

        for(int i=0;i<arr.length;i++){
            g.get(arr[i][0]).add(arr[i][1]);
        }

        // dfs for order
        Stack<Integer> order = dfs(g);

        // transpose
        List<List<Integer>> t = transpose(g);

        // dfs for scc
        int[] scc = dfs(t, order);

        // check edge for indegree
        int[] indegree = checkIndegreeOfScc(g, scc);

        int sIdx = -1;
        for(int i=0;i<indegree.length;i++){
            if(indegree[i] == 0){
                if(sIdx == -1){
                    sIdx = i;
                }else{
                    sIdx = -1;
                    break;
                }
            }
        }

        if(sIdx == -1)
            return null;

        List<Integer> res = new ArrayList<>();
        for(int i=0;i<N;i++){
            if(sIdx == scc[i])
                res.add(i);
        }

        res.sort(null);
        return res.stream().mapToInt(Integer::intValue).toArray();
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
            List<Integer> adj = g.get(i);
            for(int node : adj){
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

    static void _dfs(int cur, List<List<Integer>> t, boolean[] visited, int[] scc, int sccIdx){
        visited[cur] = true;
        scc[cur] = sccIdx;

        List<Integer> adj = t.get(cur);
        for(int node : adj){
            if(visited[node])
                continue;

            _dfs(node, t, visited, scc, sccIdx);
        }
    }

    static int[] checkIndegreeOfScc(List<List<Integer>> g, int[] scc){
        int sccIdx = Arrays.stream(scc).max().orElse(-1);
        int[] indegree = new int[sccIdx+1];

        for(int i=0;i<g.size();i++){
            List<Integer> adj = g.get(i);
            for(int node : adj){
                if(scc[i] != scc[node])
                    indegree[scc[node]]++;
            }
        }

        return indegree;
    }

    static void printArr(int[] arr){
        for(int a : arr){
            System.out.printf("%d ", a);
        }
        System.out.println();
    }
}
