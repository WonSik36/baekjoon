/*
    baekjoon online judge
    problem number 1037
    https://www.acmicpc.net/problem/1037
    ** there is a question that if I take max, min it doesn't work **
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int N = Integer.parseInt(str);
        int[] arr = new int[N];
        str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        //int max = 0, min = 2^31-1;
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            // if(num > max)
            // max = num;
            // if(num < min)
            // min = num;
        }
        Arrays.sort(arr);
        int result = arr[0]*arr[N-1];
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
}