/*
    baekjoon online judge
    problem number 17141
    https://www.acmicpc.net/problem/17141

    Breadth First Search
    Bruth Force
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
import java.util.ArrayDeque;
import java.lang.StringBuilder;

public class Main{
    static final int EMPTY = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;

    static final int[] dy = {0,-1,0,1};
    static final int[] dx = {1,0,-1,0};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Pos> viruses = new ArrayList<>();
        int[][] map = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int e = Integer.parseInt(st.nextToken());

                switch(e){
                    case VIRUS:
                        viruses.add(new Pos(i,j,0));
                    case EMPTY:
                        map[i][j] = EMPTY;
                        break;
                    case WALL:
                        map[i][j] = WALL;
                        break;
                    default:
                        throw new AssertionError("Unknown Input");
                }
            }
        }

        int min = solve(map, viruses, M);

        bw.write(Integer.toString(min)+"\n");

        bw.close();
        br.close();
    }

    static int solve(int[][] map, List<Pos> viruses, int M){
        List<List<Pos>> allSelectedCase = select(M, viruses);

        int min = -1;
        for(List<Pos> oneCase: allSelectedCase){
            int cnt = countGivenCase(map, oneCase);
            min = cnt == -1 ? min : (min == -1 ? cnt : Math.min(min, cnt));
        }

        return min;
    }

    static List<List<Pos>> select(int M, List<Pos> viruses){
        List<List<Pos>> cases = new ArrayList<>();
        List<Pos> curCase = new ArrayList<>();
        select(0, M, curCase, cases, viruses);

        return cases;
    }

    static void select(int cur, final int cnt, List<Pos> curCase, List<List<Pos>> cases, List<Pos> viruses){
        if(curCase.size() == cnt){
            cases.add(new ArrayList<>(curCase));
            return;
        }

        if(cur >= viruses.size())
            return;

        curCase.add(viruses.get(cur));
        select(cur+1, cnt, curCase, cases, viruses);
        curCase.remove(curCase.size()-1);

        select(cur+1, cnt, curCase, cases, viruses);
    }

    static int countGivenCase(int[][] map, List<Pos> givenCase){
        int N = map.length;
        int[][] clone = cloneMap(map);
        Queue<Pos> queue = new ArrayDeque<>();
        queue.addAll(givenCase);

        int cnt = 0;
        while(!queue.isEmpty()){
            Pos first = queue.poll();

            if(clone[first.y][first.x] != EMPTY)
                continue;
            clone[first.y][first.x] = VIRUS;

            cnt = Math.max(cnt, first.cnt);

            for(int i=0;i<4;i++){
                if(first.y+dy[i] >= 0 && first.y+dy[i] < N && first.x+dx[i] >= 0 && first.x+dx[i] < N){
                    int newY = first.y+dy[i];
                    int newX = first.x+dx[i];

                    if(clone[newY][newX] == EMPTY){
                        queue.add(new Pos(newY, newX, first.cnt+1));
                    }
                }
            }
        }

        if(validMap(clone))
            return cnt;
        else
            return -1;
    }

    static int[][] cloneMap(int[][] map){
        int N = map.length;
        int[][] clone = new int[N][N];

        for(int i=0;i<N;i++){
            System.arraycopy(map[i], 0, clone[i], 0, N);
        }

        return clone;
    }

    static boolean validMap(int[][] map){
        int N = map.length;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] == EMPTY)
                    return false;
            }
        }

        return true;
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

        sb.append("y: ");
        sb.append(y);
        sb.append(", x: ");
        sb.append(x);
        sb.append(", cnt: ");
        sb.append(cnt);

        return sb.toString();
    }
}