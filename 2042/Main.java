/*
    baekjoon online judge
    problem number 2042
    https://www.acmicpc.net/problem/2042
    https://www.acmicpc.net/blog/view/9
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        for(int i=0;i<N;i++){
            str = br.readLine();
            arr[i] = Long.parseLong(str);
        }
        SegmentTree tree = new SegmentTree(arr);
        for(int i=0;i<M+K;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            int flag = Integer.parseInt(st.nextToken());
            if(flag == 1){
                int idx = Integer.parseInt(st.nextToken())-1;
                long ch = Long.parseLong(st.nextToken());
                tree.update(idx,ch);
                // tree.print();
            }else if(flag == 2){
                int first = Integer.parseInt(st.nextToken())-1;
                int last = Integer.parseInt(st.nextToken())-1;
                bw.write(Long.toString(tree.sum(first,last))+"\n");
            }else{
                bw.write("Error: Flag is not 1 or 2\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class SegmentTree{
        private long[] treeArr;
        private long[] originArr;
        private int size;
        private int height;
        private int originLen;
    
        public SegmentTree(long[] arr){
            originLen = arr.length;
            height = (int)Math.ceil(baseLog(originLen,2));
            size = (int)Math.pow(2,height+1);
            treeArr = new long[size];
            originArr = arr.clone();
            init(arr, 1, 0, originLen-1);
        }   
    
        /* 
            arr: input array
            node: segment tree node number
            start - end: array range of node covers, not tree array
        */
        public long init(long[] arr, int node, int start, int end){
            if(start == end)
                return treeArr[node] = arr[start];
            else{
                return treeArr[node] = init(arr,2*node,start, (start+end)/2) + init(arr,2*node+1,(start+end)/2+1, end);
            }
        }
    
        public long sum(int left, int right){
            return __sum(1,0,originLen-1,left,right);
        }
    
        /*
            node: segment tree node number
            start - end: array range of node covers, not tree array
            right - left: what we want to find array range of sum, not tree array
        */
        public long __sum(int node, int start, int end, int left, int right){
            if(end<left || start>right)
                return 0;
            else if(start>=left && end<=right)
                return treeArr[node];
            else
                return __sum(node*2, start, (start+end)/2, left, right) + __sum(node*2+1, (start+end)/2+1, end, left, right);
        }
    
        public void update(int idx, long ch){
            long diff = ch - originArr[idx];
            originArr[idx] = ch;
            __update(1,0,originLen-1,idx,diff);
        }
    
        /*
            node: segment tree node number
            start - end: array range of node covers, not tree array
            idx: what we want to change value
            diff: difference between original and appended
        */
        public void __update(int node, int start, int end, int idx, long diff){
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
}
