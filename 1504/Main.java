/*
    baekjoon online judge
    problem number 1504
    https://www.acmicpc.net/problem/1504
    https://jaimemin.tistory.com/1004

    use dijkstra to first node and given 2 nodes

    ref of dijkstra algorithm
    https://www.acmicpc.net/problem/1753
    https://hsp1116.tistory.com/42
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
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // make graph
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
        // System.out.println(g.toString());

        // get node a,b
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        // apply dijkstra to first,A,B node
        int[] firstPath = dijkstra(g, 1);
        int result;        

        // handle case that no way from first node to N node
        if(firstPath[N] == -1){
            result = -1;
        }else{
            int[] aPath = dijkstra(g, a);
            int[] bPath = dijkstra(g, b);

            int aFirst = firstPath[a] + aPath[b] + bPath[N];
            int bFirst = firstPath[b] + bPath[a] + aPath[N];
            
            // System.out.printf("aFirst = %d = %d + %d + %d\n", aFirst,firstPath[a],aPath[b],bPath[N]);
            // System.out.printf("bFirst = %d = %d + %d + %d\n", bFirst,firstPath[b],bPath[a],aPath[N]);

            result = aFirst < bFirst ? aFirst: bFirst;
        }

        bw.write(Integer.toString(result));
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
                // if temp node is not visited before or temp's path(weight) is bigger than new path
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
            for(int i=0;i<N;i++){
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
                    int nodeNum = temp.getNodeNum();
                    int weight = temp.getWeight();
                    ret += String.format("%d[%d] ",nodeNum,weight);
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
            getList(start).add(new Node(end,weight));
            getList(end).add(new Node(start,weight));
            edge++;
        }
    
        public ArrayList<Node> getList(int node){
            return adjList.get(node-1);
        }
    }
}