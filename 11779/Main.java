/*
    baekjoon online judge
    problem number 11779
    https://www.acmicpc.net/problem/11779

    Dijkstra Algorithm + Shortest Path Reverse Tracking
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.lang.StringBuilder;

public class Main{
    static int[] dist;
    static int[] before;
    static int cnt = 0;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        Graph g = new Graph(N);

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, weight);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        
        dijkstra(start, g);
        bw.write(Integer.toString(dist[end])+"\n");

        StringBuilder sb = new StringBuilder();
        trace(end, sb);
        bw.write(Integer.toString(cnt)+"\n");
        sb.append("\n");
        bw.write(sb.toString());

        // printArr(dist);
        // printArr(before);

        bw.close();
        br.close();
    }

    static void dijkstra(int start, Graph g){
        dist = new int[g.getVertex()+1];
        Arrays.fill(dist, -1);
        dist[start] = 0;
        
        before = new int[g.getVertex()+1];
        before[start] = -1;

        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node head = pq.poll();
            Iterator<Node> it = g.getList(head.getNodeNum()).iterator();
            
            while(it.hasNext()){
                Node tmp = it.next();
                int nodeNum = tmp.getNodeNum();
                int weight = tmp.getWeight();

                if(dist[nodeNum] == -1 || dist[nodeNum] > dist[head.getNodeNum()] + weight){
                    dist[nodeNum] = dist[head.getNodeNum()] + weight;
                    // System.out.printf("dist[%d] = dist[%d] + %d = %d\n", nodeNum, head.getNodeNum(), weight, dist[nodeNum]);
                    before[nodeNum] = head.getNodeNum();
                    pq.add(new Node(nodeNum, dist[nodeNum]));
                }
            }
        }
    }

    static void trace(int idx, StringBuilder sb){
        cnt++;

        // start idx
        if(before[idx] == -1){
            sb.append(Integer.toString(idx));
            sb.append(" ");
            return;
        }

        trace(before[idx], sb);

        sb.append(Integer.toString(idx));
        sb.append(" ");
    }

    static void printArr(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.printf("%d ",arr[i]);
        }
        System.out.println();
    }
}

class Node implements Comparable<Node>{
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

class Graph{
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
        edge++;
    }

    public ArrayList<Node> getList(int node){
        return adjList.get(node-1);
    }
}