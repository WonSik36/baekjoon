/*
    baekjoon online judge
    problem number 10990
    https://www.acmicpc.net/problem/10990
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

        for(int i=0;i<N;i++){
            int cnt = 0;
            for(int j=1;j<=2*N-1;j++){
                if(j == N-i || j == N+i){
                    bw.write("*");
                    cnt++;
                    if(i==0 || cnt == 2){
                        break;
                    }
                }else{
                    bw.write(" ");
                }
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
