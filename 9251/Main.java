/*
    baekjoon online judge
    problem number 9251
    https://www.acmicpc.net/problem/9251
    https://twinw.tistory.com/126
    LCS(Longest Common Subsequence) Problem
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
        
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] dp = new int[str2.length()+1][str1.length()+1];

        for(int i=1;i<str2.length()+1;i++){
            for(int j=1;j<str1.length()+1;j++){
                if(str2.charAt(i-1) == str1.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Max(dp[i-1][j], dp[i][j-1]);
            }
        }

        bw.write(Integer.toString(dp[str2.length()][str1.length()]));
        bw.flush();
        bw.close();
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }
}
