/*
    baekjoon online judge
    problem number 5427
    https://www.acmicpc.net/problem/5427

    HARD BFS Problem

    high level reference:
    https://ssu-gongdoli.tistory.com/15

    I need to use 2 queues to solve bfs problem.
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static final int WALL = 0;
    static final int FIRE = 1;
    static final int EMPTY = 2;

    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            calculate(br,bw);
        }

        bw.close();
        br.close();
    }

    public static void calculate(BufferedReader br, BufferedWriter bw)throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str;

        int cols = Integer.parseInt(st.nextToken());
        int rows = Integer.parseInt(st.nextToken());
        int[][] map = new int[rows][cols];
        Position pos = null;
        Queue<Position> fires = new LinkedList<Position>();

        for(int i=0;i<rows;i++){
            str = br.readLine();
            for(int j=0;j<cols;j++){
                switch(str.charAt(j)){
                    case '#':
                        map[i][j] = WALL;
                        break;
                    case '*':
                        map[i][j] = FIRE;
                        fires.add(new Position(i,j,0));
                        break;
                    case '.':
                        map[i][j] = EMPTY;
                        break;
                    case '@':
                        map[i][j] = EMPTY;
                        pos = new Position(i, j, 0);
                        break;
                }
            }
        }

        int res = bfs(pos, fires, map);
        if(res != -1)
            bw.write(Integer.toString(res));
        else
            bw.write("IMPOSSIBLE");
        bw.write("\n");
    }

    public static int bfs(Position pos, Queue<Position> fires, int[][] map){
        Queue<Position> menQ = new LinkedList<Position>();
        Queue<Position> fireQ = fires;
        boolean[][] menVisited = new boolean[map.length][map[0].length];
        boolean[][] fireVisited = new boolean[map.length][map[0].length];
        
        menQ.add(pos);

        int count = 1;
        Position tmp = null;
        while(true){
            while(!fireQ.isEmpty()){
                tmp = fireQ.peek();
                if(tmp.cnt == count)
                    break;
                tmp = fireQ.poll();
                int y = tmp.y;
                int x = tmp.x;
                int cnt = tmp.cnt;

                if(fireVisited[y][x])
                    continue;
                fireVisited[y][x] = true;

                if(y>0 && isValidPos2Fire(y-1,x,map)){
                    fireQ.add(new Position(y-1,x,cnt+1));
                    map[y-1][x] = FIRE;
                }
                if(y<map.length-1 && isValidPos2Fire(y+1,x,map)){
                    fireQ.add(new Position(y+1,x,cnt+1));
                    map[y+1][x] = FIRE;
                }
                if(x>0 && isValidPos2Fire(y,x-1,map)){
                    fireQ.add(new Position(y,x-1,cnt+1));
                    map[y][x-1] = FIRE;
                }
                if(x<map[0].length-1 && isValidPos2Fire(y,x+1,map)){
                    fireQ.add(new Position(y,x+1,cnt+1));
                    map[y][x+1] = FIRE;
                }
            }

            while(!menQ.isEmpty()){
                tmp = menQ.peek();
                if(tmp.cnt == count)
                    break;
                tmp = menQ.poll();
                int y = tmp.y;
                int x = tmp.x;
                int cnt = tmp.cnt;

                if(exit(tmp, map))
                    return cnt+1;

                if(menVisited[y][x])
                    continue;
                menVisited[y][x] = true;

                if(y>0 && isValidPos2Men(y-1,x,map))
                    menQ.add(new Position(y-1,x,cnt+1));
                if(y<map.length-1 && isValidPos2Men(y+1,x,map))
                    menQ.add(new Position(y+1,x,cnt+1));
                if(x>0 && isValidPos2Men(y,x-1,map))
                    menQ.add(new Position(y,x-1,cnt+1));
                if(x<map[0].length-1 && isValidPos2Men(y,x+1,map))
                    menQ.add(new Position(y,x+1,cnt+1));
            }

            if(menQ.isEmpty())
                break;

            count++;
        }

        return -1;
    }

    public static boolean isValidPos2Fire(int y, int x, int[][] map){
        if(map[y][x] == WALL)
            return false;
        else
            return true;
    }

    public static boolean isValidPos2Men(int y, int x, int[][] map){
        if(map[y][x] == EMPTY)
            return true;
        else
            return false;
    }

    private static boolean exit(Position pos, int[][] map){
        if(pos.x==0 || pos.x==map[0].length-1)
            return true;
        else if(pos.y==0 || pos.y==map.length-1){
            return true;
        }else{
            return false;
        }
    }
}

class Position{
    public int y;
    public int x;
    public int cnt;

    public Position(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}