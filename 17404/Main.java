/*
    baekjoon online judge
    problem number 17404
    https://www.acmicpc.net/problem/17404

    Dynamic Programming
    
    reference: https://cooling.tistory.com/4
    Use trick to not choose others
    -> initialize others to MAX value
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final int RED = 0;
    static final int GREEN = 1;
    static final int BLUE = 2;
    static final int MAX_VALUE = 10000000;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            arr[i][RED] = Integer.parseInt(st.nextToken());
            arr[i][GREEN] = Integer.parseInt(st.nextToken());
            arr[i][BLUE] = Integer.parseInt(st.nextToken());
        }

        int result = Integer.MAX_VALUE;
        int[][] memo;

        for(int k=0;k<3;k++){
            memo = new int[N][3];

            // initialize one to normal value and others to max value
            for(int i=0;i<3;i++){
                if(i!=k)
                    memo[0][i] = MAX_VALUE;
                else
                    memo[0][i] = arr[0][i];
            }

            // dynamic programming
            for(int i=1;i<N;i++){
                memo[i][RED] = Min(memo[i-1][GREEN]+arr[i][RED], memo[i-1][BLUE]+arr[i][RED]);
                memo[i][GREEN] = Min(memo[i-1][RED]+arr[i][GREEN], memo[i-1][BLUE]+arr[i][GREEN]);
                memo[i][BLUE] = Min(memo[i-1][GREEN]+arr[i][BLUE], memo[i-1][RED]+arr[i][BLUE]);
            }

            // choose min value
            for(int i=0;i<3;i++){
                if(i == k)
                    continue;

                result = Min(result, memo[N-1][i]);
            }

            // printArr(memo);
        }


        bw.write(Integer.toString(result)+"\n");
        bw.close();
        br.close();
    }

    static int Min(int a, int b){
        return a<b?a:b;
    }

    static void printArr(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[j][i]+" ");
            }
            System.out.println();
        }
    }
}
