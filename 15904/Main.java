/*
    baekjoon online judge
    problem number 15904
    https://www.acmicpc.net/problem/15904
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final char[] pattern = {'U', 'C', 'P', 'C'};
    static final String PASS = "I love UCPC\n";
    static final String FAIL = "I hate UCPC\n";

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();

        boolean flag = false;
        for(int i=0, idx=0;i < str.length(); i++){
            if(str.charAt(i) == pattern[idx]){
                idx++;

                if(idx == pattern.length){
                    flag = true;
                    break;
                }
            }
        }

        if(flag)
            bw.write(PASS);
        else
            bw.write(FAIL);

        bw.close();
        br.close();
    }
}
