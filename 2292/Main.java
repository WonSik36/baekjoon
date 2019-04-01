/*
    baekjoon online judge
    problem number 2292
*/
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        final int comDif = 6;
        final int maxNum = 1000000000;
        int arithSeq = 1;
        int i;

        for(i=0;arithSeq<maxNum;i++){
            arithSeq += i*comDif;
            if(arithSeq>=number)
                break;
        }

        System.out.println(i+1);
        sc.close();
    }
}