/*
    baekjoon online judge
    problem number 2502
    https://www.acmicpc.net/problem/2502

    Dynamic Programming
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
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] memo = new int[D+1][3];
        memo[1][1] = 1;
        memo[2][2] = 1;

        for(int i=3;i<=D;i++){
            memo[i][1] = memo[i-1][1] + memo[i-2][1];
            memo[i][2] = memo[i-1][2] + memo[i-2][2];
        }

        int a = memo[D][1], b = memo[D][2];
        int A = 0, B = 0;
        for(int x=1;x<=K/2;x++){
            if(A != 0 && B != 0)
                break;

            for(int y=1;y<=K;y++){
                if(a*x+b*y == K){
                    A = x;
                    B = y;
                    break;
                }else if(a*x+b*y > K){
                    break;
                }
            }
        }

        bw.write(Integer.toString(A));
        bw.write("\n");
        bw.write(Integer.toString(B));
        bw.write("\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
