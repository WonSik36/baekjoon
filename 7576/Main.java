/*
    baekjoon online judge
    problem number 7576
    https://www.acmicpc.net/problem/7576
    BFS has property that it visits each node shortest path
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Arrays;

public class Main{
    static boolean DEBUG = false;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cols = Integer.parseInt(st.nextToken());
        int rows = Integer.parseInt(st.nextToken());
        int[][] maps = new int[rows][cols];
        for(int i=0;i<rows;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<cols;j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Matrix mat = new Matrix(cols,rows,maps);
        // printMatrix(mat.maps);
        mat.BFS();
        // printMatrix(mat.count);

        bw.write(Integer.toString(mat.getMaxDay())+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static class Matrix{
        int rows;
        int cols;
        public int[][] maps;
        // for bfs
        public int[][] count;
        LinkedList<Point> list;
        boolean[][] visited;

        public Matrix(int cols, int rows, int[][] maps){
            this.cols = cols;
            this.rows = rows;
            this.maps = maps;
        }

        // hidden input: rows, cols, count
        public int getMaxDay(){
            int max = 0;
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(count[i][j] == -1)
                        return -1;
                    else if(max < count[i][j]){
                        max = count[i][j];
                    }
                }
            }
            return max;
        }

        // hidden input: rows,cols,maps
        // hidden ouput: count
        public void BFS(){
            //initialize
            list = new LinkedList<Point>();
            visited = new boolean[rows][cols];
            count = new int[rows][cols];
            for(int i=0;i<rows;i++){
                Arrays.fill(count[i], -1);
            }
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    if(maps[i][j] == 1){
                        list.add(new Point(j,i,0));
                    }else if(maps[i][j] == -1){
                        count[i][j] = -100;
                    }
                }
            }

            // BFS
            while(!list.isEmpty()){
                Point temp = list.removeFirst();
                int x = temp.x;
                int y = temp.y;
                int cnt = temp.cnt;
                // System.out.printf("%d %d %d\n",x,y,cnt);

                if(count[y][x] == -100 || visited[y][x])
                    continue;
                
                visited[y][x] = true;
                count[y][x] = temp.cnt;
                if(x>0)
                    list.add(new Point(x-1,y,cnt+1));
                if(x<cols-1)
                    list.add(new Point(x+1,y,cnt+1));
                if(y>0)
                    list.add(new Point(x,y-1,cnt+1));
                if(y<rows-1)
                    list.add(new Point(x,y+1,cnt+1));
            }
        }
    }

    public static class Point{
        public int x;
        public int y;
        public int cnt;

        Point(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void printMatrix(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}