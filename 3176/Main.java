/*
    baekjoon online judge
    problem number 3176
    https://www.acmicpc.net/problem/3176

    LCA Algorithm
    reference: https://www.crocus.co.kr/672
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.lang.StringBuilder;

public class Main{
    static final int MAX_NODE = 100000;
    static final int MAX_IDX = baseLog(MAX_NODE, 2);

    static int N;
    static int M;

    static List<List<Node>> g;
    static int[][] memo;
    static int[] level;
    static int[][] maxEdge;
    static int[][] minEdge;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        g = new ArrayList<>();
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            g.add(new ArrayList<>());
        }
        memo = new int[MAX_IDX][N];
        level = new int[N];
        maxEdge = new int[MAX_IDX][N];
        minEdge = new int[MAX_IDX][N];
        initTable();

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            g.get(start).add(new Node(end, weight, -1));
            g.get(end).add(new Node(start, weight, -1));
        }

        dfs(0);
        updateTable();
        // printStatus();

        M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken()) - 1;
            int R = Integer.parseInt(st.nextToken()) - 1;

            Result res = query(L, R);
            bw.write(res.toString());
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void initTable(){
        Arrays.fill(level, -1);
        for(int i=0;i<MAX_IDX;i++){
            Arrays.fill(memo[i], -1);
            Arrays.fill(maxEdge[i], 0);
            Arrays.fill(minEdge[i], Integer.MAX_VALUE);
        }
    }

    static void dfs(int root){
        Stack<Node> st = new Stack<>();
        st.add(new Node(root, 0, 0));

        while(!st.isEmpty()){
            Node last = st.pop();
            
            if(level[last.num] != -1)
                continue;
            level[last.num] = last.level;
            
            List<Node> adj = g.get(last.num);
            for(Node n : adj){
                if(level[n.num] != -1)
                    continue;

                memo[0][n.num] = last.num;
                maxEdge[0][n.num] = n.weight;
                minEdge[0][n.num] = n.weight;

                st.add(new Node(n.num, -1, last.level+1));
            }
        }
    }

    static void updateTable(){
        for(int i=0;i<MAX_IDX-1;i++){
            for(int j=0;j<N;j++){
                if(memo[i][j] != -1){
                    memo[i+1][j] = memo[i][memo[i][j]];
                    maxEdge[i+1][j] = Math.max(maxEdge[i][j], maxEdge[i][memo[i][j]]);
                    minEdge[i+1][j] = Math.min(minEdge[i][j], minEdge[i][memo[i][j]]);
                }
            }
        }
    }

    static Result query(int a, int b){
        Result res = new Result(0, Integer.MAX_VALUE);

        if(level[a] > level[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }

        for(int gap = level[b] - level[a], idx=0; gap > 0; idx++, gap /= 2){
            if(gap % 2 == 1){
                res.updateMaxEdge(maxEdge[idx][b]);
                res.updateMinEdge(minEdge[idx][b]);
                b = memo[idx][b];
            }
        }

        if(a != b){
            for(int idx=MAX_IDX-1; idx >= 0; idx--){
                if(memo[idx][a] != -1 && memo[idx][a] != memo[idx][b]){
                    res.updateMaxEdge(Math.max(maxEdge[idx][a], maxEdge[idx][b]));
                    res.updateMinEdge(Math.min(minEdge[idx][a], minEdge[idx][b]));

                    a = memo[idx][a];
                    b = memo[idx][b];
                }
            }

            res.updateMaxEdge(Math.max(maxEdge[0][a], maxEdge[0][b]));
            res.updateMinEdge(Math.min(minEdge[0][a], minEdge[0][b]));
            a = memo[0][a];     // lca
        }

        return res;
    }

    static int baseLog(int nodeNum, int base){
        return (int)Math.ceil(Math.log(nodeNum) / Math.log(base));
    }

    static void printStatus(){
        System.out.println("level");
        printArr(level);
        System.out.println("memo");
        print2DArr(memo);
        System.out.println("maxEdge");
        print2DArr(maxEdge);
        System.out.println("minEdge");
        print2DArr(minEdge);
    }

    static void print2DArr(int[][] arr){
        for(int[] a : arr){
            printArr(a);
        }
    }

    static void printArr(int[] arr){
        for(int a : arr){
            System.out.printf("%d ", a);
        }
        System.out.println();
    }
}

class Node {
    public int num;
    public int weight;
    public int level;

    public Node(int num, int weight, int level){
        this.num = num;
        this.weight = weight;
        this.level = level;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("num: ");
        sb.append(num);
        sb.append(", weight: ");
        sb.append(weight);
        sb.append(", level: ");
        sb.append(level);

        return sb.toString();
    }
}

class Result {
    public int maxEdge;
    public int minEdge;

    public Result(int maxEdge, int minEdge){
        this.maxEdge = maxEdge;
        this.minEdge = minEdge;
    }

    public void updateMaxEdge(int maxEdge){
        this.maxEdge = Math.max(this.maxEdge, maxEdge);
    }

    public void updateMinEdge(int minEdge){
        this.minEdge = Math.min(this.minEdge, minEdge);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(minEdge);
        sb.append(" ");
        sb.append(maxEdge);

        return sb.toString();
    }
}