/*
    baekjoon online judge
    problem number 15900
    https://www.acmicpc.net/problem/15900

    Depth First Search Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.stream.Stream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class Main{
    static int N;
    static List<List<Integer>> g;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        g = Stream.generate(() -> new ArrayList<Integer>()).limit(N).collect(toList());

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            g.get(start).add(end);
            g.get(end).add(start);
        }

        int res = dfs(0);
        if(res % 2 == 0){
            bw.write("No\n");
        }else{
            bw.write("Yes\n");
        }

        bw.close();
        br.close();
    }

    static int dfs(int root){
        boolean[] visited = new boolean[N];
        visited[root] = true;

        int sum = 0;

        List<Integer> adj = g.get(root);
        for(int node : adj){
            sum += _dfs(node, 1, visited);
        }

        return sum;
    }

    static int _dfs(int cur, int dist, boolean[] visited){
        visited[cur] = true;

        List<Integer> adj = g.get(cur);

        if(adj.size() == 1)
            return dist;
        

        int sum = 0;
        for(int node: adj){
            if(visited[node])
                continue;

            sum += _dfs(node, dist+1, visited);
        }

        return sum;
    }
}
