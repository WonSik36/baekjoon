/*
    baekjoon online judge
    problem number 2463
    https://www.acmicpc.net/problem/2463
    https://jaimemin.tistory.com/943

    add set problem

    This problem first requires finding the rules of the cost. 
    You can find the cost by sorting the edges in descending order and adding them one by one.
    Secondly, this problem requires that the sum of the sets be performed in an effective way. 
    Representing a set of each node in a generic list, set will result in a memory overflow error. 
    Therefore, you need to take advantage of the ancestor nodes to sum the set.
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;
import java.lang.Comparable;

public class Main{
    static boolean DEBUG = false;
    static final int mod = 1000000000;
    static long SUM = 0;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // get first line -> vertex,edge
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // get second line -> edge
        // ArrayList<Edge> list = new ArrayList<Edge>();
        PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            queue.add(new Edge(start,end,weight));
            SUM += weight;
        }

        // make set of each node
        // by setting parent and size
        int parent[] = new int[V+1];
        int size[] = new int[V+1];
        for(int i=1;i<=V;i++){
            parent[i] = i;
            size[i] = 1;
        }

        // calculate sum of cost(u,v)
        long cur = 0;
        long result = 0;
        while(!queue.isEmpty()){
            Edge e = queue.poll();
            cur += e.weight;
            int from, to;

            // from should be smaller than to
            if(e.from > e.to){
                from = e.to;
                to = e.from;
            }else{
                from = e.from;
                to = e.to;
            }
            
            int fromParent = findParent(from, parent);
            int toParent = findParent(to, parent);
            // if added edge doesn't affect set of node
            if(fromParent == toParent)
                continue;

            // else add two set to unified set 
            long cases = (long)size[fromParent]*size[toParent]%mod;
            parent[toParent] = fromParent;
            size[fromParent] += size[toParent];
            
            // add cost(u,v) to result
            // current weight need to be contained
            result = (result+(SUM-cur+e.weight)*cases)%mod;
        }

        bw.write(Long.toString(result)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int findParent(int idx, int[] parent){
        if(parent[idx] == idx)
            return idx;
        else{
            parent[idx] = findParent(parent[idx], parent);
            return parent[idx];
        }
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
            if(this.weight > that.weight){
                return -1;
            }else if(this.weight == that.weight){
                return 0;
            }else{
                return 1;
            }
        }
    }
}
