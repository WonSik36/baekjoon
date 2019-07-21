/*
    baekjoon online judge
    problem number 11444
    https://www.acmicpc.net/problem/11444
    https://www.acmicpc.net/blog/view/28
    look up problem number 1629, 2749
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.StringBuilder;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        long num = Long.parseLong(inputStr);    //input number is upto 1,000,000,000,000,000,000
        
        long result = fibo(num);
        bw.write(Long.toString(result)+"\n");
        
        bw.flush();
        bw.close();
    }
    
    public static long fibo(long n){        
        if(n == 0)
            return 0;
        else{
            long[][] result = {{1,0},{0,1}};
            long[][] fiboArr = {{1,1},{1,0}};
        
            while(n>0){
                if(n%2 == 1)
                    result = multiply(result, fiboArr);
                fiboArr = multiply(fiboArr, fiboArr);
                n /= 2;
            }

            return result[0][1];
        }
    }
    
    public static long[][] multiply(long[][] A, long[][] B){
        if(A[0].length != B.length){
            throw new RuntimeException("Matirx Multipy Exception");
        }

        final long mod = 1000000007;
        int col = A.length;
        int row = B[0].length;
        long[][] C = new long[col][row];

        for(int i=0;i<col;i++){
            for(int j=0;j<row;j++){
                for(int k=0;k<A[0].length;k++){
                    C[i][j] += A[i][k] * B[k][j];
                }
                C[i][j] %= mod;
            }
        }

        return C;
    }

    public static void printMatrix(int[][] A)throws IOException{
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[0].length;j++){
                System.out.print(A[i][j]+" ");
            }
            System.out.println();
        }
    }
}
