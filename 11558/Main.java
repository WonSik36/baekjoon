/*
    baekjoon online judge
    problem number 11558
    https://www.acmicpc.net/problem/11558

    Depth First Search
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            for(int j=0;j<N;j++){
                arr[j] = Integer.parseInt(br.readLine()) - 1;
            }

            int res = solve(N, arr);
            bw.write(Integer.toString(res));
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int solve(int N, int[] arr){
        boolean[] visited = new boolean[N];
        int cnt = 0;
        int cur = 0;

        while(true){
            if(cur == N-1)
                break;
            
            if(visited[cur]){
                cnt = 0;
                break;
            }
            visited[cur] = true;

            cnt++;
            cur = arr[cur];
        }

        return cnt;
    }
}
