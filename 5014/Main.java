/*
    baekjoon online judge
    problem number 5014
    https://www.acmicpc.net/problem/5014

    BFS Problem
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());   // max floor
        int S = Integer.parseInt(st.nextToken());   // current floor
        int G = Integer.parseInt(st.nextToken());   // destination
        int U = Integer.parseInt(st.nextToken());   // up
        int D = Integer.parseInt(st.nextToken());   // down

        boolean[] visited = new boolean[F+1];
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(S, 0));
        int result = -1;
        while(!q.isEmpty()){
            Pair first = q.poll();

            if(first.floor == G){
                result = first.count;
                break;
            }

            if(first.floor < 1 || first.floor >F)
                continue;

            if(visited[first.floor])
                continue;
            visited[first.floor] = true;
                
            q.add(new Pair(first.floor+U, first.count+1));
            q.add(new Pair(first.floor-D, first.count+1));
        }

        if(result == -1)
            bw.write("use the stairs\n");
        else
            bw.write(Integer.toString(result)+"\n");

        bw.close();
        br.close();
    }
}

class Pair{
    public int floor;
    public int count;

    public Pair(int floor, int count){
        this.floor = floor;
        this.count = count;
    }
}