/*
    baekjoon online judge
    problem number 1300
    https://www.acmicpc.net/problem/1300
    http://wookje.dance/2017/02/20/boj-1300-K%EB%B2%88%EC%A7%B8-%EC%88%98/
*/
import java.util.Arrays;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main{
    static long[] result;
    static final int range = 100;
    public static void main(String[] args)throws IOException{
        try{
            for(int i=1;i<=range;i++){
                for(int j=1;j<=i*i;j++){
                    System.out.printf("i: %d, j: %d \n",i,j);
                    if(search(i, j) == getAscendOrder(i, j))
                        ;// System.out.printf("SUCEED\n");
                    else{
                        // System.out.printf("FAILED\n");
                        String e = String.format("i: %d, j: %d, ans: %d, comp: %d",i,j,getAscendOrder(i, j),search(i,j));
                        throw new Exception(e);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static long search(int N, int k){
        return _search(N, k, 1, k);
    }

    // Assumption: arr[k] <= k for every k
    public static long _search(int N, int k, int start, int end){
        int mid = (start + end) /2;
        // System.out.printf("start: %d, end: %d, mid: %d\n",start,end,mid);
        if(start > end)
            return -1;
        int count = 0;

        for(int i=1;i<=N;i++){
            count += (mid/i>N?N:mid/i);
        }
        // System.out.printf("mid: %d, count: %d\n",mid,count);
        // if count is bigger than k than there is possibility that mid is kth number
        // so store mid and compare it to another answer range(start, mid-1)
        // if count is same as k than there is possibility that mid is not kth number
        // example) when N = 3 -> arr = {1 2 2 3 3 4 6 6 9}
        // if k = 6, than 5 could be mid but 5 doesn't exist in arr
        // so store mid and compare it to another answer range(start, mid-1)
        if(count >= k){
            long comp = _search(N,k,start,mid-1);
            // System.out.printf("mid: %d, comp: %d\n",mid,comp);
            return  (comp == -1)?mid:comp;
        }else{
            return _search(N,k,mid+1,end);
        }
    }

    public static void printArray(long[] arr, BufferedWriter bw)throws IOException{
        for(int i=1;i<arr.length;i++)
            bw.write(Long.toString(arr[i])+" ");
        bw.write("\n");
    }

    public static long getAscendOrder(int N, int k)throws IOException{
        if(result != null && result.length == N*N+1)
            return result[k];

        // System.out.print("Result array is changed ");
        result = new long[N*N+1];

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                result[(i-1)*N+j] = i*j;
            }
        }   

        Arrays.sort(result);
        // printArray(result, bw);
    
        return result[k];
    }
}
