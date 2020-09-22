/*
    baekjoon online judge
    problem number 13720
    https://www.acmicpc.net/problem/13720

    Parametric Search
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static int N;
    static int K;
    static int[] arr;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        int max = 0;
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        if(max == 0){
            bw.write("0\n");
            bw.close();
            br.close();
            return;
        }

        int l = -1, r = max;
        while(l+1 < r){
            int mid = (l+r) >> 1;

            if(calc(mid) == -1){
                r = mid;
            }else{
                l = mid;
            }
        }

        bw.write(Integer.toString(l)+"\n");

        bw.close();
        br.close();
    }

    static long calc(int index){
        long cnt = 0;
        for(int i=0;i<N;i++){
            cnt += (arr[i] / index);
        }

        if(cnt >= K)
            return (long)K * index;
        else
            return -1;
    }
}
