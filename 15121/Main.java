/*
    baekjoon online judge
    problem number 15552
*/

import java.util.Scanner;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main{
    public static boolean[][] arr;
    public static int arrHeight;
    public static int arrWidth;
    public static void main(String[] args)throws IOException{
        Scanner sc = new Scanner(System.in);

        int inputNumber = sc.nextInt()/3;
        int level = 1;
        while(inputNumber != 1 ){
            //System.out.println("inputNumber: "+inputNumber);
            inputNumber/=2;
            level++;
        }
        //System.out.println("level: "+level);
        arrHeight = 3 * (int)Math.pow(2, level-1);
        arrWidth = 3 * (int)Math.pow(2, level) - 1;
        arr = new boolean[arrHeight][arrWidth];
        //System.out.println(arrHeight);
        //System.out.println(arrWidth);
        paternTriangle(level, 0, arrHeight-1);
        drawTriangle();
        sc.close();
    }

    //draw a triangle by given 2d array
    public static void drawTriangle()throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0;i<arrHeight;i++){
            for(int j=0;j<arrWidth;j++){
                if(arr[i][j]==false)
                    bw.write(" ");
                else
                    bw.write("*");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    //get patern of a triangle and insert triangle information to 2d array recursively
    public static void paternTriangle(int level, int height, int gap){
        //System.out.println("lv: "+level+" height: "+height+" gap: "+gap);
        if(level==1){
            arr[height][gap] = true;
            arr[height+1][gap-1] = true;
            arr[height+1][gap+1] = true;
            for(int i=0;i<5;i++)
                arr[height+2][gap-2+i] = true;
        }else{
            paternTriangle(level-1, height, gap);
            paternTriangle(level-1, height+3*(int)Math.pow(2,level-2), gap-3*(int)Math.pow(2,level-2));
            paternTriangle(level-1, height+3*(int)Math.pow(2,level-2), gap+3*(int)Math.pow(2,level-2));
        }
    }
}