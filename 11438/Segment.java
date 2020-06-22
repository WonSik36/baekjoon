/*
    baekjoon online judge
    problem number 11438
    https://www.acmicpc.net/problem/11438

    LCA(Lowest Common Ancestor) by Segment Tree
    reference: 
    https://www.acmicpc.net/blog/view/9
    http://blog.naver.com/jh20s/221339300027
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
import java.util.function.BinaryOperator;
import java.lang.StringBuilder;

public class Main{
    static int N;
    static int M;

    static Node[] visited;
    static int visitedCnt;
    static int[] cnt;

    static List<List<Integer>> g;
    static SegmentTree<Node> tree;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        visited = new Node[2*N-1];
        cnt = new int[N];
        g = new ArrayList<>();
        for(int i=0;i<N;i++){
            g.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            g.get(start).add(end);
            g.get(end).add(start);
        }
        
        // update visited
        dfs(0);

        // printVisited(visited);
    
        // segment tree
        BinaryOperator<Node> op = (n1, n2) -> n1.level < n2.level ? n1 : n2;
        tree = new SegmentTree<>(visited, op, new Node(-1, Integer.MAX_VALUE));

        // tree.print();
        
        Node res = null;
        M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int left = cnt[Integer.parseInt(st.nextToken()) - 1];
            int right = cnt[Integer.parseInt(st.nextToken()) - 1];

            if(left <= right){
                res = tree.range(left, right);
            }else{
                res = tree.range(right, left);
            }

            bw.write(Integer.toString(res.num + 1));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    static void dfs(int root){
        visitedCnt = 0;
        _dfs(root, -1, 0);
    }

    static void _dfs(int cur, int prev, int level){
        cnt[cur] = visitedCnt;
        visited[visitedCnt++] = new Node(cur, level);

        List<Integer> adj = g.get(cur);
        for(int n : adj){
            if(n == prev)
                continue;

            _dfs(n, cur, level+1);
            visited[visitedCnt++] = new Node(cur, level);
        }
    }

    static void printVisited(Node[] visited){
        for(Node n : visited){
            System.out.println(n);
        }
    }
}

class Node{
    public int num;
    public int level;

    public Node(int num, int level){
        this.num = num;
        this.level = level;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("num: ");
        sb.append(num);
        sb.append(", level: ");
        sb.append(level);

        return sb.toString();
    }
}

class SegmentTree<T>{
    private T[] originArr;
    private T[] treeArr;

    private BinaryOperator<T> op;
    private T dummy;

    private final int size;
    private final int height;
    private final int originLen;


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
        // System.out.printf("left: %d, right: %d\n", left, right);
        return __range(1,0,originLen-1,left,right);
    }

    /*
        node: segment tree node number
        start - end: array range of node covers, tree array
        left - right: what we want to find array range operation, not tree array
    */
    public T __range(int node, int start, int end, int left, int right){
        // System.out.printf("node: %d\n", node);
        // System.out.printf("start: %d, end: %d\n", start, end);
        if(end<left || start>right){
            // System.out.println("Range not matched");
            return this.dummy;
        }else if(start>=left && end<=right){
            // System.out.println("range matched");
            // System.out.println(treeArr[node]);
            return treeArr[node];
        }else{
            // System.out.println("range overlapped");
            return op.apply(__range(node*2, start, (start+end)/2, left, right), __range(node*2+1, (start+end)/2+1, end, left, right));
        }
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
        System.out.printf("node:%d %s\n",idx,treeArr[idx]);
        __print(2*idx);
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