/*
    baekjoon online judge
    problem number 14500
    https://www.acmicpc.net/problem/14500

    Brute Force Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static final int[] maxX = {3, 0, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2};
    static final int[] maxY = {0, 3, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 1};
    static final int[][] dX = {
            {0, 1, 2, 3}, {0, 0, 0, 0}, 
            {0, 1, 0, 1}, 
            {0, 0, 0, 1}, {0, 1, 2, 0}, {0, 1, 1, 1}, {2, 2, 1, 0},
            {0, 0, 1, 1}, {0, 1, 1, 2}, {1, 1, 0, 0}, {0, 1, 1, 2},
            {0, 1, 2, 1}, {0, 0, 0, 1}, {0, 1, 2, 1}, {1, 1, 1, 0},
            {0, 1, 1, 1}, {0, 1, 2, 2}, {1, 0, 0, 0}, {0, 0, 1, 2}
        };
    static final int[][] dY = {
            {0, 0, 0, 0}, {0, 1, 2, 3}, 
            {0, 0, 1, 1}, 
            {0, 1, 2, 2}, {0, 0, 0, 1}, {0, 0, 1, 2}, {0, 1, 1, 1},
            {0, 1, 1, 2}, {1, 1, 0, 0}, {0, 1, 1, 2}, {0, 0, 1, 1},
            {1, 1, 1, 0}, {0, 1, 2, 1}, {0, 0, 0, 1}, {0, 1, 2, 1},
            {2, 2, 1, 0}, {0, 0, 0, 1}, {0, 0, 1, 2}, {0, 1, 1, 1}
        };

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = arr[0];
        int M = arr[1];

        int[][] map = new int[N][];
        for(int i=0;i<N;i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        
        int max = 0;
        for(int k=0;k<maxX.length;k++){
            for(int i=0;i<N-maxY[k];i++){
                for(int j=0;j<M-maxX[k];j++){
                    max = Math.max(max, map[i+dY[k][0]][j+dX[k][0]] + map[i+dY[k][1]][j+dX[k][1]]
                            + map[i+dY[k][2]][j+dX[k][2]]+ map[i+dY[k][3]][j+dX[k][3]]);
                }
            }
        }

        bw.write(Integer.toString(max)+"\n");

        bw.close();
        br.close();
    }
}
