/*
    baekjoon online judge
    problem number 2798
    https://www.acmicpc.net/problem/2798
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
        StringTokenizer st = new StringTokenizer(inputStr);
        int num = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        
        inputStr = br.readLine();
        int[] inputArr = new int[num];
        st = new StringTokenizer(inputStr);
        for(int i=0;i<num;i++){            
            inputArr[i] = Integer.parseInt(st.nextToken());
        }
        
        int max = 0;
        for(int i=0;i<num;i++){
            for(int j=i+1;j<num;j++){
                for(int k=j+1;k<num;k++){
                    int sum = inputArr[i] + inputArr[j] + inputArr[k];
                    if(sum<=target && sum>max)
                        max = sum;
                }
            }
        }

        bw.write(Integer.toString(max)+"\n");
        bw.flush();
        bw.close();
    }
}
