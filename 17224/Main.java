/*
    baekjoon online judge
    problem number 17224
    https://www.acmicpc.net/problem/17224
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import static java.util.Comparator.comparingInt;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        List<Problem> list = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int sub1 = Integer.parseInt(st.nextToken());
            int sub2 = Integer.parseInt(st.nextToken());

            list.add(new Problem(sub1, sub2));
        }

        int sum = 0;
        int idx = 0;

        list.sort(comparingInt((Problem p) -> p.sub2));
        for(Iterator<Problem> it = list.iterator(); it.hasNext() && idx < K;){
            Problem p = it.next();

            if(L >= p.sub2){
                sum += 140;
                idx++;
                it.remove();
            } else {
                break;
            }
        }

        list.sort(comparingInt((Problem p) -> p.sub1));
        for(Iterator<Problem> it = list.iterator(); it.hasNext() && idx < K;){
            Problem p = it.next();

            if(L >= p.sub1){
                sum += 100;
                idx++;
                it.remove();
            } else {
                break;
            }
        }

        bw.write(Integer.toString(sum)+"\n");

        bw.close();
        br.close();
    }
}

class Problem {
    public int sub1;
    public int sub2;

    public Problem(int sub1, int sub2){
        this.sub1 = sub1;
        this.sub2 = sub2;
    }

    @Override
    public String toString(){
        return "sub1: "+sub1+", sub2: "+sub2;
    }
}