/*
    baekjoon online judge
    problem number 2579
    https://www.acmicpc.net/problem/2579
    https://kwanghyuk.tistory.com/4

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
        int[] stairs = new int[num];

        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            stairs[i] = Integer.parseInt(inputStr);
        }

        int[] dp = new int[num];
        
        dp[0] = stairs[0];
        dp[1] = Max(stairs[0]+stairs[1], stairs[1]);
        dp[2] = Max(stairs[0]+stairs[2], stairs[1]+stairs[2]);
        
        // dp(n) = stairs(n) + stairs(n-1) + dp(n-3)
        // or    = stairs(n) + dp(n-2)
        for(int i=3;i<num;i++){
            dp[i] = Max(stairs[i] + stairs[i-1] + dp[i-3], stairs[i] + dp[i-2]);
        }
        

        bw.write(Integer.toString(dp[num-1])+"\n");
        bw.flush();
        bw.close();
    }

    public static int Max(int a, int b){
        return a>b?a:b;
    }
}
