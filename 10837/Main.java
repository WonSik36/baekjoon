/*
    baekjoon online judge
    problem number 10837
    https://www.acmicpc.net/problem/10837
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
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int K = Integer.parseInt(str);
        int[][] result = new int[K+1][K+1];
        for(int i=0;i<=K;i++)
            Arrays.fill(result[i], -1);
        result[0][0] = 1;

        str = br.readLine();
        int C = Integer.parseInt(str);

        for(int i=0;i<C;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            bw.write(Integer.toString(calculateResult(K,M,N,result))+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int calculateResult(int K, int M, int N, int[][] result){
        if(result[M][N]!=-1){
            return result[M][N];
        }

        if((M!=0) && (calculateResult(K,M-1,N,result) == 1)){
            if(!isDone(K,M-1,N,true)){
                result[M][N] = 1;
                return 1;
            }
        }

        if((N!=0) && (calculateResult(K,M,N-1,result) == 1)){
            if(!isDone(K,M,N-1,false)){
                result[M][N] = 1;
                return 1;
            }
        }

        result[M][N] = 0;
        return 0;
    }

    public static boolean isDone(int K, int M, int N, boolean order){
        if(order){
            return isDoneLeftOrder(K, M, N);
        }else{
            return isDoneRightOrder(K, M, N);
        }
    }

    public static boolean isDoneLeftOrder(int K, int M, int N){
        if(M<N){
            if(K-N+M>=N)
                return false;
            else
                return true;
        }else{
            if(K-M+N>=M)
                return false;
            else
                return true;
        }
    }

    public static boolean isDoneRightOrder(int K, int M, int N){
        if(M<N){
            if(K-N+M>N)
                return false;
            else
                return true;
        }else{
            if(K-M+N+1>=M)
                return false;
            else
                return true;
        }
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
