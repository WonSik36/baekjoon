/*
    baekjoon online judge
    problem number 16112
    https://www.acmicpc.net/problem/16112

    Greedy Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> list = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .sorted()
                .collect(toList());

        long sum = 0;
        for(int i=0; i<N; i++){
            int multiplier = i < K ? i : K;
            sum += (long)multiplier * list.get(i);
        }

        bw.write(Long.toString(sum)+"\n");

        bw.close();
        br.close();
    }
}
