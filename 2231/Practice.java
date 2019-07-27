import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Practice{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputStr = br.readLine();
        int inputNum = Integer.parseInt(inputStr);
        String outputStr;

        for(int i=1;i<inputNum+1;i++){
            outputStr = String.format("[%d]: ", i);
            bw.write(outputStr);
            int n = i;
            while(n>0){
                if(decompositionSum(n) == i){
                    outputStr = String.format("%d ", n);
                    bw.write(outputStr);
                }
                n--;
            }
            bw.write("\n");
        }
            
        bw.flush();
        bw.close();
    }

    public static int decompositionSum(int n){
        int num = n;
        int sum = 0;

        while(num>0){
            int digit = num%10;
            sum += digit;
            num /= 10;
        }
        return n+sum;
    }
    
}