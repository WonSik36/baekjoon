/*
    baekjoon online judge
    problem number 5622
*/
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        int[] times = {11,2,3,4,5,6,7,8,9,10};
        int sum=0;

        for(int i=0;i<inputStr.length();i++){
            sum += times[returnNumber(inputStr.charAt(i))];
        }

        System.out.println(sum);
    }

    public static int returnNumber(char ch){
        int re = 0;

        switch(ch){
            case 'A':
            case 'B':
            case 'C':
                re=2;
                break;
            case 'D':
            case 'E':
            case 'F':
                re=3;
                break;
            case 'G':
            case 'H':
            case 'I':
                re=4;
                break;
            case 'J':
            case 'K':
            case 'L':
                re=5;
                break;
            case 'M':
            case 'N':
            case 'O':
                re=6;
                break;
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
                re=7;
                break;
            case 'T':
            case 'U':
            case 'V':
                re=8;
                break;
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
                re=9;
                break;
        }
        return re;
    }
}