/*
    baekjoon online judge
    problem number 2252
    https://www.acmicpc.net/problem/2252

    Topological Sort
    reference: https://gmlwjd9405.github.io/2018/08/27/algorithm-topological-sort.html
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

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> g = new ArrayList<>();
        for(int i=0;i<=N;i++){
            g.add(new ArrayList<>());
        }
        int[] cnt = new int[N+1];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            g.get(start).add(end);
            cnt[end]++;
        }

        Queue<Integer> order = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=N;i++){
            if(cnt[i] == 0)
                queue.add(i);
        }

        // bfs
        while(!queue.isEmpty()){
            int first = queue.poll();

            order.add(first);

            List<Integer> adj = g.get(first);
            for(int node: adj){
                cnt[node]--;
                if(cnt[node] == 0)
                    queue.add(node);
            }
        }

        while(!order.isEmpty()){
            int first = order.poll();
            bw.write(Integer.toString(first));
            bw.write(" ");
        }
        bw.write("\n");

        bw.close();
        br.close();
    }
}
