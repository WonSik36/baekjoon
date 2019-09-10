/*
    baekjoon online judge
    problem number 1654
    https://www.acmicpc.net/problem/1654
    https://coderkoo.tistory.com/m/8 for parametic search
    https://www.acmicpc.net/board/view/17306 for understanding question
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int K = Integer.parseInt(str.split(" ")[0]);
        int N = Integer.parseInt(str.split(" ")[1]);
        int[] arr = new int[K];

        for(int i=0;i<K;i++){
            str = br.readLine();
            arr[i] = Integer.parseInt(str);
        }

        Arrays.sort(arr);
        bw.write(Integer.toString(search(arr,N))+"\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static int search(int[] arr, int target){
        return _search(arr,target,0,arr[arr.length-1]);
    }

    public static int _search(int[] arr, int target, long start, long end){
        if(start > end)
            return (int)end;
        long mid = (start+end)/2;   // sometimes mid is larger than Integer.MAX_VALUE
        mid = (mid==0)?1:mid;   // if mid = 0, division by zero exception occur
        int num = calcWireNum(arr,mid);
        // System.out.printf("start:%d, end:%d, mid:%d, target:%d, num:%d\n",start,end,mid,target,num);
        if(num >= target){
            long right = _search(arr, target, mid+1, end);
            return mid>right?(int)mid:(int)right;
        }else{
            return (int)_search(arr, target, start, mid-1);
        }
    }

    public static int calcWireNum(int[] arr, long len){
        int num = 0;
        for(int i=0;i<arr.length;i++){
            num += (int)(arr[i] / len);
        }

        return num;
    }
}