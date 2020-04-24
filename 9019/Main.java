/*
    baekjoon online judge
    problem number 9019
    https://www.acmicpc.net/problem/9019

    Dynamic Programming + Shortest Path Reverse Tracking
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

public class Main{
    static final int D = 1;
    static final int S = 2;
    static final int L = 3;
    static final int R = 4;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        Queue<Pair> queue = new LinkedList<Pair>();
        int[] visited = null;
        int[] calc = null;

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            calc = new int[10000];
            visited = new int[10000];
            Arrays.fill(visited, -1);

            queue.clear();
            queue.add(new Pair(start, 0));
            visited[start] = 0;
            while(!queue.isEmpty()){
                Pair first = queue.poll();

                if(first.cur == target)
                    break;

                int d = calcD(first.cur);
                int s = calcS(first.cur);
                int l = calcL(first.cur);
                int r = calcR(first.cur);

                if(visited[d] == -1){
                    visited[d] = first.cur;
                    calc[d] = D;
                    queue.add(new Pair(d, first.cnt+1));
                }

                if(visited[s] == -1){
                    visited[s] = first.cur;
                    calc[s] = S;
                    queue.add(new Pair(s, first.cnt+1));
                }

                if(visited[l] == -1){
                    visited[l] = first.cur;
                    calc[l] = L;
                    queue.add(new Pair(l, first.cnt+1));
                }

                if(visited[r] == -1){
                    visited[r] = first.cur;
                    calc[r] = R;
                    queue.add(new Pair(r, first.cnt+1));
                }
            }

            // printArr(visited);

            trace(target, visited, calc, start, bw);
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    static void printArr(int[] arr){
        for(int i=0;i<20;i++){
            for(int j=0;i<50;j++){
                System.out.print(arr[i*50+j]+" ");
            }
            System.out.println();
        }
    }

    static int calcD(int num){
        int ret = 2*num;
        ret %= 10000;

        return ret;
    }

    static int calcS(int num){
        if(num == 0)
            return 9999;
        return num-1;
    }

    static int calcL(int num){
        int digit1 = num / 1000;
        num %= 1000;
        int ret = num*10 + digit1;

        return ret;
    }

    static int calcR(int num){
        int digit4 = num % 10;
        num /= 10;
        int ret = num + digit4*1000;

        return ret;
    }

    static void trace(int cur, int[] visited, int[] calc, int last, BufferedWriter bw) throws IOException{
        if(cur == last)
            return;

        trace(visited[cur], visited, calc, last, bw);

        if(calc[cur] == D)
            bw.write("D");
        else if(calc[cur] == S)
            bw.write("S");
        else if(calc[cur] == L)
            bw.write("L");
        else if(calc[cur] == R)
            bw.write("R");
    }
}

class Pair {
    public int cur;
    public int cnt;

    public Pair(int cur, int cnt){
        this.cur = cur;
        this.cnt =cnt;
    }
}