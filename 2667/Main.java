/*
    baekjoon online judge
    problem number 2667
    https://www.acmicpc.net/problem/2667
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Stack;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

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
        // print2DArray(matrix);
        Matrix mat = new Matrix(N,matrix);
        mat.DFS();  //DFS normal person
        // print2DArray(mat.region);
        mat.printResult(bw);
        bw.flush();
        bw.close();
        br.close();
    }

    public static int[][] parseMatrix(int N, String[] arr){
        int[][] ret = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(arr[i].charAt(j) == '0')
                    ret[i][j] = 0;
                else if(arr[i].charAt(j) == '1')
                    ret[i][j] = 1;
            }
        }

        return ret;
    }

    public static class Matrix{
        private int[][] color;
        private int N;
        public int[][] region;
        private int cnt;
        private ArrayList<Integer> list;

        public Matrix(int N, int[][] matrix){
            this.color = matrix.clone();
            this.N = N;
            this.region = new int[N][N];
        }

        public void DFS(){
            // initialize region and cnt
            for(int i=0;i<N;i++){
                Arrays.fill(region[i], -1);
            }
            cnt = 0;
            list = new ArrayList<Integer>();
            list.add(0);
            // execute DFS
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    _DFS(i,j,color[i][j],this.color,true);
                }
            }
        }

        public void printResult(BufferedWriter bw)throws IOException{
            bw.write(Integer.toString(cnt)+"\n");
            list.remove(0);
            Collections.sort(list);
            for(int i=0;i<list.size();i++){
                int num = list.get(i);
                bw.write(Integer.toString(num)+"\n");
            }
        }

        public void _DFS(int i, int j, int fromColor, int[][] color, boolean isFirst){
            if(region[i][j] != -1)
                return;
            if(isFirst && color[i][j] != 0){
                cnt++;
                list.add(0);
            }

            if(fromColor == color[i][j]){
                if(fromColor == 0){
                    region[i][j] = 0;    
                    list.set(0, list.get(0)+1);
                }else{
                    region[i][j] = cnt;
                    list.set(cnt, list.get(cnt)+1);
                }

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
        System.out.println();
    }
}
