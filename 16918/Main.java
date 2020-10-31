/*
    baekjoon online judge
    problem number 16918
    https://www.acmicpc.net/problem/16918
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Main{
    static int R;
    static int C;
    static int N;

    static final int EMPTY = 0;
    static final int BOMB = 1;
    static final int DUMMY = -1;
    static final int EXP_TIME = 3;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];
        int[][] time = new int[R][C];

        for(int i=0;i<R;i++){
            String line = br.readLine();
            for(int j=0;j<C;j++){
                if(line.charAt(j) == '.'){
                    map[i][j] = EMPTY;
                    time[i][j] = DUMMY;
                } else {
                    map[i][j] = BOMB;
                    time[i][j] = EXP_TIME;
                }
            }
        }

        for(int t=1;t<=N;t++){
            if(t % 2 == 0) {
                fillBomb(t, map, time);
            } else {
                fireBomb(t, map, time);
            }
            // System.out.println("time: "+t);
            // System.out.println(mapToString(map));
            // System.out.println();
        }

        bw.write(mapToString(map));

        bw.close();
        br.close();
    }

    static void fillBomb(int now, int[][] map, int[][] time) {
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j] == EMPTY) {
                    map[i][j] = BOMB;
                    time[i][j] = now + EXP_TIME;
                }
            }
        }
    }

    static void fireBomb(int now, int[][] map, int[][] time) {
        Queue<Pos> queue = new ArrayDeque<>();

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(time[i][j] == now) {
                    queue.add(new Pos(i,j));
                    time[i][j] = DUMMY;
                }
            }
        }

        while(!queue.isEmpty()) {
            Pos first = queue.poll();

            map[first.y][first.x] = EMPTY;
            
            for(Pos adj : first.adjacentPos()) {
                if(adj.y >= 0 && adj.y < R && adj.x >= 0 && adj.x < C)
                    map[adj.y][adj.x] = EMPTY;
            }
        }
    }

    static String mapToString(int[][] map) {
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j] == EMPTY)
                    sb.append('.');
                else
                    sb.append('O');
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}

class Pos {
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};

    public int y;
    public int x;

    public Pos(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public List<Pos> adjacentPos() {
        List<Pos> adj = new ArrayList<>();

        for(int i=0;i<4;i++){
            adj.add(new Pos(this.y+dy[i], this.x+dx[i]));
        }

        return adj;
    }
}