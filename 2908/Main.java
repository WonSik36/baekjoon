/*
    baekjoon online judge
    problem number 2908
*/
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        int x = Integer.parseInt(reverseString(inputStr.split(" ")[0]));
        int y = Integer.parseInt(reverseString(inputStr.split(" ")[1]));

        if(x>y)
            System.out.println(x);
        else
            System.out.println(y);
    }

    public static String reverseString(String str){
        StringBuffer sb = new StringBuffer();
        
        for(int i=str.length()-1;i>=0;i--)
            sb.append(str.charAt(i));

        return sb.toString(); 
    }

    
}