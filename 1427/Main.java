/*
    baekjoon online judge
    problem number 1427
    https://www.acmicpc.net/problem/1427
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
        int[] numArr = new int[10];
        char ch;
        
        for(int i=0;i<inputStr.length();i++){
            ch = inputStr.charAt(i);            
            numArr[ch-'0']++;
        }
        
        for(int i=9;i>=0;i--){
            for(int j=0;j<numArr[i];j++){
                bw.write(Integer.toString(i));
            }
        }

        //bw.write(outputStr);
        bw.flush();
        bw.close();
    }
}
