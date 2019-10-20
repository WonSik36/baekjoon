/*
    baekjoon online judge
    problem number 10217
    https://www.acmicpc.net/problem/10217
    ref level high
    https://zoomkoding.github.io/codingtest/2019/08/05/baekjoon-10217.html
    https://kyunstudio.tistory.com/160

    There is additional condition budget
    use dijkstra and DP to solve this problem
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.lang.Comparable;

public class Main{
    public static final int INF = 100000000;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int Num = Integer.parseInt(br.readLine());
        for(int i=0;i<Num;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int budget = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            Graph g = new Graph(N);

            for(int j=0;j<E;j++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                g.addEdge(start, end, weight,cost);
            }
            
            int res = g.dijkstra(budget);
            if(res == -1)
                bw.write("Poor KCM\n");
            else
                bw.write(Integer.toString(res)+"\n");
        }

        bw.flush();
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

        public int dijkstra(int budget){
            // initialize dp
            // dp[i][j] = time spent vertex i to N(target) when cost is j
            int[][] dp = new int[vertex+1][budget+1];
            for(int i=2;i<dp.length;i++){
                Arrays.fill(dp[i],INF);
            }

            // initialize queue for BFS
            PriorityQueue<Node> queue = new PriorityQueue<Node>();
            queue.add(new Node(1,0,0));
            
            int min = INF;
            // run dijkstra
            while(!queue.isEmpty()){
                Node cur = queue.poll();
                // System.out.printf("current[%d] c:%d w:%d \n",cur.getNodeNum(),cur.getWeight(),cur.getCost());
                ArrayList<Node> list = getList(cur.getNodeNum());
                Iterator<Node> it = list.iterator();
                while(it.hasNext()){
                    Node tmp = it.next();
                    int nodeNum = tmp.getNodeNum();
                    int cost = tmp.getCost()+cur.getCost();
                    int weight = cur.getWeight()+tmp.getWeight();

                    // if cost is over the budget or weight is over the minimum cost, than continue
                    if(cost > budget || weight >= min){
                        continue;

                    // if tmp is destination
                    }else if(nodeNum == vertex){
                        // because weight is smaller than min update min
                        min = weight;
                        continue;

                    // if time is bigger than memoized value(which has same cost), than continue
                    }else if(dp[nodeNum][cost] <= weight){
                        continue;

                    // else add node tmp to queue
                    }else{
                        queue.add(new Node(tmp.getNodeNum(),weight,cost));
                        // update dp[nodeNum]'s weight from current cost to budget
                        // where cost is bigger than current cost
                        // for condition: dp[nodeNum][cost] <= weight is vulnerable
                        for(int i=cost;i<=budget;i++){
                            if(dp[nodeNum][i] > weight)
                                dp[nodeNum][i] = weight;
                        }
                    }
                }
            }

            if(min == INF)
                return -1;
            else
                return min;
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
                    int cost = node.getCost();
                    ret += String.format("%d[weight: %d, cost: %d] ", nodeNum, weight,cost);
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

        public void addEdge(int start, int end, int weight, int cost){
            ArrayList<Node> list = adjList.get(start-1);
            list.add(new Node(end, weight, cost));
            edge++;
        }

        public ArrayList<Node> getList(int node){
            return adjList.get(node-1);
        }
    }

    public static class Node implements Comparable<Node>{
        private int nodeNum;
        private int weight;
        private int cost;
        private Node next;

        public Node(int nodeNum, int weight, int cost){
            this.nodeNum = nodeNum;
            this.weight = weight;
            this.cost = cost;
            next = null;
        }

        Node(int nodeNum, int weight, int cost, Node next){
            this(nodeNum, weight, cost);
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

        public int getCost(){
            return cost;
        }

        public void setCost(int cost){
            this.cost = cost;
        }

        public Node getNext(){
            return next;
        }

        public void setNext(Node next){
            this.next = next;
        }

        @Override
        public int compareTo(Node that){
            if(this.cost < that.cost)
                return -1;
            else if(this.cost > that.cost)
                return 1;
            else{
                if(this.weight < that.weight)
                    return -1;
                else if(this.weight > that.weight)
                    return 1;
                else
                    return 0;
            }
        }
    }
}
