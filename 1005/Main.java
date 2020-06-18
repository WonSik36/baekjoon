/*
    baekjoon online judge
    problem number 1005
    https://www.acmicpc.net/problem/1005

    Dynamic Programming
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main{
    static int N;
    static int K;
    static int W;
    static int[] arr;
    static List<List<Integer>> g;
    static int[] memo;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            g = new ArrayList<>();
            for(int j=0;j<N;j++){
                g.add(new ArrayList<>());
            }

            memo = new int[N];
            Arrays.fill(memo, -1);

            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for(int j=0;j<K;j++){
                int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e)-1).toArray();
                
                g.get(edge[1]).add(edge[0]);
            }

            W = Integer.parseInt(br.readLine())-1;

            int res = dp(W);
            bw.write(Integer.toString(res));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    static int dp(int target){
        if(memo[target] != -1)
            return memo[target];

        int max = 0;

        List<Integer> adj = g.get(target);
        for(int num : adj){
            max = Math.max(max, dp(num));
        }

        memo[target] = max + arr[target];
        return memo[target];
    }

    static void printState(){
        System.out.println("----------");
        System.out.printf("N: %d, K: %d, W: %d\n", N,K,W);
        printArr(arr);
        printGraph(g);
        System.out.println("----------");
    }

    static void printArr(int[] arr){
        for(int a : arr){
            System.out.printf("%d ", a);
        }
        System.out.println();
    }

    static void printGraph(List<List<Integer>> g){
        for(int i=0;i<g.size();i++){
            System.out.printf("adj %d: ", i);
            for(int num: g.get(i)){
                System.out.printf("%d ", num);
            }
            System.out.println();
        }
    }
}
