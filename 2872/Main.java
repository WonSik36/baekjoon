/*
    baekjoon online judge
    problem number 2872
    https://www.acmicpc.net/problem/2872

    Greedy Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<N;i++) {
            int num = Integer.parseInt(br.readLine());
            list.add(num);
        }

        int cur = N;
        for(int i=N-1;i>=0;i--) {
            if(list.get(i) == cur)
                cur--;
        }

        bw.write(Integer.toString(cur)+"\n");

        bw.close();
        br.close();
    }
}
