/*
    baekjoon online judge
    problem number 1774
    https://www.acmicpc.net/problem/1774

    Kruskal MST Algorithm
    Hint: given M Edge can be cycle
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.lang.Comparable;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        List<Pos> posList = new ArrayList<Pos>();
        posList.add(new Pos(0,0));  // dummy value
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            posList.add(new Pos(y,x));
        }

        int[] parent = new int[N+1];
        Arrays.fill(parent, -1);
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            merge(start, end, parent);
        }

        // push edge
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        for(int i=1;i<=N;i++){
            for(int j=i+1;j<=N;j++){
                // System.out.printf("new Edge(%d,%d,%.2f)\n",i,j, posList.get(i).getDistance(posList.get(j)));
                pq.add(new Edge(i,j, posList.get(i).getDistance(posList.get(j))));
            }
        }

        // get edge
        double total = 0;
        int cnt = 0;
        while(!pq.isEmpty() && cnt < N-1){
            Edge root = pq.poll();
            // System.out.printf("root[%d,%d]: %.2f\n", root.start, root.end, root.dist);

            if(getParent(root.start, parent) == getParent(root.end, parent))
                continue;
            merge(root.start, root.end, parent);
            total += root.dist;
            cnt++;
        }

        String result = String.format("%.2f\n", total);
        bw.write(result);

        bw.close();
        br.close();
    }

    static void printArr(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }

    static int getParent(int node, int[] parent){
        if(parent[node] == -1)
            return node;

        return parent[node] = getParent(parent[node], parent);
    }

    static void merge(int A, int B, int[] parent){
        int parentOfA = getParent(A, parent);
        int parentOfB = getParent(B, parent);

        if(parentOfA == parentOfB)
            return;

        if(parentOfA < parentOfB){
            parent[parentOfB] = parentOfA;
        }else{
            parent[parentOfA] = parentOfB;
        }
    }
}

class Pos {
    public int y;
    public int x;

    public Pos(int y, int x){
        this.y = y;
        this.x = x;
    }

    public double getDistance(Pos that){
        return Math.sqrt(Math.pow(this.y-that.y, 2)+Math.pow(this.x-that.x, 2));
    }
}

class Edge implements Comparable<Edge> {
    public int start;
    public int end;
    public double dist;

    public Edge(int start, int end , double dist){
        this.start = start;
        this.end = end;
        this.dist = dist;
    }

    @Override
    public int compareTo(Edge that){
        if(this.dist < that.dist)
            return -1;
        else if(this.dist > that.dist)
            return 1;
        else
            return 0;
    }
}