/*
    baekjoon online judge
    problem number 10026
    https://www.acmicpc.net/problem/10026
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Stack;
import java.util.Arrays;

public class Main{
    static boolean DEBUG = false;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        for(int i=0;i<N;i++){
            arr[i] = br.readLine();
        }
        int[][] matrix = parseMatrix(N, arr);
        Matrix mat = new Matrix(N,matrix);
        mat.DFS(true);  //DFS normal person
        int cntNormal = mat.getCount();
        mat.DFS(false); //DFS RG person
        int cntRG = mat.getCount();
        String output = String.format("%d %d\n", cntNormal, cntRG);
        bw.write(output);
        bw.flush();
        bw.close();
        br.close();
    }

    public static int[][] parseMatrix(int N, String[] arr){
        int[][] ret = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(arr[i].charAt(j) == 'R')
                    ret[i][j] = 0;
                else if(arr[i].charAt(j) == 'G')
                    ret[i][j] = 1;
                else
                    ret[i][j] = 2;
            }
        }

        return ret;
    }

    public static class Matrix{
        private int[][] color;
        private int[][] colorRG;
        private int N;
        private int[][] region;
        private int cnt;

        public Matrix(int N, int[][] matrix){
            this.color = matrix.clone();
            this.N = N;
            this.region = new int[N][N];
            this.colorRG = makeColorRG(N, matrix);
        }

        public void DFS(boolean flag){
            // initialize region and cnt
            for(int i=0;i<N;i++){
                Arrays.fill(region[i], -1);
            }
            cnt = 0;
            // execute DFS
            if(flag){
                for(int i=0;i<N;i++){
                    for(int j=0;j<N;j++){
                        _DFS(i,j,color[i][j],this.color,true);
                    }
                }
            }else{
                for(int i=0;i<N;i++){
                    for(int j=0;j<N;j++){
                        _DFS(i,j,colorRG[i][j],this.colorRG,true);
                    }
                }
            }
        }

        public void _DFS(int i, int j, int fromColor, int[][] color, boolean isFirst){
            if(region[i][j] != -1)
                return;
            if(isFirst)
                cnt++;

            if(fromColor == color[i][j]){
                region[i][j] = cnt;
                if(i>0)
                    _DFS(i-1,j,color[i][j],color,false);
                if(i<N-1)
                    _DFS(i+1,j,color[i][j],color,false);
                if(j>0)
                    _DFS(i,j-1,color[i][j],color,false);
                if(j<N-1)
                    _DFS(i,j+1,color[i][j],color,false);
            }else{
                return;
            }
        }

        public int getCount(){
            return cnt;
        }

        private int[][] makeColorRG(int N, final int[][] matrix){
            int[][] colorRG = new int[N][N];

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(matrix[i][j] == 0 || matrix[i][j] == 1){
                        colorRG[i][j] = 0;
                    }else{
                        colorRG[i][j] = 1;
                    }
                }
            }

            return colorRG;
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

    public static void print2DArray(int[][] arr){
        for(int i=0;i<arr.length;i++){
            printArray(arr[i]);
        }
    }
}
