/*
    baekjoon online judge
    problem number 2206
    https://www.acmicpc.net/problem/2206
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
        Matrix mat = new Matrix(rows,cols,maps);
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

        public Matrix(int rows, int cols, int[][] maps){
            this.rows = rows;
            this.cols = cols;
            this.maps = maps;
        }

        public void printMatrix(){
            System.out.printf("rows: %d, cols: %d\n",rows,cols);
            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){
                    System.out.print(maps[i][j]+" ");
                }
                System.out.println();
            }
        }

        // hidden input: rows,cols,maps
        // output: count of fastest path
        public int BFS(){
            // initialize
            boolean[][] visited = new boolean[rows][cols]; // visited before
            boolean[][] checkPower = new boolean[rows][cols]; // do I have power when I visited before
            LinkedList<Point> list = new LinkedList<Point>();
            list.add(new Point(0,0,1,true));
            // int totalCnt = 0;
            //BFS
            while(!list.isEmpty()){
                // totalCnt++;
                Point temp = list.removeFirst();
                int x = temp.x;
                int y = temp.y;
                int cnt = temp.cnt;
                boolean power = temp.power;

                // if riched at end than return
                if(y == rows-1 && x == cols-1){
                    // System.out.println("total Count: "+totalCnt);
                    return cnt;
                }

                // if visited before and visited power is not less than current power
                // than continue
                if(visited[y][x] && !(!checkPower[y][x] && power))
                    continue;
                
                // if current pos is wall than check power
                if(maps[y][x] == 1){
                    if(power)
                    power = false;
                    else
                    continue;
                }

                visited[y][x] = true;
                checkPower[y][x] = power;

                // System.out.printf("visit [%d,%d]: %d\n",y,x,cnt);
                
                if(x>0)
                    list.add(new Point(y,x-1,cnt+1,power));
                if(x<cols-1)
                    list.add(new Point(y,x+1,cnt+1,power));
                if(y>0)
                    list.add(new Point(y-1,x,cnt+1,power));
                if(y<rows-1)
                    list.add(new Point(y+1,x,cnt+1,power));
            }
            // System.out.println("total Count: "+totalCnt);
            return -1;
        }
    }

    public static class Point{
        public int y;
        public int x;
        public int cnt;
        public boolean power;

        Point(int y, int x, int cnt, boolean power){
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.power = power;
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
