/*
    baekjoon online judge
    problem number 13907
    https://www.acmicpc.net/problem/13907
    https://lyzqm.blogspot.com/2017/06/codeground_22.html

    deep reference level

    I firstly tried to use dijkstra and add weight to all edge and recalculate dijkstra
    But its result was time out

    Secondly I tried DFS and get all path from start to end, but it also result TLE

    Thirdly I tried dijkstra and save hop number

    I missed 3rd condition for continue in dijkstra, which is dist[i][j] < dist[i][k] (j<=k) than it can skip it
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
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Comparable;

public class Main{
    static final long INF = 1000000000000000000L;

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

        long[] result = dijkstra(g, S, D);
        
        // get min
        long min = INF;
        for(int i=0;i<result.length;i++){
            if(result[i] == -1)
                continue;
            min = Min(min, result[i]);
        }
        bw.write(Long.toString(min)+"\n");

        for(int i=0;i<K;i++){
            // add weight to all edges
            for(int j=0;j<result.length;j++){
                if(result[j] == -1)
                    continue;
                result[j] += taxes.get(i)*j;
            }
            
            // get min
            min = INF;
            for(int j=0;j<result.length;j++){
                if(result[j] == -1)
                    continue;
                min = Min(min, result[j]);
            }
            bw.write(Long.toString(min)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static long[] dijkstra(Graph g, int start, int dest){
        // initialize result array
        long[][] result = new long[g.getVertex()+1][g.getVertex()];
        for(int i=0;i<result.length;i++)
            Arrays.fill(result[i], -1);
        Arrays.fill(result[start],0);

        // initialize priority queue
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(start, 0));

        // dijkstra algorithm
        while(!pq.isEmpty()){
            // get minimum weight node from pq
            Node top = pq.poll();
            int topNodeNum = top.getNodeNum();
            int topWeight = top.getWeight();
            int cnt = top.getCnt();

            // if 1. count is over Node-2, 2. current node is destination
            if(cnt+1 >= g.getVertex() || top.getNodeNum() == dest)
                continue;

            // 3. there is a node which has smaller count and weight than current node
            boolean flag = false;
            for(int i=1;i<=cnt;i++){
                if(result[topNodeNum][i] == -1)
                    continue;
                if(result[topNodeNum][i] < topWeight){
                    flag = true;
                    break;
                }
            }
            if(flag)
                continue;

            
            Iterator<Node> it = g.getList(top.getNodeNum()).iterator();
            // search other nodes which are connected with top node
            while(it.hasNext()){
                Node tmp = it.next();
                int tmpNodeNum = tmp.getNodeNum();
                int tmpWeight = tmp.getWeight();

                // if temp node is not searched or temp's path(weight) is bigger than new path
                if((result[tmpNodeNum][cnt+1] == -1) || (result[tmpNodeNum][cnt+1] > (tmpWeight+topWeight))){
                    result[tmpNodeNum][cnt+1] = tmpWeight+topWeight;
                    pq.add(new Node(tmpNodeNum,tmpWeight+topWeight, cnt+1));
                }
            }
        }

        return result[dest];
    }

    public static long Min(long a, long b){
        return a<b?a:b;
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
    }

    public static class Node implements Comparable<Node>{
        private int nodeNum;
        private int weight;
        private int cnt;
        
        public Node(int nodeNum, int weight){
            this.nodeNum = nodeNum;
            this.weight = weight;
            this.cnt = 0;
        }
    
        Node(int nodeNum, int weight, int cnt){
            this(nodeNum, weight);
            this.cnt = cnt;
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
    
        public int getCnt(){
            return cnt;
        }

        public void setCnt(int cnt){
            this.cnt = cnt;
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
