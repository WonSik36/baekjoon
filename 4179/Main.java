/*
    baekjoon online judge
    problem number 4179
    https://www.acmicpc.net/problem/4179

    Breadth First Search

    Reference: https://ssu-gongdoli.tistory.com/15?category=831553
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
import java.lang.StringBuilder;

public class Main{
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int FIRE = 2;

    static final int[] dy = {0,1,0,-1};
    static final int[] dx = {1,0,-1,0};

    static int row;
    static int col;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        int[][] map = new int[row][col];
        List<Pos> listOfFires = new ArrayList<>();
        Pos start = null;
        for(int i=0;i<row;i++){
            String str = br.readLine();
            for(int j=0;j<col;j++){
                switch(str.charAt(j)){
                    case 'J':
                        start = new Pos(i,j,0);
                    case '.':
                        map[i][j] = EMPTY;
                        break;

                    case '#':
                        map[i][j] = WALL;
                        break;

                    case 'F':
                        map[i][j] = FIRE;
                        listOfFires.add(new Pos(i,j,0));
                        break;
                }
            }
        }
        
        int res = bfs(start, listOfFires, map);

        if(res == -1){
            bw.write("IMPOSSIBLE\n");
        }else{
            bw.write(Integer.toString(res+1)+"\n");
        }

        bw.close();
        br.close();
    }

    static int bfs(Pos start, List<Pos> listOfFires, int[][] map){
        boolean[][] visited = new boolean[row][col];
        Queue<Pos> men = new LinkedList<>();
        men.add(start);
        int cntOfMen = men.size();
        Queue<Pos> fires = new LinkedList<>(listOfFires);
        int cntOfFires = fires.size();

        while(cntOfMen > 0){
            int nextCntOfMen = 0;
            int nextCntOfFires = 0;

            for(int i=0;i<cntOfFires;i++){
                Pos fire = fires.poll();

                for(int j=0;j<4;j++){
                    int y = fire.y+dy[j];
                    int x = fire.x+dx[j];
                    if(y >= 0 && y < row && x >= 0 && x < col && map[y][x] == EMPTY){
                        map[y][x] = FIRE;
                        fires.add(new Pos(y,x,0));
                        nextCntOfFires++;
                    }
                }
            }

            for(int i=0;i<cntOfMen;i++){
                Pos man = men.poll();

                if(man.y == 0 || man.y == row-1 || man.x == 0 || man.x == col-1)
                    return man.cnt;

                if(visited[man.y][man.x])
                    continue;
                visited[man.y][man.x] = true;

                for(int j=0;j<4;j++){
                    int y = man.y+dy[j];
                    int x = man.x+dx[j];
                    if(y >= 0 && y < row && x >= 0 && x < col && map[y][x] == EMPTY){
                        map[y][x] = FIRE;
                        men.add(new Pos(y,x,man.cnt+1));
                        nextCntOfMen++;
                    }
                }
            }

            cntOfFires = nextCntOfFires;
            cntOfMen = nextCntOfMen;
        }

        return -1;
    }

    static void printMap(int[][] map){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(map[i][j] == EMPTY)
                    System.out.print(".");
                else if(map[i][j] == WALL)
                    System.out.print("#");
                else
                    System.out.print("F");
            }
            System.out.println();
        }
    }
}

class Pos {
    public int y;
    public int x;
    public int cnt;

    public Pos(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("cnt: ");
        sb.append(cnt);
        sb.append(", y: ");
        sb.append(y);
        sb.append(", x: ");
        sb.append(x);

        return sb.toString();
    }
}