/*
    baekjoon online judge
    problem number 1912
    https://www.acmicpc.net/problem/1912
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int[] arr = new int[num];
        int[] dp = new int[num];

        inputStr = br.readLine();
        StringTokenizer st = new StringTokenizer(inputStr);
        for(int i=0;i<num;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = -1000;
        dp[0] = arr[0];
        for(int i=1;i<num;i++){
            if(dp[i-1]+arr[i] < arr[i])
                dp[i] = arr[i];
            else
                dp[i] = dp[i-1] + arr[i];
            if(max<dp[i])
                max = dp[i];
        }
        max = dp[0]>max?dp[0]:max;

        bw.write(Integer.toString(max)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}