/*
    baekjoon online judge
    problem number 
    https://www.acmicpc.net/problem/
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
        
        String str = br.readLine();

        bw.write(Integer.toString(dp[str2.length()][str1.length()]));
        bw.flush();
        bw.close();
        br.close();
    }
}