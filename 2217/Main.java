/*
    baekjoon online judge
    problem number 2217
    https://www.acmicpc.net/problem/2217
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
        for(int i=0;i<N;i++){
            str = br.readLine();
            arr[i] = Integer.parseInt(str);
        }
        Arrays.sort(arr);
        // printArray(arr);
        int w = 0;
        int max = 0;
        for(int i=N-1;i>=0;i--){
            w = arr[i]*(N-i);
            if(max < w)
                max = w;
        }
        bw.write(Integer.toString(max)+"\n");
        bw.flush();
        bw.close();
    }

    static void printArray(int[] arr)throws IOException{
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}