import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class MakeRandomNumber{
    public static void main(String[] args) throws IOException {
        File file = new File("./rand.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("100\n");
        for(long i=0;i<100;i++) {
            int num = (int)(Math.random()*10000)+1;
            bw.write(Integer.toString(num));
            bw.write("\n");
        }
        bw.close();
    }
}