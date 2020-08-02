/*
    baekjoon online judge
    problem number 2170
    https://www.acmicpc.net/problem/2170

    Sweeping Algorithm
    reference: https://m.blog.naver.com/kks227/220907708368
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;
import static java.util.Comparator.comparingInt;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;
        List<Line> list = new ArrayList<>();
        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.add(new Line(start,end));
        }

        list.sort(comparingInt((Line l) -> l.start));

        int sum = 0;
        int curStart = -2000000000;
        int curEnd = -2000000000;
        for(Line l : list){
            if(curEnd < l.start){
                sum += (curEnd - curStart);
                curStart = l.start;
            }
            
            curEnd = curEnd > l.end ? curEnd : l.end;
        }
        sum += (curEnd - curStart);

        bw.write(Integer.toString(sum)+"\n");

        bw.close();
        br.close();
    }
}

class Line {
    public final int start;
    public final int end;

    public Line(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("start: ");
        sb.append(start);
        sb.append(", end: ");
        sb.append(end);

        return sb.toString();
    }
}