/*
    baekjoon online judge
    problem number 11725
    https://www.acmicpc.net/problem/11725

    Tree Problem: Find Parent Node By Using DFS Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Stack;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        Graph g = new Graph(N);

        for(int i=1;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, 0);
        }

        int[] parent = g.dfs();

        for(int i=2;i<=N;i++){
            bw.write(Integer.toString(parent[i]));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }
}

class Graph{
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

    public int[] dfs(){
        int[] parent = new int[vertex+1];
        Stack<Node> stack = new Stack<Node>();
        stack.add(new Node(1,0));

        while(!stack.isEmpty()){
            Node last = stack.pop();

            Iterator<Node> it = adjList.get(last.getNodeNum()-1).iterator();
            while(it.hasNext()){
                Node tmp = it.next();

                if(parent[tmp.getNodeNum()] != 0)
                    continue;

                parent[tmp.getNodeNum()] = last.getNodeNum();
                stack.add(tmp);
            }
        }

        return parent;
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
                ret += String.format("%d[%d]", nodeNum, weight);
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

class Node{
    private int nodeNum;
    private int weight;

    public Node(int nodeNum, int weight){
        this.nodeNum = nodeNum;
        this.weight = weight;
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
}