/*
    baekjoon online judge
    problem number 11399
    https://www.acmicpc.net/problem/11399
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
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        // printArray(arr);
        int sum = 0;
        int time = 0;
        for(int i=0;i<N;i++){
            time += arr[i];
            sum += time;
        }
        bw.write(Integer.toString(sum)+"\n");
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