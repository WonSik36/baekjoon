/*
    baekjoon online judge
    problem number 10039
*/
import java.util.Scanner;

public class Main{
    private int[][] fiboArr;
    private boolean[][] flag;
    private int length;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] cases = new int[num];

        for(int i=0;i<num;i++){
            cases[i] = sc.nextInt();
        }

        Main fiboMain = new Main();
        int zero, one;
        for(int i=0;i<num;i++){
            zero = fiboMain.fiboZero(cases[i]);
            one = fiboMain.fiboOne(cases[i]);
            System.out.println(zero+" "+one);
        }
    }

    public Main(){
        length = 41;
        fiboArr = new int[length][2];
        flag = new boolean[length][2];
        fiboArr[0][0] = 1;
        fiboArr[0][1] = 0;
        fiboArr[1][0] = 0;
        fiboArr[1][1] = 1;
        flag[0][0] = true;
        flag[0][1] = true;
        flag[1][0] = true;
        flag[1][1] = true;
    }

    public int fiboZero(int level){
        //System.out.println("fiboZero called level: "+level);
        if(level==0)
            return 1;
        else if(level==1)
            return 0;
        else if(flag[level][0]==true)
            return fiboArr[level][0];
        else{
            fiboArr[level][0] = fiboZero(level-1) + fiboZero(level-2);
            flag[level][0] = true;
            return fiboArr[level][0];
        }
    }

    public int fiboOne(int level){
        //System.out.println("fiboOne called level: "+level);
        if(level==0)
            return 0;
        else if(level==1)
            return 1;
        else if(flag[level][1]==true)
            return fiboArr[level][1];
        else{
            fiboArr[level][1] = fiboOne(level-1) + fiboOne(level-2);
            flag[level][1] = true;
            return fiboArr[level][1];
        }
    }
}