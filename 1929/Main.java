/*
    baekjoon online judge
    problem number 1929
    https://www.acmicpc.net/problem/1929
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
        StringTokenizer st = new StringTokenizer(inputStr);
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        final int maxNum = 1000000;
        boolean[] primeArr = new boolean[maxNum+1];
        primeArr[0] = true; primeArr[1] = true;
        
        for(int i=2;i<=Math.sqrt(n);i++){
            for(int j=i*2;j<=n;j+=i){
                primeArr[j] = true;
            }
        }
        
        for(int i=m;i<=n;i++){
            if(!primeArr[i]){
                bw.write(Integer.toString(i)+"\n");
            }
        }
        
        bw.flush();
        bw.close();
    }
}
