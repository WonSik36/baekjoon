/*
    baekjoon online judge
    problem number 2357
    https://www.acmicpc.net/problem/2357

    Segment Tree
    Use Strategy Pattern
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static int N;
    static int M;

    static int[] arr;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        SegmentTree minTree = new SegmentTree(arr, new SegmentTree.Strategy(){
                    @Override
                    public int dummy(){
                        return Integer.MAX_VALUE;
                    }

                    @Override
                    public int calc(int a, int b){
                        return a<b?a:b;
                    }
                });
        SegmentTree maxTree = new SegmentTree(arr, new SegmentTree.Strategy(){
                    @Override
                    public int dummy(){
                        return Integer.MIN_VALUE;
                    }

                    @Override
                    public int calc(int a, int b){
                        return a>b?a:b;
                    }
                });

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            int min = minTree.range(l, r);
            int max = maxTree.range(l, r);

            bw.write(Integer.toString(min));
            bw.write(" ");
            bw.write(Integer.toString(max));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }
}

class SegmentTree {
    int size;
    int height;
    int[] tree;
    Strategy strategy;

    public SegmentTree(int[] origin, Strategy strategy){
        this.height = calcHeight(origin.length);
        this.size = calcSize(this.height);
        this.strategy = strategy;

        initTree(origin);
    }

    private void initTree(int[] origin){
        tree = new int[size];
        Arrays.fill(tree, strategy.dummy());

        for(int i=0;i<origin.length;i++){
            tree[i + size/2] = origin[i];
        }

        for(int i=size/2-1;i>=1;i--){
            tree[i] = strategy.calc(tree[2*i], tree[2*i+1]);
        }
    }

    public int range(int l, int r){
        return range(1, 1, size/2, l, r);
    }

    private int range(int nodeNum, int s, int e, final int l, final int r){
        if(r<s || e<l)
            return strategy.dummy();

        if(l <= s && e <= r)
            return tree[nodeNum];
        

        int mid = (s+e) >> 1;
        return strategy.calc(range(2*nodeNum, s, mid, l, r), range(2*nodeNum+1, mid+1, e, l, r));
    }

    static int calcHeight(int arrLen){
        return (int)Math.ceil(Math.log(arrLen) / Math.log(2));
    }

    static int calcSize(int height){
        return (int)Math.pow(2, height+1);
    }

    static interface Strategy {
        public int dummy();
        public int calc(int a, int b);
    }
}