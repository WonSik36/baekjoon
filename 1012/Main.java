/*
    baekjoon online judge
    problem number 1012
    https://www.acmicpc.net/problem/1012
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static boolean DEBUG = false;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[][] map = new int[row][col];
            for(int j=0;j<k;j++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
            }
            Matrix mat = new Matrix(map);
            // mat.printMap();
            mat.DFS();
            bw.write(mat.getCount()+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class Matrix{
        private int[][] map;
        private int rows;
        private int cols;
        private boolean[][] visited;
        private int cnt;

        public Matrix(int[][] matrix){
            this.map = matrix;
            this.rows = matrix.length;
            this.cols = matrix[0].length;
        }

        public int getCount(){
            return cnt;
        }

        public void DFS(){
            // initialize visited and cnt
            visited = new boolean[rows][cols];
            cnt = 0;
            // execute DFS
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    _DFS(i,j,map[i][j],true);
                }
            }
        }

        public void printMap(){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        }

        public void _DFS(int i, int j, int from, boolean isFirst){
            if(visited[i][j])
                return;
            if(isFirst && map[i][j] != 0){
                // System.out.println("Visited First");
                cnt++;
            }

            if(from == map[i][j]){
                visited[i][j] = true;
                if(i>0)
                    _DFS(i-1,j,map[i][j],false);
                if(i<rows-1)
                    _DFS(i+1,j,map[i][j],false);
                if(j>0)
                    _DFS(i,j-1,map[i][j],false);
                if(j<cols-1)
                    _DFS(i,j+1,map[i][j],false);
            }else{
                return;
            }
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
