/*
    baekjoon online judge
    problem number 15903
    https://www.acmicpc.net/problem/15903
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Long> list = Arrays.stream(br.readLine().split(" ")).map(Long::valueOf).collect(toList());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for(Long e : list){
            pq.add(e);
        }

        for(int i=0;i<M;i++){
            long a = pq.poll();
            long b = pq.poll();

            a = a+b;
            
            pq.add(a);
            pq.add(a);
        }

        long sum = 0;
        while(!pq.isEmpty()){
            sum += pq.poll();
        }

        bw.write(Long.toString(sum)+"\n");

        bw.close();
        br.close();
    }
}
