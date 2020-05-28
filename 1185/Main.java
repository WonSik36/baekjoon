/*
    baekjoon online judge
    problem number 1185
    https://www.acmicpc.net/problem/1185

    Minimum Spanning Tree
    Kruskal Algorithm
    reference: https://m.blog.naver.com/zmtn94/221312946758

    need to use characteristic of tree
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
import java.util.Comparator;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int minWeight = Integer.MAX_VALUE;
        List<Integer> nodeList = new ArrayList<>();
        nodeList.add(0);    // dummy value
        for(int i=0;i<N;i++){
            int weight = Integer.parseInt(br.readLine());
            nodeList.add(weight);

            minWeight = Min(minWeight, weight);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt((Edge e)->e.weight));
        for(int i=0;i<P;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // using characteristic of tree and search
            // edge will be visited twice
            // and add two nodes(start, end) weight
            weight *= 2;
            weight += nodeList.get(start) + nodeList.get(end);

            pq.add(new Edge(start, end, weight));
        }

        int edges = 0;
        int sum = minWeight;            // start node will be visited one more time
                                        // add start node's weight(min weight)
        int[] parent = new int[N+1];
        Arrays.fill(parent, -1);
        while(edges < N-1 && !pq.isEmpty()){
            Edge head = pq.poll();

            if(getRoot(head.start, parent) == getRoot(head.end, parent))
                continue;

            merge(head.start, head.end, parent);
            edges++;
            sum += head.weight;
        }

        bw.write(Integer.toString(sum)+"\n");

        bw.close();
        br.close();
    }

    static int getRoot(int node, int[] parent){
        if(parent[node] == -1)
            return node;

        return parent[node] = getRoot(parent[node], parent);
    }

    static void merge(int a, int b, int[] parent){
        a = getRoot(a, parent);
        b = getRoot(b, parent);

        if(a < b){
            parent[b] = a;
        }else{
            parent[a] = b;
        }
    }

    static int Min(int a, int b){
        return a<b?a:b;
    }
}

class Edge {
    public int start;
    public int end;
    public int weight;

    public Edge(int start, int end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}