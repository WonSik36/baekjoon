/*
    baekjoon online judge
    problem number 4354
    https://www.acmicpc.net/problem/4354

    KMP Algorithm
    Using Failure Function
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final String END = ".";
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while(true){
            String str = br.readLine();

            if(str.equals(END))
                break;

            int[] fail = getFail(str);
            // printArr(fail);
            int cnt = 0, idx = fail.length-1;

            if(fail[idx] != fail.length-1 && fail[idx] != 0)
                idx = fail[idx];

            if(str.charAt(0) != str.charAt(idx)){
                bw.write("1\n");
            }else{
                while(idx >= 0){
                    idx = fail[idx]-1;
                    cnt++;
                }
                
                bw.write(Integer.toString(cnt)+"\n");
            }
        }

        bw.close();
        br.close();
    }

    public static void printArr(int[] arr){
        for(int num: arr){
            System.out.printf("%d ",num);
        }
        System.out.println();
    }

    public static int[] getFail(String P){
        int M = P.length();
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
}
