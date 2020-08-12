/*
    baekjoon online judge
    problem number 14502
    https://www.acmicpc.net/problem/14502

    Brute Force
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;

    static int N;
    static int M;
    static int[][] map;
    static List<Pos> viruses;

    static int Max = 0;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        viruses = new ArrayList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int v = Integer.parseInt(st.nextToken());
                if(v == VIRUS){
                    viruses.add(new Pos(i,j));
                }

                map[i][j] = v;
            }
        }

        long start = System.nanoTime();

        backtrack(0, -1);
        bw.write(Integer.toString(Max)+"\n");

        long end = System.nanoTime();

        System.out.println((end-start) / 1000000000.0);

        bw.close();
        br.close();
    }

    static void backtrack(int cnt, int prev){
        if(cnt == 3){
            Max = Math.max(Max, calcMap());
            return;
        }

        for(int idx=prev+1; idx <= N*M - 3 + cnt; idx++){
            if(map[idx/M][idx%M] != EMPTY)
                continue;

            map[idx/M][idx%M] = WALL;
            backtrack(cnt+1, idx);
            map[idx/M][idx%M] = EMPTY;
        }
    }

    static int calcMap(){
        // printMap(map);
        int[][] clone = cloneMap();
        for(Pos virus : viruses){
            spreadVirusToMap(clone, virus);
        }

        int cnt = calcSafetyZone(clone);

        // if(cnt > 0){
        //     printMap(clone);
        // }

        return cnt;
    }

    static void spreadVirusToMap(int[][] copiedMap, Pos virus){
        Queue<Pos> queue = new LinkedList<>();
        queue.add(virus);

        while(!queue.isEmpty()){
            Pos first = queue.poll();

            copiedMap[first.y][first.x] = VIRUS;

            if(first.y > 0 && copiedMap[first.y-1][first.x] == EMPTY){
                queue.add(new Pos(first.y-1, first.x));
            }
            if(first.y < N-1 && copiedMap[first.y+1][first.x] == EMPTY){
                queue.add(new Pos(first.y+1, first.x));
            }
            if(first.x > 0 && copiedMap[first.y][first.x-1] == EMPTY){
                queue.add(new Pos(first.y, first.x-1));
            }
            if(first.x < M-1 && copiedMap[first.y][first.x+1] == EMPTY){
                queue.add(new Pos(first.y, first.x+1));
            }
        }
    }

    static int calcSafetyZone(int[][] copiedMap){
        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(copiedMap[i][j] == EMPTY)
                    cnt++;
            }
        }

        return cnt;
    }

    static int[][] cloneMap(){
        int[][] copied = new int[N][M];

        for(int i=0;i<N;i++){
            System.arraycopy(map[i], 0, copied[i], 0, M);
        }

        return copied;
    }

    static void printMap(int[][] map){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n");
    }
}

class Pos {
    public final int y;
    public final int x;

    public Pos(int y, int x){
        this.y = y;
        this.x = x;
    }
}