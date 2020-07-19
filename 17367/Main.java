/*
    baekjoon online judge
    problem number 17367
    https://www.acmicpc.net/problem/17367

    Dynamic Programming
    deep reference: https://sos0911.github.io/ps/alg17367/
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static int N;
    static double[][][][] memo;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        memo = new double[7][7][7][N+1];
        for(double[][][] m1 : memo){
            for(double[][] m2 : m1){
                for(double[] m3 : m2){
                    Arrays.fill(m3, -1);
                }
            }
        }

        double sum = 0;
        for(int i=1;i<=6;i++){
            for(int j=1;j<=6;j++){
                for(int k=1;k<=6;k++){
                    sum += dp(i,j,k,N-3);
                }
            }
        }

        String res = String.format("%.8f\n", sum / 216);
        bw.write(res);

        bw.close();
        br.close();
    }

    static double dp(int c1, int c2, int c3, int left){
        if(memo[c1][c2][c3][left] > 0)
            return memo[c1][c2][c3][left];

        double cur = (double)score(c1,c2,c3);
        if(left == 0){
            return memo[c1][c2][c3][0] = cur;
        }

        double more = 0;
        for(int i=1;i<=6;i++){
            more += dp(c2, c3, i, left-1);
        }

        return memo[c1][c2][c3][left] = Math.max(cur, more / 6);
    }

    static int score(int c1, int c2, int c3){
        if(c1 == c2 && c2 == c3){
            return 10000 + c1 * 1000;
        }else if(c1 == c2 || c2 == c3 || c3 == c1){
            return 1000 + ((c1==c2) ? c1 : c3) * 100;
        }else{
            int max = Math.max(c1, c2);
            max = Math.max(max, c3);
            return max * 100;
        }
    }
}
