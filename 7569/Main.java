/*
    baekjoon online judge
    problem number 7569
    https://www.acmicpc.net/problem/7569
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
        int h = Integer.parseInt(st.nextToken());
        int[][][] maps = new int[h][rows][cols];

        for(int k=0;k<h;k++){
            for(int i=0;i<rows;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<cols;j++){
                    maps[k][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
        // printMatrix(maps);
        Matrix mat = new Matrix(h,rows,cols,maps);
        // printMatrix(mat.maps);
        mat.BFS();
        // printMatrix(mat.count);

        bw.write(Integer.toString(mat.getMaxDay())+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static class Matrix{
        int heights;
        int rows;
        int cols;
        public int[][][] maps;
        // for bfs
        public int[][][] count;
        LinkedList<Point> list;
        boolean[][][] visited;

        public Matrix(int heights, int rows, int cols, int[][][] maps){
            this.heights = heights;
            this.rows = rows;
            this.cols = cols;
            this.maps = maps;
        }

        // hidden input: heights, rows, cols, count
        public int getMaxDay(){
            int max = 0;
            for(int k=0;k<heights;k++){
                for(int i=0;i<rows;i++){
                    for(int j=0;j<cols;j++){
                        if(count[k][i][j] == -1)
                            return -1;
                        else if(max < count[k][i][j])
                            max = count[k][i][j];
                    }
                }
            }
            return max;
        }

        // hidden input: heights,rows,cols,maps
        // hidden ouput: count
        public void BFS(){
            //initialize
            list = new LinkedList<Point>();
            visited = new boolean[heights][rows][cols];
            count = new int[heights][rows][cols];
            for(int j=0;j<heights;j++){
                for(int i=0;i<rows;i++){
                    Arrays.fill(count[j][i], -1);
                }
            }
            for(int k=0;k<heights;k++){
                for(int i=0;i<rows;i++){
                    for(int j=0;j<cols;j++){
                        if(maps[k][i][j] == 1){
                            list.add(new Point(k,i,j,0));
                        }else if(maps[k][i][j] == -1){
                            count[k][i][j] = -100;
                        }
                    }
                }
            }

            // BFS
            while(!list.isEmpty()){
                Point temp = list.removeFirst();
                int x = temp.x;
                int y = temp.y;
                int z = temp.z;
                int cnt = temp.cnt;
                // System.out.printf("%d %d %d\n",x,y,cnt);

                if(count[z][y][x] == -100 || visited[z][y][x])
                    continue;
                
                visited[z][y][x] = true;
                count[z][y][x] = temp.cnt;
                if(x>0)
                    list.add(new Point(z,y,x-1,cnt+1));
                if(x<cols-1)
                    list.add(new Point(z,y,x+1,cnt+1));
                if(y>0)
                    list.add(new Point(z,y-1,x,cnt+1));
                if(y<rows-1)
                    list.add(new Point(z,y+1,x,cnt+1));
                if(z>0)
                    list.add(new Point(z-1,y,x,cnt+1));
                if(z<heights-1)
                    list.add(new Point(z+1,y,x,cnt+1));
            }
        }
    }

    public static class Point{
        public int x;
        public int y;
        public int z;
        public int cnt;

        Point(int z, int y, int x, int cnt){
            this.x = x;
            this.y = y;
            this.z = z;
            this.cnt = cnt;
        }
    }

    public static void printMatrix(int[][][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                for(int k=0;k<arr[0][0].length;k++){
                    System.out.print(arr[i][j][k]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}