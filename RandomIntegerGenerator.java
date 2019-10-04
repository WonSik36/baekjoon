import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class RandomIntegerGenerator{
    public static void main(String[] args) throws IOException {
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter X Y: ");
        int col = sc.nextInt();
        int row = sc.nextInt();
        System.out.print("Range(Integer range [-2,147,483,648 ~ 2,147,483,647]):");
        int start = sc.nextInt();
        int end = sc.nextInt();


        BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/wonsik/Documents/baekjoon/rand.in"));
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int rNum = rand.nextInt(end-start+1) + start;
                String rStr = String.format("%d ",rNum);
                bw.write(rStr);
            }
            bw.write("\n");
        }
        

            
        bw.close();
        sc.close();
    }
}