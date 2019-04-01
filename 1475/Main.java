/*
    baekjoon online judge
    problem number 1475
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int[] nums = new int[10];
        String inputStr = br.readLine();
        
        //System.out.println(triSum(1));
        for(int i=0;i<inputStr.length();i++){
            nums[inputStr.charAt(i)-'0']++;
        }

        if((nums[6]+nums[9])%2 == 0)
            nums[6] = (nums[6]+nums[9]) / 2;
        else
            nums[6] = (nums[6]+nums[9]) / 2 + 1;
        
        int max = 0;
        for(int i=0;i<9;i++){
            if(max < nums[i])
                max = nums[i];
        }

        bw.write(Integer.toString(max));
        bw.write("\n");
        
        bw.flush();
        bw.close();
        return;
    }
}