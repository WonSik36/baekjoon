/*
    baekjoon online judge
    problem number 15653
    https://www.acmicpc.net/problem/15653

    BFS Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class Main{
    static final int EMPTY = 0;
    static final int MARBLE = 1;
    static final int WALL = 2;
    static final int HOLE = 3;
    
    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;

    static final int[] dy = {-1,0,1,0};
    static final int[] dx = {0,1,0,-1};

    static int N;
    static int M;
    static int[][] map;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        Map<String, Pos> marbles = new HashMap<>();
        for(int i=0;i<N;i++){
            String str = br.readLine();

            for(int j=0;j<M;j++){
                char ch = str.charAt(j);

                switch(ch){
                    case '.':
                        map[i][j] = EMPTY;
                        break;
                    case 'R':
                        marbles.put("RED", new Pos(i,j,0));
                        map[i][j] = EMPTY;
                        break;
                    case 'B':
                        marbles.put("BLUE", new Pos(i,j,0));
                        map[i][j] = EMPTY;
                        break;
                    case '#':
                        map[i][j] = WALL;
                        break;
                    case 'O':
                        map[i][j] = HOLE;
                        break;
                    default:
                        throw new AssertionError("Unknown Input");
                }
            }
        }
        
        bw.write(Integer.toString(bfs(marbles))+"\n");
        

        bw.close();
        br.close();
    }

    static int bfs(Map<String, Pos> marbles){
        boolean[][][][] visited = new boolean[N][M][N][M];
        Queue<Map<String, Pos>> queue = new LinkedList<>();
        queue.add(marbles);
        while(!queue.isEmpty()){
            Map<String, Pos> first = queue.poll();
            Pos red = first.get("RED");
            Pos blue = first.get("BLUE");

            // System.out.printf("RED: (%d, %d), cnt: %d\n", red.y, red.x, red.cnt);
            // System.out.printf("BLUE: (%d, %d), cnt: %d\n", blue.y, blue.x, blue.cnt);
            // System.out.println();

            if(map[red.y][red.x] == HOLE){
                if(map[blue.y][blue.x] == HOLE)
                    continue;

                return red.cnt;
            }

            if(visited[red.y][red.x][blue.y][blue.x])
                continue;
            visited[red.y][red.x][blue.y][blue.x] = true;

            // UP
            if(red.compareY(blue) < 0){
                addNextMarbleToQueue(red, blue, true, UP, map, queue);
            }else{
                addNextMarbleToQueue(red, blue, false, UP, map, queue);
            }

            // RIGHT
            if(red.compareX(blue) > 0){
                addNextMarbleToQueue(red, blue, true, RIGHT, map, queue);
            }else{
                addNextMarbleToQueue(red, blue, false, RIGHT, map, queue);
            }

            // DOWN
            if(red.compareY(blue) > 0){
                addNextMarbleToQueue(red, blue, true, DOWN, map, queue);
            }else{
                addNextMarbleToQueue(red, blue, false, DOWN, map, queue);
            }

            // LEFT
            if(red.compareX(blue) < 0){
                addNextMarbleToQueue(red, blue, true, LEFT, map, queue);
            }else{
                addNextMarbleToQueue(red, blue, false, LEFT, map, queue);
            }
        }

        return -1;
    }

    static void addNextMarbleToQueue(Pos red, Pos blue, boolean isRedFirst, int dir, int[][] map, Queue<Map<String, Pos>> queue){
        // move red first
        if(isRedFirst){
            Pos nextRed = tilt(red,dir,map);
            Pos nextBlue = afterTilt(blue,dir,map,nextRed);
            Map<String, Pos> tmp = new HashMap<>();
            tmp.put("RED", nextRed);
            tmp.put("BLUE", nextBlue);
            queue.add(tmp);
        // move blue first
        }else{
            Pos nextBlue = tilt(blue,dir,map);
            Pos nextRed = afterTilt(red,dir,map,nextBlue);
            Map<String, Pos> tmp = new HashMap<>();
            tmp.put("RED", nextRed);
            tmp.put("BLUE", nextBlue);
            queue.add(tmp);
        }
    }

    static Pos afterTilt(Pos marble, int dir, int[][] map, Pos before){
        if(map[before.y][before.x] != HOLE)
            map[before.y][before.x] = MARBLE;

        Pos ret = tilt(marble, dir, map);

        if(map[before.y][before.x] != HOLE)
            map[before.y][before.x] = EMPTY;

        return ret;
    }

    static Pos tilt(Pos marble, int dir, int[][] map){
        int y = marble.y;
        int x = marble.x;

        for(;y<N && y >=0 && x<M && x >= 0; y+=dy[dir], x+=dx[dir]){
            if(map[y][x] == HOLE)
                break;

            if(map[y][x] != EMPTY){
                y-=dy[dir]; 
                x-=dx[dir];
                break;
            }
        }

        return new Pos(y,x, marble.cnt+1);
    }
}

class Pos {
    public final int y;
    public final int x;
    public final int cnt;

    public Pos(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    public int compareY(Pos that){
        return Integer.compare(this.y, that.y);
    }

    public int compareX(Pos that){
        return Integer.compare(this.x, that.x);
    }
}