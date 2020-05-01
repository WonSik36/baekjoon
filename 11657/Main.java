/*
    baekjoon online judge
    problem number 11657
    https://www.acmicpc.net/problem/11657
    https://ratsgo.github.io/data%20structure&algorithm/2017/11/27/bellmanford/
    bellman ford algorithm

    find shortest path from one node to others
    can detect negative cycle
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Comparable;

public class Main{
    static boolean DEBUG = false;
    static final long INF = Long.MAX_VALUE;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        Graph g = new Graph(N);
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            g.addEdge(start, end, weight);
        }

        long[] res = bellmanFord(g, 1);
        if(res == null){
            bw.write("-1\n");
        }else{
            for(int i=2;i<=g.getVertex();i++){
                bw.write(Long.toString(res[i])+"\n");
            }
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
            for(int i=1;i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
    }

    public static long[] bellmanFord(Graph g, int start){
        // 1.initialize
        long[] result = new long[g.getVertex()+1];
        Arrays.fill(result, INF);
        result[start] = 0;

        // 2.edge relaxation V-1 times
        for(int i=0;i<g.getVertex()-1;i++){
            for(int j=1;j<=g.getVertex();j++){
                if(result[j] == INF)
                    continue;

                ArrayList<Node> list = g.getList(j);
                Iterator<Node> it = list.iterator();
                while(it.hasNext()){
                    Node temp = it.next();
                    // edge relaxation
                    if(result[temp.getNodeNum()] > result[j] + temp.getWeight()){
                        result[temp.getNodeNum()] = result[j] + temp.getWeight();
                    }
                }
            }
        }

        // printArray(result);

        // 3.check negative edge
        for(int j=1;j<=g.getVertex();j++){
            if(result[j] == INF)
                continue;

            ArrayList<Node> list = g.getList(j);
            Iterator<Node> it = list.iterator();
            while(it.hasNext()){
                Node temp = it.next();
                // if edge relaxation happen there is negative edge
                if(result[temp.getNodeNum()] > result[j] + temp.getWeight()){
                    // System.out.printf("negative cycle between %d and %d\n",j,temp.getNodeNum());
                    return null;
                }
            }
        }

        // 4.if result[i] is INF convert it to -1
        for(int i=1;i<result.length;i++){
            if(result[i] == INF)
                result[i] = -1;
        }

        // printArray(result);
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
                    Node temp = it.next();
                    int node = temp.getNodeNum();
                    int weight = temp.getWeight();
                    ret += String.format("%d[%d] ",node,weight);
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