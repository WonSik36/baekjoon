/*
    baekjoon online judge
    problem number 2580
    https://www.acmicpc.net/problem/2580
    https://www.acmicpc.net/board/view/33168
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{
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

        // phase 1: can fill 0 without assumption
        int idx = 0;
        int size = list.size();
        for(int i=0;i<size;i++){
            if(idx >= list.size())
                idx = 0;

            if(rowCheckAndFill(list.get(idx), board) || colCheckAndFill(list.get(idx), board) 
                    || boxCheckAndFill(list.get(idx), board)){
                list.remove(idx);
                idx--;
            }
            idx++;
        }

        // phase 2: can fill 0 with assumption using backtrace
        if(!checkPerfect(board))
            board = backTrace(list, board);

        print2DArray(board);
        // System.out.println(checkPerfect(board));

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

        public void setX(int x){
            this.x = x;
        }

        public void setY(int y){
            this.y = y;
        }
    }

    public static int[][] backTrace(ArrayList<Pos> list, int[][] board){
        return _backTrace(0,list,board);
    }

    public static int[][] _backTrace(int cnt, ArrayList<Pos> list, int[][] board){
        if(cnt == list.size()){
            if(checkPerfect(board))
                return board;
            return null;
        }
        Pos p = list.get(cnt);
        // narrow range of p's value
        boolean[] checkRow = new boolean[LENGTH+1];
        boolean[] checkCol = new boolean[LENGTH+1];
        boolean[] checkBox = new boolean[LENGTH+1];
        Arrays.fill(checkRow,true);
        Arrays.fill(checkCol,true);
        Arrays.fill(checkBox,true);
        rowCheck(p, board, checkRow);
        colCheck(p, board, checkCol);
        boxCheck(p, board, checkBox);

        // get subset of checked value
        boolean[] check = new boolean[LENGTH+1];
        for(int i=1;i<=LENGTH;i++){
            if(checkRow[i] && checkCol[i] && checkBox[i])
                check[i] = true;
        }

        // back trace value
        for(int i=1;i<=LENGTH;i++){
            // if the number is used than try other number
            if(!check[i]) continue;
            board[p.y][p.x] = i;
            int[][] temp = _backTrace(cnt+1, list, board);
            if(temp != null)
                return temp;
            board[p.y][p.x] = 0;
        }
        return null;
    }

    public static boolean rowCheckAndFill(Pos p, int[][] board){
        boolean[] check = new boolean[LENGTH+1];
        Arrays.fill(check, true);
        // count number of 0 and check 1~9
        int cnt = rowCheck(p, board, check);

        // if number of 0 is larger than 1
        // can't fill the board
        if(cnt >= 2)
            return false;

        // else can fill the board
        fill(p, board, check);
        return true;
    }

    public static int rowCheck(Pos p, int[][] board, boolean[] check){
        int cnt = 0;
        // check number of 0 in row
        for(int i=0;i<LENGTH;i++){
            if(board[i][p.x] == 0)
                cnt++;
            check[board[i][p.x]] = false;
        }
        return cnt;
    }

    public static void fill(Pos p, int[][] board, boolean[] check){
        for(int i=1;i<=LENGTH;i++){
            if(!check[i])
                continue;
            board[p.y][p.x] = i;
            break; 
        }
    }

    public static boolean colCheckAndFill(Pos p, int[][] board){
        boolean[] check = new boolean[LENGTH+1];
        Arrays.fill(check, true);
        // count number of 0 and check 1~9
        int cnt = colCheck(p, board, check);

        // if number of 0 is larger than 1
        // can't fill the board
        if(cnt >= 2)
            return false;

        // else can fill the board
        fill(p, board, check);
        return true;
    }

    public static int colCheck(Pos p, int[][] board, boolean[] check){
        int cnt = 0;
        // check number of 0 in row
        for(int i=0;i<LENGTH;i++){
            if(board[p.y][i] == 0)
                cnt++;
            check[board[p.y][i]] = false;
        }
        return cnt;
    }


    public static boolean boxCheckAndFill(Pos p, int[][] board){
        boolean[] check = new boolean[LENGTH+1];
        Arrays.fill(check, true);
        // count number of 0 and check 1~9
        int cnt = boxCheck(p, board, check);
        // if number of 0 is larger than 1
        // can't fill the board
        if(cnt >= 2)
            return false;

        // else can fill the board
        fill(p, board, check);
        return true;
    }

    public static int boxCheck(Pos p, int[][] board, boolean[] check){
        int cnt = 0;
        // check number of 0 in box
        int row = p.y/3*3;
        int col = p.x/3*3;
        for(int i=row;i<row+3;i++){
            for(int j=col;j<col+3;j++){
                if(board[i][j] == 0)
                    cnt++;
                check[board[i][j]] = false;
            }
        }
        return cnt;
    }

    public static boolean checkPerfect(int[][] board){
        boolean[] check = new boolean[LENGTH+1];
        Pos p = new Pos(0,0);

        // check all rows
        for(int i=0;i<LENGTH;i++){
            p.setX(i);
            Arrays.fill(check, true);
            int cnt = rowCheck(p, board, check);
            // there should be no 0 and should be 1~9
            if(cnt > 0 || !checkFulFill(check))
                return false;
        }
        
        // check all columns
        for(int i=0;i<LENGTH;i++){
            p.setY(i);
            Arrays.fill(check, true);
            int cnt = colCheck(p, board, check);
            // there should be no 0 and should be 1~9
            if(cnt > 0 || !checkFulFill(check))
                return false;
        }
        
        // check all boxes
        int row = LENGTH/3;
        int col = LENGTH/3;
        for(int i=0;i<LENGTH;i+=row){
            for(int j=0;j<LENGTH;j+=col){
                p.setY(i);
                p.setX(j);
                Arrays.fill(check, true);
                int cnt = boxCheck(p, board, check);
                // there should be no 0 and should be 1~9
                if(cnt > 0 || !checkFulFill(check))
                    return false;
            }
        }
        return true;
    }

    public static boolean checkFulFill(boolean[] check){
        for(int i=1;i<=LENGTH;i++){
            if(check[i])
                return false;
        }
        return true;
    }
}
