/*
    baekjoon online judge
    problem number 9372
    https://www.acmicpc.net/problem/9372

    Tree has N-1 Edge
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
        StringTokenizer st = null;

        int Num = Integer.parseInt(br.readLine());
        for(int i=0;i<Num;i++){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for(int j=0;j<M;j++){
                br.readLine();
            }

            bw.write(Integer.toString(N-1)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
