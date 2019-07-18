/*
    baekjoon online judge
    problem number 2749
    https://www.acmicpc.net/problem/2749
    https://www.acmicpc.net/blog/view/28
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        final int mod = 1000000;
        final int period = mod/10*15;   //10^k, K>2 period is 10^(k-1)*15
        long[] fiboArr = new long[period];
        fiboArr[0] = 0; fiboArr[1] = 1;

        String inputStr = br.readLine();
        long num = Long.parseLong(inputStr);
        
        for (int i=2; i<period; i++) {
            fiboArr[i] = fiboArr[i-1] + fiboArr[i-2];
            fiboArr[i] %= mod;
        }
        bw.write(Long.toString(fiboArr[(int)(num%period)])+"\n");

        bw.flush();
        bw.close();
    }
}
