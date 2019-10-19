/*
    baekjoon online judge
    problem number 1865
    https://www.acmicpc.net/problem/1865
    bellman ford algorithm

    ref
    https://www.acmicpc.net/problem/11657
    https://ratsgo.github.io/data%20structure&algorithm/2017/11/27/bellmanford/

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
    static final int INF = 10000000;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int Num = Integer.parseInt(br.readLine());
        for(int i=0;i<Num;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int road = Integer.parseInt(st.nextToken());
            int wormhole = Integer.parseInt(st.nextToken());

            Graph g = new Graph(N);
            for(int j=0;j<road;j++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                g.addEdgeBiDirect(start, end, weight);
            }

            for(int j=0;j<wormhole;j++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                g.addEdgeUniDirect(start, end, (-1)*weight);
            }

            if(isTimeTravelExist(g))
                bw.write("YES\n");
            else
                bw.write("NO\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean isTimeTravelExist(Graph g){
        boolean[] checked = new boolean[g.getVertex()+1];
        int[] result;

        for(int i=1;i<checked.length;i++){
            // if node i was checked before
            if(checked[i])
                continue;

            result = bellmanFord(g, i);

            // if there is wormhole in graph than return true
            if(result == null)
                return true;

            for(int j=1;j<result.length;j++){
                // if node j was visited than check node j
                if(result[j] != -1)
                    checked[j] = true;
            }
        }
        // if there is no wormhole in graph than return false
        return false;
    }

    public static int[] bellmanFord(Graph g, int start){
        // 1.initialize
        int[] result = new int[g.getVertex()+1];
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
    
        public void addEdgeUniDirect(int start, int end, int weight){
            adjList.get(start-1).add(new Node(end,weight));
            edge++;
        }

        public void addEdgeBiDirect(int start, int end, int weight){
            adjList.get(start-1).add(new Node(end,weight));
            adjList.get(end-1).add(new Node(start,weight));
            edge+=2;
        }
    
        public ArrayList<Node> getList(int node){
            return adjList.get(node-1);
        }
    }
}