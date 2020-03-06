/*
    baekjoon online judge
    problem number 2410
    https://www.acmicpc.net/problem/2410

    Dynamic Programming
    
    high reference:
    https://casterian.net/archives/176

    memo[i][j] = memo[i][j-1] + memo[i-2^j][j]
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final int mod = 1000000000;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[][] memo = new int[N+1][20];    // Max N = 1000000, 2^19 < Max N < 2^20

        int result = dp(N,19,memo);

        bw.write(Integer.toString(result)+"\n");
        bw.close();
        br.close();
    }

    static int dp(int target, int binary, int[][] memo){
        if(memo[target][binary] != 0)
            return memo[target][binary];

        // the way to express 2^N by 2^N
        if(target == 0)
            return 1;

        // the way to express N by 2^0
        if(binary == 0)
            return 1;

        if(target < Math.pow(2,binary)){
            memo[target][binary] = dp(target, binary-1, memo);
        }else{
            memo[target][binary] = (dp((int)(target - Math.pow(2,binary)), binary, memo) + dp(target,binary-1,memo)) % mod;
        }

        return memo[target][binary];
    }
}
