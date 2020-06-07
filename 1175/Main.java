/*
    baekjoon online judge
    problem number 1175
    https://www.acmicpc.net/problem/1175

    Application of BFS by using various status of visited
    reference: https://bowbowbow.tistory.com/7
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.StringBuilder;

public class Main{
    static int ROW;
    static int COL;
    
    static boolean[][] map;
    static final boolean EMPTY = false;
    static final boolean WALL = true;

    static Pos start = null;
    static Pos end1 = null;
    static Pos end2 = null;

    static boolean[][][][][] visited;   // y, x, dir, end1, end2

    static int[] Y = new int[]{0, -1, 0, 1};
    static int[] X = new int[]{1, 0, -1, 0};

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        ROW = Integer.parseInt(st.nextToken());
        COL = Integer.parseInt(st.nextToken());
        map = new boolean[ROW][COL];

        for(int i=0;i<ROW;i++){
            String line = br.readLine();
            for(int j=0;j<COL;j++){
                switch(line.charAt(j)){
                case 'S':
                    start = new Pos(i,j);
                    map[i][j] = EMPTY;
                    break;

                case 'C':
                    if(end1 == null){
                        end1 = new Pos(i,j);
                    }else{
                        end2 = new Pos(i,j);
                    }
                    map[i][j] = EMPTY;
                    break;

                case '.':
                    map[i][j] = EMPTY;
                    break;
                    
                case '#':
                    map[i][j] = WALL;
                    break;

                default:
                    throw new AssertionError("Unknown Input Value");
                }
            }
        }

        int min = bfs();
        bw.write(Integer.toString(min)+"\n");

        bw.close();
        br.close();
    }

    static int bfs(){
        visited = new boolean[ROW][COL][4][2][2];
        Queue<Pos> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            Pos last = queue.poll();
            // System.out.println(last);

            int visitFirst = last.visitFirst;
            int visitSecond = last.visitSecond;
            if(last.equals(end1))
                visitFirst = 1;
            if(last.equals(end2))
                visitSecond = 1;

            if(visitFirst==1 && visitSecond==1)
                return last.cnt;

            if(last.dir != -1){
                if(visited[last.y][last.x][last.dir][visitFirst][visitSecond]){
                    // System.out.println("visited before");
                        continue;
                }

                visited[last.y][last.x][last.dir][visitFirst][visitSecond] = true;
            }

            for(int dir=0;dir<4;dir++){
                if(last.dir == dir)
                    continue;

                if(last.y + Y[dir] >= 0 && last.y + Y[dir] < ROW && last.x + X[dir] >= 0 && last.x + X[dir] < COL){
                    if(map[last.y + Y[dir]][last.x + X[dir]] == WALL)
                        continue;

                    Pos tmp = new Pos(last.y + Y[dir], last.x + X[dir], dir, last.cnt+1, visitFirst, visitSecond);
                    // System.out.println("push "+tmp);
                    queue.add(tmp);
                }
            }
        }

        return -1;
    }
}

class Pos {
    public int y;
    public int x;
    public int dir;
    public int cnt;
    public int visitFirst;
    public int visitSecond;

    public Pos(int y, int x){
        this(y,x,-1,0,0,0);
    }

    public Pos(int y, int x, int dir, int cnt, int visitFirst, int visitSecond){
        this.y = y;
        this.x = x;
        this.dir = dir;
        this.cnt = cnt;
        this.visitFirst = visitFirst;
        this.visitSecond = visitSecond;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Pos))
            return false;

        Pos that = (Pos)obj;
        if(this.y == that.y && this.x == that.x)
            return true;
        else
            return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("y: ");
        sb.append(y);
        sb.append(", x: ");
        sb.append(x);
        sb.append(", dir: ");
        sb.append(dir);
        sb.append(", cnt: ");
        sb.append(cnt);
        sb.append(", visitFirst: ");
        sb.append(visitFirst);
        sb.append(", visitSecond: ");
        sb.append(visitSecond);

        return sb.toString();
    }
}