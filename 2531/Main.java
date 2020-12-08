/*
    baekjoon online judge
    problem number 2531
    https://www.acmicpc.net/problem/2531
    
    Two Pointers
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    static int N;
    static int D;
    static int K;
    static int C;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        List<Integer> sushis = new ArrayList<>();
        for(int i=0;i<N;i++){
            int sushi = Integer.parseInt(br.readLine()); 

            sushis.add(sushi);
        }

        SushiQueue sushiQueue = new SushiQueue(sushis.subList(0, K));
        int maxCount = sushiQueue.contains(C) ? sushiQueue.count() : sushiQueue.count()+1;

        if(N == K) {
            bw.write(Integer.toString(maxCount)+"\n");

            bw.close();
            br.close();
            return;
        }



        for(int i=0;i<N;i++) {
            int nextIdx = calcNextIdx(i+K);

            sushiQueue.add(sushis.get(nextIdx));

            maxCount = Math.max(maxCount, sushiQueue.contains(C) ? sushiQueue.count() : sushiQueue.count()+1);
        }

        bw.write(Integer.toString(maxCount)+"\n");

        bw.close();
        br.close();
    }

    static int calcNextIdx(int idx) {
        return idx % N;
    }
}

class SushiQueue {
    private Map<Integer, Integer> count;
    private Queue<Integer> queue;
    
    public SushiQueue(List<Integer> sushis) {
        count = new HashMap<>();
        queue = new LinkedList<>();

        for(int s: sushis) {
            count.compute(s, (k, v) -> (v==null) ? 1 : v+1);
            queue.add(s);
        }
    }

    public boolean contains(int sushi) {
        return count.containsKey(sushi);
    }

    public int count() {
        return count.size();
    }

    public void add(int sushi) {
        count.compute(sushi, (k, v) -> (v==null) ? 1 : v+1);
        queue.add(sushi);

        int first = queue.poll();
        int size = count.get(first);
        if(size == 1) {
            count.remove(first);
        } else {
            count.put(first, size-1);
        }
    }
}