/*
    baekjoon online judge
    problem number 17370
    https://www.acmicpc.net/problem/17370

    Backtracking
    reference: https://footprint-of-nawin.tistory.com/20
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static int[] dX = {1,0,-1,-1,0,1};
    static int[] dY = {1,2,1,-1,-2,-1};

    static int N;
    static int COUNT;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        N++;

        long res = backtrack();
        bw.write(Long.toString(res)+"\n");

        bw.close();
        br.close();
    }

    static long backtrack(){
        COUNT = 0;
        boolean[][] visited = new boolean[100][100];
        int x = 50, y = 50;

        visited[y][x] = true;
        backtrack(y+dY[1], x+dX[1], 1, 1, visited);
        visited[y][x] = false;

        return COUNT;
    }

    static void backtrack(int y, int x, int prev, int cnt, boolean[][] visited){
        // for(int i=0;i<cnt;i++){
        //     System.out.print(" ");
        // }
        // System.out.printf("y:%d, x:%d, prev:%d, cnt: %d\n", y,x,prev,cnt);
        if(cnt >= N){
            if(visited[y][x])
                COUNT++;

            return;
        }

        if(visited[y][x])
            return;

        visited[y][x] = true;

        int cur1 = (prev+1) % 6;
        backtrack(y+dY[cur1], x+dX[cur1], cur1, cnt+1, visited);

        int cur2 = (prev+5) % 6;
        backtrack(y+dY[cur2], x+dX[cur2], cur2, cnt+1, visited);

        visited[y][x] = false;
    }
}