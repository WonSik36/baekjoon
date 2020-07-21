import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomGenerator {
    static final String file = "./data.in";
    static final int N = 1000000;

    public static void main(String[] args) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);

        bw.write(Integer.toString(N)+"\n");

        for(int i=0;i<N;i++){
            bw.write(Integer.toString(rand.nextInt(268435456)+1));
            bw.write(" ");
        }
        bw.write("\n");

        for(int i=0;i<N;i++){
            bw.write(Integer.toString(rand.nextInt(268435456)+1));
            bw.write(" ");
        }
        bw.write("\n");

        bw.close();
    }
}