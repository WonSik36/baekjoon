/*
    baekjoon online judge
    problem number 1463
    https://www.acmicpc.net/problem/1463
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
        int[] arr = new int[num+1];
        arr[1] = 0;

        for(int i=2;i<=num;i++){
            int min = arr[i-1]+1;
            if(i%3 == 0)
                min = Min(min,arr[i/3] + 1);
            if(i%2 == 0)
                min = Min(min,arr[i/2] + 1);

            arr[i] = min;
        }
        
        bw.write(Integer.toString(arr[num])+"\n");
        bw.flush();
        bw.close();
    }

    static int Min(int a, int b){
        return a<b?a:b;
    }
}
