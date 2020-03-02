/*
    baekjoon online judge
    problem number 10265
    https://www.acmicpc.net/problem/10265

    DP(Knapsack) + DFS(Strongly Connected Component) Problem

    reference:
    https://jason9319.tistory.com/107
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Iterator;

public class Main{
    static int N;
    static int K;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Graph g = new Graph(N);

        st = new StringTokenizer(br.readLine());
        for(int end=1;end<=N;end++){
            int start = Integer.parseInt(st.nextToken());
            g.addEdge(start, end, 0);
        }

        /***** get SCC and CC *****/
        // SCC-1: get finishing time by running dfs to graph
        Deque<Integer> deque1 = g.dfsReturnFinishingTime();
        Deque<Integer> deque2 = new LinkedList<Integer>(deque1);
        
        // SCC-2: get transponse of graph
        Graph t = g.transpose();
        
        // SCC-3: get SCC by running dfs to transonse of graph by ordering Deque
        int[] scc = t.dfsByDeque(deque1);

        // get CC by running dfs to graph by ordering Deque
        int[] cc = g.dfsByDeque(deque2);

        /***** get min and max of Connected Component *****/
        int[] min = new int[N+1];
        int[] max = new int[N+1];
        int len = 0;
        
        // get max of connecnted component
        for(int i=1;i<cc.length;i++){
            max[cc[i]]++;
        }

        // get min of connected component
        for(int i=1;i<scc.length;i++){
            if(max[scc[i]] == 0)
                continue;

            min[scc[i]]++;
        }

        // get size of non zero value in max
        for(int i=1;i<max.length;i++){
            if(max[i] != 0)
                len++;
        }

        // compress -> delete 0 value
        min = compress(min, len);
        max = compress(max, len);

        // printArray(min);
        // printArray(max);

        /***** get maximum member of given graph by knapsack *****/
        int res = knapsack(min,max);

        bw.write(Integer.toString(res)+"\n");
        bw.close();
        br.close();
    }

    public static int knapsack(int[] min, int[] max){
        int[][] dp = new int[max.length][K+1];
        
        for(int idx=1;idx<max.length;idx++){
            for(int w=min[idx];w<=max[idx];w++){
                for(int W=1;W<=K;W++){
                    if(w > W){
                        dp[idx][W] = Max(dp[idx][W],dp[idx-1][W]);
                    }else{
                        if(w+dp[idx-1][W-w] > dp[idx-1][W])
                            dp[idx][W] = Max(dp[idx][W],w+dp[idx-1][W-w]);
                        else
                            dp[idx][W] = Max(dp[idx][W],dp[idx-1][W]);
                    }
                }
            }
        }

        // print2DArray(dp);

        return dp[max.length-1][K];
    }

    public static int Max(int a, int b){
        return a>b?a:b;
    }

    public static void print2DArray(int[][] dp){
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[i].length;j++){
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static int[] compress(int[] arr, int len){
        int[] res = new int[len+1];
        int idx=1;

        for(int i=1;i<arr.length;i++){
            if(arr[i] != 0){
                res[idx++] = arr[i];
            }
        }

        return res;
    }
    
    public static void printDeque(Deque<Integer> deque){
        while(!deque.isEmpty()){
            System.out.printf("%d ",deque.removeLast());
        }
        System.out.println();
    }

    public static void printArray(int[] arr){
        for(int i=1;i<arr.length;i++){
            System.out.printf("%d ",arr[i]);
        }
        System.out.println();
    }
}

class Graph{
    private ArrayList<ArrayList<Node>> adjList;
    private int vertex;
    private int edge;

    public Graph(int N){
        this.vertex = N;
        this.edge = 0;
        adjList = new ArrayList<ArrayList<Node>>();
        for(int i=0;i<N;i++){
            adjList.add(new ArrayList<Node>());
        }
    }

    public Graph transpose(){
        Graph t = new Graph(vertex);
        
        for(int i=0;i<adjList.size();i++){
            ArrayList<Node> list = adjList.get(i);
            for(int j=0;j<list.size();j++){
                Node tmp = list.get(j);
                
                t.addEdge(tmp.getNodeNum(), i+1, tmp.getWeight());
            }
        }

        return t;
    }

    // run dfs and return SCC group number of node
    public int[] dfsByDeque(Deque<Integer> deque){
        int[] scc = new int[vertex+1];
        while(!deque.isEmpty()){
            int top = deque.removeLast();
            _dfs(top, top, scc);
        }

        return scc;
    }

    private void _dfs(int cur, int src, int[]scc){
        // visited before
        if(scc[cur] != 0)
            return;
        scc[cur] = src;

        List<Node> list = getList(cur);
        for(int i=0;i<list.size();i++){
            _dfs(list.get(i).getNodeNum(), src, scc);
        }
    }

    // Run dfs and return the Deque (which sorted all nodes in order of finishing)
    public Deque<Integer> dfsReturnFinishingTime(){
        Deque<Integer> deque = new LinkedList<Integer>();
        boolean[] visited = new boolean[vertex+1];
        for(int i=1;i<=vertex;i++){
            _dfs(i, visited, deque);
        }

        return deque;
    }

    private void _dfs(int cur, boolean[] visited, Deque<Integer> deque){
        if(visited[cur])
            return;
        visited[cur] = true;

        List<Node> list = getList(cur);
        for(int i=0;i<list.size();i++){
            _dfs(list.get(i).getNodeNum(),visited,deque);
        }
        deque.addLast(cur);
    }

    public int getVertex(){
        return vertex;
    }

    public int getEdge(){
        return edge;
    }

    public void addEdge(int start, int end, int weight){
        ArrayList<Node> list = adjList.get(start-1);
        list.add(new Node(end, weight));
        edge++;
    }

    public ArrayList<Node> getList(int node){
        return adjList.get(node-1);
    }

    public String toString(){
        String ret = "";
        for(int i=0;i<adjList.size();i++){
            ArrayList<Node> list = adjList.get(i);
            ret += ("Node["+(i+1)+"]: ");

            Iterator<Node> it = list.iterator();
            while(it.hasNext()){
                Node node = it.next(); 
                int nodeNum = node.getNodeNum(); 
                int weight = node.getWeight();
                ret += String.format("%d[%d]", nodeNum, weight);
            }
            ret += "\n";
        }
        return ret;
    }
}

class Node{
    private int nodeNum;
    private int weight;
    private Node next;

    public Node(int nodeNum, int weight){
        this.nodeNum = nodeNum;
        this.weight = weight;
        next = null;
    }

    Node(int nodeNum, int weight, Node next){
        this(nodeNum, weight);
        this.next = next;
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

    public Node getNext(){
        return next;
    }

    public void setNext(Node next){
        this.next = next;
    }
}