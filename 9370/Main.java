/*
    baekjoon online judge
    problem number 9370
    https://www.acmicpc.net/problem/9370
    shortest path on given situation problem

    ref of shortest path on given situation
    https://www.acmicpc.net/problem/1504
    https://jaimemin.tistory.com/1004

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
import java.lang.Comparable;
import java.util.Collections;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int Num = Integer.parseInt(br.readLine());
        for(int j=0;j<Num;j++){
            // read first row, which is number of node, edge, target's dest candidates
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            // read second row, which is node of start point, target visited points
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            // make graph
            Graph g = new Graph(N);
            for(int i=0;i<E;i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
    
                g.addEdge(start, end, weight);
            }

            // get target's destination candidates
            ArrayList<Integer> list = new ArrayList<Integer>();
            for(int i=0;i<T;i++){
                int tmp = Integer.parseInt(br.readLine());
                list.add(tmp);
            }
    
            // get shortest path to other point by using dijkstra
            int[] sPath = dijkstra(g, S);
            int[] gPath = dijkstra(g, G);
            int[] hPath = dijkstra(g, H);

            // check candidates are acceptable
            Iterator<Integer> it = list.iterator();
            while(it.hasNext()){
                int target = it.next();
                // System.out.println("target check: "+target);
                // path s-...-g-h-...-d
                int sghdWeight = sPath[G] + g.getEdgeWeight(G,H) + hPath[target];
                // System.out.printf("sghd: %d = %d + %d + %d\n",sghdWeight,sPath[G],g.getEdgeWeight(G,H),hPath[target]);
                // path s-...-h-g-...-d
                int shgdWeight = sPath[H] + g.getEdgeWeight(H,G) + gPath[target];
                // System.out.printf("sghd: %d = %d + %d + %d\n",shgdWeight,sPath[H],g.getEdgeWeight(H,G),gPath[target]);
                // System.out.printf("s to target: %d\n\n",sPath[target]);
                // if path from start to target is not matched with shgd or sghd than remove it
                if(sPath[target]!=sghdWeight && sPath[target]!=shgdWeight)
                    it.remove();
            }
            // System.out.println("\n");
            // print final destination candidates
            Collections.sort(list);
            for(int i=0;i<list.size();i++){
                bw.write(Integer.toString(list.get(i))+" ");
            }
            bw.write("\n");
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

        public int getEdgeWeight(int from, int to){
            ArrayList<Node> list = getList(from);
            for(int i=0;i<list.size();i++){
                Node temp = list.get(i);
                if(temp.getNodeNum() == to){
                    return temp.getWeight();
                }
            }

            throw new RuntimeException("There is no edge between node["+from+"] to node["+to+"]");
        }
    }
}