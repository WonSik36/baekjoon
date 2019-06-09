/*
    baekjoon online judge
    problem number 1978
    https://www.acmicpc.net/problem/1978
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
        
        final int maxNum = 1000;
        boolean[] primeArr = new boolean[maxNum+1];
        primeArr[0] = true; primeArr[1] = true;
        
        for(int i=2;i<=Math.sqrt(maxNum);i++){
            for(int j=i*2;j<=maxNum;j+=i){
                primeArr[j] = true;
            }
        }
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        inputStr = br.readLine();
        StringTokenizer st = new StringTokenizer(inputStr);
        int numOfPrime = 0;
        int n;
        for(int i=0;i<num;i++){
            n = Integer.parseInt(st.nextToken());
            if(!primeArr[n])
                numOfPrime++;
        }
        String outputStr = Integer.toString(numOfPrime) + "\n";
        bw.write(outputStr);
        bw.flush();
        bw.close();
    }
}
