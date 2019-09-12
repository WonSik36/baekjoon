/*
    baekjoon online judge
    problem number 2110
    https://www.acmicpc.net/problem/2110
    https://romanceboong.tistory.com/entry/%EB%B0%B1%EC%A4%80-2110%EB%B2%88-%EB%AC%B8%EC%A0%9C-%EA%B3%B5%EC%9C%A0%EA%B8%B0-%EC%84%A4%EC%B9%98
    ref: 1654, 2805
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
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        long[] arr = new long[N];
        for(int i=0;i<N;i++){
            str = br.readLine();
            arr[i] = Long.parseLong(str);
        }

        bw.write(Long.toString(searchMaxLength(arr, C)));
        bw.flush();
        bw.close();
        br.close();
    }

    public static long searchMaxLength(long[] arr, int C){
        Arrays.sort(arr);
        return _searchMaxLength(arr, C, 1, arr[arr.length-1]-arr[0]);
    }

    public static long _searchMaxLength(long[] arr, int C, long left, long right){
        // System.out.printf("left: %d, right: %d\n",left,right);
        if(left > right)
            return -1;
        
        long mid = (left+right)/2;
        int idx = 0;
        int count = 1;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i+1] - arr[idx] >= mid){
                // System.out.printf("arr[%d] - arr[%d] = %d - %d >= %d\n",i+1,idx,arr[i+1],arr[idx],mid);
                idx = i+1;
                count++;
            }
        }

        // System.out.printf("C: %d, mid: %d, count: %d\n",C,mid,count);
        if(count >= C){
            long R = _searchMaxLength(arr, C, mid+1, right);
            return mid>R?mid:R;
        }else{
            return _searchMaxLength(arr, C, left, mid-1);
        }
    }
}