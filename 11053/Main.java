/*
    baekjoon online judge
    problem number 11053
    https://www.acmicpc.net/problem/11053
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int[] arr = new int[num];
        inputStr = br.readLine();
        StringTokenizer st = new StringTokenizer(inputStr);

        for(int i=0;i<num;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[num];
        dp[0] = 1;
        int maxLen = dp[0];
        for(int i=1;i<num;i++){
            int max = 0;
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j] && dp[j]>max){
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
            if(maxLen<dp[i])
                maxLen = dp[i];
        }
        
        bw.write(Integer.toString(maxLen)+"\n");
        bw.flush();
        bw.close();
    }
}
