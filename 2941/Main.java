/*
    baekjoon online judge
    problem number 2941
*/
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        int numOfWords = 1;

        for(int i=1;i<inputStr.length();i++){
            switch(inputStr.charAt(i)){
                case '-':
                break;
                case '=':
                    if(i>1 && inputStr.substring(i-2, i).equals("dz"))
                        numOfWords--;
                break;
                case 'j':
                if(inputStr.charAt(i-1)=='l' || inputStr.charAt(i-1)=='n')
                break;
                else{
                    numOfWords++;
                    break;
                }
                default:
                numOfWords++;
                break;
            }
        }

        System.out.println(numOfWords);
        sc.close();
    }
}