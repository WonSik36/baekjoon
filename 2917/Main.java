/*
    baekjoon online judge
    problem number 2917
    https://www.acmicpc.net/problem/2917

    BFS with Priority Queue
    high reference: https://jaimemin.tistory.com/1014

    Memory Limitation is 128MB
    So it is important to check valid pos
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main{
    static int N;
    static int M;
    static int[][] dist;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = init(N,M);
        Queue<Pos> queue = new LinkedList<Pos>();

        Pos start = null;
        Pos dest = null;
        for(int i=0;i<N;i++){
            String str = br.readLine();

            for(int j=0;j<M;j++){
                if(str.charAt(j) == '.'){

                }else if(str.charAt(j) == 'V'){
                    start = new Pos(i,j,0,0);
                }else if(str.charAt(j) == 'J'){
                    dest = new Pos(i,j,0,0);
                }else{
                    queue.add(new Pos(i,j,0,0));
                }
            }
        }

        // get distance of each pos to tree
        while(!queue.isEmpty()){
            Pos first = queue.poll();

            if(dist[first.y][first.x] != -1)    // check visited before
                continue;
            dist[first.y][first.x] = first.dist;
            
            if(first.y < N-1 && dist[first.y+1][first.x] == -1)
                queue.add(new Pos(first.y+1, first.x, first.dist+1, 0));
            if(first.y > 0 && dist[first.y-1][first.x] == -1)
                queue.add(new Pos(first.y-1, first.x, first.dist+1, 0));
            if(first.x < M-1 && dist[first.y][first.x+1] == -1)
                queue.add(new Pos(first.y, first.x+1, first.dist+1, 0));
            if(first.x > 0 && dist[first.y][first.x-1] == -1)
                queue.add(new Pos(first.y, first.x-1, first.dist+1, 0));
        }

        // printArr(dist);

        // get minimum distance from cur pos to tree
        PriorityQueue<Pos> pq = new PriorityQueue<Pos>();
        pq.add(new Pos(start.y, start.x, dist[start.y][start.x], dist[start.y][start.x]));
        while(!pq.isEmpty()){
            Pos head = pq.poll();
            // System.out.printf("(%d,%d) dist:%d min:%d\n",head.y,head.x,head.dist,head.min);

            if(head.y == dest.y && head.x == dest.x){
                bw.write(Integer.toString(head.min)+"\n");
                break;
            }

            if(dist[head.y][head.x] == -1)  // check visited before
                continue;
            dist[head.y][head.x] = -1;

            if(head.y < N-1 && dist[head.y+1][head.x] != -1)
                pq.add(new Pos(head.y+1, head.x, dist[head.y+1][head.x], Min(head.min, dist[head.y+1][head.x])));
            if(head.y > 0 && dist[head.y-1][head.x] != -1)
                pq.add(new Pos(head.y-1, head.x, dist[head.y-1][head.x], Min(head.min, dist[head.y-1][head.x])));
            if(head.x < M-1 && dist[head.y][head.x+1] != -1)
                pq.add(new Pos(head.y, head.x+1, dist[head.y][head.x+1], Min(head.min, dist[head.y][head.x+1])));
            if(head.x > 0 && dist[head.y][head.x-1] != -1)
                pq.add(new Pos(head.y, head.x-1, dist[head.y][head.x-1], Min(head.min, dist[head.y][head.x-1])));
        }

        bw.close();
        br.close();
    }

    static int[][] init(int N, int M){
        int[][] ret = new int[N][M];

        for(int i=0;i<N;i++){
            Arrays.fill(ret[i], -1);
        }

        return ret;
    }

    static int Min(int a, int b){
        return a<b?a:b;
    }

    static void printArr(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.printf("%d ",arr[i][j]);
            }
            System.out.println();
        }
    }
}

class Pos implements Comparable<Pos>{
    public int y;
    public int x;
    public int dist;    // for compareTo function for priority queue
    public int min;

    public Pos(int y, int x, int dist, int min){
        this.y = y;
        this.x = x;
        this.dist = dist;
        this.min = min;
    }

    @Override
    public int compareTo(Pos that){
        if(this.dist < that.dist){
            return 1;
        }else{
            return -1;
        }
    }
}