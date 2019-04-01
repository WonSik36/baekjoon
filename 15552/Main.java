/*
    baekjoon online judge
    problem number 15552
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
        int num = Integer.parseInt(inputStr);
        int x,y;
        String[] arr = new String[2];
        
        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            //split
            /*
            arr = inputStr.split(" ");
            x = Integer.parseInt(arr[0]);
            y = Integer.parseInt(arr[1]);
            //outputStr += Integer.toString(x+y) + "\n";
            bw.write(Integer.toString(x+y) + "\n");
            */
            
            //stringTokenizer
            
            StringTokenizer st = new StringTokenizer(inputStr);
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            //outputStr += Integer.toString(x+y) + "\n";
            bw.write(Integer.toString(x+y) + "\n");
        }
        
        //bw.write(outputStr);
        bw.flush();
        bw.close();
    }
}