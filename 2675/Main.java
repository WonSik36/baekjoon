/*
    baekjoon online judge
    problem number 2675
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        //String outputStr = "";
        int caseNum = Integer.parseInt(inputStr);
        for(int i=0;i<caseNum;i++){
            inputStr = br.readLine();
            StringTokenizer st = new StringTokenizer(inputStr);
            int alphaNum = Integer.parseInt(st.nextToken());
            String caseStr = st.nextToken();

            for(int j=0;j<caseStr.length();j++){
                for(int k=0;k<alphaNum;k++)
                    bw.write(caseStr.charAt(j));
            }
            bw.write("\n");
        }
        
        //bw.write(outputStr);
        bw.flush();
        bw.close();
    }
}