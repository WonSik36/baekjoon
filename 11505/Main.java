/*
    baekjoon online judge
    problem number 11505
    https://www.acmicpc.net/problem/11505

    Segment Tree Algorithm
    Init, Range, Update Method
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
    static int K;
    static long[] arr;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree tree = new SegmentTree(arr);
        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1){
                tree.update(b, c);
            }else{
                bw.write(Long.toString(tree.range(b, c)));
                bw.write("\n");
            }
        }

        bw.close();
        br.close();
    }
}

class SegmentTree {
    static final int MOD = 1000000007;
    private long[] tree;
    private int height;
    private int size;

    public SegmentTree(long[] arr){
        this.height = calcHeight(arr.length);
        this.size = calcSize(this.height);

        init(arr);
    }

    private void init(long[] arr){
        tree = new long[size];

        for(int i=0; i<size/2; i++){
            tree[i+size/2] = 1;
        }

        for(int i=0; i<arr.length; i++){
            tree[i+size/2] = arr[i];
        }

        for(int i=size/2-1; i>=1; i--){
            tree[i] = (tree[i*2] * tree[i*2+1]) % MOD;
        }
    }

    public long range(int l, int r){
        return range(1, 1, size/2, l, r);
    }

    private long range(int nodeNum, int s, int e, final int l, final int r){
        if(r < s || e < l)
            return 1;

        if(l <= s && e <= r)
            return tree[nodeNum];
        
        int mid = (s + e) >> 1;
        return (range(nodeNum*2, s, mid, l, r) * range(nodeNum*2+1, mid+1, e, l, r)) % MOD;
    }

    public void update(int idx, long num){
        int node = size/2 + idx - 1;
        tree[node] = num;

        for(node = node >> 1; node >= 1; node >>= 1){
            tree[node] = (tree[node*2] * tree[node*2+1]) % MOD;
        }
    }

    static int calcHeight(int len){
        return (int)Math.ceil(Math.log(len) / Math.log(2));
    }

    static int calcSize(int height){
        return (int)Math.pow(2, height+1);
    }
}