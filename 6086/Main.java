/*
    baekjoon online judge
    problem number 6086
    https://www.acmicpc.net/problem/6086

    Network Flow Theory
    Edmonds-Karp Algorithm
    https://blog.naver.com/PostView.nhn?blogId=kks227&logNo=220804885235

    high reference level
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Main{
    static final int INF = 100000000;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Graph g = new Graph(52);
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = charToInt(st.nextToken().charAt(0));
            int end = charToInt(st.nextToken().charAt(0));
            int capacity = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, capacity);
        }

        // bw.write(g.toString());
        int sum = g.EdmondsKarp();

        bw.write(Integer.toString(sum)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int charToInt(char c){
        if(c>='A' && c<='Z')
            return c - 'A' +1;
        else if(c>='a' && c<='z')
            return c - 'a' + 26 +1;
        else
            throw new AssertionError("Input Data Error");
    }

    public static class Node{
        private int nodeNum;
        private int flow;
        private int capacity;
    
        public Node(int nodeNum, int capacity){
            this.nodeNum = nodeNum;
            this.flow = 0;
            this.capacity = capacity;
        }

        public int getNodeNum(){
            return nodeNum;
        }
    
        public void setNodeNum(int nodeNum){
            this.nodeNum = nodeNum;
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

        public int EdmondsKarp(){
            int sum = 0;
            int start = charToInt('A');
            int end = charToInt('Z');

            //find augmented path
            while(true){
                int[] prev = new int[vertex+1];
                LinkedList<Node> queue = new LinkedList<Node>();
                queue.addLast(new Node(start, 0));

                while(!queue.isEmpty() && prev[end]==0){
                    Node top = queue.removeFirst();
                    Iterator<Node> it = getList(top.getNodeNum()).iterator();

                    while(it.hasNext()){
                        Node tmp = it.next();
                        if(tmp.getRemain() > 0 && prev[tmp.getNodeNum()]==0){
                            queue.addLast(tmp);
                            prev[tmp.getNodeNum()] = top.getNodeNum();

                            if(tmp.getNodeNum() == end)
                                break;
                        }
                    }
                }

                if(prev[end] == 0)
                    break;
                
                // find minimum flow in the path
                int minFlow = INF;
                for(int i=end;i!=start;i=prev[i]){
                    int remain = getEdge(prev[i],i).getRemain();
                    minFlow = minFlow<remain?minFlow:remain;
                }

                // add flow to every edge in the path
                for(int i=end;i!=start;i=prev[i]){
                    getEdge(prev[i],i).addFlow(minFlow);    // forward flow add
                    getEdge(i,prev[i]).addFlow(minFlow*(-1)); // reverse flow add
                }
                sum += minFlow;
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
    
        // can be refactored
        public void addEdge(int start, int end, int capacity){
            ArrayList<Node> list = adjList.get(start-1);
            int idx;
            // if edge from start to end exist
            if((idx = list.indexOf(new Node(end,0))) == -1){
                list.add(new Node(end, capacity));
            }else{
                int c = list.get(idx).getCapacity();
                list.get(idx).setCapacity(c+capacity);
            }

            list = adjList.get(end-1);
            if((idx = list.indexOf(new Node(start,0))) == -1){
                list.add(new Node(start, capacity));
            }else{
                int c = list.get(idx).getCapacity();
                list.get(idx).setCapacity(c+capacity);
            }

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
