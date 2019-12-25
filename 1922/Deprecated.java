/*
    baekjoon online judge
    problem number 1922
    https://www.acmicpc.net/problem/1922

    Minimum Spanning Tree Problem
    Prim Algorithm
    + Priority Queue
    -> O(ElogE)
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.lang.Comparable;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        List<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
        for(int i=0;i<V;i++){
            graph.add(new ArrayList<Node>());
        }

        for(int i=0;i<E;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from-1).add(new Node(to, weight));
            graph.get(to-1).add(new Node(from, weight));
        }

        int sum = prim(graph,V,E);
        String output = String.format("%d\n", sum);
        bw.write(output);
        bw.close();
        br.close();
    }

    private static int prim(List<ArrayList<Node>> graph, int V, int E){
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        List<Node> adj = graph.get(0);
        boolean[] visited = new boolean[V+1];
        visited[1] = true;  // start from 1st node
        int cnt = 0, sum = 0;
        
        for(int i=0;i<adj.size();i++){
            pq.add(adj.get(i));
        }

        while(cnt<V-1 && !pq.isEmpty()){    
            Node tmp = pq.poll();

            if(visited[tmp.node])
                continue;

            visited[tmp.node] = true;
            adj = graph.get(tmp.node-1);
            for(int i=0;i<adj.size();i++){
                pq.add(adj.get(i));
            }

            cnt++;
            sum += tmp.weight;
        }

        if(cnt != V-1)
            throw new AssertionError("count is not V-1");

        return sum;
    }

    private static class Node implements Comparable<Node>{
        int node;
        int weight;

        public Node(int node, int weight){
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node that){
            if(this.weight < that.weight)
                return -1;
            else if(this.weight == that.weight)
                return 0;
            else
                return 1;
        }
    }
}
