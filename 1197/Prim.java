/*
    baekjoon online judge
    problem number 1197
    https://www.acmicpc.net/problem/1197

    Prim Minimum Spanning Tree Algorithm
    reference: https://www.weeklyps.com/entry/%ED%94%84%EB%A6%BC-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Prims-algorithm
    Time Complexity: ElgV -> Appropirate for Dense Graph
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.lang.Comparable;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<List<Node>> g = new ArrayList<List<Node>>();
        for(int i=0;i<=V;i++){
            g.add(new ArrayList<Node>());
        }

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            g.get(a).add(new Node(b, weight));
            g.get(b).add(new Node(a, weight));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        boolean[] visited = new boolean[V+1];
        int totalWeight = 0;
        int vertex = 0;
        pq.add(new Node(1,0));

        while(vertex < V && !pq.isEmpty()){
            Node first = pq.poll();

            if(visited[first.end])
                continue;
            visited[first.end] = true;
            totalWeight += first.weight;
            vertex++;

            for(Node node : g.get(first.end))
                pq.add(node);
        }

        bw.write(Integer.toString(totalWeight)+"\n");

        bw.close();
        br.close();
    }
}

class Node implements Comparable<Node>{
    public int end;
    public int weight;

    public Node(int end, int weight){
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node that){
        if(this.weight < that.weight)
            return -1;
        else if(this.weight > that.weight)
            return 1;
        else
            return 0;
    }
}