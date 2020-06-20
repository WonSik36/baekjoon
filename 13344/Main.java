/*
    baekjoon online judge
    problem number 13344
    https://www.acmicpc.net/problem/13344

    Union & Topological Sort
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
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static final String gt = ">";
    static final String eq = "=";

    static int N;
    static int M;

    static List<List<Integer>> g;
    static int[] numOfParent;
    static int[] parent;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // init graph, array of num of parent
        g = new ArrayList<>();
        for(int i=0;i<N;i++){
            g.add(new ArrayList<>());
        }
        numOfParent = new int[N];

        // init union
        parent = new int[N];
        Arrays.fill(parent, -1);

        List<Pair> list = new ArrayList<>();

        for(int i=0;i<M;i++){
            String[] input = br.readLine().split(" ");

            int L = Integer.parseInt(input[0]);
            int K = Integer.parseInt(input[2]);

            if(input[1].equals(gt)){
                list.add(new Pair(L,K));
            }else if(input[1].equals(eq)){
                merge(L,K,parent);
            }else{
                list.add(new Pair(K,L));
            }
        }

        // printArr(parent);

        // make topological graph
        try{
            makeTopoGraph(list);
            list = null;

            topoSort();

            bw.write("consistent\n");
        }catch(RuntimeException e){
            bw.write("inconsistent\n");
        }

        bw.close();
        br.close();
    }
    
    static void printArr(int[] arr){
        for(int a : arr){
            System.out.printf("%d ", a);
        }
        System.out.println();
    }

    static void topoSort(){
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<N;i++){
            if(numOfParent[i] == 0){
                if(i == findRoot(i, parent))
                    queue.add(i);
                else
                    cnt++;
            }
        }

        while(!queue.isEmpty()){
            int first = queue.poll();
            cnt++;


            List<Integer> adj = g.get(findRoot(first, parent));
            for(int node : adj){
                numOfParent[node]--;
                if(numOfParent[node] == 0)
                    queue.add(node);
            }
        }

        if(cnt != N)
            throw new RuntimeException();
    }

    static void makeTopoGraph(List<Pair> list){
        for(Pair p : list){
            int L = findRoot(p.L, parent);
            int K = findRoot(p.K, parent);

            if(L == K)
                throw new RuntimeException();

            g.get(L).add(K);
            numOfParent[K]++;
        }
    }

    static int findRoot(int num, int[] parent){
        if(parent[num] == -1){
            return num;
        }

        return parent[num] = findRoot(parent[num], parent);
    }

    static void merge(int a, int b, int[] parent){
        a = findRoot(a, parent);
        b = findRoot(b, parent);

        if(a == b)
            return;

        if(a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }
}

class Pair{
    public int L;
    public int K;

    Pair(int L, int K){
        this.L = L;
        this.K = K;
    }
}