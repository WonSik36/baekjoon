/*
    baekjoon online judge
    problem number 1520
    https://www.acmicpc.net/problem/1520
    https://simsimjae.tistory.com/23
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static int[] x;
    static int[] y;
    static int[][] map;
    static int[][] way;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        x = new int[]{-1,0,1,0};
        y = new int[]{0,-1,0,1};
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        map = new int[col][row];
        way = new int[col][row];
        for(int i=0;i<col;i++)
            Arrays.fill(way[i], -1);

        for(int i=0;i<col;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            for(int j=0;j<row;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = calculateWay(0,0);
        bw.write(Integer.toString(result)+"\n");
        bw.flush();
        //printArray(way);
        bw.close();
        br.close();
    }

    static int calculateWay(int col, int row){
        if(way[col][row]!=-1)
            return way[col][row];
        if(col == map.length-1 && row == map[0].length-1)
            return 1;

        way[col][row] = 0;
        for(int i=0;i<4;i++){
            int nextCol = col+x[i];
            int nextRow = row+y[i];
            // map index out of boundary
            if(nextCol<0||nextRow<0||nextCol>=map.length||nextRow>=map[0].length)
                continue;
            if(map[col][row]>map[nextCol][nextRow])
                way[col][row] += calculateWay(nextCol, nextRow);
        }
        return way[col][row];
    }

    static void printArray(int[][] arr)throws IOException{
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++)
                System.out.print(arr[i][j]+" ");
            System.out.println();
        }
    }
}
