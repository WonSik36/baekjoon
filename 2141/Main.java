/*
    baekjoon online judge
    problem number 2141
    https://www.acmicpc.net/problem/2141

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
import static java.util.Comparator.comparingInt;

import java.io.FileReader;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        List<Town> towns = new ArrayList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            towns.add(new Town(x, num));
        }

        towns.sort(comparingInt((Town t) -> t.x));

        long sum = 0;
        for(Town t : towns) {
            sum += t.num;
        }

        sum = (sum+1) / 2;
        
        long cur = 0;
        int idx = 0;
        while(cur < sum) {
            cur += towns.get(idx).num;
            idx++;
        }

        bw.write(Integer.toString(towns.get(idx-1).x)+"\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static long calcDistance(List<Town> towns, long x) {
        long dist = 0;
        for(Town t : towns) {
            dist += Math.abs(x - t.x) * (long)t.num;
        }

        return dist;
    }
}

class Town {
    public int x;
    public int num;

    public Town(int x, int num) {
        this.x = x;
        this.num = num;
    }
}