/*
    baekjoon online judge
    problem number 1676
    https://www.acmicpc.net/problem/1676
    it ask how many 10 is in factorial(n)
    and it can change how many 5 in factorial(n) (Because there are many 2)
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
        final int factor = 5;
        String str = br.readLine();
        int N = Integer.parseInt(str);
        int sum = 0;
        for(int i=factor;i<=N;i+=factor){
            sum += getHowManyN(i,factor);
        }
        
        bw.write(Integer.toString(sum)+"\n");
        // bw.write(Long.toString(factorial(N))+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getHowManyN(int target, int n){
        int ret = 0;
        while(target%n==0){
            ret++;
            target /= n;
        }
        return ret;
    }

    public static long factorial(int n){
        long ret = 1;
        for(int i=2;i<=n;i++)
            ret *= i;
        return ret;
    }
}