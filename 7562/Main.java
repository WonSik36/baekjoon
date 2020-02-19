/*
    baekjoon online judge
    problem number 7562
    https://www.acmicpc.net/problem/7562

    BFS Problem
    Using array and for-loop to move knight to other position
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
    static final int[] moveY = {1,2,2,1,-1,-2,-2,-1};
    static final int[] moveX = {2,1,-1,-2,-2,-1,1,2};
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            int M = Integer.parseInt(br.readLine());
            
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point start = new Point(y,x,0);
            
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            Point dest = new Point(y,x,0);

            int result = bfs(start,dest,M);
            bw.write(Integer.toString(result));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    private static int bfs(Point start, Point dest, int M){
        boolean[][] visited = new boolean[M][M];
        Queue<Point> q = new LinkedList<Point>();
        q.add(start);

        while(!q.isEmpty()){
            Point first = q.poll();

            if(first.equals(dest))
                return first.cnt;
            
            if(!isValidPoint(first,M))
                continue;

            if(visited[first.y][first.x])
                continue;
            visited[first.y][first.x] = true;

            for(int i=0;i<moveX.length;i++)
                q.add(new Point(first.y+moveY[i],first.x+moveX[i],first.cnt+1));
        }

        return -1;
    }

    private static boolean isValidPoint(Point p, int M){
        if(p.x < 0 || p.x >= M)
            return false;
        else if(p.y < 0 || p.y >= M)
            return false;
        else
            return true;
    }
}

class Point{
    public int y;
    public int x;
    public int cnt;

    public Point(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    @Override
    public boolean equals(Object obj){
        Point that = (Point)obj;

        if(this.y == that.y && this.x == that.x)
            return true;
        else
            return false;
    }
}