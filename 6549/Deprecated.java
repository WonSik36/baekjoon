/*
    baekjoon online judge
    problem number 6549
    https://www.acmicpc.net/problem/6549
    https://www.acmicpc.net/blog/view/12
    solving by segment tree
    
    firstly I want to use only getMaxArea
    but problem is I need to check height and area both
    so I give up and look other's solving
    they divide it to two functions
    first function search minimum height of given range
    second function get minimum height of given range by using first function
    and calculate area and divide range to two parts(pivot is minimum height index) and recursslively call
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Stack;

public class Deprecated{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        
        while(true){
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int N = Integer.parseInt(st.nextToken());
            if(N == 0)
                break;
            int[] arr = new int[N];
            for(int i=0;i<N;i++)
                arr[i] = Integer.parseInt(st.nextToken());
            SegmentTree tree = new SegmentTree(arr);
            bw.write(Long.toString(tree.getMaxArea())+"\n");
            // tree.print();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class SegmentTree{
        private int[] treeArr;
        private int[] originArr;
        private int size;
        private int height;
        private int originLen;
    
        public SegmentTree(int[] arr){
            originLen = arr.length;
            height = (int)Math.ceil(baseLog(originLen,2));
            size = (int)Math.pow(2,height+1);
            treeArr = new int[size];
            originArr = arr.clone();
            init(arr, 1, 0, arr.length-1);
        }   
    
        /* 
            arr: input array
            node: segment tree node number
            start - end: array range of node covers, not tree array
            store minimum value's index to tree array
        */
        public int init(int[] arr, int node, int start, int end){
            if(start == end)
                return treeArr[node] = start;
            else{
                int a = init(arr,2*node,start, (start+end)/2);
                int b = init(arr,2*node+1,(start+end)/2+1, end);
                return treeArr[node] = arr[a]<arr[b]?a:b;
            }
        }

        public long getMaxArea(){
            return __getMaxArea(0, originLen-1);
        }

        public long __getMaxArea(int left, int right){
            int idx = getMinHeightIdx(1, 0, originLen-1, left, right);
            long area = (long)originArr[idx]*(right-left+1);
            // System.out.printf("left: %d, right: %d\n",left,right);
            if(left < idx){
                long tmp = __getMaxArea(left, idx-1);
                if(tmp > area)
                    area = tmp;
            }
            if(right > idx){
                long tmp = __getMaxArea(idx+1, right);
                if(tmp > area)
                    area = tmp;
            }
            return area;
        }

        public int getMinHeightIdx(int node, int start, int end, int left, int right){
            if(left>end || right<start)
                return -1;
            // if node is in the range of searching area of tree than return index of Original array
            else if(left<=start && right>=end)
                return treeArr[node];
            else{
                int leftHeight = getMinHeightIdx(2*node, start, (start+end)/2, left, right); 
                int rightHeight = getMinHeightIdx(2*node+1, (start+end)/2+1, end, left, right);
                if(leftHeight == -1)
                    return rightHeight;
                else if(rightHeight == -1)
                    return leftHeight;
                else{
                    if(originArr[leftHeight] < originArr[rightHeight])
                        return leftHeight;
                    else
                        return rightHeight;
                }
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

    public static int Min(int a, int b){
        return a<b?a:b;
    }

    public static long Max(long a, long b){
        return a>b?a:b;
    }
}