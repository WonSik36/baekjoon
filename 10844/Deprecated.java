/*
    baekjoon online judge
    problem number 10844
    https://www.acmicpc.net/problem/10844
    This is deprecated because when i over 70,
    the sum is over long variable range
    And (a+b)%c = (a%c+b%c)%c can't used in here
    because sNum[i] % c = (2*sNum[i-1]-i) % c != (2*(sNum[i-1]%c)-i) % c
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Deprecated{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        long[] sNum = new long[num];
        sNum[0] = 9;

        for(int i=1;i<num;i++){
            sNum[i] = sNum[i-1]*2 - i;
        }
        
        //long mod = 1000000000;
        //bw.write(Long.toString(sNum[num-1]%mod));
        bw.write(Long.toString(sNum[num-1]));
        bw.flush();
        bw.close();
    }
}
