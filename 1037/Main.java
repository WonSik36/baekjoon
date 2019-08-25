/*
    baekjoon online judge
    problem number 1037
    https://www.acmicpc.net/problem/1037
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int N = Integer.parseInt(str);
        str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int max = 0, min = 2147483647;
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(st.nextToken());
            if(num > max)
            max = num;
            if(num < min)
            min = num;
        }
        int result = max*min;
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
        br.close();
    }
}