/*
    baekjoon online judge
    problem number 9461
    https://www.acmicpc.net/problem/9461
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    public static long[] p;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        p = new long[101];
        p[1] = 1;
        p[2] = 1;
        p[3] = 1;

        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        
        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            long result = padoban(Integer.parseInt(inputStr));
            bw.write(result+"\n");
        }
        
        bw.flush();
        bw.close();
    }

    static long padoban(int n){
        if(p[n] != 0)
            return p[n];
        else{
            long result = padoban(n-2) + padoban(n-3);
            p[n] = result;
            return result;
        }
    }
}