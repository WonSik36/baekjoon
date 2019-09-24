/*
    baekjoon online judge
    problem number 2580
    https://www.acmicpc.net/problem/2580
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Deprecated{
    static final int LENGTH = 9;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[][] board = new int[LENGTH][LENGTH];
        ArrayList<Pos> list = new ArrayList<Pos>();

        for(int i=0;i<LENGTH;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<LENGTH;j++){
                board[i][j] = Integer.parseInt(st.nextToken()); 
                if(board[i][j] == 0)
                    list.add(new Pos(i,j));
            }
        }
        int idx = 0;
        while(list.size() != 0){
            if(idx >= list.size())
                idx = 0;

            if(rowCheckAndFill(list.get(idx), board) || colCheckAndFill(list.get(idx), board) 
                    || boxCheckAndFill(list.get(idx), board)){
                list.remove(idx);
                idx--;
            }
            idx++;
        }

        print2DArray(board);

        bw.flush();
        bw.close();
        br.close();
    }

    public static void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void print2DArray(int[][] arr){
        for(int i=0;i<arr.length;i++)
            printArray(arr[i]);
    }

    public static class Pos{
        public int y;
        public int x;

        Pos(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static boolean rowCheckAndFill(Pos p, int[][] board){
        int x = p.x;
        int y = p.y;
        boolean[] check = new boolean[LENGTH+1];
        Arrays.fill(check, true);
        int cnt = 0;

        // check number of 0 in row
        for(int i=0;i<LENGTH;i++){
            if(board[i][x] == 0)
                cnt++;
            check[board[i][x]] = false;
        }
        // if number of 0 is larger than 1
        // can't fill the board
        if(cnt >= 2)
            return false;

        // else can fill the board
        for(int i=1;i<=LENGTH;i++){
            if(!check[i])
                continue;
            board[y][x] = i;
            break; 
        }
        return true;
    }

    public static boolean colCheckAndFill(Pos p, int[][] board){
        int x = p.x;
        int y = p.y;
        boolean[] check = new boolean[LENGTH+1];
        Arrays.fill(check, true);
        int cnt = 0;

        // check number of 0 in column
        for(int i=0;i<LENGTH;i++){
            if(board[y][i] == 0)
                cnt++;
            check[board[y][i]] = false;
        }
        // if number of 0 is larger than 1
        // can't fill the board
        if(cnt >= 2)
            return false;

        // else can fill the board
        for(int i=1;i<=LENGTH;i++){
            if(!check[i])
                continue;
            board[y][x] = i;
            break; 
        }
        return true;
    }

    public static boolean boxCheckAndFill(Pos p, int[][] board){
        int x = p.x;
        int y = p.y;
        boolean[] check = new boolean[LENGTH+1];
        Arrays.fill(check, true);
        int cnt = 0;

        // check number of 0 in box
        int row = y/3*3;
        int col = x/3*3;
        for(int i=row;i<row+3;i++){
            for(int j=col;j<col+3;j++){
                if(board[i][j] == 0)
                    cnt++;
                check[board[i][j]] = false;
            }
        }
        // if number of 0 is larger than 1
        // can't fill the board
        if(cnt >= 2)
            return false;

        // else can fill the board
        for(int i=1;i<=LENGTH;i++){
            if(!check[i])
                continue;
            board[y][x] = i;
            break; 
        }
        return true;
    }
}
