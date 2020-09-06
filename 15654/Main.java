/*
    baekjoon online judge
    problem number 15654
    https://www.acmicpc.net/problem/15654

    Permutation
    Backtraking Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        LinkedList<Integer> sequence = new LinkedList<>();
        boolean[] visited = new boolean[N];

        for(int i=0;i<N;i++){
            sequence.addLast(arr[i]);
            visited[i] = true;
            backtrack(1, M, arr, visited, sequence, bw);
            visited[i] = false;
            sequence.removeLast();
        }

        bw.close();
        br.close();
    }

    static void backtrack(int cur, int size, int[] arr, boolean[] visited, LinkedList<Integer> sequence, BufferedWriter bw) throws IOException {
        if(size == cur){
            if(size == sequence.size()){
                for(int num: sequence){
                    bw.write(Integer.toString(num));
                    bw.write(" ");
                }
                bw.write("\n");
            }
            return;
        }

        for(int i=0;i<arr.length;i++){
            if(visited[i])
                continue;

            sequence.addLast(arr[i]);
            visited[i] = true;
            backtrack(cur+1, size, arr, visited, sequence, bw);
            visited[i] = false;
            sequence.removeLast();
        }
    }
}
