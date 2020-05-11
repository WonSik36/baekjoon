/*
    baekjoon online judge
    problem number 4386
    https://www.acmicpc.net/problem/4386

    Prim MST Algotrithm
    Because given graph is Dense Graph, I used prim algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        List<Point> list = new ArrayList<Point>();
        List<List<Node>> g = new ArrayList<List<Node>>();
        for(int i=0;i<=N;i++){
            g.add(new ArrayList<Node>());
        }

        // get data
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            list.add(new Point(y,x));
        }

        // make graph
        for(int i=0;i<N;i++){
            Point p1 = list.get(i);

            for(int j=i+1;j<N;j++){
                Point p2 = list.get(j);

                double dist = p1.getDistance(p2);
                g.get(i).add(new Node(j, dist));
                g.get(j).add(new Node(i, dist));
            }
        }

        // use prim algorithm
        double totalWeight = 0;
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(1,0));
        while(!pq.isEmpty()){
            Node root = pq.poll();

            if(visited[root.num])
                continue;
            visited[root.num] = true;
            totalWeight += root.dist;

            List<Node> adjList = g.get(root.num);
            for(int i=0;i<adjList.size();i++){
                pq.add(adjList.get(i));
            }
        }

        String str = String.format("%.2f\n", totalWeight);
        bw.write(str);

        bw.close();
        br.close();
    }
}

class Node implements Comparable<Node>{
    public int num;
    public double dist;

    public Node(int num, double dist){
        this.num = num;
        this.dist = dist;
    }

    public int compareTo(Node that){
        if(this.dist < that.dist)
            return -1;
        else if(this.dist > that.dist)
            return 1;
        else
            return 0;
    }
}

class Point {
    public double y;
    public double x;

    public Point(double y, double x){
        this.y = y;
        this.x = x;
    }

    public double getDistance(Point that){
        return Math.sqrt(Math.pow(this.y-that.y, 2)+Math.pow(this.x-that.x, 2));
    }
}