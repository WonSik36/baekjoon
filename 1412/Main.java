/*
    baekjoon online judge
    problem number 1412
    https://www.acmicpc.net/problem/1412

    Floyd-Warshall Algorithm & Directed Acyclic Graph
    reference: https://akim9905.tistory.com/73

    If there is cycle with only Directed Edge
    then it will fail
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
        
        int N = Integer.parseInt(br.readLine());
        
        boolean[][] g = new boolean[N][N];
        for(int i=0;i<N;i++){
            String str = br.readLine();
            for(int j=0;j<N;j++){
                if(str.charAt(j) == 'Y')
                    g[i][j] = true;
                else
                    g[i][j] = false;
            }   
        }

        // directed edge graph
        boolean[][] deg = new boolean[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(g[i][j] && !g[j][i])
                    deg[i][j] = true;
            }
        }

        // floyd-warshall
        for(int k=0;k<N;k++){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(deg[i][k] && deg[k][j])
                        deg[i][j] = true;
                }
            }
        }

        // find cycle
        boolean flag = true;
        for(int i=0;i<N;i++){
            if(deg[i][i]){
                flag = false;
                break;
            }
        }

        if(flag)
            bw.write("YES\n");
        else
            bw.write("NO\n");

        bw.close();
        br.close();
    }
}
