/*
    baekjoon online judge
    problem number 10830
    https://www.acmicpc.net/problem/10830
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
        
        final int mod = 1000;
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        int[][] A = new int[N][N];
        for(int i=0;i<N;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            for(int j=0;j<N;j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] res = calcPow(A, B, mod);
        printMatrix(res, bw);

        bw.flush();
        bw.close();
        br.close();
    }

    public static int[][] multiplyMatrix(int[][] A, int[][] B, int mod){
        int row = A.length;
        int col = B[0].length;
        int[][] C = new int[row][col];

        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++){
                for(int k=0;k<A[0].length;k++){
                    C[i][j] += A[i][k] * B[k][j];
                }
                C[i][j] %= mod;
            }

        return C;
    }

    public static void printMatrix(int[][] A, BufferedWriter bw)throws IOException{
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                bw.write(Integer.toString(A[i][j])+" ");
            }
            bw.write("\n");
        }
    }

    public static int[][] calcPow(int[][] A, long n, int mod){
        int[][] result = new int[A.length][A.length];
        for(int i=0;i<A.length;i++)
            result[i][i] = 1;
        int[][] multiply = A.clone();

        while(n > 0){
            if(n%2 == 1){
                result = multiplyMatrix(result, multiply, mod);
            }
            multiply = multiplyMatrix(multiply, multiply, mod);
            n /= 2;
        }
        return result;
    }
}