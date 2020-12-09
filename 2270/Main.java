/*
    baekjoon online judge
    problem number 2270
    https://www.acmicpc.net/problem/2270

    Tower of Hanoi
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toCollection;

public class Main{
    static final long MOD = 1000000;
    // static final long MOD = 184648477834656L;

    static long[] powerMemo;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        br.readLine();
        List<List<Integer>> rings = new ArrayList<>();

        for(int i=0;i<3;i++) {
            String line = br.readLine();

            List<Integer> ring = null;
            if(line.length() == 0)
                ring = new ArrayList<>();
            else
                ring = Arrays.stream(line.split(" ")).map(Integer::valueOf).collect(toList());

            rings.add(ring);
        }
        

        long[] ans = solve(N, rings);

        bw.write(Long.toString(ans[0])+"\n");
        bw.write(Long.toString(ans[1])+"\n");

        bw.close();
        br.close();
    }

    static long[] solve(int N, List<List<Integer>> rings) {
        // printRings(rings);

        int from = -1, through = -1, to = -1;
        int nextN = N;

        for(int i=0;i<3;i++) {
            List<Integer> ring = rings.get(i);

            if(ring.size() == N)
                return new long[]{i+1, 0};

            if(ring.contains(N)) {
                to = i;
                
                for(Iterator<Integer> it = ring.iterator(); it.hasNext();) {
                    int disk = it.next();
                    if(disk != nextN)
                        break;

                    nextN--;
                    it.remove();
                }
            }
        }

        for(int i=0;i<3;i++) {
            List<Integer> ring = rings.get(i);

            if(ring.contains(nextN)) {
                from = i;
                Iterator<Integer> it = ring.iterator();
                it.next();
                it.remove();
            }
        }

        through = 3 - from - to;

        // System.out.printf("from: %d, through: %d, to: %d\n", from, through, to);
        // System.out.printf("nextN: %d\n", nextN);

        long count = (solve(nextN-1, through, rings) + moveTower(nextN-1) + 1) % MOD;

        return new long[]{to+1, count};
    }

    static long solve(int N, int to, List<List<Integer>> rings) {
        // printRings(rings);

        int from = -1, through = -1;
        List<Integer> toRing = rings.get(to);
        int nextN = N;

        if(toRing.size() == N)
            return 0;

        for(Iterator<Integer> it = toRing.iterator(); it.hasNext();) {
            int disk = it.next();
            if(disk != nextN)
                break;

            nextN--;
            it.remove();
        }

        for(int i=0;i<3;i++) {
            List<Integer> ring = rings.get(i);

            if(ring.contains(nextN)) {
                from = i;
                Iterator<Integer> it = ring.iterator();
                it.next();
                it.remove();
            }
        }

        through = 3 - from - to;

        // System.out.printf("from: %d, through: %d, to: %d\n", from, through, to);
        // System.out.printf("nextN: %d\n", nextN);

        return (solve(nextN-1, through, rings) + moveTower(nextN-1) + 1) % MOD;
    }

    static long moveTower(int height) {
        return power(2, height, MOD) - 1;
    }

    static long power(int a, int b, long mod) {
        long res = 1;
        long multi = a % mod;

        while(b >= 1) {
            if(b % 2 == 1)
                res = res * multi % mod;

            multi = multi * multi % mod;
            b /= 2;
        }

        return res;
    }

    static void printRings(List<List<Integer>> rings) {
        for(List<Integer> r: rings) {
            for(int d: r) {
                System.out.printf("%d ", d);
            }
            System.out.println();
        }
    }
}
