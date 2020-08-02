/*
    baekjoon online judge
    problem number 11658
    https://www.acmicpc.net/problem/11658

    2D Segment Tree
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][];
        for(int i=0;i<N;i++){
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        SegmentTree2D tree = new SegmentTree2D(arr);
        
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int order = Integer.parseInt(st.nextToken());
            if(order == 0){
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                tree.update(x,y,c);
            }else{
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                bw.write(Integer.toString(tree.range(x1, x2, y1, y2)));
                bw.write("\n");
            }
        }

        bw.close();
        br.close();
    }
}

class SegmentTree2D {
    private int size;
    private int height;
    private SegmentTree[] tree;

    public SegmentTree2D(int[][] arr){
        this.height = calcHeight(arr.length);
        this.size = calcSize(height);

        initTree(arr);
    }

    private void initTree(int[][] arr){
        tree = new SegmentTree[size];
        for(int i=0;i<arr.length;i++){
            tree[i+size/2] = new SegmentTree(arr[i]);
        }

        for(int i=size/2-1;i>=1;i--){
            tree[i] = SegmentTree.add(tree[i*2], tree[i*2+1]);
        }
    }

    public int range(int l1, int r1, int l2, int r2){
        return range(1, 1, size/2, l1, r1, l2, r2);
    }

    private int range(int nodeNum, int s, int e, final int l1, final int r1, final int l2, final int r2){
        if(r1 < s || e < l1)
            return 0;

        if(l1<=s && e<=r1)
            return tree[nodeNum].range(l2, r2);

        int mid = (s+e) >> 1;
        return range(nodeNum*2, s, mid, l1, r1, l2, r2) + range(nodeNum*2+1, mid+1, e, l1, r1, l2, r2);
    }


    public void update(int idx1, int idx2, int v){
        update(1, 1, size/2, idx1, idx2, v);
    }

    private void update(int nodeNum, int s, int e, final int idx1, final int idx2, final int v){
        if(idx1 < s || e < idx1)
            return;

        if(s == e){
            if(s == idx1)
                tree[nodeNum].update(idx2, v);

            return;
        }

        int mid = (s+e)>>1;
        update(nodeNum*2, s, mid, idx1, idx2, v);
        update(nodeNum*2+1, mid+1, e, idx1, idx2, v);
        tree[nodeNum] = SegmentTree.add(tree[nodeNum*2], tree[nodeNum*2+1]);
    }

    private static int calcHeight(int len){
        return (int)Math.ceil(Math.log(len) / Math.log(2));
    }

    private static int calcSize(int height){
        return (int)Math.pow(2, height+1);
    }
}

class SegmentTree {
    private int height;
    private int size;
    private int[] tree;

    private SegmentTree(){}

    public SegmentTree(int[] arr){
        this.height = calcHeight(arr.length);
        this.size = calcSize(height);

        initTree(arr);
    }

    public static SegmentTree add(SegmentTree t1, SegmentTree t2){
        SegmentTree ret = new SegmentTree();
        ret.size = t1.size;
        ret.height = t1.height;
        ret.tree = new int[ret.size];

        for(int i=0;i<ret.size;i++){
            ret.tree[i] = t1.tree[i] + t2.tree[i];
        }

        return ret;
    }

    private void initTree(int[] arr){
        tree = new int[size];
        for(int i=0;i<arr.length;i++){
            tree[i+size/2] = arr[i];
        }

        for(int i=size/2-1;i>=1;i--){
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }

    public int range(int l, int r){
        return range(1, 1, size/2, l, r);
    }

    private int range(int nodeNum, int s, int e, final int l, final int r){
        if(r < s || e < l)
            return 0;

        if(l<=s && e<=r)
            return tree[nodeNum];

        int mid = (s+e) >> 1;
        return range(nodeNum*2, s, mid, l, r) + range(nodeNum*2+1, mid+1, e, l, r);
    }


    public void update(int idx, int v){
        update(1, 1, size/2, idx, v);
    }

    private void update(int nodeNum, int s, int e, final int idx, final int v){
        if(idx < s || e < idx)
            return;

        if(s == e){
            if(s == idx)
                tree[nodeNum] = v;

            return;
        }

        int mid = (s+e)>>1;
        update(nodeNum*2, s, mid, idx, v);
        update(nodeNum*2+1, mid+1, e, idx, v);
        tree[nodeNum] = tree[nodeNum*2] + tree[nodeNum*2+1];
    }

    private static int calcHeight(int len){
        return (int)Math.ceil(Math.log(len) / Math.log(2));
    }

    private static int calcSize(int height){
        return (int)Math.pow(2, height+1);
    }
}