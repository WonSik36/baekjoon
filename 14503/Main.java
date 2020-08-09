/*
    baekjoon online judge
    problem number 14503
    https://www.acmicpc.net/problem/14503

    Simulation
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = arr[0];
        int M = arr[1];

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int y = arr[0];
        int x = arr[1];
        int dir = arr[2];

        int[][] map = new int[N][];
        for(int i=0;i<N;i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        Robot robot = new Robot(y,x,dir,N,M);
        while(!robot.isDone()){
            robot.clean(map);
            robot.oper2(map);

            // printMap(map);
        }

        bw.write(Integer.toString(robot.getCount())+"\n");

        bw.close();
        br.close();
    }

    static void printMap(int[][] map){
        for(int[] arr : map){
            for(int a : arr){
                System.out.printf("%d ",a);
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }
}

class Robot {
    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int CLEANED = 2;

    private static final int NORTH = 0;
    private static final int EAST = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;

    private final int maxY;
    private final int maxX;
    
    private int y;
    private int x;
    private int dir;

    private int cnt;
    private boolean flag;

    public Robot(int y, int x, int dir, int maxY, int maxX){
        this.y = y;
        this.x = x;
        this.dir = dir;
        this.maxY = maxY;
        this.maxX = maxX;
        
        cnt = 0;
        flag = false;
    }

    public void clean(int[][] map){
        if(map[y][x] == EMPTY){
            map[y][x] = CLEANED;
            cnt++;
        }
    }

    public void oper2(int[][] map){
        for(int i=1;i<=4;i++){
            int dir = (this.dir+4-i) % 4;
            if(isEmpty(dir, map)){
                this.dir = dir;
                forward();
                // System.out.println("forward");
                return;
            }
        }

        int dir = (this.dir+2) % 4;
        if(isWall(dir, map)){
            this.flag = true;
            return;
        }

        // System.out.println("backward");
        backward();
    }

    private boolean isWall(int dir, int[][] map){
        return isSomething(dir, map, WALL);
    }

    private boolean isEmpty(int dir, int[][] map){
        return isSomething(dir, map, EMPTY);
    }

    private boolean isSomething(int dir, int[][] map, int something){
        int y = this.y;
        int x = this.x;
        switch(dir){
            case NORTH:
                y--;
                break;
            case SOUTH:
                y++;
                break;
            case EAST:
                x++;
                break;
            case WEST:
                x--;
                break;
            default:
                throw new AssertionError("Unknown Direction");
        }

        if(y > maxY-1 || y < 0)
            return false;

        if(x > maxX-1 || x < 0)
            return false;

        if(map[y][x] == something)
            return true;
        else
            return false;
    }

    public boolean isDone(){
        return this.flag;
    }

    public int getCount(){
        return cnt;
    }

    public void forward(){
        move(1, this.dir);
    }

    public void backward(){
        move(-1, this.dir);
    }

    private void move(int v, int dir){
        switch(dir){
            case NORTH:
                this.y -= v;
                break;
            case SOUTH:
                this.y += v;
                break;
            case EAST:
                this.x += v;
                break;
            case WEST:
                this.x -= v;
                break;
            default:
                throw new AssertionError("Unknown Direction");
        }
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