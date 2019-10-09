/*
    baekjoon online judge
    problem number 6989
    https://www.acmicpc.net/problem/6989
    http://www.digitalculture.or.kr/koi/selectOlymPiadDissentList.do
    Use Dynamic Programming but it can be improve to more faster way
    for memory limitation, it use bit operation by using char
    (In java char is only unsigned varaible)
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

public class Main{
    static int cnt = 0;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

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

        char[][] dp = new char[N][maxScore[N-1]/16+1];

        initDP(dp, N, score, maxScore);
        // printDP(dp);
        
        // verify score i is possible start form K
        for(int minScore=K; minScore<=maxScore[N-1]+1; minScore++){
            if(!isPossibleScore(N,minScore,dp,score,maxScore))
                return minScore;
        }
        return -1;
    }

    public static boolean isPossibleScore(int N, int K, char[][] dp, int[] score, int[] maxScore){
        // System.out.println(cnt++);
        if(K > maxScore[N-1])
            return false;
        
        char[][] visited = new char[N][maxScore[N-1]/16+1];
        Stack<Pair> stack = new Stack<Pair>();
        stack.add(new Pair(N-1,K));
        Pair pair = null;

        while(!stack.isEmpty()){
            pair = stack.pop();

            if(getBit(pair.left, pair.right, dp)){
                // register dp[pair.left][pair.right] = 1 from true pair to ancestor pairs
                registerPair2DP(pair, dp);
                // System.out.printf("Pair %d %d is key\n",pair.left,pair.right); 
                return true;
            }

            System.out.printf("pair popped: %d %d\n",pair.left, pair.right);
            int sum = 0;
            for(int i=pair.left;i>0;i--){
                if(pair.right-sum < 0)
                    break;
                else if(!getBit(i-1,pair.right-sum,visited)){
                    System.out.printf("pair added: %d %d\n",i-1, pair.right-sum);
                    stack.add(new Pair(i-1,pair.right-sum,pair));
                    setBit(i-1,pair.right-sum,visited);
                }else{
                    System.out.printf("pair visited: %d %d\n",i-1, pair.right-sum);
                }
                sum += score[i]*(pair.left-i+1);
            }
        }


        return false;
    }

    public static void initDP(char[][] dp, int N, int[] score, int[] maxScore){
        for(int i=0;i<N;i++){
            int sum = 0;
            for(int j=i;j>=1;j--){
                sum += score[j]*(i-j+1);
                setBit(i, sum, dp);
            }

            setBit(i, 0, dp);
            setBit(i, score[i], dp);
            setBit(i, maxScore[i], dp);
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

    public static void printDP(char[][] dp){
        for(int i=0;i<dp.length;i++){
            // System.out.printf("dp[%d]: ", i);
            for(int j=0;j<dp[0].length;j++){
                char temp = dp[i][j];
                for(int k=0;k<16;k++){
                    char one = 1;
                    System.out.printf("%d ",(int)(one&temp));
                    temp = (char)(temp>>1);
                }
            }
            System.out.println();
        }
    }

    public static class Pair{
        public int left;
        public int right;
        public Pair parent;

        public Pair(int left, int right){
            this.left = left;
            this.right = right;
        }

        public Pair(int left, int right, Pair parent){
            this(left,right);
            this.parent = parent;
        }
    }

    public static void registerPair2DP(Pair pair, char[][] dp){
        Pair temp = pair;
        
        while(temp != null){
            // System.out.printf("Pair %d %d is possible\n",temp.left,temp.right);
            setBit(temp.left,temp.right,dp);
            temp = temp.parent;
        }
    }
}