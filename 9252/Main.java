/*
    baekjoon online judge
    problem number 9252
    https://www.acmicpc.net/problem/9252
    https://twinw.tistory.com/126
    LCS(Longest Common Subsequence) Problem
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.StringBuilder;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str1 = br.readLine();
        String str2 = br.readLine();

        int strlen1 = str1.length();
        int strlen2 = str2.length();

        /* get LCS length */
        int[][] memo = new int[strlen1+1][strlen2+1];
        for(int i=1;i<=strlen1;i++){
            for(int j=1;j<=strlen2;j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    memo[i][j] = memo[i-1][j-1] + 1;
                }else{
                    memo[i][j] = Max(memo[i-1][j], memo[i][j-1]);
                }
            }
        }
        bw.write(Integer.toString(memo[strlen1][strlen2])+"\n");
        // printArr(memo);

        /* get LCS */
        int idx = memo[strlen1][strlen2];
        StringBuilder sb = new StringBuilder();
        for(int i=strlen1;i>0;i--){
            for(int j=strlen2;j>0;j--){
                if(memo[i][j] == idx && memo[i-1][j] == idx-1 && memo[i][j-1] == idx-1 && memo[i-1][j-1] == idx-1){
                    sb.insert(0, str1.charAt(i-1));
                    idx--;
                    break;
                }
            }
        }
        sb.append("\n");
        bw.write(sb.toString());

        bw.close();
        br.close();
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }

    static void printArr(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.printf("%d ",arr[i][j]);
            }
            System.out.println();
        }
    }
}
