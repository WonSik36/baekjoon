/*
    baekjoon online judge
    problem number 11660
    https://www.acmicpc.net/problem/11660

    Dynamic Programming
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static int N;
    static int M;
    static int[][] arr;
    static int[][] sum;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N][];

        for(int i=0;i<N;i++){
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        init();
        // printArr(sum);

        for(int i=0;i<M;i++){
            int[] q = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = q[0];
            int y1 = q[1];
            int x2 = q[2];
            int y2 = q[3];

            int res = sum[x2][y2] - sum[x1-1][y2] - sum[x2][y1-1] + sum[x1-1][y1-1];
            // System.out.printf("%d - %d - %d + %d = %d\n", sum[x2][y2], sum[x1-1][y2], sum[x2][y1-1], sum[x1-1][y1-1], res);
            bw.write(Integer.toString(res));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    static void init(){
        sum = new int[N+1][N+1];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sum[i+1][j+1] = sum[i][j+1] + sum[i+1][j] - sum[i][j] + arr[i][j];
            }
        }
    }

    static void printArr(int[][] arr){
        for(int[] a : arr){
            for(int num : a){
                System.out.printf("%d ", num);
            }
            System.out.println();
        }
    }
}
