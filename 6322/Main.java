/*
    baekjoon online judge
    problem number 6322
    https://www.acmicpc.net/problem/6322
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
        
        int idx = 1;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a==0 && b==0 && c==0)
                break;

            String resStr = String.format("Triangle #%d\n",idx);
            bw.write(resStr);
            
            double res;
            if(a == -1){
                res = Math.sqrt(Math.pow(c,2) - Math.pow(b,2));

            }else if(b == -1){
                res = Math.sqrt(Math.pow(c,2) - Math.pow(a,2));
            }else{
                res = Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
            }

            if(Double.isNaN(res) || res == 0)
                resStr = "Impossible.\n\n";
            else{
                if(a == -1){
                    resStr = "a = ";
                }else if(b == -1){
                    resStr = "b = ";
                }else{
                    resStr = "c = ";
                }

                resStr += String.format("%.3f\n\n",res);
            }

            bw.write(resStr);
            idx++;
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
