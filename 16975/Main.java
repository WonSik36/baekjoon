/*
    baekjoon online judge
    problem number 16975
    https://www.acmicpc.net/problem/16975

    Segment Tree & Lazy Propagation
    reference: https://kyu9341.github.io/algorithm/2020/04/06/algorithm16975/
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
    static int[] arr;
    static int M;
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        SegmentTree tree = new SegmentTree(arr);
        
        M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());

            if(order == 1){
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                tree.update(l,r,v);
            }else{
                int idx = Integer.parseInt(st.nextToken());

                long res = tree.get(idx);
                bw.write(Long.toString(res));
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class SegmentTree {
    private int size;
    private int height;
    private long[] tree;

    public SegmentTree(int[] origin){
        this.height = calcHeight(origin.length);
        this.size = calcSize(height);

        initTree(origin);
    }

    private void initTree(int[] origin){
        tree = new long[size];

        for(int i=0;i<origin.length;i++){
            tree[size/2+i] = origin[i];
        }
    }

    public void update(int l, int r, int v){
        update(1, 1, size/2, l, r, v);
    }

    private void update(int nodeNum, int s, int e, int l, int r, int v){
        if(e<l || r<s)
            return;

        if(l<=s && e<=r){
            tree[nodeNum] += v;
            return;
        }

        int mid = (s+e) >> 1;
        update(nodeNum*2, s, mid, l, r, v);
        update(nodeNum*2+1, mid+1, e, l, r, v);
    }

    public long get(int idx){
        return get(1, 1, size/2, idx);
    }

    private long get(int nodeNum, int s, int e, int idx){
        if(idx < s || e < idx)
            return 0;

        if(s==e && idx==s)
            return tree[nodeNum];

        long res = tree[nodeNum];
        int mid = (s+e) >> 1;
        return res + get(nodeNum*2, s, mid, idx) + get(nodeNum*2+1, mid+1, e, idx);
    }

    private static int calcSize(int height){
        return (int)Math.pow(2, height+1);
    }

    private static int calcHeight(int len){
        return (int)Math.ceil(Math.log(len) / Math.log(2));
    }
}