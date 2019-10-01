/*
    baekjoon online judge
    problem number 10273
    https://www.acmicpc.net/problem/10273
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.lang.Comparable;
// import java.io.FileReader;
// import java.io.FileWriter;

public class Main{
    static int MAX_VALUE;
    static int VISITED_NODE;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./testcases/antiBF.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./2.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // get test case number
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0;i<N;i++){
            // get vertex and edge number
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            Graph g = new Graph(V);

            // get vertex value
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=V;j++){
                int price = Integer.parseInt(st.nextToken());
                g.setVertexValue(j, price);
            }

            // get edge weight
            for(int j=0;j<E;j++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                g.addEdge(start, end, weight);
            }
            // calculate maximum value of each node from 1st node
            MAX_VALUE = 0;
            VISITED_NODE = 0;
            dijkstra(g, 1,bw);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dijkstra(Graph g, int start, BufferedWriter bw)throws IOException{
        // initialize
        Graph visited = new Graph(g.getVertex());
        int[] result = new int[g.getVertex()+1];
        int[] visitedNode = new int[g.getVertex()+1];
        int[] reverse = new int[g.getVertex()+1];
        Arrays.fill(result, -10000);
        Arrays.fill(reverse, -1);
        result[start] = g.getVertexValue(start)-0;
        visitedNode[1] = 1;
        int maxIdx = 1;

        // initialize priority queue
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(start, g.getVertexValue(start)));

        // activate dijkstra algorithm
        while(!pq.isEmpty()){
            // get maximum weight node from pq
            Node top = pq.poll();
            Iterator<Node> it = g.getList(top.getNodeNum()).iterator();
            // search other nodes which are connected with top node            
            while(it.hasNext()){
                Node temp = it.next();
                int vertex = temp.getNodeNum();
                int weight = g.getVertexValue(temp.getNodeNum())-temp.getWeight()+top.getWeight();
                // if edge has been visited -> cycle
                if(visited.containEdge(top.getNodeNum(), vertex))
                    continue;
                else
                    visited.addEdge(top.getNodeNum(), vertex, 0);;

                // if temp node has not been searched or temp's path(weight) is bigger than new path
                if((result[vertex] == -10000) || (result[vertex] < weight)){
                    // if pq has temp than replace it
                    if(pq.contains(temp))
                        pq.remove(temp);
                    pq.add(new Node(vertex,weight));
                    result[vertex] = weight;
                    visitedNode[vertex] = visitedNode[top.getNodeNum()]+1;
                    reverse[vertex] = top.getNodeNum();
                    visited.copyList(vertex, visited.getList(top.getNodeNum()));
                    if(result[maxIdx] < result[vertex])
                        maxIdx = vertex;
                }
            }
        }
        
        MAX_VALUE = result[maxIdx];
        VISITED_NODE = visitedNode[maxIdx];
        String str = String.format("%d %d\n",MAX_VALUE,VISITED_NODE);
        bw.write(str);

        int[] inOrder = new int[g.getVertex()];
        for(int i=0;i<VISITED_NODE;i++){
            inOrder[VISITED_NODE-1-i] = maxIdx;
            maxIdx = reverse[maxIdx];
        }
        for(int i=0;i<VISITED_NODE;i++){
            bw.write(Integer.toString(inOrder[i])+" ");
        }
        bw.write("\n");
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
                    return 1;
                else if(weight > target.getWeight())
                    return -1;
                else
                    return 0;
            }
        }

        @Override
        public boolean equals(Object obj){
            if(this.nodeNum == ((Node)obj).nodeNum)
                return true;
            else
                return false;
        }
    }

    public static class Graph{
        private ArrayList<ArrayList<Node>> adjList;
        private int vertex;
        private int edge;
        private int[] vertexValue;
    
        public Graph(int N){
            this.vertex = N;
            this.edge = 0;
            adjList = new ArrayList<ArrayList<Node>>();
            for(int i=1;i<=N;i++){
                adjList.add(new ArrayList<Node>());
            }
            this.vertexValue = new int[N+1];
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

        public void setVertexValue(int idx, int value){
            vertexValue[idx] = value;
        }

        public int getVertexValue(int idx){
            return vertexValue[idx];
        }

        public boolean containEdge(int start, int end){
            ArrayList<Node> list = getList(start);
            return list.contains(new Node(end,0));
        }

        public void copyList(int idx, ArrayList<Node> list){
            adjList.set(idx-1, new ArrayList<Node>(list));
        }

        public boolean isSameList(int a, int b){
            ArrayList<Node> aList = getList(a);
            ArrayList<Node> bList = getList(b);
            
            if(aList.size() != bList.size())
            return false;

            for(int i=0;i<aList.size();i++){
                if(!aList.get(i).equals(aList.get(i)))
                    return false;
            }
            return true;
        }
    }
}
