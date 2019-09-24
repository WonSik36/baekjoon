/*
    baekjoon online judge
    problem number 5347
    https://www.acmicpc.net/problem/5347
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

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int gcd = GCD(x,y);
            long result = (long)x*y/gcd;
    
            bw.write(Long.toString(result)+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static int GCD(int x, int y){
        while(y != 0){
            int q = x % y;
            x = y;
            y = q;
        }

        return x;
    }
}
