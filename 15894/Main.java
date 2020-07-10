/*
    baekjoon online judge
    problem number 15894
    https://www.acmicpc.net/problem/15894
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
        
        long N = Long.parseLong(br.readLine());

        bw.write(Long.toString(N*4)+"\n");

        bw.close();
        br.close();
    }
}
