/*
    baekjoon online judge
    problem number 10816
    https://www.acmicpc.net/problem/10816
    binary search count by using Divide&Conquer and memoization
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int maxNum = 10000000;

        // get N
        String str = br.readLine();
        int N = Integer.parseInt(str);
        int[] arr = new int[N];
        int[] memo = new int[maxNum*2+1];
        Arrays.fill(memo, -1);

        // get arr
        str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // sort array for binary search
        Arrays.sort(arr);

        // get M
        str = br.readLine();
        int M = Integer.parseInt(str);

        // check whether input is in array or not
        str = br.readLine();
        st = new StringTokenizer(str);
        for(int i=0;i<M;i++){
            int target = Integer.parseInt(st.nextToken());
            int ret = memo[target+maxNum];
            if(ret == -1){
                int cnt = count(arr, target);
                memo[target+maxNum] = cnt;
                bw.write(Integer.toString(cnt)+" ");
            }else{
                bw.write(Integer.toString(ret)+" ");
            }
        }
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int count(int[] arr, int target){
        return _count(arr,target,0,arr.length-1);
    }

    public static int _count(int[] arr, int target, int start, int end){
        int ret = _search(arr, target, start, end);
        if(ret == -1)
            return 0;
        else{
            int right = _count(arr,target,ret+1,end);
            int left = _count(arr,target,start,ret-1);
            int cnt = right + left + 1;
            return cnt;
        }
    }

    public static int _search(int[] arr, int target, int start, int end){
        int mid = (start+end)/2;
        // this exit line is important
        if(start > end)
            return -1;

        if(arr[mid] == target){
            return mid;
        }else if(arr[mid] > target){
            return _search(arr, target, start, mid-1);
        }else{
            return _search(arr, target, mid+1, end);
        }
    }
}
