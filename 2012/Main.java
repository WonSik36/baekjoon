/*
    baekjoon online judge
    problem number 2012
    https://www.acmicpc.net/problem/2012
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import static java.util.Comparator.comparingInt;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        list.sort(comparingInt(x->x));

        long sum = 0;
        for(int i=0;i<N;i++){
            sum += Math.abs(list.get(i) - (i+1));
        }


        bw.write(Long.toString(sum));
        bw.write("\n");

        bw.close();
        br.close();
    }
}
