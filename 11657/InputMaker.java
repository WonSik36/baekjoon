import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;

public class InputMaker {
    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("input.txt"));

        bw.write("500 500\n");
        for(int i=1;i<=499;i++){
            bw.write(Integer.toString(i));
            bw.write(" ");
            bw.write(Integer.toString(i+1));
            bw.write(" ");
            bw.write("-10000");
            bw.write("\n");
        }
        bw.write("500 1 -10000\n");

        bw.close();
    }

}