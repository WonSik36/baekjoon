/*
    baekjoon online judge
    problem number 16928
    https://www.acmicpc.net/problem/16928

    Breadth First Search
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> ladders = new HashMap<>();
        Map<Integer, Integer> snakes = new HashMap<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            ladders.put(start,end);
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            snakes.put(start,end);
        }

        
        Queue<Pos> queue = new ArrayDeque<>();
        queue.add(new Pos(1,0));
        boolean[] visited = new boolean[101];

        int res = 0;
        while(!queue.isEmpty()){
            Pos first = queue.poll();

            if(first.x == 100){
                res = first.cnt;
                break;
            }
                
            if(first.x > 100 || visited[first.x])
                continue;
            visited[first.x] = true;

            for(int i=1;i<=6;i++){
                int x = first.x+i;

                if(ladders.containsKey(x))
                    x = ladders.get(x);
                
                if(snakes.containsKey(x))
                    x = snakes.get(x);

                queue.add(new Pos(x, first.cnt+1));
            }
        }

        bw.write(Integer.toString(res)+"\n");

        bw.close();
        br.close();
    }
}

class Pos {
    public int x;
    public int cnt;

    public Pos(int x, int cnt){
        this.x = x;
        this.cnt = cnt;
    }
}