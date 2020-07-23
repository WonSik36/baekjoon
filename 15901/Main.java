/*
    baekjoon online judge
    problem number 15901
    https://www.acmicpc.net/problem/15901

    Deque
    Segment Tree Lazy Propagation

    high reference:
    https://docs.google.com/presentation/d/1y4f_ZCcWgCZocPZozsaFZpn2AJSx3ZtPwEFM3h7NurU/edit#slide=id.g3d8629bdfa_0_26
    https://www.acmicpc.net/board/view/52547
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.lang.StringBuilder;

public class Main{
    private static final int EMPTY = 0;

    static int N;
    static int M;
    static int K;
    static int Q;
    static int[] arr;
    static Deque<Trash> wait = new LinkedList<>();
    static SegmentTree box;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        initDeque(arr);
        // printDeque(wait);
        box = new SegmentTree(order4(M, M), M);

        StringBuilder outputOfOrder2 = new StringBuilder();
        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());

            int o = Integer.parseInt(st.nextToken());
            switch(o){
                case 1:
                    int l = Integer.parseInt(st.nextToken()) - 1;
                    int r = Integer.parseInt(st.nextToken()) - 1;
                    order1(l,r);
                    break;

                case 2:
                    int idx = Integer.parseInt(st.nextToken()) - 1;
                    outputOfOrder2.append(order2(idx));
                    outputOfOrder2.append(" ");
                    break;

                case 3:
                    int p = Integer.parseInt(st.nextToken());
                    int q = Integer.parseInt(st.nextToken());
                    order3(p,q);
                    break;

                case 4:
                    long t = Long.parseLong(st.nextToken());
                    order4(t,t);
                    break;

                default:
                    throw new AssertionError("Unknown Order");
            }
        }
        bw.write(outputOfOrder2.toString());
        bw.write("\n");
        bw.write(box.toString());
        bw.write("\n");

        bw.close();
        br.close();
    }

    static void initDeque(int[] arr){
    	if(arr.length == 1){
            wait.addLast(new Trash(arr[0], 1));
            return;
        }
    	
        for(int i=1, kind=arr[0], fIdx=0; i<N; i++){
            if(kind != arr[i]){
                wait.addLast(new Trash(kind, i-fIdx));
                kind = arr[i];
                fIdx = i;
            }

            if(i == N-1){
                wait.addLast(new Trash(kind, i-fIdx+1));
            }
        }
    }

    static void printDeque(Deque<Trash> deque){
        for(Trash t : deque){
            System.out.println(t);
        }
    }

    static void order1(int l, int r){
        int size = r - l + 1;
        box.update(l, r, order4(size, size));
    }

    static int order2(int idx){
        return box.range(idx);
    }

    static void order3(int p, int q){
        wait.addLast(new Trash(p, q));
    }

    static List<Trash> order4(long t, final long size){
        List<Trash> removed = new ArrayList<>();
        long curSize = 0;

        while(!wait.isEmpty() && t > 0){
            Trash first = wait.pollFirst();
            if(t >= first.size){
                curSize += first.size;
                t -= first.size;
                removed.add(first);
            }else{
            	curSize += t;
                first.size = first.size - t;
                removed.add(new Trash(first.kind, t));
                t = 0;
                wait.addFirst(first);
            }
        }

        if(curSize < size)
            removed.add(new Trash(EMPTY, size - curSize));

        return removed;
    }
}

class SegmentTree {
    private int[] tree;
    private int height;
    private int size;
    private int boxSize;

    public SegmentTree(List<Trash> list, int boxSize){
        this.height = calcHeight(boxSize);
        this.size = calcSize(this.height);
        this.boxSize = boxSize;
        this.tree = new int[this.size];

        init(list);
    }

    private void init(List<Trash> list){
        update(0, boxSize-1, list);
    }

    public int range(int idx){
        return range(1, 0, boxSize - 1, idx);
    }

    private int range(int nodeNum, int s, int e, final int idx){
        if(tree[nodeNum] != -1)
            return tree[nodeNum];

        int mid = (s+e)/2;
        if(idx <= mid)
            return range(nodeNum*2, s, mid, idx);
        else
            return range(nodeNum*2+1, mid+1, e, idx);
    }

    public void update(int l, int r, List<Trash> list){
        int idx = l;
        for(Trash t : list){
            _update(1, 0, boxSize - 1, idx, idx+t.size-1, t.kind);
            idx += t.size;
        }
    }

    private void _update(int nodeNum, int s, int e, final long l, final long r, final int kind){
        // case 1: out of range
        if(r<s || e<l)
            return;

        // case 2: in range
        if(l<=s && e<=r){
            tree[nodeNum] = kind;
            return;
        }

        // case 3-1: overlap range, same value
        if(tree[nodeNum] == kind)
            return;

        // case 3-2: overlap range, different value
        if(tree[nodeNum] != -1) {
            tree[nodeNum*2] = tree[nodeNum];
            tree[nodeNum*2+1] = tree[nodeNum];
            tree[nodeNum] = -1;
        }

        int mid = (s + e) / 2;
        _update(nodeNum*2, s, mid, l, r, kind);
        _update(nodeNum*2+1, mid+1, e, l, r, kind);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        _toString(1, 0, boxSize-1, sb);

        return sb.toString();
    }

    private void _toString(int nodeNum, int s, int e, StringBuilder sb){
        if(tree[nodeNum] != -1){
            for(int i=s;i<=e;i++){
                sb.append(tree[nodeNum]);
                sb.append(" ");
            }
        }else{
            int mid = (s+e) / 2;
            _toString(nodeNum*2, s, mid, sb); 
            _toString(nodeNum*2+1, mid+1, e, sb); 
        }
    }

    static int calcHeight(int len){
        return (int)Math.ceil(Math.log(len) / Math.log(2));
    }

    static int calcSize(int height){
        return (int)Math.pow(2, height+1);
    }
}

class Trash {
    public int kind;
    public long size;

    public Trash(int kind, long size){
        this.kind = kind;
        this.size = size;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("kind: ");
        sb.append(kind);
        sb.append(", size: ");
        sb.append(size);

        return sb.toString();
    }
}