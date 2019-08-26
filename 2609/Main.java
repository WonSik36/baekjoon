/*
    baekjoon online judge
    problem number 2609
    https://www.acmicpc.net/problem/2609
    https://gomcine.tistory.com/207

    ** GCD(A,B) **
    if A>B
    A%B == 0 -> B is GCD
    A%B == C -> GCD(B,C) keep going until X%Y == 0

    ** LCM(A,B) **
    LCM = A * B / GCD = a * GCD * b * GCD / GCD = a*b*GCD
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
        StringTokenizer st = new StringTokenizer(str);
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        if(A<B){
            int temp = A;
            A = B;
            B = temp;
        }
        int x=A,y=B;
        while(x%y!=0){
            int temp = y;
            y = x%y;
            x = temp;
        }
        int GCD = y;
        int a = A/GCD, b= B/GCD;
        int LCM = a*b*GCD;

        bw.write(Integer.toString(GCD)+"\n");
        bw.write(Integer.toString(LCM)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
