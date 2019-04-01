/*
    baekjoon online judge
    problem number 10809
*/
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int alpha;
        int[] alphaArr = new int[26];
        for(int i=0;i<alphaArr.length;i++) {
            alphaArr[i] = -1;
        }
        
        String inputStr = sc.nextLine();
        for(int i=0;i<inputStr.length();i++){
            alpha = inputStr.charAt(i) - 'a';
            if(alphaArr[alpha] == -1)
                alphaArr[alpha] = i;
        }

        for(int i: alphaArr){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}