/*
    baekjoon online judge
    problem number 6209
    https://www.acmicpc.net/problem/6209

    Binary Search with Parametric Search
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
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+2];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        arr[N] = 0;
        arr[N+1] = D;
        Arrays.sort(arr);

        int hi = D+1;
        int lo = 0;
        while(lo+1<hi){
            int mid = (hi+lo)/2;

            if(isAvailable(mid,M,arr)){
                lo = mid;
            }else{
                hi = mid;
            }
        }

        bw.write(Integer.toString(lo)+"\n");
        bw.close();
        br.close();
    }

    static boolean isAvailable(int min, int cnt, int[] arr){
        int j = 0;
        for(int i=1;i<arr.length;i++){
            // System.out.printf("i: %d, j: %d\n",i,j);
            if(i + j >= arr.length){
                cnt = cnt - j;
                break;
            }

            // System.out.printf("arr[%d]-arr[%d] = %d, min: %d\n", i+j, i-1, arr[i+j]-arr[i-1], min);
            // pass
            if(arr[i+j]-arr[i-1] >= min){
                i += j;
                j = 0;

            // not pass
            }else{
                cnt--;
                j++;
                i--;
            }
        }

        if(cnt >=0)
            return true;
        else
            return false;
    }
}
