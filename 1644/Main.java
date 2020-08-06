/*
    baekjoon online judge
    problem number 1644
    https://www.acmicpc.net/problem/1644

    Two Pointers Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());

        int[] arr = getPrimeSequence(N);

        int l = 0, r = 0;
        int sum = 0;
        int cnt = 0;
        while(true){
            if(sum >= N){
                sum -= arr[l++];
            }else if(r == arr.length){
                break;
            }else{
                sum += arr[r++];
            }

            if(sum == N)
                cnt++;    
        }

        bw.write(Integer.toString(cnt)+"\n");

        bw.close();
        br.close();
    }

    static int[] getPrimeSequence(int N){
        boolean[] arr = new boolean[N+1];
        Arrays.fill(arr, true);
        arr[0] = false;
        arr[1] = false;

        for(int i=2, len=(int)Math.ceil(Math.sqrt(N)); i <= len; i++){
            for(int j=i*2; j <= N; j += i){
                arr[j] = false;
            }
        }

        List<Integer> list = new ArrayList<>();
        for(int i=0;i<=N;i++){
            if(arr[i])
                list.add(i);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
