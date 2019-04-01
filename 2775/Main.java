/*
    baekjoon online judge
    problem number 2775
*/
import java.util.Scanner;

public class Main{
    public int[][] apart;
    public Main(int floor, int room){
        apart = new int[floor+1][room+1];

        for(int i=0;i<room;i++){
            apart[0][i+1] = i+1;
        }

        for(int i=1;i<=floor;i++){
            apart[i][1] = apart[i-1][1];
            for(int j=1;j<room;j++){
                apart[i][j+1] = apart[i-1][j+1] + apart[i][j];
            }
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Main apartment = new Main(14,14);
        
        int inputNum = sc.nextInt();
    
        for(int i=0;i<inputNum;i++){
            int k = sc.nextInt();
            int n = sc.nextInt();            
            
            System.out.println(apartment.apart[k][n]);
        }
        return;
        /*
        for(int i=0;i<apartment.apart.length;i++){
            for(int j=0;j<apartment.apart[0].length;j++){
                System.out.print(apartment.apart[i][j]+ " ");
                
            }
            System.out.println();
        }
        */
    }
}