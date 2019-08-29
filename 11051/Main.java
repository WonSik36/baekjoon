/*
    baekjoon online judge
    problem number 11051
    https://www.acmicpc.net/problem/11051
    solve by dynamic programming
    pascal's triangle
    nCk = (n-1)Ck + (n-1)C(k-1)
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    final static int mod = 10007;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][N+1];

        // initialize
        for(int i=1;i<=N;i++){
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        // initialize
        for(int i=2;i<=N;i++){
            dp[i][i-1] = i;
            dp[i][1] = i;
        }

        // get nCk by dynamic programming
        for(int i=3;i<=N;i++){
            for(int j=1;j<=N;j++){
                dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % mod;
            }
        }

        
        bw.write(Integer.toString(dp[N][K]));
        bw.flush();
        bw.close();
        br.close();
    }
}
