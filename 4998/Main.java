/*
    baekjoon online judge
    problem number 4998
    https://www.acmicpc.net/problem/4998
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
        StringTokenizer st = null;
        String str = null;

        while(true){
            str = br.readLine();
            if(str == null || str.equals(""))
                break;

            st = new StringTokenizer(str);
            double N = Double.parseDouble(st.nextToken());
            double B = Double.parseDouble(st.nextToken());
            double M = Double.parseDouble(st.nextToken());

            int num = 0;
            while(N <= M){
                N = N * (100 + B) / 100;
                num++;
            }

            bw.write(Integer.toString(num)+"\n");
        }

        bw.close();
        br.close();
    }
}
