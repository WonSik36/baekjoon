/*
    baekjoon online judge
    problem number 2836
    https://www.acmicpc.net/problem/2836

    Sweeping Algorithm
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
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Passenger> list = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(end < start)
                list.add(new Passenger(start, end));
        }

        list.sort(comparingInt((Passenger p) -> p.start).reversed());

        long sum = M;
        int curEnd = 2000000000;
        int curStart = 2000000000;
        for(Passenger p : list){
            if(p.start < curEnd){
                sum += 2*(curStart - curEnd);
                curStart = p.start;
            }

            curEnd = p.end < curEnd ? p.end : curEnd;
        }
        sum += 2*(curStart - curEnd);

        bw.write(Long.toString(sum)+"\n");

        bw.close();
        br.close();
    }
}

class Passenger {
    public final int start;
    public final int end;

    public Passenger(int start, int end){
        this.start = start;
        this.end = end;
    }
}