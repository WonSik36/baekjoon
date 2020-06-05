/*
    baekjoon online judge
    problem number 1305
    https://www.acmicpc.net/problem/1305

    KMP Algorithm
    Using Failure Function
    reference: https://zoomkoding.github.io/%EB%B0%B1%EC%A4%80/2019/09/16/baekjoon-1305.html
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int[] fail = getFailure(str);

        int result = fail.length - fail[fail.length-1];
        bw.write(Integer.toString(result)+"\n");

        bw.close();
        br.close();
    }

    public static void printArr(int[] arr){
        for(int a: arr){
            System.out.printf("%d ", a);
        }
        System.out.println();
    }

    public static int[] getFailure(String P){
        char[] arr = P.toCharArray();
        int[] fail = new int[arr.length]; 

        for(int i=1,j=0; i<arr.length; i++){
            while(j>0 && arr[i] != arr[j])
                j = fail[j-1];

            if(arr[i] == arr[j])
                fail[i] = ++j;
        }

        return fail;
    }
}
