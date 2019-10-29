/*
    baekjoon online judge
    problem number 2617
    https://www.acmicpc.net/problem/2617

    DFS Problem
*/

// import java.io.FileReader;
// import java.io.FileWriter;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        Graph g = new Graph(V);
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            // start is bigger than end 
            // from start to end edge wieght is -1
            // from end to start edge wieght is 1
            g.addEdge(start, end);
        }

        // return number of marble that can not be median
        int res = g.DFS();

        bw.write(Integer.toString(res)+"\n");
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
            for(int i=0;i<N;i++){
                adjList.add(new ArrayList<Node>());
            }
        }

        public int DFS(){
            int cnt = 0;

            for(int i=1;i<=vertex;i++){
                int upperCnt = _DFS(i,1);
                int underCnt = _DFS(i,-1);
                int resCnt = upperCnt>underCnt?upperCnt:underCnt;
                if(resCnt >= (vertex+1)/2)
                    cnt++;
            }

            return cnt;
        }


        // cur: current node, visited: visited node, tw: target weight
        public int _DFS(int cur, int tW){
            boolean[] visited = new boolean[vertex+1];
            Stack<Node> st = new Stack<Node>();
            st.add(new Node(cur,0));

            int cnt = 0;
            while(!st.isEmpty()){
                Node top = st.pop();
                // if visited before
                if(visited[top.getNodeNum()])
                    continue;
                visited[top.getNodeNum()] = true;
                cnt++;
                ArrayList<Node> list = getList(top.getNodeNum());
                Iterator<Node> it = list.iterator();

                while(it.hasNext()){
                    Node tmp = it.next();
                    
                    // if visited before
                    if(visited[tmp.getNodeNum()])
                        continue;
                    
                    // if this edge's wieght is not target weight
                    if(tmp.getWeight() != tW)
                        continue;

                    st.add(tmp);
                }
            }

            return cnt-1;
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
    
        public void addEdge(int start, int end){
            //start is bigger than end 
            // from start to end edge wieght is -1
            ArrayList<Node> list = adjList.get(start-1);
            list.add(new Node(end, -1));
            // from end to start edge wieght is 1
            list = adjList.get(end-1);
            list.add(new Node(start, 1));
            edge++;
        }
    
        public ArrayList<Node> getList(int node){
            return adjList.get(node-1);
        }
    }

    public static class Node{
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
}