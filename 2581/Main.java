/*
    baekjoon online judge
    problem number 2581
    https://www.acmicpc.net/problem/2581
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
        
        String inputStr = br.readLine();
        int m = Integer.parseInt(inputStr);
        inputStr = br.readLine();
        int n = Integer.parseInt(inputStr);

        final int maxNum = 10000;
        boolean[] primeArr = new boolean[maxNum+1];
        primeArr[0] = true; primeArr[1] = true;
        
        for(int i=2;i<=Math.sqrt(n);i++){
            for(int j=i*2;j<=n;j+=i){
                primeArr[j] = true;
            }
        }
        
        int sumOfPrime = 0;
        int minOfPrime = 0;
        for(int i=m;i<=n;i++){
            if(!primeArr[i]){
                sumOfPrime += i;
                if(minOfPrime == 0)
                    minOfPrime = i;
            }
        }
        
        if(sumOfPrime == 0){
            bw.write("-1\n");
        }
        else{
            String outputStr = Integer.toString(sumOfPrime) + "\n";
            bw.write(outputStr);
            outputStr = Integer.toString(minOfPrime) + "\n";
            bw.write(outputStr);
        }

        bw.flush();
        bw.close();
    }
}
