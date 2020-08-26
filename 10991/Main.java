/*
    baekjoon online judge
    problem number 10991
    https://www.acmicpc.net/problem/10991
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

        for(int i=1;i<=N;i++){
            for(int j=0;j<N-i;j++)
                bw.write(" ");

            for(int j=0;j<i;j++)    
                bw.write("* ");

            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
