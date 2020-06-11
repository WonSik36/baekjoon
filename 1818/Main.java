/*
    baekjoon online judge
    problem number 1818
    https://www.acmicpc.net/problem/1818

    LIS(Longest Incresing Subsequence) Algorithm
    https://www.crocus.co.kr/681
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> lis = new ArrayList<>();

        for(int i=0;i<N;i++){
            int num = Integer.parseInt(st.nextToken());
            int idx = Collections.binarySearch(lis, num);

            if(idx < 0)
                idx = -1 * (idx + 1);

            if(idx == lis.size())
                lis.add(num);
            else
                lis.set(idx, num);
        }

        int res = N - lis.size();
        bw.write(Integer.toString(res)+"\n");

        bw.close();
        br.close();
    }
}
