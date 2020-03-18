/*
    baekjoon online judge
    problem number 10472
    https://www.acmicpc.net/problem/10472

    BFS Problem
    reference: http://joonas-yoon.blogspot.com/2016/03/10472.html
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Main{
    static final int[][] flipPos = {{0,1,3},{0,1,2,4},{1,2,5},
                            {0,3,4,6},{1,3,4,5,7},{2,4,5,8},
                            {3,6,7},{4,6,7,8},{5,7,8}};

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str;
        boolean[] target;
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            target = new boolean[9];

            for(int j=0;j<3;j++){
                str = br.readLine();
                for(int k=0;k<3;k++){
                    if(str.charAt(k) == '*')
                        target[3*j+k] = false;
                    else    
                        target[3*j+k] = true;
                }
            }

            int min = bfs(target);
            bw.write(Integer.toString(min)+"\n");
        }

        bw.close();
        br.close();
    }

    static int bfs(boolean[] target){
        Queue<Pair> q = new LinkedList<Pair>();
        boolean[] start = new boolean[9];
        boolean[] visited = new boolean[512];  // 2^10
    
        for(int i=0;i<9;i++){
            start[i] = true;
        }
        q.add(new Pair(start,0));

        while(!q.isEmpty()){
            Pair first = q.poll();

            if(equals(first.arr, target))
                return first.cnt;

            int idx = booleanArrtoInt(first.arr);
            if(visited[idx])
                continue;
            visited[idx] = true;
            
            for(int i=0;i<9;i++){
                q.add(new Pair(flip(first.arr, i),first.cnt+1));
            }
        }

        return -1;
    }

    static boolean[] flip(final boolean[] src, int pos){
        boolean[] dst = new boolean[9];
        int[] arr = flipPos[pos];

        for(int i=0;i<dst.length;i++){
            dst[i] = src[i];
        }

        for(int i=0;i<arr.length;i++){
            dst[arr[i]] = !dst[arr[i]];
        }

        return dst;
    }

    static int booleanArrtoInt(boolean[] arr){
        // printArr(arr);
        int sum = 0;

        for(int i=0,j=1;i<arr.length;i++,j*=2){
            if(arr[i])
                sum += j;
        }

        // System.out.println("sum: "+sum);

        return sum;
    }

    static void printArr(boolean[] arr){
        for(int i=0;i<9;i++){
            if(arr[i])
                System.out.print("1");
            else
                System.out.print("0");
        }
        System.out.println();
    }

    static boolean equals(boolean[] arr1, boolean[] arr2){
        for(int i=0;i<arr1.length;i++){
            if(arr1[i] != arr2[i])
                return false;
        }

        return true;
    }
}

class Pair{
    public boolean[] arr;
    public int cnt;

    public Pair(boolean[] arr, int cnt){
        this.arr = arr;
        this.cnt = cnt;
    }
}