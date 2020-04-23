/*
    baekjoon online judge
    problem number 13913
    https://www.acmicpc.net/problem/13913

    Dynamic Programming + Shortest Path Reverse Tracking
    
    Compare checking first to checking last
    checking first: after polling check visited before  -> 47392 KB, 260 ms, convinient and not redundant
    checking last: using if to add or not               -> 33384 KB, 196 ms, redundant
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
    static int N;
    static int K;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] before = new int[100001];
        Arrays.fill(before, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Trio> queue = new LinkedList<Trio>();
        queue.add(new Trio(N, N, 0));
        while(!queue.isEmpty()){
            Trio first = queue.poll();
            // System.out.printf("pos:%d cnt:%d\n",first.pos, first.cnt);

            if(before[first.cur] != -1)
                continue;
            before[first.cur] = first.prev;

            if(first.cur == K){
                bw.write(Integer.toString(first.cnt)+"\n");
                break;
            }

            if(first.cur > 0){
                queue.add(new Trio(first.cur-1, first.cur, first.cnt+1));
            }
        
            if(first.cur < 100000){
                queue.add(new Trio(first.cur+1, first.cur, first.cnt+1));
            }

            if(first.cur*2 <= 100000){
                queue.add(new Trio(first.cur*2, first.cur, first.cnt+1));
            }
        }

        // printArr(before);

        trace(K, before, bw);
        bw.write("\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static void trace(int idx, int[] before, BufferedWriter bw) throws IOException {
        if(idx != N){
            trace(before[idx], before, bw);
        }

        bw.write(Integer.toString(idx));
        bw.write(" ");
    }

    static void printArr(int[] arr){
        for(int i=0;i<100;i++){
            System.out.printf("%d ", arr[i]);
        }
        System.out.println();
    }
}

class Trio {
    public int cur;
    public int prev;
    public int cnt;

    public Trio(int cur, int prev, int cnt){
        this.cur = cur;
        this.prev = prev;
        this.cnt = cnt;
    }
}