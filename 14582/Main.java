/*
    baekjoon online judge
    problem number 14582
    https://www.acmicpc.net/problem/14582
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    private static final String YES = "Yes\n";
    private static final String NO = "No\n";
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] ourScores = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] otherScores = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean flag = false;
        int ourTotal = 0;
        int otherTotal = 0;
        for(int i=0;i<9;i++){
            ourTotal += ourScores[i];
            if(ourTotal > otherTotal) {
                flag = true;
                break;
            }

            otherTotal += otherScores[i];
        }

        if(flag)
            bw.write(YES);
        else
            bw.write(NO);

        bw.close();
        br.close();
    }
}
