/*
    baekjoon online judge
    problem number 5533
    https://www.acmicpc.net/problem/5533
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());

        int[][] scores = new int[N][];

        for(int i=0;i<N;i++){
            scores[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] sum = new int[N];
        for(int i=0;i<3;i++){
            for(int j=0;j<N;j++){
                boolean isDupliacted = false;
                for(int k=0;k<N;k++){
                    if(j==k)
                        continue;

                    if(scores[j][i] == scores[k][i]){
                        isDupliacted = true;
                        break;
                    }
                }

                if(!isDupliacted)
                    sum[j] += scores[j][i];
            }
        }

        for(int i=0;i<N;i++){
            bw.write(Integer.toString(sum[i]));
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
