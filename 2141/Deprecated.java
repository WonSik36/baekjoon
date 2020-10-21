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
import java.io.IOException;
import java.math.BigInteger;
import java.util.StringTokenizer;
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

            BigInteger pDist = calcDistance(towns, p);
            BigInteger qDist = calcDistance(towns, q);

            // System.out.printf("p: %d, q: %d\n", p, q);
            // System.out.printf("pDist: %s, qDist: %s\n", pDist.toString(), qDist.toString());

            if(pDist.compareTo(qDist) <= 0) {
                r = q;
            } else {
                l = p;
            }
        }

        BigInteger min = calcDistance(towns, l);
        long minIdx = l;
        for(long i=l+1; i<=r; i++){
            BigInteger dist = calcDistance(towns, i);
            if(min.compareTo(dist) > 0) {
                min = dist;
                minIdx = i;
            }
        }

        bw.write(Long.toString(minIdx)+"\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static BigInteger calcDistance(List<Town> towns, long x) {
        BigInteger dist = BigInteger.valueOf(0);
        for(Town t : towns) {
            dist = dist.add(BigInteger.valueOf(Math.abs(x - t.x) * (long)t.num));
            // System.out.println(dist.toString());
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