/*
    baekjoon online judge
    problem number 1786
    https://www.acmicpc.net/problem/1786

    KMP Algorithm
    high reference: 
    https://bowbowbow.tistory.com/6
    https://m.blog.naver.com/kks227/220917078260
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main{
    static int N;   // length of T
    static int M;   // length of P
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String T = br.readLine();
        String P = br.readLine();

        N = T.length();
        M = P.length();

        List<Integer> matched = kmp(T,P);
        bw.write(Integer.toString(matched.size())+"\n");
        for(int a: matched){
            bw.write(Integer.toString(a));
            bw.write(" ");
        }
        bw.write("\n");

        bw.close();
        br.close();
    }

    public static List<Integer> kmp(String T, String P){
        List<Integer> res = new ArrayList<>();
        int[] fail = getFail(P);

        for(int i=0, j=0; i < N; i++){
            while(j > 0 && T.charAt(i) != P.charAt(j))
                j = fail[j-1];

            if(T.charAt(i) == P.charAt(j)){
                if(j == M-1){
                    res.add(i-M+1+1);   // idx start from 1

                    j = fail[M-1];
                }else{
                    j++;
                }
            }

        }

        return res;
    }

    public static int[] getFail(String P){
        int[] fail = new int[M];

        for(int i=1, j=0;i<M;i++){
            while(j>0 && P.charAt(i) != P.charAt(j))
                j = fail[j-1];
            
            if(P.charAt(i) == P.charAt(j))
                fail[i] = ++j;
        }

        // printArr(fail);

        return fail;
    }

    public static void printArr(int[] arr){
        for(int a: arr){
            System.out.printf("%d ", a);
        }
        System.out.println();
    }
}
