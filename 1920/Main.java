/*
    baekjoon online judge
    problem number 1920
    https://www.acmicpc.net/problem/1920
    https://ledgku.tistory.com/35
    binary search
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
        
        // get N
        String str = br.readLine();
        int N = Integer.parseInt(str);
        int[] arr = new int[N];

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
            boolean check = check(arr, Integer.parseInt(st.nextToken()));
            if(check)
                bw.write("1\n");
            else
                bw.write("0\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean check(int[] arr, int target){
        int ret = _search(arr, target, 0, arr.length-1);
        return ret==-1?false:true;
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
