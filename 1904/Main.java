/*
    baekjoon online judge
    problem number 1904
    https://www.acmicpc.net/problem/1904

    actually this problem is fibonacci problem
    tile(1) = 1     1
    tile(2) = 2     00,11
    tile(3) = tile(1) + tile(2) 1+00, 00+1, 11+1
    ...
    tile(n) = tile(n-1) + tile(n-2)
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        final int mod = 15746;
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int a = 1, b = 2;
        
        for(int i=1;i<num;i++){
            int temp = b;
            b = (a + b) % mod;  // (a+b)%c = (a%c + b%c)%c
            a = temp;
        }
        
        bw.write(Integer.toString(a)+"\n");
        bw.flush();
        bw.close();
    }
}
