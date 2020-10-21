import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class InputGenerator {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./1.in"));

        bw.write("100000\n");

        for(int i=0;i<100000;i++){
            // int x = ThreadLocalRandom.current().nextInt(-100, 101);
            int x = (i-50000)*10000;
            bw.write(Integer.toString(x));
            bw.write(" ");
            int num = ThreadLocalRandom.current().nextInt(0, 1000000001);
            bw.write(Integer.toString(num));
            bw.write("\n");
        }

        bw.close();
    }
}
