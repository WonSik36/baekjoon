/*
    baekjoon online judge
    problem number 1157
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int[] alphaNum = new int[26];
        char alpha;

        for(int i=0;i<inputStr.length();i++){
            alpha = inputStr.charAt(i);
            if(alpha>='a' && alpha<='z')
                alphaNum[alpha-'a']++;
            else
                alphaNum[alpha-'A']++;
        }
        
        int max=0, secondMax=-1;
        for(int i=1;i<alphaNum.length;i++){
            if(alphaNum[i]>=alphaNum[max]){
                secondMax = max;
                max = i;
            }
        }

        if(secondMax == -1)
            bw.write("A\n");
        else{
            if(alphaNum[max] == alphaNum[secondMax])
                bw.write("?\n");
            else{
                bw.write(max+'A');
                bw.write("\n");
            }
        }
            
        bw.flush();
        bw.close();
    }

    
}