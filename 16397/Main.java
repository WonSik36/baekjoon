/*
    baekjoon online judge
    problem number 16397
    https://www.acmicpc.net/problem/16397

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
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        int result = bfs(N,T,G);

        if(result == -1)
            bw.write("ANG\n");
        else
            bw.write(Integer.toString(result)+"\n");

        bw.close();
        br.close();
    }

    private static int bfs(int N, int T, int G){
        Queue<Pair> q = new LinkedList<Pair>();
        q.add(new Pair(N,0));
        boolean[] visited = new boolean[100000];

        while(!q.isEmpty()){
            Pair first = q.poll();

            if(first.cnt > T)
                continue;

            if(first.num == G)
                return first.cnt;

            if(visited[first.num])
                continue;
            visited[first.num] = true;

            int pressA = pressBtnA(first.num);
            int pressB = pressBtnB(first.num);

            if(isValidN(pressA))
                q.add(new Pair(pressA, first.cnt+1));

            if(isValidN(pressB))
                q.add(new Pair(pressB, first.cnt+1));
        }

        return -1;
    }

    private static int pressBtnA(int N){
        if(isValidN(N+1))
            return N+1;
        else
            return -1;
    }

    private static int pressBtnB(int N){
        if(isValidN(N*2)){
            return minusOneMostBiggerDigit(N*2);
        }else{
            return -1;
        }
    }

    private static int minusOneMostBiggerDigit(int N){
        if(N == 0)
            return 0;
        else{
            int digit;
            for(digit=1; digit <= 10000; digit*=10){
                if(N/digit == 0)
                    break;
            }
            digit /= 10;

            return N-digit;
        }
    }

    private static boolean isValidN(int N){
        if(N > 99999 || N < 0)
            return false;
        else
            return true;
    }
}

class Pair{
    public int num;
    public int cnt;

    public Pair(int num, int cnt){
        this.num = num;
        this.cnt = cnt;
    }
}