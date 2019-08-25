/*
    baekjoon online judge
    problem number 11653
    https://www.acmicpc.net/problem/11653
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.lang.Math;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int N = Integer.parseInt(str);
        while(N>1){
            int idx = 2;
            int max = (int)Math.ceil(Math.sqrt(N)+1);
            while(N%idx != 0 && idx<max) 
                idx++;
            if(idx >= max){
                bw.write(Integer.toString(N)+"\n");
                break;
            }
            N /= idx;
            bw.write(Integer.toString(idx)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}