/*
    baekjoon online judge
    problem number 12886
    https://www.acmicpc.net/problem/12886

    Breadth Frist Search Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        int third = Integer.parseInt(st.nextToken());

        Rocks rocks = new Rocks(first, second, third);
        
        Set<Rocks> visited = new HashSet<>();
        Queue<Rocks> queue = new LinkedList<>();
        queue.add(rocks.sort());
        boolean flag = false;
        while(!queue.isEmpty()){
            Rocks tmp = queue.poll();

            if(tmp.isDone()){
                flag = true;
                break;
            }

            if(visited.contains(tmp))
                continue;
            visited.add(tmp);

            tmp.addCasesToQueue(queue);
        }

        if(flag){
            bw.write("1\n");
        }else{
            bw.write("0\n");
        }

        bw.close();
        br.close();
    }
}

class Rocks {
    public int first;
    public int second;
    public int third;

    public Rocks(int first, int secod, int third){
        this.first = first;
        this.second = secod;
        this.third = third;
    }

    public Rocks sort(){
        int[] arr = new int[3];
        arr[0] = first;
        arr[1] = second;
        arr[2] = third;

        Arrays.sort(arr);

        first = arr[0];
        second = arr[1];
        third = arr[2];

        return this;
    }

    public void addCasesToQueue(Queue<Rocks> queue){
        if(first > second){
            queue.add((new Rocks(first - second, second*2, third)).sort());
        }else{
            queue.add((new Rocks(first*2, second-first, third)).sort());
        }
        if(first > third){
            queue.add((new Rocks(first - third, second, third*2)).sort());
        }else{
            queue.add((new Rocks(first*2, second, third-first)).sort());
        }
        if(third > second){
            queue.add((new Rocks(first, second*2, third-second)).sort());
        }else{
            queue.add((new Rocks(first, second-third, third*2)).sort());
        }
    }

    public boolean isDone(){
        return first == second && second == third;
    }

    @Override
    public boolean equals(Object obj){
        Rocks that = (Rocks)obj;

        if(this.first == that.first
                && this.second == that.second
                && this.third == that.third)
                return true;

        return false;
    }

    @Override
    public int hashCode(){
        return first*1024*1024 + second*1024 + third*1024;
    }
}