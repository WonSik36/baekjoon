/*
    baekjoon online judge
    problem number 11280
    https://www.acmicpc.net/problem/11280

    SCC(Strongly Connected Component)
    and 2-SAT(2-Satisfiability Problem)

    high reference: https://kks227.blog.me/220803009418
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
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class Main{
    static int N;
    static int M;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<List<Integer>> g = Stream.generate(() -> new ArrayList<Integer>()).limit(2*N).collect(toList());

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            start = convertToIdx(start);
            end = convertToIdx(end);

            g.get(opposite(start)).add(end);
            g.get(opposite(end)).add(start);
        }

        Stack<Integer> order = dfs(g);

        List<List<Integer>> t = transpose(g);

        int[] scc = dfs(t, order);
        
        boolean result = isValid(scc);
        
        if(result)
            bw.write("1\n");
        else
            bw.write("0\n");

        bw.close();
        br.close();
    }

    static Stack<Integer> dfs(List<List<Integer>> g){
        Stack<Integer> order = new Stack<>();
        boolean[] visited = new boolean[N*2];

        for(int i=0;i<N*2;i++){
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
        List<List<Integer>> t = Stream.generate(() -> new ArrayList<Integer>()).limit(N*2).collect(toList());
        
        for(int i=0;i<2*N;i++){
            List<Integer> adj = g.get(i);
            for(int node : adj)
                t.get(node).add(i);
        }

        return t;
    }

    static int[] dfs(List<List<Integer>> t, Stack<Integer> order){
        int[] scc = new int[2*N];
        Arrays.fill(scc, -1);
        int sccIdx = 0;

        while(!order.isEmpty()){
            int last = order.pop();
            if(scc[last] != -1)
                continue;

            _dfs(last, t, scc, sccIdx);
            sccIdx++;
        }

        return scc;
    }

    static void _dfs(int cur, List<List<Integer>> t, int[] scc, int sccIdx){
        scc[cur] = sccIdx;

        List<Integer> adj = t.get(cur);
        for(int node : adj){
            if(scc[node] != -1)
                continue;

            _dfs(node, t, scc, sccIdx);
        }
    }

    static boolean isValid(int[] scc){
        for(int i=0;i<N;i++){
            if(scc[i*2] == scc[i*2+1])
                return false;
        }

        return true;
    }

    static int convertToIdx(int idx){
        return idx < 0 ? -1*(idx+1)*2 : idx*2-1;
    }

    static int opposite(int idx){
        return idx%2==1 ? idx-1 : idx+1;
    }
}
