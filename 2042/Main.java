/*
    baekjoon online judge
    problem number 2042
    https://www.acmicpc.net/problem/2042
    https://www.acmicpc.net/blog/view/21
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
        
        FenwickTree tree = new FenwickTree(arr);
        for(int i=0;i<M+K;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            int flag = Integer.parseInt(st.nextToken());
            if(flag == 1){
                int idx = Integer.parseInt(st.nextToken());
                long ch = Long.parseLong(st.nextToken());
                tree.update(idx,ch);
                // tree.print();
            }else if(flag == 2){
                int first = Integer.parseInt(st.nextToken());
                int last = Integer.parseInt(st.nextToken());
                bw.write(Long.toString(tree.sum(first,last))+"\n");
            }else{
                bw.write("Error: Flag is not 1 or 2\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static class FenwickTree{
        private long[] treeArr;
        private long[] originArr;
        public int height;
        public int length;
        
        public FenwickTree(long[] arr){
            treeArr = new long[arr.length+1];
            originArr = new long[arr.length+1];
            length = arr.length;
            height = (int)baseLog(length, 2)+1;
            init(arr);
        }
    
        public void init(long[] arr){
            for(int i=0;i<length;i++){
                update(i+1,arr[i]);
            }
        }
    
        public void update(int idx, long ch){
            long diff = ch - originArr[idx];
            originArr[idx] = ch;
            __update(idx, diff);
        }
    
        // update idx node to upper node 
        public void __update(int idx, long diff){
            while(idx <= length){
                // System.out.print(treeArr[idx]+ " ");
                treeArr[idx] += diff;
                idx += (idx & (-idx));
            }
            // System.out.println();
        }
    
        public long sum(int start, int end){
            return __sum(end) - __sum(start-1);
        }
    
        public long __sum(int idx){
            long ret = 0;
            while(idx>0){
                ret += treeArr[idx];
                idx -= (idx &(-idx));
            }
            return ret;
        }
    
        public void print(){
            printLinearly();
        }
    
        public void printLinearly(){
            for(int i=1;i<=length;i++)
                System.out.print(treeArr[i]+" ");
            System.out.println();
        }
    
        public static double baseLog(double x, double base){
            return Math.log(x)/Math.log(base);
        }
    }
}