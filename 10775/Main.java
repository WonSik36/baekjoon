/*
    baekjoon online judge
    problem number 10775
    https://www.acmicpc.net/problem/10775

    application of union find

    reference: https://mygumi.tistory.com/245
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
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] next = new int[N+1];
        Arrays.fill(next, -1);

        int result = 0;
        for(int i=0;i<M;i++){
            int gate = Integer.parseInt(br.readLine());

            int root = findRoot(gate, next);
            if(root == 0)
                break;

            result++;
            int nextOfRoot = findRoot(root-1, next);
            next[root] = nextOfRoot;
        }

        bw.write(Integer.toString(result)+"\n");

        bw.close();
        br.close();
    }

    static int findRoot(int node, int[] next){
        if(next[node] == -1)
            return node;
        
        return next[node] = findRoot(next[node], next);
    }
}
