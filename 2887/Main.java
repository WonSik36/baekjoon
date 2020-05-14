/*
    baekjoon online judge
    problem number 2887
    https://www.acmicpc.net/problem/2887

    Kruskal MST Algorithm
    reference: https://js1jj2sk3.tistory.com/27
    Use 3(N-1) Edges by sorting 3 time order by x,y,z
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.lang.StringBuilder;
import java.lang.Comparable;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        
        int N = Integer.parseInt(br.readLine());
        List<Pos> list = new ArrayList<Pos>();
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            list.add(new Pos(i,x,y,z));
        }

        // sorting by x axis
        Collections.sort(list, new Comparator<Pos>(){
            @Override
            public int compare(Pos p1, Pos p2){
                if(p1.x < p2.x)
                    return -1;
                else if(p1.x > p2.x)
                    return 1;
                else
                    return 0;
            }
        });

        // get edge by comparing ith value and (i+1)th value
        for(int i=1;i<N;i++){
            pq.add(new Edge(list.get(i).num, list.get(i-1).num, list.get(i).x - list.get(i-1).x));
        }

        // sorting by y axis
        Collections.sort(list, new Comparator<Pos>(){
            @Override
            public int compare(Pos p1, Pos p2){
                if(p1.y < p2.y)
                    return -1;
                else if(p1.y > p2.y)
                    return 1;
                else
                    return 0;
            }
        });

        // get edge by comparing ith value and (i+1)th value
        for(int i=1;i<N;i++){
            pq.add(new Edge(list.get(i).num, list.get(i-1).num, list.get(i).y - list.get(i-1).y));
        }

        // sorting by z axis
        Collections.sort(list, new Comparator<Pos>(){
            @Override
            public int compare(Pos p1, Pos p2){
                if(p1.z < p2.z)
                    return -1;
                else if(p1.z > p2.z)
                    return 1;
                else
                    return 0;
            }
        });

        // get edge by comparing ith value and (i+1)th value
        for(int i=1;i<N;i++){
            pq.add(new Edge(list.get(i).num, list.get(i-1).num, list.get(i).z - list.get(i-1).z));
        }

        int[] parent = new int[N];
        Arrays.fill(parent, -1);
        int cnt = 0;
        long total = 0;
        while(cnt<N-1 && !pq.isEmpty()){
            Edge top = pq.poll();
            // System.out.println(top.toString());

            if(getParent(top.start, parent) == getParent(top.end, parent))
                continue;
            merge(top.start, top.end, parent);

            cnt++;
            total += top.dist;
        }

        bw.write(Long.toString(total)+"\n");

        bw.close();
        br.close();
    }

    static int getParent(int node, int[] parent){
        if(parent[node] == -1)
            return node;

        return parent[node] = getParent(parent[node], parent);
    }

    static void merge(int A, int B, int[] parent){
        A = getParent(A, parent);
        B = getParent(B, parent);

        if(A == B)
            return;

        if(A < B){
            parent[B] = A;
        }else{
            parent[A] = B;
        }
    }

    static void printList(List<Pos> list){
        for(Pos p : list){
            System.out.println(p.toString());
        }
    }
}

class Edge implements Comparable<Edge> {
    public int start;
    public int end;
    public int dist;

    public Edge(int start, int end, int dist){
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("start: ");
        sb.append(start);
        sb.append(", end: ");
        sb.append(end);
        sb.append(", dist: ");
        sb.append(dist);

        return sb.toString();
    }
}

class Pos {
    public int num;
    public int x;
    public int y;
    public int z;

    public Pos(int num, int x, int y, int z){
        this.num = num;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("num: ");
        sb.append(num);
        sb.append(", x: ");
        sb.append(x);
        sb.append(", y: ");
        sb.append(y);
        sb.append(", z: ");
        sb.append(z);

        return sb.toString();
    }
}