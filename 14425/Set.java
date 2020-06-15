/*
    baekjoon online judge
    problem number 14425
    https://www.acmicpc.net/problem/14425
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        for(int i=0;i<N;i++){
            set.add(br.readLine());
        }

        int res = 0;
        for(int i=0;i<M;i++){
            String str = br.readLine();

            if(set.contains(str))
                res++;
        }

        bw.write(Integer.toString(res)+"\n");

        bw.close();
        br.close();
    }
}
