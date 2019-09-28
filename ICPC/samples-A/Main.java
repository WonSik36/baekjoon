/*
    ACM-ICPC Regional 2018
    Problem 
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
        
        long N = Long.parseLong(br.readLine());

        long res = 0;
       for(int i=0;i<100;i++){
            if((Math.pow(2,i)*i<=N) && (Math.pow(2,i+1)*(i+1)>N)){
                res = i;
                break;
            }
        }

        bw.write(Long.toString(res));

        bw.flush();
        bw.close();
        br.close();
    }
}