/*
    baekjoon online judge
    problem number 2156
    https://www.acmicpc.net/problem/2156
    https://debuglog.tistory.com/80
    Firstly I thought it is similar to 2579
    But it wasn't, because d[n] can exclude wine[n]
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
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int[] wine = new int[num];
        int[] dp = new int[num];
        
        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            wine[i] = Integer.parseInt(inputStr);
        }

        dp[0] = wine[0];
        if(num>1)
            dp[1] = wine[0]+wine[1];
        if(num>2)
            dp[2] = Max(wine[0]+wine[1],wine[0]+wine[2],wine[1]+wine[2]);

        // dp[i] = dp[i-1]                  X
        // or  = dp[i-2]+wine[i]            O X O
        // or =  dp[i-3]+wine[i-1]+wine[i]  O X O O
        for(int i=3;i<num;i++){
            dp[i] = Max(dp[i-1], dp[i-2]+wine[i], dp[i-3]+wine[i-1]+wine[i]);
        }
        
        bw.write(Integer.toString(dp[num-1]));
        bw.flush();
        bw.close();
    }

    static int Max(int a, int b, int c){
        int max = a;
        if(b>max)
            max = b;
        if(c>max)
            max = c;
        return max;
    }
}