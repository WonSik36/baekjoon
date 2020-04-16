/*
    baekjoon online judge
    problem number 2412
    https://www.acmicpc.net/problem/2412

    Breadth Fisrst Search, Binary Search Problem
    reference: https://stack07142.tistory.com/270
    java collections binary search: https://tadomstudio.tistory.com/47
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        List<Pos> list = new ArrayList<Pos>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new Pos(y,x,0));
        }

        Collections.sort(list);

        int result = -1;

        boolean[] visited = new boolean[N];
        Queue<Pos> queue = new LinkedList<Pos>();
        queue.add(new Pos(0,0,0));
        while(!queue.isEmpty()){
            Pos first = queue.poll();
            // System.out.printf("Pos (%d,%d), cnt: %d\n",first.y,first.x,first.cnt);

            if(first.y == T){
                result = first.cnt;
                break;
            }

            // get range of compare
            int sIdx = Collections.binarySearch(list, new Pos(first.y-2,first.x-2,0));
            int eIdx = Collections.binarySearch(list, new Pos(first.y+2,first.x+2,0));

            if(sIdx < 0) 
                sIdx = (-1) * (sIdx + 1);
            if(eIdx < 0) 
                eIdx = (-1) * (eIdx + 1);
            else
                eIdx++;

            for(int i=sIdx; i<eIdx; i++){
                if(visited[i])
                continue;
                
                Pos tmp = list.get(i);
                // y value can be out of range
                // because list was sorted by x first, than y
                if(Math.abs(first.y - tmp.y) > 2)
                    continue;
                    
                tmp.cnt = first.cnt+1;
                queue.add(tmp);
                visited[i] = true;
            }
        }

        bw.write(Integer.toString(result)+"\n");
        bw.close();
        br.close();
    }
}

class Pos implements Comparable<Pos>{
    public int y;
    public int x;
    public int cnt;

    public Pos(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Pos that){
        if(this.x < that.x){
            return -1;
        }else if(this.x > that.x){
            return 1;
        }else{
            if(this.y < that.y){
                return -1;
            }else if(this.y > that.y){
                return 1;
            }else{
                return 0;
            }
        }
    }
}