/*
    baekjoon online judge
    problem number 1922
    https://www.acmicpc.net/problem/1922

    Minimum Spanning Tree Problem
    Kruskal Algorithm
    + Union Find Algorithm
    -> O(ElogE)
    because of sort edges
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
        
        List<Edge> list = new ArrayList<Edge>();

        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        for(int i=0;i<E;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.add(new Edge(from, to, weight));
        }

        int[] parent = new int[V+1];
        for(int i=0;i<=V;i++){
            parent[i] = i;
        }

        Collections.sort(list);
        Iterator<Edge> it = list.iterator();


        int sum = 0;
        int cnt = 0;
        while(cnt<V && it.hasNext()){
            Edge tmp = it.next();

            int fromRoot = getRoot(tmp.from, parent);
            int toRoot = getRoot(tmp.to, parent);
            if(fromRoot == toRoot)
                continue;

            if(fromRoot < toRoot)
                parent[toRoot] = fromRoot;
            else
                parent[fromRoot] = toRoot;

            cnt++;
            sum += tmp.weight;
        }

        bw.write(Integer.toString(sum)+"\n");
        bw.close();
        br.close();
    }

    public static int getRoot(int node, int[] parent){
        if(node == parent[node])
            return node;
        
        int root = getRoot(parent[node], parent);
        parent[node] = root;
        return root;
    }

    public static class Edge implements Comparable<Edge>{
        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge that){
            if(this.weight > that.weight)
                return 1;
            else if(this.weight < that.weight)
                return -1;
            else
                return 0;
        }
    }
}
