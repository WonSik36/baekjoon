/*
    baekjoon online judge
    problem number 1941
    https://www.acmicpc.net/problem/1941

    Backtracking
    Choose 7 Students from 25 (25 Combination 7)
    Then, Check 7 Students are connected
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Stack;

public class Main{
    static final int N = 5;
    static final int TOTAL_NUM = 7;
    static final int BORDER_NUM = 4;
    static int CNT = 0;
    static boolean[][] map = null;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = null;

        map = new boolean[N][N];

        for(int i=0;i<N;i++){
            str = br.readLine();

            for(int j=0;j<N;j++){
                if(str.charAt(j) == 'S')
                    map[i][j] = true;
                else
                    map[i][j] = false;
            }
        }

        backtrack();        

        bw.write(Integer.toString(CNT)+"\n");

        bw.close();
        br.close();
    }

    static void backtrack(){
        boolean[][] checked = new boolean[N][N];

        for(int i=0; i<=N*N-TOTAL_NUM; i++){
            checked[i/N][i%N] = true;

            if(map[i/N][i%N])
                _backtrack(i, 1, 1, checked);
            else
                _backtrack(i, 0, 1, checked);

            checked[i/N][i%N] = false;
        }
    }

    static void _backtrack(int idx, int num, int cnt, boolean[][] checked){
        if(cnt == TOTAL_NUM){
            if(num >= BORDER_NUM && isValid(checked)){
                // printArr(checked);
                CNT++;
            }
            return;
        }

        for(int i=idx+1; i<=N*N-TOTAL_NUM+cnt; i++){
            if(i == N*N)
                break;
            checked[i/N][i%N] = true;

            if(map[i/N][i%N])
                _backtrack(i, num+1, cnt+1, checked);
            else
                _backtrack(i, num, cnt+1, checked);

            checked[i/N][i%N] = false;
        }
    }


    // check 7 students are connected
    static boolean isValid(final boolean[][] checked){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(checked[i][j])
                    return _isValid(i, j, checked);
            }
        }

        throw new AssertionError("Array Checked is not good to test validity");
    }

    static boolean _isValid(int y, int x, final boolean[][] checked){
        boolean[][] visited = new boolean[N][N];
        Stack<Pos> stack = new Stack<Pos>();
        stack.add(new Pos(y,x));
        int connectedNode = 0;

        while(!stack.isEmpty()){
            Pos last = stack.pop();

            if(!checked[last.y][last.x] || visited[last.y][last.x])
                continue;
            visited[last.y][last.x] = true;
            connectedNode++;

            if(last.y > 0 && !visited[last.y-1][last.x])
                stack.add(new Pos(last.y-1, last.x));
            if(last.y < N-1 && !visited[last.y+1][last.x])
                stack.add(new Pos(last.y+1, last.x));
            if(last.x > 0 && !visited[last.y][last.x-1])
                stack.add(new Pos(last.y, last.x-1));
            if(last.x < N-1 && !visited[last.y][last.x+1])
                stack.add(new Pos(last.y, last.x+1));
        }
        

        if(connectedNode == TOTAL_NUM)
            return true;
        else
            return false;
    }

    static void printArr(boolean[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(arr[i][j])
                    System.out.print("1");
                else
                    System.out.print("0");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Pos {
    public int y;
    public int x;

    public Pos(int y, int x){
        this.y = y;
        this.x = x;
    }
}