/*
    baekjoon online judge
    problem number 1967
    https://www.acmicpc.net/problem/1967

    same with problem 1167

    reference:
    https://zoomkoding.github.io/%EB%B0%B1%EC%A4%80/2019/07/17/baekjoon-1167.html
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
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        Graph g = new Graph(N);

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            g.addEdge(start, end, weight);
        }

        if(N == 1){
            bw.write("0\n");
        }else{
            int diameter = g.getDiameter();
            bw.write(Integer.toString(diameter)+"\n");
        }

        bw.close();
        br.close();
    }
}

class Graph{
    private ArrayList<ArrayList<Node>> adjList;
    private int vertex;
    private int edge;
    private int farthestNode;
    private int diameter;

    public Graph(int N){
        this.vertex = N;
        this.edge = 0;
        adjList = new ArrayList<ArrayList<Node>>();
        for(int i=0;i<N;i++){
            adjList.add(new ArrayList<Node>());
        }
    }

    public int getDiameter(){
        dfs(1);
        final int start = this.farthestNode;
        dfs(start);
        
        return this.diameter;
    }

    public int[] dfs(int start){
        int[] dist = new int[vertex+1];
        Arrays.fill(dist, -1);
        dist[start] = 0;

        Stack<Node> stack = new Stack<Node>();
        stack.add(new Node(start,0));

        while(!stack.isEmpty()){
            Node last = stack.pop();

            if(diameter < last.getWeight()){
                diameter = last.getWeight();
                farthestNode = last.getNodeNum();
            }

            Iterator<Node> it = adjList.get(last.getNodeNum()-1).iterator();
            while(it.hasNext()){
                Node tmp = it.next();

                if(dist[tmp.getNodeNum()] != -1)
                    continue;

                dist[tmp.getNodeNum()] = last.getWeight() + tmp.getWeight();
                stack.add(new Node(tmp.getNodeNum(), dist[tmp.getNodeNum()]));
            }
        }

        return dist;
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