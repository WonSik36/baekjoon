/*
    baekjoon online judge
    problem number 6989
    https://www.acmicpc.net/problem/6989
    http://www.digitalculture.or.kr/koi/selectOlymPiadDissentList.do
    Use Dynamic Programming but it can be improve to more faster way
    for memory limitation, it use bit operation by using char
    (In java char is only unsigned varaible)
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N];
        int[] sumScore = new int[N]; //sumScore[i]: when problems 0 ~ i are correct, i's score
        int[] maxScore = new int[N]; //maxScore[i]: when problems 0 ~ i are correct, 0 ~ i's score sum

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            score[i] = Integer.parseInt(st.nextToken());
        }
        int K = Integer.parseInt(br.readLine());
        initScores(N,score,sumScore,maxScore);
        
        int min = getImpossibleScore(N, K, score, maxScore);
        
        bw.write(Integer.toString(min)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getImpossibleScore(int N, int K, int[] score, int[] maxScore){
        if(K > maxScore[N-1])
            return K;

        // int[][] dp = new int[N][maxScore[N-1]+1];
        char[][] dp = new char[N][maxScore[N-1]/16+1];

        // first problem
        setBit(0, 0, dp);
        setBit(0, maxScore[0], dp);

        // dynamic programming
        for(int i=1;i<N;i++){
            int sum = 0;
            // dp(0,i) = dp(0,i-1) + (dp(0,i-2)+sum(i,i)) + (dp(0,i-3)+sum(i-1,i)) + (dp(0,i-4)+sum(i-2,i))
            // + ... + (dp(0,0) + sum(2,i)) + sum(1,i) + sum(0,i)
            for(int j=i;j>=1;j--){
                copyRangeBit(dp,j-1,maxScore[j-1],i,sum);
                sum += score[j]*(i-j+1);
            }
            setBit(i, sum, dp);
            setBit(i, maxScore[i], dp);
        }

        int minScore;
        // verify score i is possible start form K
        for(minScore=K; minScore<=maxScore[N-1]+1; minScore++){
            if(!getBit(N-1, minScore,dp))
                return minScore;
        }
        return -1;
    }

    // copy previous array to current array
    // copy range is 0 ~ prevMaxScore to curMaxScore ~ curMaxScore+prevMaxScore
    public static void copyRangeBit(char[][] dp, int prev, int prevMaxScore, int cur, int curMaxScore){
        int cur_th = curMaxScore /16;
        int cur_r = curMaxScore %16;
        int prev_th = prevMaxScore /16;
        
        if(cur_r == 0){
            for(int i=0;i<=prev_th;i++){
                dp[cur][cur_th+i] |= dp[prev][i];
            }
        }else{
            for(int i=0;i<=prev_th;i++){
                dp[cur][cur_th+i] |= (dp[prev][i] << cur_r);
                dp[cur][cur_th+i+1] |= (dp[prev][i] >> (16 - cur_r));
            }
        }
    }

    public static void initScores(int N, int[] score, int[] sumScore, int[] maxScore){
        sumScore[0] = score[0];
        maxScore[0] = sumScore[0];

        for(int i=1;i<N;i++){
            sumScore[i] = score[i] + sumScore[i-1];
            maxScore[i] = sumScore[i] + maxScore[i-1];
        }
    }

    public static void setBit(int row, int n, char[][] dp){
        int th = n /16;
        int r = n %16;
        dp[row][th] |= (1<<r);
    }

    public static boolean getBit(int row, int n, char[][] dp){
        int th = n /16;
        int r = n %16;
        if((dp[row][th] & (1<<r)) > 0)
            return true;
        return false;
    }
}