/*
    baekjoon online judge
    problem number 2740
    https://www.acmicpc.net/problem/2740
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
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] A = new int[N][M];
        for(int i=0;i<N;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            for(int j=0;j<M;j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }

        str = br.readLine();
        st = new StringTokenizer(str);
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] B = new int[M][K];
        for(int i=0;i<M;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            for(int j=0;j<K;j++)
                B[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] C = multiplyMatrix(A, B);
        printMatrix(C, bw);

        bw.flush();
        bw.close();
        br.close();
    }

    public static int[][] multiplyMatrix(int[][] A, int[][] B){
        int row = A.length;
        int col = B[0].length;
        int[][] C = new int[row][col];

        for(int i=0;i<row;i++)
            for(int j=0;j<col;j++)
                for(int k=0;k<A[0].length;k++){
                    C[i][j] += A[i][k] * B[k][j];
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
}