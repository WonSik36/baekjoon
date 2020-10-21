/*
    baekjoon online judge
    problem number 2141
    https://www.acmicpc.net/problem/2141

    Parametric Search & Ternary Search
    Calculate Distance make Overflow
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

        long l = towns.get(0).x, r = towns.get(towns.size()-1).x;
        while(l+2 < r) {
            long p = (l*2+r) / 3 , q = (l+r*2)/3;

            long pDist = calcDistance(towns, p);
            long qDist = calcDistance(towns, q);

            System.out.printf("p: %d, q: %d\n", p, q);
            System.out.printf("pDist: %d, qDist: %d\n", pDist, qDist);

            if(pDist <= qDist) {
                r = q;
            } else {
                l = p;
            }
        }

        long min = Long.MAX_VALUE;
        long minIdx = 0;
        for(long i=l; i<=r; i++){
            long dist = calcDistance(towns, i);
            if(min > dist) {
                min = dist;
                minIdx = i;
            }
        }

        bw.write(Long.toString(minIdx)+"\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static long calcDistance(List<Town> towns, long x) {
        long dist = 0;
        for(Town t : towns) {
            dist += Math.abs(x - t.x) * (long)t.num;
            System.out.printf("dist += %d * %d = %d\n", Math.abs(x-t.x), t.num, dist);
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