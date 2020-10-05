/*
    baekjoon online judge
    problem number 5675
    https://www.acmicpc.net/problem/5675
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
        
        String str = null;
        while((str = br.readLine()) != null){
            int A = Integer.parseInt(str);

            if(A % 6 == 0){
                bw.write("Y\n");
            }else{
                bw.write("N\n");
            }
        }

        bw.close();
        br.close();
    }
}
