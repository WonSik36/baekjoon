/*
    baekjoon online judge
    problem number 5624
    https://www.acmicpc.net/problem/5624

    Referece:
    https://jaimemin.tistory.com/876

    Make Triple Loop to Double Loop
    A+B+C = D -> A+B = D-C
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final int GAP = 200000;
    static boolean[] visited = new boolean[400001];
    static int N;
    static int[] arr;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<i;j++){
                // D-C
                if(visited[arr[i] - arr[j] + GAP]){
                    result++;
                    break;
                }
            }
            
            for(int j=0;j<=i;j++){
                // A+B
                visited[arr[i] + arr[j] + GAP] = true;
            }
        }

        bw.write(Integer.toString(result)+"\n");

        bw.close();
        br.close();
    }
}
