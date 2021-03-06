/*
    baekjoon online judge
    problem number 10552
    https://www.acmicpc.net/problem/10552

    DFS Problem
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        Graph g = new Graph(M);

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int end = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, 0);
        }

        int cnt = g.dfs(P);

        bw.write(Integer.toString(cnt)+"\n");
        bw.close();
        br.close();
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

    public int dfs(int start){
        boolean[] visited = new boolean[vertex+1];

        return _dfs(start, 0, visited);
    }

    private int _dfs(int cur, int cnt, boolean[] visited){
        ArrayList<Node> list = getList(cur);
        if(list.isEmpty())
            return cnt;
        
        if(visited[cur])
            return -1;
        visited[cur] = true;
        
        return _dfs(list.get(0).getNodeNum(), cnt+1, visited);
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

    public int getVertex(){
        return vertex;
    }

    public int getEdge(){
        return edge;
    }

    public void addEdge(int start, int end, int weight){
        ArrayList<Node> list = adjList.get(start-1);
        list.add(new Node(end, weight));
        // list = adjList.get(end-1);
        // list.add(new Node(start, weight));
        edge++;
    }

    public ArrayList<Node> getList(int node){
        return adjList.get(node-1);
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