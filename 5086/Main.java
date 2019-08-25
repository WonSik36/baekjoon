/*
    baekjoon online judge
    problem number 5086
    https://www.acmicpc.net/problem/5086
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
        
        while(true){
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a==0 && b==0)
                break;
            else if(b%a == 0)
                bw.write("factor\n");
            else if(a%b == 0)
                bw.write("multiple\n");
            else
                bw.write("neither\n");

        }
        bw.flush();
        bw.close();
        br.close();
    }
}