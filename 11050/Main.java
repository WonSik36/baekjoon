/*
    baekjoon online judge
    problem number 11050
    https://www.acmicpc.net/problem/11050
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
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int ret = 1;

        for(int i=N;i>(N-K);i--)
            ret *= i;

        ret /= factorial(K);
        
        bw.write(Integer.toString(ret));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int factorial(int a){
        int ret = 1;
        for(int i=2;i<=a;i++)
            ret *= i;
        return ret;
    }
}
