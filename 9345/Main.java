/*
    baekjoon online judge
    problem number 9345
    https://www.acmicpc.net/problem/9345

    Segment Tree
    Use Strategy Pattern

    reference: https://jason9319.tistory.com/40

    range function return dummy when out of bound
    update function return node value when out of bound
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main{
    static int T;
    static int N;
    static int K;

    static int[] arr;

    static SegmentTree minTree;
    static SegmentTree maxTree;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = IntStream.range(0, N).toArray();

            minTree = new SegmentTree(arr, new SegmentTree.Strategy(){
                        @Override
                        public int dummy(){
                            return Integer.MAX_VALUE;
                        }
    
                        @Override
                        public int calc(int a, int b){
                            return a<b?a:b;
                        }
                    });
            maxTree = new SegmentTree(arr, new SegmentTree.Strategy(){
                        @Override
                        public int dummy(){
                            return Integer.MIN_VALUE;
                        }
    
                        @Override
                        public int calc(int a, int b){
                            return a>b?a:b;
                        }
                    });


            for(int j=0;j<K;j++){
                st = new StringTokenizer(br.readLine());
    
                int order = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
    
                if(order == 0){
                    minTree.swap(l,r);
                    maxTree.swap(l,r);
                }else{
                    int min = minTree.range(l, r);
                    int max = maxTree.range(l, r);
        
                    if(min == l && max == r){
                        bw.write("YES\n");
                    }else{
                        bw.write("NO\n");
                    }
                }
            }
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
        return range(1, 0, size/2-1, l, r);
    }

    private int range(int nodeNum, int s, int e, final int l, final int r){
        if(r<s || e<l)
            return strategy.dummy();

        if(l <= s && e <= r)
            return tree[nodeNum];
        

        int mid = (s+e) >> 1;
        return strategy.calc(range(2*nodeNum, s, mid, l, r), range(2*nodeNum+1, mid+1, e, l, r));
    }

    public void swap(int l, int r){
        int vl = tree[size/2+l];
        int vr = tree[size/2+r];

        update(1, 0, size/2-1, l, vr);
        update(1, 0, size/2-1, r, vl);
    }

    private int update(int nodeNum, int s, int e, int idx, int value){
        if(idx < s || e < idx)
            return tree[nodeNum];

        if(s == e && s == idx){
            tree[nodeNum] = value;

            return value;
        }

        int mid = (s+e) / 2;
        return tree[nodeNum] = strategy.calc(update(nodeNum*2, s, mid, idx, value), update(nodeNum*2+1, mid+1, e, idx, value));
    }

    public void print(){
        for(int i=size/2;i<size;i++){
            System.out.print(tree[i]);
            System.out.print(" ");
        }
        System.out.println();
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