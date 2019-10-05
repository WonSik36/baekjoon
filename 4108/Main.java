/*
    baekjoon online judge
    problem number 4108
    https://www.acmicpc.net/problem/4108
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
        
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            if(row == 0 || col == 0)
                break;
            
            int[][] Mat = getMatrix(row,col,br);
            calcMine(Mat);
            printMine(Mat,bw);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int[][] getMatrix(int row, int col, BufferedReader br)throws IOException{
        int[][] ret = new int[row][col];

        for(int i=0;i<row;i++){
            String str = br.readLine();
            for(int j=0;j<col;j++){
                if(str.charAt(j) == '.')
                    ret[i][j] = 0;
                else
                    ret[i][j] = -100;
            }
        }

        return ret;
    }

    public static void calcMine(int[][] Mat){
        for(int i=0;i<Mat.length;i++){
            for(int j=0;j<Mat[0].length;j++){
                if(Mat[i][j] < 0){
                    plusNeighbor(i,j,Mat);
                }
            }
        }
    }

    public static void plusNeighbor(int row, int col, int[][] Mat){
        if(row > 0)
            Mat[row-1][col]++;
        if(row < Mat.length-1)
            Mat[row+1][col]++;
        if(col > 0)
            Mat[row][col-1]++;
        if(col < Mat[0].length-1)
            Mat[row][col+1]++;

        if(row>0 && col>0)
            Mat[row-1][col-1]++;
        if(row>0 && col<Mat[0].length-1)
            Mat[row-1][col+1]++;
        if(row<Mat.length-1 && col>0)
            Mat[row+1][col-1]++;
        if(row<Mat.length-1 && col<Mat[0].length-1)
            Mat[row+1][col+1]++;
    }

    public static void printMine(int[][] Mat, BufferedWriter bw)throws IOException{
        for(int i=0;i<Mat.length;i++){
            for(int j=0;j<Mat[0].length;j++){
                if(Mat[i][j] < 0)
                    bw.write("*");
                else
                    bw.write(Integer.toString(Mat[i][j]));
            }
            bw.write("\n");
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
