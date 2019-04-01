/*
    baekjoon online judge
    problem number 2577
*/
import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        int[] num = new int[10];
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int res = a*b*c;

        String reStr = Integer.toString(res);
        for(int i=0;i<reStr.length();i++){
            num[reStr.charAt(i)-48]++;
        }

        for(int i=0;i<10;i++){
            System.out.println(num[i]);
        }
        sc.close();
    }
}