/*
    baekjoon online judge
    problem number 2096
    https://www.acmicpc.net/problem/2096

    Sliding Window & Dynamic Programming
    reference: https://m.blog.naver.com/kks227/220795165570
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
        
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] maxArr = new int[3];
        int[] minArr = new int[3];
        int[] tmpMaxArr = new int[3];
        int[] tmpMinArr = new int[3];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                tmpMaxArr[j] = Integer.parseInt(st.nextToken());
                tmpMinArr[j] = tmpMaxArr[j];

                if(j == 1){
                    tmpMaxArr[j] += Math.max(maxArr[0], Math.max(maxArr[1], maxArr[2]));
                    tmpMinArr[j] += Math.min(minArr[0], Math.min(minArr[1], minArr[2]));
                }else{
                    tmpMaxArr[j] += Math.max(maxArr[j], maxArr[1]);
                    tmpMinArr[j] += Math.min(minArr[j], minArr[1]);
                }
            }

            System.arraycopy(tmpMaxArr, 0, maxArr, 0, 3);
            System.arraycopy(tmpMinArr, 0, minArr, 0, 3);
        }

        Arrays.sort(maxArr);
        Arrays.sort(minArr);

        bw.write(Integer.toString(maxArr[2]));
        bw.write(" ");
        bw.write(Integer.toString(minArr[0]));
        bw.write("\n");


        bw.close();
        br.close();
    }
}
