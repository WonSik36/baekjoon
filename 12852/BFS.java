/*
    baekjoon online judge
    problem number 12852
    https://www.acmicpc.net/problem/12852

    Breadth First Search

    Time Comparison DP vs BFS
    DP:     152 ms, 21884 KB
    BFS:    88  ms, 14612 KB
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.List;

public class Main{
    static List<Integer> path;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        
        int cnt = bfs(N);
        bw.write(Integer.toString(cnt)+"\n");

        for(int i=0;i<path.size();i++){
            bw.write(Integer.toString(path.get(i)));
            bw.write(" ");
        }
        bw.write("\n");

        bw.close();
        br.close();
    }

    static int bfs(int N){
        boolean[] visited = new boolean[N+1];
        Queue<Pair> queue = new LinkedList<Pair>();
        queue.add(new Pair(N, 0));

        while(!queue.isEmpty()){
            Pair first = queue.poll();

            if(first.num == 1){
                path = first.memo;
                return first.cnt;
            }

            if(visited[first.num])
                continue;
            visited[first.num] = true;


            if(first.num % 3 == 0){
                queue.add(new Pair(first.num/3, first.cnt+1, first.memo));
            }
            if(first.num % 2 == 0){
                queue.add(new Pair(first.num/2, first.cnt+1, first.memo));
            }
            queue.add(new Pair(first.num-1, first.cnt+1, first.memo));
        }

        return -1;
    }
}

class Pair {
    public int num;
    public int cnt;
    public List<Integer> memo;

    public Pair(int num, int cnt){
        this.num = num;
        this.cnt = cnt;
        memo = new ArrayList<Integer>();
        memo.add(num);
    }

    public Pair(int num, int cnt, List<Integer> list){
        this.num = num;
        this.cnt = cnt;
        memo = new ArrayList<Integer>(list);
        memo.add(num);
    }
}