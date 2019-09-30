/*
    baekjoon online judge
    problem number 1613
    https://www.acmicpc.net/problem/1613
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

// import java.io.FileReader;
// import java.io.FileWriter;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        Graph g = new Graph(V);
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, 0);
        }

        int cases = Integer.parseInt(br.readLine());
        for(int i=0;i<cases;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());


            if(g.DFS(start,end)){
                bw.write("-1\n");        
            }else if(g.DFS(end,start)){
                bw.write("1\n");        
            }else{
                bw.write("0\n");        
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class Graph{
        private ArrayList<ArrayList<Node>> adjList;
        private int vertex;
        private int edge;
        private int[][] dp;
    
        public Graph(int N){
            this.vertex = N;
            this.edge = 0;
            adjList = new ArrayList<ArrayList<Node>>();
            for(int i=0;i<N;i++){
                adjList.add(new ArrayList<Node>());
            }
            this.dp = new int[N+1][N+1];
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
                    ret += (nodeNum+" ");
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
            edge++;
        }
    
        public ArrayList<Node> getList(int node){
            return adjList.get(node-1);
        }

        public boolean DFS(int start, int target){
            // initialize
            boolean[] visited = new boolean[vertex+1];
            // execute DFS
            return _DFS(start,target,visited);
        }

        public boolean _DFS(int start, int target, boolean[] visited){
            if(start == target || dp[start][target] == 1)
                return true;
            if(visited[start] ||  dp[start][target] == -1)
                return false;

            visited[start] = true;
            boolean flag = false;
            ArrayList<Node> list= adjList.get(start-1);
            for(int i=0;i<list.size();i++){
                Node temp = list.get(i);
                boolean res = _DFS(temp.getNodeNum(),target,visited);
                flag = (flag || res);
            }
            if(flag){
                dp[start][target] = 1;
            }else{
                dp[start][target] = -1;
            }

            return flag;
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
