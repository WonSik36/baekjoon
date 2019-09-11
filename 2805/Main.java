/*
    baekjoon online judge
    problem number 2805
    https://www.acmicpc.net/problem/2805
    parametic search
    ref: 1654
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int N = Integer.parseInt(str.split(" ")[0]);
        long M = Long.parseLong(str.split(" ")[1]);
        long[] arr = new long[N];

        str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        for(int i=0;i<N;i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);
        bw.write(Long.toString(search(arr,M))+"\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static long search(long[] arr, long target){
        return _search(arr,target,0,arr[arr.length-1]);
    }

    public static long _search(long[] arr, long target, long start, long end){
        if(start > end)
            return (int)end;
        long mid = (start+end)/2;
        long sum = calcWoodLength(arr,mid);
        // System.out.printf("start:%d, end:%d, mid:%d, target:%d, sum:%d\n",start,end,mid,target,sum);
        if(sum == target){
            return mid;
        }else if(sum>target){
            return _search(arr, target, mid+1, end);
        }else{
            return _search(arr, target, start, mid-1);
        }
    }

    public static long calcWoodLength(long[] arr, long len){
        long sum = 0;
        for(int i=0;i<arr.length;i++){
            if(len!=0 && arr[i]/len == 0)
                continue;
            sum += (arr[i] - len);
        }

        return sum;
    }
}