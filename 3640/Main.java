/*
    baekjoon online judge
    problem number 3640
    https://www.acmicpc.net/problem/3640
    https://handongproblemsolvers.github.io/2019/10/28/Week_10_Contest_Problem_Solving/#%EC%A0%9C%EB%8F%85

    solve by MCMF but capacity is 1
    and also need to know vertex splitting
    (split input part and output part and from input to ouput capacity is 1
    , but start node and end node's capacity is 2)

    MCMF(Minimum Cost Maximum Flow) Problem
    https://m.blog.naver.com/kks227/220810623254

    SPFA(Shortest Path Faster Algorithm) Problem
    https://www.crocus.co.kr/1089

    I forgot this graph is one-way graph
    in one-way graph inverse path has 0 capacity
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Main{
    static final int INF = 2000000000;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while(true){
            String input = br.readLine();
            if(input == null)
                break;

            StringTokenizer st = new StringTokenizer(input);
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            Graph g = new Graph(V*2); // vertex splitting without start and end
            
            // vertex splitting
            // 1 -> 1,2 capacity:2
            // 2 -> 3,4 capacity:1
            // 3 -> 5,6 capacity:1
            // 4 -> 7,8 capacity:1
            // V(end) -> 2*V-1, 2*V capacity:2
            g.addEdge(1,2,0,2);
            g.addEdge(2*V-1,2*V,0,2);
            for(int i=2;i<V;i++){
                int in = 2*i-1, out = 2*i;
                g.addEdge(in,out,0,1);
            }

            for(int i=0;i<E;i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                
                int startOut = start*2;
                int endIn = end*2-1;
                g.addEdge(startOut, endIn, weight,1);
            }
            
            int first = 1; // starting node
            int last = 2*V; // ending node
            int sum = g.MCMF(first, last);
    
            bw.write(Integer.toString(sum)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class Node{
        private int nodeNum;
        private int capacity;
        private int weight;
        private int flow;
    
        public Node(int nodeNum, int weight){
            this(nodeNum,weight,1);
        }

        public Node(int nodeNum, int weight, int capacity){
            this.nodeNum = nodeNum;
            this.weight = weight;
            this.capacity = capacity;
            this.flow = 0;
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

        public int getCapacity(){
            return capacity;
        }
    
        public void setCapacity(int capacity){
            this.capacity = capacity;
        }
    
        public void addFlow(int flow){
            this.flow += flow;
            if(this.flow > this.capacity)
                throw new AssertionError("Flow over Capacity");
        }

        public int getRemain(){
            if(this.flow > this.capacity)
                throw new AssertionError("Flow over Capacity");
            return this.capacity - this.flow;
        }

        @Override
        public boolean equals(Object obj){
            Node that = (Node)obj;
            if(this.nodeNum == that.getNodeNum())
                return true;
            return false;
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

        public int MCMF(int start, int end){
            // int maxFlow = 0;
            int sum = 0;

            //find augmented path
            while(true){
                int[] prev = new int[vertex+1];
                int[] dist = new int[vertex+1];
                boolean[] inQ = new boolean[vertex+1];
                LinkedList<Node> queue = new LinkedList<Node>();
                
                Arrays.fill(dist,INF);
                dist[start] = 0;
                inQ[start] = true;
                queue.addLast(new Node(start, 0));

                // SPFA
                while(!queue.isEmpty()){
                    Node top = queue.removeFirst();
                    inQ[top.getNodeNum()] = false; 
                    Iterator<Node> it = getList(top.getNodeNum()).iterator();

                    while(it.hasNext()){
                        Node tmp = it.next();
                        int tmpNum = tmp.getNodeNum();

                        // 1. edge from top to tmp should have remain flow
                        // 2. edge from top to tmp + weight from start to top is shorter than weight from start to tmp
                        if(tmp.getRemain() > 0 && dist[tmpNum] > dist[top.getNodeNum()] + tmp.getWeight()){
                            dist[tmpNum] = dist[top.getNodeNum()] + tmp.getWeight();
                            prev[tmpNum] = top.getNodeNum();
                            
                            if(!inQ[tmpNum]){
                                queue.addLast(tmp);
                                inQ[tmpNum] = true;
                            }
                        }
                    }
                }

                // there is no path from start to end
                if(prev[end] == 0)
                    break;
                
                // find minimum flow in the path
                // check the node was visited without start and end
                int minFlow = INF;
                int totalWeight = 0;
                for(int i=end;i!=start;i=prev[i]){
                    int remain = getEdge(prev[i],i).getRemain();
                    minFlow = minFlow<remain?minFlow:remain;
                }

                // add flow to every edge in the path
                // get total weight
                for(int i=end;i!=start;i=prev[i]){
                    getEdge(prev[i],i).addFlow(minFlow);    // forward flow add
                    getEdge(i,prev[i]).addFlow(minFlow*(-1)); // reverse flow add
                    totalWeight += getEdge(prev[i],i).getWeight();
                }

                // maxFlow += minFlow;
                sum += minFlow*totalWeight;
            }

            return sum;
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
                    int capacity = node.getCapacity();
                    ret += String.format("%d[%d]", nodeNum, capacity);
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
    
        // directed graph and from u to v there can be only one edge
        public void addEdge(int start, int end, int weight, int capacity){
            ArrayList<Node> list = adjList.get(start-1);
            list.add(new Node(end, weight,capacity));
            list = adjList.get(end-1);
            list.add(new Node(start, (-1)*weight,0));
            edge++;
        }

        public Node getEdge(int start, int end){
            ArrayList<Node> list = getList(start);
            for(int i=0;i<list.size();i++){
                Node tmp = list.get(i);
                if(tmp.getNodeNum() == end)
                    return tmp;
            }

            throw new AssertionError("No node in adj List "+start);
        }
    
        public ArrayList<Node> getList(int node){
            return adjList.get(node-1);
        }
    }
}
