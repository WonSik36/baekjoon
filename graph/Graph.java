/*
    To execute this program
    1. cd C:\Users\wonsik\Documents
    2. javac .\baekjoon\graph\Node.java .\baekjoon\graph\LinkedList.java .\baekjoon\graph\Graph.java
    3. java baekjoon.graph.Graph
*/
package baekjoon.graph;
import java.util.Iterator;

public class Graph{
    private LinkedList[] adjList;
    private int vertex;
    private int edge;

    public static void main(String[] args){
        Graph g = new Graph(5);

        // test 1
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 2);
        g.addEdge(3, 4, 3);
        g.addEdge(4, 5, 4);
        g.addEdge(2, 4, 5);
        g.addEdge(1, 5, 6);

        System.out.printf("vertex:%d, edge:%d\n\n",g.getVertex(), g.getEdge());
        
        // test 2
        System.out.println(g.toString());

        // test 3
        LinkedList list = g.getList(1);
        Iterator<Node> it = list.iterator();
        System.out.print("Node 1 has edge\n");
        while(it.hasNext()){
            Node node = it.next();
            System.out.printf("To: %d, W: %d\n",node.getNodeNum(), node.getWeight());
        }

    }

    public Graph(int N){
        this.vertex = N;
        this.edge = 0;
        adjList = new LinkedList[N+1];
        for(int i=1;i<=N;i++){
            adjList[i] = new LinkedList();
        }
    }

    public String toString(){
        String ret = "";
        for(int i=1;i<adjList.length;i++){
            Iterator<Node> it = adjList[i].iterator();
            ret += ("Node["+i+"]: ");
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
        adjList[start].addEdge(end,weight);
        adjList[end].addEdge(start,weight);
        edge++;
    }

    public LinkedList getList(int node){
        return adjList[node];
    }
}