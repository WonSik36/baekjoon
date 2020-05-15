/*
    baekjoon online judge
    problem number 15681
    https://www.acmicpc.net/problem/15681

    Tree with Dynamic Programming
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        List<List<Integer>> g = new ArrayList<List<Integer>>();
        for(int i=0;i<=N;i++){
            g.add(new ArrayList<Integer>());
        }

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            g.get(u).add(v);
            g.get(v).add(u);
        }

        int[] memo = new int[N+1];
        calcNumberOfSubNode(R, R, g, memo);

        for(int i=0;i<Q;i++){
            int node = Integer.parseInt(br.readLine());

            bw.write(Integer.toString(memo[node]));
            bw.write("\n");
        }


        bw.close();
        br.close();
    }

    static int calcNumberOfSubNode(int node, int parent, List<List<Integer>> g, int[] memo){
        memo[node] = 1;

        Iterator<Integer> it = g.get(node).iterator();
        while(it.hasNext()){
            int child = it.next();

            if(child == parent)
                continue;

            memo[node] += calcNumberOfSubNode(child, node, g, memo);
        }

        return memo[node];
    }
}
