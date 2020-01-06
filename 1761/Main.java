/*
    baekjoon online judge
    problem number 1761
    https://www.acmicpc.net/problem/1761
    https://m.blog.naver.com/kks227/220820773477

    Tree Problem
    Lowest Common Ancestor(LCA)
    very high reference level
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
import java.util.ArrayList;
import java.util.Iterator;

public class Main{

    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        Graph g = new Graph(N);

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, weight);
        }

        Tree tree = new Tree(g);

        int M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;

            int lca = tree.getLCA(start,end);
            int dist = tree.getDist2Root(start) + tree.getDist2Root(end) - 2 * tree.getDist2Root(lca);
            bw.write(Integer.toString(dist)+"\n");
        }

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

    public static class Tree{
        private int vertex;
        private int height;
        private int[][] parent;
        private int[] depth;
        private int[] dist;

        // make tree which has N node, and 2^h height
        public Tree(Graph g){
            vertex = g.getVertex();
            height = (int)Math.ceil((Math.log(vertex)/Math.log(2)));    // 2^(height-1) parent will be root
            parent = new int[vertex][height];
            depth = new int[vertex];
            dist = new int[vertex];

            initParent();
            initDepthAndDist();

            depth[0] = 0;
            dist[0] = 0;
            makeTreeByDFS(0,g); // start from 0

            fillParent();   // fill 2^1, 2^2, 2^3, ..., 2^(height-1) parent
        }

        private void initParent(){
            for(int i=0;i<parent.length;i++){
                Arrays.fill(parent[i],-1);
            }
        }

        private void initDepthAndDist(){
            Arrays.fill(depth,-1);
            Arrays.fill(dist,-1);
        }

        private void makeTreeByDFS(int cur, Graph g){
            ArrayList<Node> list = g.getList(cur+1);
            for(int i=0;i<list.size();i++){
                int nodeNum = list.get(i).getNodeNum()-1;
                int weight = list.get(i).getWeight();
                if(depth[nodeNum] == -1){
                    parent[nodeNum][0] = cur;       // immediate parent
                    depth[nodeNum] = depth[cur]+1;
                    dist[nodeNum] = dist[cur]+weight;
                    makeTreeByDFS(nodeNum, g);
                }
            }
        }

        private void fillParent(){
            for(int j=0;j<height-1;j++){
                for(int i=0;i<vertex;i++){
                    if(parent[i][j] != -1)
                        /* 
                            parent[i][j+1] = 2^(j+1) parent 
                            = (2^j + 2^j) parent 
                            = 2^j parent's 2^j parent
                            = parent[parent[i][j]][j];   
                        */
                        parent[i][j+1] = parent[parent[i][j]][j];   
                }
            }
        }

        public int getLCA(int u, int v){
            
            // make depth[u] >= depth[v]
            if(depth[u]<depth[v]){
                int tmp = u;
                u = v;
                v = tmp;
            }

            // make u and v's depth to equal
            // if difference is 11 -> 1011
            // than get 2^0, 2^1, 2^3 parent
            int diff = depth[u] - depth[v];
            for(int i=0; diff!=0; i++){
                if(diff % 2 == 1) 
                    u = parent[u][i];
                diff /= 2;
            }

            if(u == v)
                return u;

            // if u != v
            // try 2^height, 2^height-1, ..., 2^2, 2, 1 height
            // this procedure is same with
            // 1. get k which makes u and v's 2^k parent is not same, but 2^(k+1) parent is same
            // 2. than find lca by looking range 2^k ~ 2^(k+1) parent
            for(int i=height-1; i>=0; i--){
                if(parent[u][i] != -1 && parent[u][i] != parent[v][i]){
                    u = parent[u][i];
                    v = parent[v][i];
                }
            }

            // finally, u and v's immediate parent is same
            // get parent
            u = parent[u][0];

            return u;
        }

        // 0 node is root node
        public int getDist2Root(int idx){
            return dist[idx];
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
