/*
    baekjoon online judge
    problem number 10844
    https://www.acmicpc.net/problem/10844
    https://debuglog.tistory.com/81
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
        int num = Integer.parseInt(inputStr);
        int[][] sNum = new int[num][10];
        int mod = 1000000000;
        // 1 digit number has 9 stair number
        for(int i=1;i<10;i++)
            sNum[0][i] = 1;

        // i is digit of number
        // j is smallest digit of number
        for(int i=0;i<num-1;i++){
            for(int j=1;j<9;j++){
                // (a+b)%c = (a%c+b%c)%c
                sNum[i+1][j] = (sNum[i][j-1] + sNum[i][j+1]) % mod;
            }
            sNum[i+1][0] = sNum[i][1];
            sNum[i+1][9] = sNum[i][8];
        }
        
        int sum = 0;
        for(int i=0;i<10;i++){
            sum = (sum + sNum[num-1][i]) % mod;
        }

        bw.write(Integer.toString(sum));
        bw.flush();
        bw.close();
    }
}
