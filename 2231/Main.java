/*
    baekjoon online judge
    problem number 2231
    https://www.acmicpc.net/problem/2231
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
        
        String inputStr = br.readLine();
        int inputNum = Integer.parseInt(inputStr);
        int n = inputNum;
        int ans = 0;
        boolean flag = false;

        while(n>0){
            if(decompositionSum(n) == inputNum){
                flag = true;
                ans = n;
            }
            n--;
        }
        if(flag){
            String outputStr = String.format("%d\n",ans);
            bw.write(outputStr);
        }else{
            bw.write("0\n");
        }
        bw.flush();
        bw.close();
    }

    public static int decompositionSum(int n){
        int num = n;
        int sum = 0;

        while(num>0){
            int digit = num%10;
            sum += digit;
            num /= 10;
        }
        return n+sum;
    }
    
}
