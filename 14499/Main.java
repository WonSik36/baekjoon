/*
    baekjoon online judge
    problem number 14499
    https://www.acmicpc.net/problem/14499

    Simulation
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = arr[0];
        int M = arr[1];
        int y = arr[2];
        int x = arr[3];
        int K = arr[4];

        Dice dice = new Dice(y,x);

        int[][] map = new int[N][];
        for(int i=0;i<N;i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] order = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i=0;i<order.length;i++){
            // Out of range
            if(order[i] == 1){
                if(dice.getX() >= M-1)  
                    continue;
            }else if(order[i] == 2){
                if(dice.getX() <= 0)    
                    continue;
            }else if(order[i] == 3){
                if(dice.getY() <= 0)    
                    continue;
            }else{
                if(dice.getY() >= N-1)  
                    continue;
            }

            dice.rotate(order[i]);
            int curY = dice.getY();
            int curX = dice.getX();
            if(map[curY][curX] == 0){
                map[curY][curX] = dice.getBottom();
            }else{
                dice.setBottom(map[curY][curX]);
                map[curY][curX] = 0;
            }

            bw.write(Integer.toString(dice.getTop()));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }
}

class Dice {
    private int y;
    private int x;
    
    private int left;
    private int right;

    private int up;
    private int down;

    private int top;
    private int bottom;

    public Dice(int y, int x){
        this.y = y;
        this.x = x;

        left = 0;
        right = 0;
        up = 0;
        down = 0;
        top = 0;
        bottom = 0;
    }

    public void rotate(int dir){
        int tmp;
        switch(dir){
            case 1:
                x++;
                tmp = top;
                top = left;
                left = bottom;
                bottom = right;
                right = tmp;
                break;
            case 2:
                x--;
                tmp = top;
                top = right;
                right = bottom;
                bottom = left;
                left = tmp;
                break;
            case 3:
                y--;
                tmp = top;
                top = down;
                down = bottom;
                bottom = up;
                up = tmp;
                break;
            case 4:
                y++;
                tmp = top;
                top = up;
                up = bottom;
                bottom = down;
                down = tmp;
                break;
            default:
                throw new AssertionError("Unknown Direction");
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getBottom(){
        return bottom;
    }

    public void setBottom(int bottom){
        this.bottom = bottom;
    }

    public int getTop(){
        return top;
    }
}