/*
    baekjoon online judge
    problem number 15897
    https://www.acmicpc.net/problem/15897

    Application of Dividers

    high reference: 
    https://docs.google.com/presentation/d/1y4f_ZCcWgCZocPZozsaFZpn2AJSx3ZtPwEFM3h7NurU/edit?usp=sharing
    https://js1jj2sk3.tistory.com/109
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
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        N--;

        long sum = 0;
        for (int i = N + 1; i != 0; i = N / ((N / i) + 1)){
            int p = N / i + 1;
            int q = i - (N / ((N / i) + 1));
            // System.out.printf("i:%d, p:%d, q:%d, p*q:%d\n",i,p,q,p*q);
            sum += p * q;
        }

        bw.write(Long.toString(sum)+"\n");

        bw.close();
        br.close();
    }

    static void printList(List<Integer> list){
        for(int i=0;i<list.size();i++){
            System.out.printf("i:%d, e: %d\n", i, list.get(i));
        }
        System.out.println();
    }
}
