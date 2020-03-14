/*
    baekjoon online judge
    problem number 2306
    https://www.acmicpc.net/problem/2306

    Dynamic Programming
    reference: https://blog.encrypted.gg/125
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static int[][] memo;
    static String input;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        input = br.readLine();
        memo = new int[input.length()][input.length()];
        for(int i=0;i<memo.length;i++){
            for(int j=0;j<memo.length;j++){
                memo[i][j] = -1;
            }
        }

        int result = dp(0,input.length()-1);

        bw.write(Integer.toString(result)+"\n");
        bw.close();
        br.close();
    }

    static int dp(int start, int end){
        if(memo[start][end] != -1)
            return memo[start][end];

        if(start == end){
            memo[start][end] = 0;
            return memo[start][end];
        }

        if(start+1 == end){
            if(input.charAt(start)=='a' && input.charAt(end)=='t'){
                memo[start][end] = 2;
            }else if(input.charAt(start)=='g' && input.charAt(end)=='c'){
                memo[start][end] = 2;
            }else{
                memo[start][end] = 0;
            }
            return memo[start][end];
        }

        memo[start][end] = 0;
        for(int i=start+1; i<end;i++){
            memo[start][end] = Max(memo[start][end], dp(start,i)+dp(i,end));
        }

        if(input.charAt(start)=='a' && input.charAt(end)=='t'){
            memo[start][end] = Max(memo[start][end], dp(start+1,end-1)+2);
        }else if(input.charAt(start)=='g' && input.charAt(end)=='c'){
            memo[start][end] = Max(memo[start][end], dp(start+1,end-1)+2);
        }

        return memo[start][end];
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }
}
