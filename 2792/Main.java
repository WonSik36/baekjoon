/*
    baekjoon online judge
    problem number 2792
    https://www.acmicpc.net/problem/2792

    Parametric Search
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
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[M];

        for(int i=0;i<M;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int l = 0, r = arr[arr.length-1]+1;
        while(l+1 < r){
            int mid = (l+r) >> 1;

            if(valid_jealous(N, arr, mid)){
                r = mid;
            } else {
                l = mid;
            }
        }

        bw.write(Integer.toString(r)+"\n");

        bw.close();
        br.close();
    }

    static boolean valid_jealous(int N, int[] arr, int jealous) {
        for(int i=0; i<arr.length; i++){
            N -= arr[i] / jealous;

            if(arr[i] % jealous > 0)
                N--;
        }

        if(N < 0) 
            return false;
        else    
            return true;
    }
}
