/*
    baekjoon online judge
    problem number 10273
    https://www.acmicpc.net/problem/10273
    https://handongproblemsolvers.github.io/2019/10/11/Week_06_Contest_Problem_Solving/#%EA%B3%A0%EB%8C%80-%EB%8F%99%EA%B5%B4-%ED%83%90%EC%82%AC
    I wrongly implemented DFS(never visit visted node)
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.io.FileReader;
import java.io.FileWriter;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./testcases/randomSmallSparse2.in"));
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
            g.DFS();
            g.printResult(bw);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class Graph{
        // initialized by main function
        private ArrayList<ArrayList<Node>> adjList;
        private int vertex;
        private int edge;
        private int[] vertexValue;
        // for dfs
        private int[] value;
        private int[] parent;
        private boolean[] visited;
        private int maxValue;

        public Graph(int N){
            this.vertex = N;
            this.edge = 0;
            adjList = new ArrayList<ArrayList<Node>>();
            for(int i=0;i<N;i++){
                adjList.add(new ArrayList<Node>());
            }
            this.vertexValue = new int[N+1];
        }

        // print maximum value and visited node count on 1st line
        // path of maximum value on 2nd line
        public void printResult(BufferedWriter bw)throws IOException{
            int idx = 1;
            int cnt = 0;
            // print 1st line
            // get cnt
            while(idx != -1){
                cnt++;  
                idx = parent[idx];
            } 
            String str = String.format("%d %d\n",maxValue,cnt);
            bw.write(str);

            // print 2nd line
            idx = 1;
            while(idx != -1){
                bw.write(Integer.toString(idx)+" ");
                idx = parent[idx];
            }
            bw.write("\n");
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

        public void DFS(){
            // initialize
            value = new int[vertex+1];
            parent = new int[vertex+1];
            Arrays.fill(parent, -1);
            visited = new boolean[vertex+1];
            maxValue = 0;

            // execute DFS
            maxValue = _DFS(1);
        }

        //***/
        public int _DFS(int cur){
            // if visited(= already calculated) return memoized value
            if(visited[cur])
                return value[cur];
            
            visited[cur] = true;

            Iterator<Node> it = getList(cur).iterator();
            while(it.hasNext()){
                Node temp = it.next();
                int num = temp.getNodeNum();

                int weight = temp.getWeight();
                int tempValue = _DFS(temp.getNodeNum()) - weight;
                if(tempValue > value[cur]){
                    value[cur] = tempValue;
                    parent[cur] = num;
                }
            }
            value[cur] += vertexValue[cur];
            return value[cur];
        }

        public void setVertexValue(int idx, int value){
            vertexValue[idx] = value;
        }

        public int getVertexValue(int idx){
            return vertexValue[idx];
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
