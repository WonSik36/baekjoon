/*
    baekjoon online judge
    problem number 1356
    https://www.acmicpc.net/problem/1356
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
        
        String str = br.readLine();
        int N = str.length();
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = str.charAt(i) - '0';
        }

        boolean flag = false;
        for(int i=0;i<N-1;i++){
            int num1 = 1, num2 = 1;

            for(int j=0;j<=i;j++){
                num1 *= arr[j];
            }

            for(int j=i+1;j<N;j++){
                num2 *= arr[j];
            }

            if(num1 == num2){
                flag = true;
                break;
            }
        }

        if(flag)
            bw.write("YES\n");
        else
            bw.write("NO\n");

        bw.close();
        br.close();
    }
}
