/*
    baekjoon online judge
    problem number 1197
    https://www.acmicpc.net/problem/1197

    Kruskal Minimum Spanning Tree Algorithm
    reference: https://gmlwjd9405.github.io/2018/08/29/algorithm-kruskal-mst.html
    Time Complexity: ElgE -> Appropirate for Sparse Graph
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.PriorityQueue;
import java.lang.Comparable;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.add(new Edge(a,b,weight));
        }

        int vertex = 0;
        int totalWeight = 0;
        int[] parent = new int[V+1];
        while(vertex < V && !pq.isEmpty()){
            Edge first = pq.poll();

            int parentOfA = findParent(first.A, parent);
            int parentOfB = findParent(first.B, parent);

            if(parentOfA == parentOfB)
                continue;

            merge(parentOfA, parentOfB, parent);
            totalWeight += first.weight;
            vertex++;
        }

        bw.write(Integer.toString(totalWeight)+"\n");

        bw.close();
        br.close();
    }

    static int findParent(int a, int[] parent){
        if(parent[a] == 0)
            return a;
        
        return parent[a] = findParent(parent[a], parent);
    }

    static void merge(int a, int b, int[] parent){
        if(a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }
}

class Edge implements Comparable<Edge>{
    public int A;
    public int B;
    public int weight;
    
    public Edge(int A, int B, int weight){
        this.A = A;
        this.B = B;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge that){
        if(this.weight < that.weight){
            return -1;
        }else if(this.weight > that.weight){
            return 1;
        }else{
            return 0;
        }
    }
}