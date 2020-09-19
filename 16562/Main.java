/*
    baekjoon online judge
    problem number 16562
    https://www.acmicpc.net/problem/

    Depth First Search
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] money = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<List<Integer>> g = IntStream.range(0, N).mapToObj(x -> new ArrayList<Integer>()).collect(toList());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            g.get(start).add(end);
            g.get(end).add(start);
        }

        int sum = 0;
        boolean[] visited = new boolean[N];
        for(int i=0;i<N;i++){
            sum += dfs(i, visited, g, money);
        }

        if(sum > K){
            bw.write("Oh no\n");
        }else{
            bw.write(Integer.toString(sum)+"\n");
        }

        bw.close();
        br.close();
    }

    static int dfs(int cur, boolean[] visited, List<List<Integer>> g, int[] money){
        if(visited[cur])
            return 0;
        visited[cur] = true;
        
        int min = money[cur];
        List<Integer> adj = g.get(cur);
        for(int n: adj){
            int res = Math.min(min, dfs(n, visited, g, money));
            min = res == 0 ? min : (res < min ? res : min);
        }

        return min;
    }
}
