/*
    baekjoon online judge
    problem number 17362
    https://www.acmicpc.net/problem/17362
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

        N = (N-1) % 8;

        int res = 0;
        switch(N){
            case 0:
                res = 1;
                break;
            case 1:
            case 7:
                res = 2;
                break;
            case 2:
            case 6:
                res = 3;
                break;
            case 3:
            case 5:
                res = 4;
                break;
            case 4:
                res = 5;
                break;
        }

        if(res == 0)
            throw new AssertionError();

        bw.write(Integer.toString(res)+"\n");

        bw.close();
        br.close();
    }
}
