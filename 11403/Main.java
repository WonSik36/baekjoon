/*
    baekjoon online judge
    problem number 11403
    https://www.acmicpc.net/problem/11403

    Solve by using Floyd-Warshall Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static int N;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        boolean[][] graph = new boolean[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                if(st.nextToken().equals("1"))
                    graph[i][j] = true;
            }
        }

        // floyd-warshall
        for(int k=0;k<N;k++){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(graph[i][k] && graph[k][j])
                        graph[i][j] = true;
                }
            }
        }

        // print result
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(graph[i][j])
                    bw.write("1 ");
                else
                    bw.write("0 ");
            }
            bw.write("\n");
        }

        bw.close();
        br.close();
    }
}
