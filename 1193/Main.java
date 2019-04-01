/*
    baekjoon online judge
    problem number 1193
*/
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int inputNum = sc.nextInt();
        
        int idx = 0;
        int sum = 0;
        int comdif = 1;
        while(sum < inputNum){
            sum += comdif++;
            idx++;
        }
        
        int myNum = 0;
        int x=0, y=1;
        int k=1;
        for(int i=0; i<idx; i++){
            k *= -1;
            if(i%2 == 1)
                y++;
            else
                x++;
            myNum++;
            for(int j=0; j<i; j++){
                System.out.println("x: "+x+" y: "+y+" myNum: "+myNum);
                if(myNum == inputNum)
                    break;
                y-=k;
                x+=k;
                myNum++;
            }
            if(myNum == inputNum)
                break;
        }
        
        System.out.println(x+"/"+y);
        sc.close();
        return;
    }
}