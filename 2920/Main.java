/*
    baekjoon algorithm
    problem number 2920
*/
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
    
        String inputStr = sc.nextLine();
        StringTokenizer st = new StringTokenizer(inputStr);

        int[] nums = new int[8];
        
        for(int i=0;i<8;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        if(isAscd(nums))
            System.out.println("ascending");
        else if(isDscd(nums))
            System.out.println("descending");
        else
            System.out.println("mixed");

        sc.close();
    }

    public static boolean isAscd(int[] numbers){
        for(int i=0;i<numbers.length-1;i++){
            if(numbers[i]>numbers[i+1])
                return false;
        }
        return true;
    }

    public static boolean isDscd(int[] numbers){
        for(int i=0;i<numbers.length-1;i++){
            if(numbers[i]<numbers[i+1])
                return false;
        }
        return true;
    }
}