/*
    baekjoon online judge
    problem number 1766
    https://www.acmicpc.net/problem/1766

    Topological Sort
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> g = new ArrayList<>();
        for(int i=0;i<N;i++){
            g.add(new ArrayList<>());
        }

        int[] numOfParent = new int[N];

        for(int i=0;i<M;i++){
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)-1).toArray();

            g.get(arr[0]).add(arr[1]);
            numOfParent[arr[1]]++;
        }

        List<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            if(numOfParent[i] == 0)
                pq.add(i);
        }

        while(!pq.isEmpty()){
            int root = pq.poll();

            res.add(root);

            List<Integer> adj = g.get(root);
            for(int node : adj){
                numOfParent[node]--;
                if(numOfParent[node] == 0){
                    pq.add(node);
                }
            }
        }

        for(int num : res){
            bw.write(Integer.toString(num+1));
            bw.write(" ");
        }
        bw.write("\n");

        bw.close();
        br.close();
    }
}
