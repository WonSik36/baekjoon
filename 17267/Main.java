/*
    baekjoon online judge
    problem number 17267
    https://www.acmicpc.net/problem/17267

    Breadth First Search
    I firstly thought this problem is about DFS & backtracking
    But it has L&R constraint, so BFS is right answer

    reference:
    https://www.acmicpc.net/board/view/41777
    https://www.acmicpc.net/board/view/37868
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
    static int N;
    static int M;
    static boolean[][] map;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        
        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        Pos start = null;
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<M;j++){
                if(str.charAt(j) == '0')
                    map[i][j] = true;
                else if(str.charAt(j) == '1')
                    map[i][j] = false;
                else{
                    map[i][j] = true;
                    start = new Pos(i,j,L,R);
                }
            }
        }

        int result = bfs(start);

        bw.write(Integer.toString(result)+"\n");
        bw.close();
        br.close();
    }

    static int bfs(Pos start){
        int cnt = 0;
        boolean[][] visited = new boolean[N][M];
        Queue<Pos> queue = new LinkedList<Pos>();
        queue.add(start);

        while(!queue.isEmpty()){
            Pos curPos = queue.poll();
            
            if(visited[curPos.y][curPos.x])
                continue;
            
            visited[curPos.y][curPos.x] = true;
            cnt++;


            for(int y = curPos.y+1; y<=N-1 && map[y][curPos.x]; y++){
                queue.add(new Pos(y, curPos.x, curPos.L, curPos.R));
            }

            for(int y = curPos.y-1; y>=0 && map[y][curPos.x]; y--){
                queue.add(new Pos(y, curPos.x, curPos.L, curPos.R));
            }

            if(curPos.L > 0 && curPos.x>0 && map[curPos.y][curPos.x-1])
                queue.add(new Pos(curPos.y, curPos.x-1, curPos.L-1, curPos.R));

            if(curPos.R > 0 && curPos.x<M-1 && map[curPos.y][curPos.x+1])
                queue.add(new Pos(curPos.y, curPos.x+1, curPos.L, curPos.R-1));
        }

        return cnt;
    }
}

class Pos{
    public int y;
    public int x;
    public int L;
    public int R;

    public Pos(int y, int x, int L, int R){
        this.y = y;
        this.x = x;
        this.L = L;
        this.R = R;
    }
}