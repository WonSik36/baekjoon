/*
    baekjoon online judge
    problem number 4948
    https://www.acmicpc.net/problem/4948
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
        
        final int maxNum = 123456 * 2;
        boolean[] primeArr = new boolean[maxNum+1];
        primeArr[0] = true; primeArr[1] = true;
        
        for(int i=2;i<=Math.sqrt(maxNum);i++){
            for(int j=i*2;j<=maxNum;j+=i){
                primeArr[j] = true;
            }
        }

        String inputStr;
        int num;

        while(true){
            inputStr = br.readLine();
            num = Integer.parseInt(inputStr);

            if(num == 0)
                break;

            //numbering prime number
            int numOfPrime = 0;
            for(int i=num+1;i<=2*num;i++){
                if(!primeArr[i])
                    numOfPrime++;
            }

            bw.write(Integer.toString(numOfPrime)+"\n");
        }

        
        bw.flush();
        bw.close();
    }
}
