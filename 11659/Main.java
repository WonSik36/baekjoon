/*
    baekjoon online judge
    problem number 11659
    https://www.acmicpc.net/problem/11659

    Segment Tree Algorithm
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

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        SegmentTree tree = new SegmentTree(arr);
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            bw.write(Integer.toString(tree.range(s, e)));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }
}

class SegmentTree {
    private int[] tree;
    private int height;
    private int size;

    public SegmentTree(int[] arr){
        this.height = calcHeight(arr.length);
        this.size = calcSize(this.height);

        init(arr);
    }

    private void init(int[] arr){
        tree = new int[size];

        for(int i=0; i<arr.length; i++){
            tree[i+size/2] = arr[i];
        }

        for(int i=size/2-1; i>=1; i--){
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }

    public int range(int i, int j){
        return range(1, 1, size/2, i, j);
    }

    private int range(int nodeNum, int s, int e, final int l, final int r){
        if(r < s || e < l)
            return 0;

        if(l <= s && e <= r)
            return tree[nodeNum];
        
        int mid = (s + e) >> 1;
        return range(nodeNum*2, s, mid, l, r) + range(nodeNum*2+1, mid+1, e, l, r);
    }

    static int calcHeight(int len){
        return (int)Math.ceil(Math.log(len) / Math.log(2));
    }

    static int calcSize(int height){
        return (int)Math.pow(2, height+1);
    }
}