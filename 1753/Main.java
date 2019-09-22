/*
    baekjoon online judge
    problem number 1753
    https://www.acmicpc.net/problem/1753
    https://hsp1116.tistory.com/42
    dijkstra problem

    Firstly, I used linkedlist which I made.
    But it occur time out.
    So I changed ArrayList.
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Iterable;
import java.lang.Comparable;

public class Main{
    static boolean DEBUG = false;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        Graph g = new Graph(V);
        str = br.readLine();
        int start = Integer.parseInt(str);
        
        for(int i=0;i<E;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            g.addEdge(u, v, w);
        }

        int[] result = dijkstra(g, start);
        for(int i=1;i<=V;i++){
            if(result[i] == -1)
                bw.write("INF\n");
            else
                bw.write(Integer.toString(result[i])+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void print(int num){
        if(DEBUG)
            System.out.println(num);
    }

    public static void print(String str){
        if(DEBUG)
            System.out.print(str);
    }

    public static void print(String str, int... ints){
        if(DEBUG){
            String[] spl = str.split("%d");
            String output = spl[0];
            int idx = 1;
            for(int number:ints){
                output += number;
                if(idx < spl.length)
                    output += spl[idx++];
            }
            System.out.print(output);
        }
    }

    public static void printArray(int[] arr){
        if(DEBUG){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
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
}