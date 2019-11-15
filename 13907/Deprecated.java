/*
    baekjoon online judge
    problem number 13907
    https://www.acmicpc.net/problem/13907

    I firstly tried to use dijkstra and add weight to all edge and recalculate dijkstra
    But its result was time out
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
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Comparable;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        Graph g = new Graph(N);
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, weight);
        }

        List<Integer> taxes = new ArrayList<Integer>();
        for(int i=0;i<K;i++){
            int weight = Integer.parseInt(br.readLine());

            taxes.add(weight);
        }

        int[] result;
        result = dijkstra(g, S);
        bw.write(Integer.toString(result[D])+"\n");

        for(int i=0;i<K;i++){
            g.addWeight2AllEdge(taxes.get(i));
            result = dijkstra(g, S);
            bw.write(Integer.toString(result[D])+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int[] dijkstra(Graph g, int start){
        // initialize result array
        int[] result = new int[g.getVertex()+1];
        Arrays.fill(result, -1);
        result[start] = 0;

        // initialize priority queue
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(start, 0));

        // activate dijkstra algorithm
        while(!pq.isEmpty()){
            // get minimum weight node from pq
            Node top = pq.poll();
            Iterator<Node> it = g.getList(top.getNodeNum()).iterator();
            // search other nodes which are connected with top node
            while(it.hasNext()){
                Node temp = it.next();
                // if temp node is not searched or temp's path(weight) is bigger than new path
                if((result[temp.getNodeNum()] == -1) || (result[temp.getNodeNum()] > (temp.getWeight()+top.getWeight()))){
                    result[temp.getNodeNum()] = temp.getWeight()+top.getWeight();
                    pq.add(new Node(temp.getNodeNum(),temp.getWeight()+top.getWeight()));
                }
            }
        }

        return result;
    }
    
    public static class Graph{
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
                    Node node = it.next();
                    int nodeNum = node.getNodeNum();
                    int weight = node.getWeight();
                    ret += String.format("%d[%d] ", nodeNum,weight);
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
            adjList.get(end-1).add(new Node(start,weight));
            edge++;
        }
        
        public void addWeight2AllEdge(int weight){
            for(int i=0;i<adjList.size();i++){
                ArrayList<Node> tmpList = adjList.get(i);
                for(int j=0;j<tmpList.size();j++){
                    Node tmp = tmpList.get(j);
                    tmp.setWeight(tmp.getWeight()+weight);
                }
            }
        }

        public ArrayList<Node> getList(int node){
            return adjList.get(node-1);
        }
    }

    public static class Node implements Comparable<Node>{
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
}
