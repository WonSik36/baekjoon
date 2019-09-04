/*
    Segment Tree of sum
    ref: https://www.acmicpc.net/blog/view/9
    Segment Tree is binary tree
    and all node in tree has sum of child node
    tree has update time: logN, sum time: longN
    update procedure update target node and node's parent node so on
    sum procedure add node which is in range
*/

public class SegmentTree{
    private int[] treeArr;
    private int[] originArr;
    private int size;
    private int height;
    private int originLen;

    public static void main(String[] args){
        int[] arr = new int[]{0,1,2,3,4,5,6,7,8,9};
        SegmentTree st = new SegmentTree(arr);

        System.out.println(st.sum(0,5));
        System.out.println(st.sum(3,7));
        st.update(0, 10);
        System.out.println(st.sum(0,5));
        System.out.println(st.sum(3,7));
        // st.print();
    }

    public SegmentTree(int[] arr){
        originLen = arr.length;
        height = (int)Math.ceil(baseLog(originLen,2));
        size = (int)Math.pow(2,height+1);
        treeArr = new int[size];
        originArr = arr.clone();
        init(arr, 1, 0, originLen-1);
    }   

    /* 
        arr: input array
        node: segment tree node number
        start - end: array range of node covers, not tree array
    */
    public int init(int[] arr, int node, int start, int end){
        if(start == end)
            return treeArr[node] = arr[start];
        else{
            return treeArr[node] = init(arr,2*node,start, (start+end)/2) + init(arr,2*node+1,(start+end)/2+1, end);
        }
    }

    public int sum(int left, int right){
        return __sum(1,0,originLen-1,left,right);
    }

    /*
        node: segment tree node number
        start - end: array range of node covers, not tree array
        right - left: what we want to find array range of sum, not tree array
    */
    public int __sum(int node, int start, int end, int left, int right){
        if(end<left || start>right)
            return 0;
        else if(start>=left && end<=right)
            return treeArr[node];
        else
            return __sum(node*2, start, (start+end)/2, left, right) + __sum(node*2+1, (start+end)/2+1, end, left, right);
    }

    public void update(int idx, int ch){
        int diff = ch - originArr[idx];
        originArr[idx] = ch;
        __update(1,0,originLen-1,idx,diff);
    }

    /*
        node: segment tree node number
        start - end: array range of node covers, not tree array
        idx: what we want to change value
        diff: difference between original and appended
    */
    public void __update(int node, int start, int end, int idx, int diff){
        if(idx>end || idx<start)
            return;
        treeArr[node] += diff;
        if(start!=end){
            __update(node*2, start, (start+end)/2, idx, diff);
            __update(node*2+1, (start+end)/2+1, end, idx, diff);
        }
    }

    public void print(){
        __print(1);
        System.out.println();
    }

    public void __print(int idx){
        if(size <= idx)
            return;
        __print(2*idx);
        System.out.print(treeArr[idx]+" ");
        __print(2*idx+1);
    }

    public static double baseLog(double x, double base){
        return Math.log(x)/Math.log(base);
    }
}