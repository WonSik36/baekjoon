/*
    baekjoon online judge
    problem number 12761
    https://www.acmicpc.net/problem/12761
    https://jaimemin.tistory.com/947
    using BFS
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static boolean DEBUG = false;
    static final int MAX = 100000;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int min = backTrace(A,B,N,M);

        bw.write(Integer.toString(min));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dp(int A, int B, int N, int M){
        int[] rock = new int[MAX+1];
        Arrays.fill(rock, MAX);
        rock[N] = 0;
        
        if(N < M){
            for(int i=N;i<=MAX;i++){
                if(i+1 <= MAX)
                    rock[i+1] = MIN(rock[i+1],rock[i]+1);
                if(i+A <= MAX)
                    rock[i+A] = MIN(rock[i+A],rock[i]+1);
                if(i+B <= MAX)
                    rock[i+B] = MIN(rock[i+B],rock[i]+1);
                if(i*A <= MAX)
                    rock[i+A] = MIN(rock[i*A],rock[i]+1);
                if(i*B <= MAX)
                    rock[i+B] = MIN(rock[i+1],rock[i]+1);
            }
        }else if(N > M){

        }else{
            return 0;
        }
    }

    public static int MIN(int a, int b){
        return a<b?a:b;
    }

    public static void print(int num){
        if(DEBUG)
            System.out.println(num);
    }

    public static void print(String str){
        if(DEBUG)
            System.out.print(str);
    }

    public static void print(String str, int... ints){
        if(DEBUG){
            String[] spl = str.split("%d");
            String output = spl[0];
            int idx = 1;
            for(int number:ints){
                output += number;
                if(idx < spl.length)
                    output += spl[idx++];
            }
            System.out.print(output);
        }
    }

    public static void printArray(int[] arr){
        if(DEBUG){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
    }
}
