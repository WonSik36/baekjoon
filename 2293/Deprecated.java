/*
    baekjoon online judge
    problem number 2293
    https://www.acmicpc.net/problem/2293
    https://debuglog.tistory.com/78

    C(i): value of ith coin
    D(i,k): number of cases to make k when ith coin used

    D(i,k) = D(i-1,k)               if C(i) > k
          or D(i,k-C(i)) + D(i-1,k) if C(i) <= k
    
    ** But this algorithm take many memory
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Deprecated{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] coin = new int[N+1];
        int[][] dp = new int[N+1][target+1];
        for(int i=1;i<=N;i++){
            str = br.readLine();
            coin[i] = Integer.parseInt(str);
        }
        int cases = numOfCases(N,coin,target,dp);
        bw.write(Integer.toString(cases));
        bw.flush();
        bw.close();
    }

    static int numOfCases(int N, int[] coin, int target, int[][] dp)throws IOException{
        for(int i=1;i<=N;i++){
            if(coin[i]>target)
                continue;
            dp[i][coin[i]] = 1;
        }
        //printArray(dp);
        for(int i=1;i<=N;i++){
            for(int j=1;j<=target;j++){
                if(coin[i]>target)
                    dp[i][j] = dp[i-1][j];
                else{
                    int idx = (j-coin[i])>0?(j-coin[i]):0;
                    dp[i][j] += dp[i][idx] + dp[i-1][j];
                }
            }
        }
        //printArray(dp);
        return dp[N][target];
    }

    static void printArray(int[][] arr)throws IOException{
        for(int i=1;i<arr.length;i++){
            for(int j=1;j<arr[0].length;j++)
                System.out.print(arr[i][j]+" ");
            System.out.println();
        }
    }
}