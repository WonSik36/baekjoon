/*
    baekjoon online judge
    problem number 10250
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
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int floor, room;
            if(n%height == 0){
                floor = height;
                room = n/height;
            }
            else{
                floor = n%height;
                room = n/height+1;
            }

            String re = String.format("%d%02d\n",floor,room);
            bw.write(re);
        }
        bw.flush();
        bw.close();
        return;
    }
}