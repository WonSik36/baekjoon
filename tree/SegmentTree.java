/*
    Segment Tree of sum
    ref: https://www.acmicpc.net/blog/view/9
    Segment Tree is binary tree
    and all node in tree has sum of child node
    tree has update time: logN, sum time: longN
    update procedure update target node and node's parent node so on
    sum procedure add node which is in range
*/
import java.util.function.BinaryOperator;

public class SegmentTree<T>{
    private T[] originArr;
    private T[] treeArr;

    private BinaryOperator<T> op;
    private T dummy;

    private final int size;
    private final int height;
    private final int originLen;

    public static void main(String[] args){
        Integer[] arr = new Integer[]{0,1,2,3,4,5,6,7,8,9};
        BinaryOperator<Integer> getSum = (a,b)->a+b;

        SegmentTree<Integer> st = new SegmentTree<>(arr, getSum, 0);

        System.out.println(st.range(0,5));
        System.out.println(st.range(3,7));
        st.update(0, 10);
        System.out.println(st.range(0,5));
        System.out.println(st.range(3,7));

        BinaryOperator<Integer> getMin = (a,b) -> (Math.min(a,b));
        st = new SegmentTree<>(arr, getMin, Integer.MAX_VALUE);
        
        System.out.println(st.range(0,5));
        System.out.println(st.range(3,7));
        st.update(0, 10);
        System.out.println(st.range(0,5));
        System.out.println(st.range(3,7));
    }

    @SuppressWarnings("unchecked")
    public SegmentTree(T[] arr, BinaryOperator<T> op, T dummy){
        originLen = arr.length;
        height = (int)Math.ceil(baseLog(originLen,2));
        size = (int)Math.pow(2,height+1);

        treeArr = (T[])new Object[size];
        originArr = arr.clone();

        this.op = op;
        this.dummy = dummy;

        init(arr, 1, 0, originLen-1);
    }   

    /* 
        arr: input array
        node: segment tree node number
        start - end: array range of node covers, not tree array
    */
    public T init(T[] arr, int node, int start, int end){
        if(start == end)
            return treeArr[node] = arr[start];
        else{
            return treeArr[node] = op.apply(init(arr,2*node,start, (start+end)/2), init(arr,2*node+1,(start+end)/2+1, end));
        }
    }

    public T range(int left, int right){
        return __range(1,0,originLen-1,left,right);
    }

    /*
        node: segment tree node number
        start - end: array range of node covers, tree array
        left - right: what we want to find array range operation, not tree array
    */
    private T __range(int node, int start, int end, int left, int right){
        if(end<left || start>right)
            return this.dummy;
        else if(start>=left && end<=right)
            return treeArr[node];
        else
            return op.apply(__range(node*2, start, (start+end)/2, left, right), __range(node*2+1, (start+end)/2+1, end, left, right));
    }

    public void update(int oIdx, T updatedValue){
        originArr[oIdx] = updatedValue;
        int tIdx = findLeafIdx(oIdx, originLen);
        treeArr[tIdx] = updatedValue;

        while(tIdx > 1){
            tIdx >>= 1;
            treeArr[tIdx] = op.apply(treeArr[2*tIdx], treeArr[2*tIdx+1]);
        }
    }

    public void print(){
        __print(1);
        System.out.println();
    }

    private void __print(int idx){
        if(size <= idx)
            return;
        __print(2*idx);
        System.out.print(treeArr[idx]+" ");
        __print(2*idx+1);
    }

    private static double baseLog(double x, double base){
        return Math.log(x)/Math.log(base);
    }

    private static int findLeafIdx(int oIdx, int originLen){
        int tIdx = 1;
        int start = 0;
        int end = originLen-1;

        while(start != end){
            int mid = (start+end)/2;

            if(mid < oIdx){
                start = mid+1;
                tIdx <<= 1;
                tIdx++;
            }else{
                end = mid;
                tIdx <<= 1;
            }
        }

        return tIdx;
    }
}