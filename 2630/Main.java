/*
    baekjoon online judge
    problem number 2630
    https://www.acmicpc.net/problem/2630
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static int[][] arr;
    static int[] numOfColor;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        numOfColor = new int[]{0,0};
        String str = br.readLine();
        int N = Integer.parseInt(str);
        arr = new int[N][N];

        for(int i=0;i<N;i++){
            str = br.readLine();
            for(int j=0;j<N;j++){
                arr[i][j] = str.charAt(2*j);
            }
        }
        /* test code for getColor method
        bw.write(Integer.toString(getColor(1,1,1))+"\n");  //1
        bw.write(Integer.toString(getColor(0,0,2))+"\n");  //1
        bw.write(Integer.toString(getColor(2,2,2))+"\n");  //0
        bw.write(Integer.toString(getColor(4,4,4))+"\n");  //1
        bw.write(Integer.toString(getColor(0,4,4))+"\n");  //-1
        */
        
        getColoredPaper();

        bw.write(Integer.toString(numOfColor[0])+"\n");
        bw.write(Integer.toString(numOfColor[1])+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getColor(int rowStart, int colStart, int length){
        int color = arr[rowStart][colStart];
        boolean flag = true;
        // System.out.printf("row: %d-%d, column: %d-%d\n", rowStart, rowEnd, colStart, colEnd);
        for(int i=rowStart; flag && i<rowStart+length; i++)
            for(int j=colStart; flag && j<colStart+length; j++){
                // System.out.printf("i: %d, j: %d\n",i,j);
                if(arr[i][j] != color){
                    flag = false;
                    // System.out.println(arr[i][j]+" "+color);
                }
            }
        if(!flag)
            return -1;
        return color-48;    // '0' is 48
    }

    public static void getColoredPaper(){
        __getColoredPaper(0,0,arr.length);
    }

    public static void __getColoredPaper(int rowStart, int colStart, int length){
        // System.out.printf("row: %d-%d, column: %d-%d\n", rowStart, rowStart+length-1, colStart, colStart+length-1);
        int color = getColor(rowStart, colStart, length);

        if(color == 0)
            numOfColor[0]++;
        else if(color == 1)
            numOfColor[1]++;
        else{
            __getColoredPaper(rowStart, colStart, length/2);
            __getColoredPaper(rowStart, colStart+length/2, length/2);
            __getColoredPaper(rowStart+length/2, colStart, length/2);
            __getColoredPaper(rowStart+length/2, colStart+length/2, length/2);
        }
    }
}