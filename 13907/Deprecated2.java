/*
    baekjoon online judge
    problem number 13907
    https://www.acmicpc.net/problem/13907

    I firstly tried to use dijkstra and add weight to all edge and recalculate dijkstra
    But its result was time out
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Main{
    static final int INF = 1000000000;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        Graph g = new Graph(N);
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, weight);
        }

        List<Integer> taxes = new ArrayList<Integer>();
        for(int i=0;i<K;i++){
            int weight = Integer.parseInt(br.readLine());

            taxes.add(weight);
        }

        List<Path> paths = g.DFS(S,D);
        int min = getMinCost(paths);
        bw.write(Integer.toString(min)+"\n");

        for(int i=0;i<K;i++){
            // add tax to all path
            for(int j=0;j<paths.size();j++)
                paths.get(j).addWeight(taxes.get(i));

            min = getMinCost(paths);
            bw.write(Integer.toString(min)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
    
    public static class Graph{
        private ArrayList<ArrayList<Node>> adjList;
        private int vertex;
        private int edge;
    
        public Graph(int N){
            this.vertex = N;
            this.edge = 0;
            adjList = new ArrayList<ArrayList<Node>>();
            for(int i=1;i<=N;i++){
                adjList.add(new ArrayList<Node>());
            }
        }
    
        public String toString(){
            String ret = "";
            for(int i=0;i<adjList.size();i++){
                Iterator<Node> it = adjList.get(i).iterator();
                ret += ("Node["+(i+1)+"]: ");
                while(it.hasNext()){
                    Node node = it.next();
                    int nodeNum = node.getNodeNum();
                    int weight = node.getWeight();
                    ret += String.format("%d[%d] ", nodeNum,weight);
                }
                ret += "\n";
            }
            return ret;
        }
    
        public int getVertex(){
            return vertex;
        }
    
        public int getEdge(){
            return edge;
        }
    
        public void addEdge(int start, int end, int weight){
            adjList.get(start-1).add(new Node(end,weight));
            adjList.get(end-1).add(new Node(start,weight));
            edge++;
        }

        public ArrayList<Node> getList(int node){
            return adjList.get(node-1);
        }

        // get all path from S to D by using DFS
        public List<Path> DFS(int S, int D){
            List<Path> res = new ArrayList<Path>();
            boolean[] visited = new boolean[vertex+1];
            
            _DFS(S,D,0,0,visited,res);

            return res;
        }

        private void _DFS(int cur, final int dest, int cnt, int totalCost, boolean[] visited, List<Path> paths){
            if(cur == dest){
                paths.add(new Path(cnt,totalCost));
                return;
            }

            visited[cur] = true;
            List<Node> list = getList(cur);
            for(int i=0;i<list.size();i++){
                Node tmp = list.get(i);
                int nodeNum = tmp.getNodeNum();
                int cost = tmp.getWeight();

                // if visited before
                if(visited[nodeNum])
                    continue;

                _DFS(nodeNum,dest,cnt+1,totalCost+cost,visited,paths);
            }

            visited[cur] = false;
        }
    }

    public static class Path{
        public int edges;
        public int cost;

        public Path(int edges, int cost){
            this.edges = edges;
            this.cost = cost;
        }

        public void addWeight(int weight){
            this.cost += weight*edges;
        }
    }

    public static int getMinCost(List<Path> paths){
        Iterator<Path> it = paths.iterator();

        int min = INF;
        while(it.hasNext()){
            Path tmp = it.next();
            if(tmp.cost > INF || tmp.cost <0)
                throw new AssertionError("cost overflow erupt");

            min = Min(min,tmp.cost);
        }

        return min;
    }

    public static int Min(int a, int b){
        return a<b?a:b;
    }

    public static class Node implements Comparable<Node>{
        private int nodeNum;
        private int weight;
    
        public Node(int nodeNum, int weight){
            this.nodeNum = nodeNum;
            this.weight = weight;
        }
    
        public int getNodeNum(){
            return nodeNum;
        }
    
        public void setNodeNum(int nodeNum){
            this.nodeNum = nodeNum;
        }
    
        public int getWeight(){
            return weight;
        }
    
        public void setWeight(int weight){
            this.weight = weight;
        }

        @Override
        public int compareTo(Node target){
            if(target.getWeight() == -1)
                return -1;
            else{
                if(weight < target.getWeight())
                    return -1;
                else if(weight > target.getWeight())
                    return 1;
                else
                    return 0;
            }
        }
    }
}
