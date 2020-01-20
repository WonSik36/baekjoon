/*
    baekjoon online judge
    problem number 2644
    https://www.acmicpc.net/problem/2644

    BFS Problem
    Get Distance between given nodes
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int target1 = Integer.parseInt(st.nextToken());
        int target2 = Integer.parseInt(st.nextToken());

        Graph g = new Graph(N);
        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, 1);
        }

        int res = g.bfs(target1, target2);

        bw.write(Integer.toString(res));
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

        // return distance between target1 and target2
        // if there is no path between target1 and target2 
        // than return -1
        public int bfs(int target1, int target2){
            boolean[] visited = new boolean[vertex+1];
            Queue<Node> q = new LinkedList<Node>();

            q.add(new Node(target1,0));
            visited[target1] = true;

            while(!q.isEmpty()){
                Node first = q.poll();

                Iterator<Node> it = getList(first.getNodeNum()).iterator();

                while(it.hasNext()){
                    Node tmp = it.next();

                    if(tmp.getNodeNum() == target2)
                        return first.getWeight()+1;

                    if(visited[tmp.getNodeNum()])
                        continue;
                    
                    visited[tmp.getNodeNum()] = true;
                    q.add(new Node(tmp.getNodeNum(), first.getWeight()+tmp.getWeight()));
                }
            }

            return -1;
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
            list = adjList.get(end-1);
            list.add(new Node(start, weight));
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
