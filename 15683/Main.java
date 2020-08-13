/*
    baekjoon online judge
    problem number 15683
    https://www.acmicpc.net/problem/15683

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

public class Main{
    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;

    static final int EMPTY = 0;
    static final int WALL = 6;
    static final int WATCHING = 7;
    static final int[][][] DIR = {
        {}, // 0
        // 1
        {
           {UP}, {RIGHT}, {DOWN}, {LEFT} 
        },
        // 2
        {
           {UP,DOWN}, {RIGHT,LEFT} 
        },
        // 3
        {
           {UP,RIGHT}, {RIGHT,DOWN}, {DOWN,LEFT}, {LEFT,UP}
        },
        // 4
        {
           {LEFT,UP,RIGHT}, {UP,RIGHT,DOWN}, {RIGHT,DOWN,LEFT}, {DOWN,LEFT,UP}
        },
        // 5
        {
           {UP,RIGHT,DOWN,LEFT}
        }
    };

    static int N;
    static int M;
    static int[][] map;
    static List<CCTV> cctvs;
    static int Min = Integer.MAX_VALUE;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cctvs = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                int v = Integer.parseInt(st.nextToken());

                if(v != EMPTY && v != WALL){
                    cctvs.add(new CCTV(i,j,v));
                }

                map[i][j] = v;
            }
        }

        int[] directions = new int[cctvs.size()];
        backtrack(0, directions);

        bw.write(Integer.toString(Min)+"\n");

        bw.close();
        br.close();
    }

    static void backtrack(int idx, int[] directions){
        if(idx == cctvs.size()){
            Min = Math.min(Min, calcBlidSpot(directions));
            return;
        }

        CCTV cctv = cctvs.get(idx);
        for(int i=0; i < DIR[cctv.kind].length; i++){
            directions[idx] = i;
            
            backtrack(idx+1, directions);
        }
    }

    static int calcBlidSpot(int[] directions){
        int[][] clone = cloneMap();
        for(int i=0;i<cctvs.size();i++){
            CCTV cctv = cctvs.get(i);

            for(int j=0;j<DIR[cctv.kind][directions[i]].length;j++){
                watchLine(DIR[cctv.kind][directions[i]][j], cctv, clone);
            }
        }

        int cnt = calcEmptySpace(clone);
        return cnt;
    }

    static void watchLine(int dir, CCTV pos, int[][] map){
        switch(dir){
            case UP:
                for(int dy=pos.y-1; dy >= 0 && map[dy][pos.x] != WALL; dy--){
                    if(map[dy][pos.x] == EMPTY)
                        map[dy][pos.x] = WATCHING;
                }
                break;
            case RIGHT:
                for(int dx=pos.x+1; dx < M && map[pos.y][dx] != WALL; dx++){
                    if(map[pos.y][dx] == EMPTY)
                        map[pos.y][dx] = WATCHING;
                }
                break;
            case DOWN:
                for(int dy=pos.y+1; dy < N && map[dy][pos.x] != WALL; dy++){
                    if(map[dy][pos.x] == EMPTY)
                        map[dy][pos.x] = WATCHING;
                }
                break;
            case LEFT:
                for(int dx=pos.x-1; dx >= 0 && map[pos.y][dx] != WALL; dx--){
                    if(map[pos.y][dx] == EMPTY)
                        map[pos.y][dx] = WATCHING;
                }
                break;
            default:
                throw new AssertionError("Unknown Direction: "+dir);
        }
    }

    static int[][] cloneMap(){
        int[][] clone = new int[N][M];
        for(int i=0;i<N;i++){
            System.arraycopy(map[i], 0, clone[i], 0, M);
        }

        return clone;
    }

    static int calcEmptySpace(int[][] map){
        int cnt = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == EMPTY)
                    cnt++;
            }
        }

        return cnt;
    }
}

class CCTV {
    public final int y;
    public final int x;
    public final int kind;

    public CCTV(int y, int x, int kind){
        this.y = y;
        this.x = x;
        this.kind = kind;
    }
}