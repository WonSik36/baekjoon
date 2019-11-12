/*
    baekjoon online judge
    problem number 13308
    https://www.acmicpc.net/problem/13308
    https://handongproblemsolvers.github.io/2019/11/08/Week_11_Contest_Problem_Solving/#%EC%A3%BC%EC%9C%A0%EC%86%8C
    
    dijkstra algorithm + dynamic programming
    use dp memoization in dijkstra distance

    dp[i][j]: i is node, j is min cost in path from 1 to i, dp[i][j] is total(min) cost from 1 to i
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Iterable;
import java.lang.Comparable;

public class Main{
    static final int MAX_COST = 2500;
    static final long INF = 1000000000000L;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] cost = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++)
            cost[i] = Integer.parseInt(st.nextToken());

        Graph g = new Graph(N);
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, weight);
        }

        long res = dijkstra(g, 1, cost);

        bw.write(Long.toString(res)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // dp[i][j]: i is node, j is min cost in path from 1 to i, dp[i][j] is total(min) cost from 1 to i
    // priority queue has not Node, has PQNode which indicate nodeNum, minCost from start to current node, totalCost from start to current node
    public static long dijkstra(Graph g, int start, int[] cost){
        // initialize result array
        long[][] result = new long[g.getVertex()+1][MAX_COST+1];
        for(int i=1;i<=g.getVertex();i++)
            Arrays.fill(result[i], -1);
        result[start][cost[start]] = 0;

        // initialize priority queue
        PriorityQueue<PQNode> pq = new PriorityQueue<PQNode>();
        pq.add(new PQNode(start,cost[start],0));

        // activate dijkstra algorithm
        while(!pq.isEmpty()){
            // get minimum weight node from pq
            PQNode top = pq.poll();
            Iterator<Node> it = g.getList(top.nodeNum).iterator();
            // search other nodes which are connected with top node
            while(it.hasNext()){
                Node temp = it.next();
                int tmpNum = temp.getNodeNum();
                int minCost = (int)Min(top.minCost, cost[tmpNum]);
                long totalCost = result[top.nodeNum][top.minCost] + temp.getWeight()*top.minCost;

                // if temp node is not searched or temp's path(weight) is bigger than new path
                if((result[tmpNum][minCost] == -1) || (result[tmpNum][minCost] > totalCost)){
                    result[tmpNum][minCost] = totalCost;
                    pq.add(new PQNode(tmpNum,minCost,totalCost));
                }
            }
        }

        // choose minimum in array result[N]
        long ret = INF;
        for(int i=0;i<=MAX_COST;i++){
            ret = Min(ret, result[g.getVertex()][i]);
        }

        return ret;
    }

    public static long Min(long a, long b){
        if(b==-1)
            return a;
        return a<b?a:b;
    }

    public static class PQNode implements Comparable<PQNode>{
        public int nodeNum;
        public int minCost;
        public long totalCost;

        public PQNode(int nodeNum, int minCost, long totalCost){
            this.nodeNum = nodeNum;
            this.minCost = minCost;
            this.totalCost = totalCost;
        }

        @Override
        public int compareTo(PQNode that){
            if(that.totalCost == -1)
                throw new AssertionError("min cost is -1");
            else{
                if(this.totalCost < that.totalCost)
                    return -1;
                else if(this.totalCost > that.totalCost)
                    return 1;
                else
                    return 0;
            }
        }
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
        public int compareTo(Node that){
            if(that.getWeight() == -1)
                return -1;
            else{
                if(weight < that.getWeight())
                    return -1;
                else if(weight > that.getWeight())
                    return 1;
                else
                    return 0;
            }
        }
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
                    int node = it.next().getNodeNum();
                    ret += (node+" ");
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
}