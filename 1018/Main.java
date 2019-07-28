/*
    baekjoon online judge
    problem number 1018
    https://www.acmicpc.net/problem/1018
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        String[] inputArr = inputStr.split(" ");
        int N = Integer.parseInt(inputArr[0]);
        int M = Integer.parseInt(inputArr[1]);;
        boolean[][] chass = new boolean[N][M];
        
        //read chass inform by using buffered reader
        for(int i=0;i<N;i++){
            inputStr = br.readLine();
            for(int j=0;j<M;j++){
                if(inputStr.charAt(j)=='W')
                    chass[i][j] = true;
                else
                    chass[i][j] = false;
            }
        }
        
        int min = 64;
        for(int i=0;i<N-7;i++){
            for(int j=0;j<M-7;j++){
                int result = calSquareRepaint(chass, i, j);
                //String re = String.format("[%d][%d]: %d", i,j,result);
                //System.out.println(re);
                if(min>result)
                    min = result;
            }
        }


        bw.write(Integer.toString(min)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void printChass(boolean[][] chass)throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0;i<chass.length;i++){
            for(int j=0;j<chass[0].length;j++){
                if(chass[i][j] == true)
                    bw.write("W");
                else
                    bw.write("B");
            }
            bw.write("\n");
        }
        
        bw.flush();
        //bw.close();
    }

    public static int calSquareRepaint(boolean[][] chass, int col, int row){
        final int len = 8;
        boolean color = true;
        int repaint = 0;
        
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(chass[col+i][row+j]^color)
                    repaint++;
                color = !color;
            }
            color = !color;
        }

        if(repaint>32)
            repaint = 64-repaint;
        
        return repaint;
    }
}