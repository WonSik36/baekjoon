/*
    baekjoon online judge
    problem number 2579
    https://www.acmicpc.net/problem/2579
    reverse think by calculating skipped stairs
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
        int[] stairs = new int[num];
        int sum = 0;

        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            stairs[i] = Integer.parseInt(inputStr);
            sum += stairs[i];
        }

        for(int i=3;i<num-1;i++){
            stairs[i] += Min(stairs[i-2], stairs[i-3]);
        }
        stairs[num-1] = stairs[num-3];

        int result = Min(stairs[num-2], stairs[num-1]);
        result = sum - result;

        bw.write(Integer.toString(result)+"\n");
        bw.flush();
        bw.close();
    }

    public static int Min(int a, int b){
        return a<b?a:b;
    }
}
