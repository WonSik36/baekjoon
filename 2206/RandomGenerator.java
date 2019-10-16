import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RandomGenerator{
    public static void main(String[] args) throws IOException {
        long seed = System.currentTimeMillis();
        Random rand = new Random(seed);
        
        int col = 10;
        int row = 10;

        BufferedWriter bw = new BufferedWriter(new FileWriter("./1.in"));
        bw.write(Integer.toString(row)+" "+Integer.toString(col)+"\n");
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int rNum = rand.nextInt(5);
                if(rNum > 0)
                    rNum = 0;
                else if(rNum == 0)
                    rNum = 1;
                String rStr = String.format("%d",rNum);
                bw.write(rStr);
            }
            bw.write("\n");
        }
            
        bw.close();
    }
}