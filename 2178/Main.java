/*
    baekjoon online judge
    problem number 2178
    https://www.acmicpc.net/problem/2178
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
        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());
        int[][] maps = new int[rows][cols];
        for(int i=0;i<rows;i++){
            String str = br.readLine();
            for(int j=0;j<cols;j++){
                maps[i][j] = str.charAt(j) - '0';
            }
        }

        Matrix mat = new Matrix(cols,rows,maps);
        // mat.printMatrix();
        int res = mat.BFS();

        bw.write(Integer.toString(res)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static class Matrix{
        int rows;
        int cols;
        int[][] maps;
        LinkedList<Point> list;
        boolean[][] visited;

        public Matrix(int cols, int rows, int[][] maps){
            this.cols = cols;
            this.rows = rows;
            this.maps = maps;
        }

        public void printMatrix(){
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    System.out.print(maps[i][j]+" ");
                }
                System.out.println();
            }
        }

        public int BFS(){
            list = new LinkedList<Point>();
            visited = new boolean[rows][cols];
            list.add(new Point(0,0,1));

            while(!list.isEmpty()){
                Point temp = list.removeFirst();
                int x = temp.x;
                int y = temp.y;
                int cnt = temp.cnt;
                // System.out.printf("%d %d %d\n",x,y,cnt);

                if(maps[y][x] == 0 || visited[y][x])
                    continue;
                else if(x == cols-1 && y == rows-1)
                    return cnt;
                
                visited[y][x] = true;
                if(x>0)
                    list.add(new Point(x-1,y,cnt+1));
                if(x<cols-1)
                    list.add(new Point(x+1,y,cnt+1));
                if(y>0)
                    list.add(new Point(x,y-1,cnt+1));
                if(y<rows-1)
                    list.add(new Point(x,y+1,cnt+1));
            }

            return -1;
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
}
