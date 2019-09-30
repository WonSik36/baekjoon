/*
    To execute this program
    1. cd C:\Users\wonsik\Documents
    2. javac .\baekjoon\graph\Node.java .\baekjoon\graph\ConvenientGraph.java
    3. java baekjoon.graph.ConvenientGraph
*/
package baekjoon.graph;
import java.util.Iterator;
import java.util.ArrayList;

public class ConvenientGraph{
    private ArrayList<ArrayList<Node>> adjList;
    private int vertex;
    private int edge;

    public static void main(String[] args){
        ConvenientGraph g = new ConvenientGraph(5);

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
        ArrayList<Node> list = g.getList(1);
        Iterator<Node> it = list.iterator();
        System.out.print("Node 1 has edge\n");
        while(it.hasNext()){
            Node node = it.next();
            System.out.printf("To: %d, W: %d\n",node.getNodeNum(), node.getWeight());
        }

        // result
        // vertex:5, edge:6

        // Node[1]: 2 5
        // Node[2]: 1 3 4
        // Node[3]: 2 4
        // Node[4]: 3 5 2
        // Node[5]: 4 1

        // Node 1 has edge
        // To: 2, W: 1
        // To: 5, W: 6
    }

    public ConvenientGraph(int N){
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
            ArrayList<Node> list = adjList.get(i);
            ret += ("Node["+(i+1)+"]: ");

            Iterator<Node> it = list.iterator();
            while(it.hasNext()){
                Node node = it.next(); 
                int nodeNum = node.getNodeNum(); 
                ret += (nodeNum+" ");
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
        ArrayList<Node> list = adjList.get(start-1);
        list.add(new Node(end, weight));
        list = adjList.get(end-1);
        list.add(new Node(start, weight));
        edge++;
    }

    public ArrayList<Node> getList(int node){
        return adjList.get(node-1);
    }
}