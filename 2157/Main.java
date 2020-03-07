/*
    baekjoon online judge
    problem number 2157
    https://www.acmicpc.net/problem/2157

    Dynamic Programming

    1. I mistook to understand problem. final destination should be in M
    2. Also I forget to check visited and mark visited
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;

public class Main{
    static int[][] memo;
    static List<List<Flight>> list;
    static final int MIN_INF = -1000000000; 
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        memo = new int[N+1][M+1];
        list = new ArrayList<List<Flight>>();
        for(int i=0;i<=N;i++){
            list.add(new ArrayList<Flight>());
        }

        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            
            list.get(end).add(new Flight(start,end,value));
        }
        // printList(list);

        int result = dp(N,M-1);
        if(result < 0)
            result = 0;

        bw.write(Integer.toString(result)+"\n");
        bw.close();
        br.close();
    }

    static int dp(int N, int M){
        if(M==0 && N!=1)
            return MIN_INF;
        
        if(N==1)
            return 0;

        if(memo[N][M] != 0)
            return memo[N][M];
        memo[N][M] = MIN_INF;
            
        List<Flight> tmp = list.get(N);
        for(int i=0;i<tmp.size();i++){
            Flight f = tmp.get(i);

            if(f.start >= f.end)
                continue;

            memo[N][M] = Max(memo[N][M], dp(f.start, M-1)+f.value);
        }  
        
        return memo[N][M];
    }

    static void printList(List<List<Flight>> list){
        for(int i=0;i<list.size();i++){
            List<Flight> tmp = list.get(i);
            for(int j=0;j<tmp.size();j++){
                Flight f = tmp.get(j);
                System.out.printf("start:%d, end:%d, value:%d\n",f.start, f.end, f.value);
            }
        }
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }
}

class Flight{
    public int start;
    public int end;
    public int value;

    public Flight(int start, int end, int value){
        this.start = start;
        this.end = end;
        this.value = value;
    }
}