/*
    baekjoon online judge
    problem number 3055
    https://www.acmicpc.net/problem/3055

    HARD BFS Problem

    This problem is same with 5427
    I need to use 2 queues to solve bfs problem.
    https://ssu-gongdoli.tistory.com/15
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
    static final int ROCK = 0;
    static final int WATER = 1;
    static final int EMPTY = 2;
    static final int EXIT = 3;

    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        calculate(br,bw);

        bw.close();
        br.close();
    }

    public static void calculate(BufferedReader br, BufferedWriter bw)throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str;

        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());
        int[][] map = new int[rows][cols];
        Position pos = null;
        Queue<Position> waters = new LinkedList<Position>();

        for(int i=0;i<rows;i++){
            str = br.readLine();
            for(int j=0;j<cols;j++){
                switch(str.charAt(j)){
                    case 'X':
                        map[i][j] = ROCK;
                        break;
                    case '*':
                        map[i][j] = WATER;
                        waters.add(new Position(i,j,0));
                        break;
                    case '.':
                        map[i][j] = EMPTY;
                        break;
                    case 'S':
                        map[i][j] = EMPTY;
                        pos = new Position(i, j, 0);
                        break;
                    case 'D':
                        map[i][j] = EXIT;
                        break;
                }
            }
        }

        int res = bfs(pos, waters, map);
        if(res != -1)
            bw.write(Integer.toString(res));
        else
            bw.write("KAKTUS");
        bw.write("\n");
    }

    public static int bfs(Position pos, Queue<Position> waters, int[][] map){
        Queue<Position> hedgehogQ = new LinkedList<Position>();
        Queue<Position> waterQ = waters;
        boolean[][] hedgehogVisited = new boolean[map.length][map[0].length];
        boolean[][] waterVisited = new boolean[map.length][map[0].length];
        
        hedgehogQ.add(pos);

        int count = 1;
        Position tmp = null;
        while(true){
            // move water
            while(!waterQ.isEmpty()){
                tmp = waterQ.peek();
                if(tmp.cnt == count)
                    break;
                tmp = waterQ.poll();
                int y = tmp.y;
                int x = tmp.x;
                int cnt = tmp.cnt;

                if(waterVisited[y][x])
                    continue;
                waterVisited[y][x] = true;

                if(y>0 && isValidPos2Water(y-1,x,map)){
                    waterQ.add(new Position(y-1,x,cnt+1));
                    map[y-1][x] = WATER;
                }
                if(y<map.length-1 && isValidPos2Water(y+1,x,map)){
                    waterQ.add(new Position(y+1,x,cnt+1));
                    map[y+1][x] = WATER;
                }
                if(x>0 && isValidPos2Water(y,x-1,map)){
                    waterQ.add(new Position(y,x-1,cnt+1));
                    map[y][x-1] = WATER;
                }
                if(x<map[0].length-1 && isValidPos2Water(y,x+1,map)){
                    waterQ.add(new Position(y,x+1,cnt+1));
                    map[y][x+1] = WATER;
                }
            }

            // move hedgehog
            while(!hedgehogQ.isEmpty()){
                tmp = hedgehogQ.peek();
                if(tmp.cnt == count)
                    break;
                tmp = hedgehogQ.poll();
                int y = tmp.y;
                int x = tmp.x;
                int cnt = tmp.cnt;

                if(exit(tmp, map))
                    return cnt;

                if(hedgehogVisited[y][x])
                    continue;
                hedgehogVisited[y][x] = true;

                if(y>0 && isValidPos2hedgehog(y-1,x,map))
                    hedgehogQ.add(new Position(y-1,x,cnt+1));
                if(y<map.length-1 && isValidPos2hedgehog(y+1,x,map))
                    hedgehogQ.add(new Position(y+1,x,cnt+1));
                if(x>0 && isValidPos2hedgehog(y,x-1,map))
                    hedgehogQ.add(new Position(y,x-1,cnt+1));
                if(x<map[0].length-1 && isValidPos2hedgehog(y,x+1,map))
                    hedgehogQ.add(new Position(y,x+1,cnt+1));
            }

            if(hedgehogQ.isEmpty())
                break;

            count++;
        }

        return -1;
    }

    public static boolean isValidPos2Water(int y, int x, int[][] map){
        if(map[y][x] == EMPTY)
            return true;
        else
            return false;
    }

    public static boolean isValidPos2hedgehog(int y, int x, int[][] map){
        if(map[y][x] == EMPTY || map[y][x] == EXIT)
            return true;
        else
            return false;
    }

    private static boolean exit(Position pos, int[][] map){
        if(map[pos.y][pos.x] == EXIT)
            return true;
        else
            return false;
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