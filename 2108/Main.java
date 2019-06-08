/*
    baekjoon online judge
    problem number 2108
    https://www.acmicpc.net/problem/2108
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        final int abs = 4000;
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        Integer[] arr = new Integer[num];
        int[] count = new int[abs*2+1];
        int max = -1*(abs+1), min = abs + 1;
        int sum = 0;

        for(int i=0;i<num;i++){
            inputStr = br.readLine();            
            StringTokenizer st = new StringTokenizer(inputStr);
            int inputNum = Integer.parseInt(st.nextToken());
            
            sum += inputNum;    //average
            arr[i] = inputNum;  //median num
            if(inputNum > max)  //max
                max = inputNum;
            if(inputNum < min)  //min
                min = inputNum;
            count[inputNum + abs]++;    //mode num
        }
        //1.average
        String outputStr = String.format("%d\n",Math.round((double)sum/num));
        bw.write(outputStr);
        //2.median num
        ArrayList<Integer> sorted = new ArrayList<Integer>(Arrays.asList(arr));
        Collections.sort(sorted);
        outputStr = String.format("%d\n",sorted.get(num/2));
        bw.write(outputStr);
        //3.mode num
        int maxCount = 0;
        ArrayList<Integer> modeNums = new ArrayList<Integer>();
        for(int i=0;i<abs*2+1;i++){
            if(maxCount < count[i]){
                modeNums.clear();
                modeNums.add(i-abs);
                maxCount = count[i];
            }else if(maxCount == count[i])
                modeNums.add(i-abs);
        }
        if(modeNums.size()>1){
            Collections.sort(modeNums);
            outputStr = String.format("%d\n",modeNums.get(1));
            bw.write(outputStr);
        }else{
            outputStr = String.format("%d\n",modeNums.get(0));
            bw.write(outputStr);
        }
            
        //4.range
        outputStr = String.format("%d\n",max-min);
        bw.write(outputStr);

        /*
        bw.write("\n\n");
        for(int i=0;i<abs*2+1;i++){
            outputStr = String.format("%d ", count[i]);
            bw.write(outputStr);
        }

        bw.write("\n\n");
        for(int i:modeNums){
            outputStr = String.format("%d ", i);
            bw.write(outputStr);
        }
        */

        bw.flush();
        bw.close();
    }
}
