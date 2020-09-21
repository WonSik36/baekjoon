/*
    baekjoon online judge
    problem number 3985
    https://www.acmicpc.net/problem/3985

    Implementation
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
        
        StringTokenizer st = null;

        int L = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[L];
        int[] cake = new int[N];

        int predMax = 0;
        int predMaxPerson = 0;
        Arrays.fill(arr, -1);
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            if(predMax < end-start+1){
                predMax = end-start+1;
                predMaxPerson = i;
            }

            for(int j=start;j<=end;j++){
                if(arr[j] != -1)
                    continue;

                arr[j] = i;
                cake[i]++;
            }
        }

        int realMax = 0;
        int realMaxPerson = 0;
        for(int i=0;i<N;i++){
            if(realMax < cake[i]){
                realMax = cake[i];
                realMaxPerson = i;
            }
        }

        bw.write(Integer.toString(predMaxPerson+1)+"\n");
        bw.write(Integer.toString(realMaxPerson+1)+"\n");

        bw.close();
        br.close();
    }
}
