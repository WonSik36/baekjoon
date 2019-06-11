/*
    baekjoon online judge
    problem number 9020
    https://www.acmicpc.net/problem/9020
    Prime Number Problem
    에라토스테네스
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        final int maxNum = 10000;
        boolean[] primeArr = new boolean[maxNum+1];
        primeArr[0] = true; primeArr[1] = true;
        
        for(int i=2;i<=Math.sqrt(maxNum);i++){
            for(int j=i*2;j<=maxNum;j+=i){
                primeArr[j] = true;
            }
        }
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int n;

        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            n = Integer.parseInt(inputStr);
            int j=0;
            while(true){
                if(primeArr[n/2+j]==false && primeArr[n/2-j]==false)
                    break;
                j++;
            }

            String outputStr = String.format("%d %d\n", n/2-j, n/2+j);
            bw.write(outputStr);
        }
        
        bw.flush();
        bw.close();
    }
}
