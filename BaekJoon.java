import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BaekJoon{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Problem Number: ");
        String problemNum = sc.nextLine();
        
        File file = new File("C:/Users/wonsik/Documents/baekjoon/"+problemNum);
        file.mkdir();
        BufferedReader br = new BufferedReader(new FileReader("C:/Users/wonsik/Documents/baekjoon/15552/Main.java"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file+"/Main.java"));
        
        while(true) {
            String line = br.readLine();
            if (line==null) break;
            bw.write(line);
            bw.write("\n");
        }
        br.close();
        bw.close();
    }
}