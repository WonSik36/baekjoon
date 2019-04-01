/*
    baekjoon online judge
    problem number 1011
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
        
        String inputStr = br.readLine();
        int inputNum = Integer.parseInt(inputStr);
    
        //System.out.println(triSum(1));
        for(int i=0;i<inputNum;i++){
            inputStr = br.readLine();
            StringTokenizer st = new StringTokenizer(inputStr);
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            long gap = y - x;
            long n, powN, minN, maxN, warpCount = 0; 
            
            for(n=1;;n++){
                powN = n*n;
                minN = powN - n +1;
                maxN = powN + n;
                if(minN<=gap && gap<=maxN){
                    if(minN<=gap && gap<=powN) 
                        warpCount = 2*n -1;
                    else   
                        warpCount = 2*n;
                    break;
                }
            }
            bw.write(Long.toString(warpCount));
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        return;
    }
}