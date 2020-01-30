/*
    baekjoon online judge
    problem number 11724
    https://www.acmicpc.net/problem/11724

    DFS Problem
    Get nummber of connnected component by using dfs
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
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Graph g = new Graph(N);

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, 0);
        }

        int res = g.dfs();

        bw.write(Integer.toString(res));
        bw.close();
        br.close();
    }

    public static class Graph{
        private ArrayList<ArrayList<Node>> adjList;
        private int vertex;
        private int edge;
        private boolean[] visited;
    
        public Graph(int N){
            this.vertex = N;
            this.edge = 0;
            adjList = new ArrayList<ArrayList<Node>>();
            for(int i=0;i<N;i++){
                adjList.add(new ArrayList<Node>());
            }
        }

        // get number of connnected component
        public int dfs(){
            visited = new boolean[vertex+1];
            int connected = 0;

            for(int i=1;i<=vertex;i++){
                if(visited[i])
                    continue;

                visited[i] = true;
                connected++;    // count connected component
                _dfs(i,visited);
            }

            return connected;
        }

        public void _dfs(int cur, boolean[] visited){
            Iterator<Node> it = getList(cur).iterator();
            
            while(it.hasNext()){
                int tmp = it.next().getNodeNum();

                if(visited[tmp])
                    continue;
                
                visited[tmp] = true;
                _dfs(tmp,visited);
            }
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
