/*
    baekjoon online judge
    problem number 2098
    https://www.acmicpc.net/problem/2098
    https://hsp1116.tistory.com/40

    Travelling Salesperson Problem
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static boolean DEBUG = false;
    static final int INF = 10000000;
    static final int MAX_MASK = 65535; // 2^16-1
    static final int MIN_MASK = 0;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // get N and make dist, and dp
        int N = Integer.parseInt(br.readLine());
        int[][] dist = new int[N+1][N+1];
        int[][] dp = new int[N+1][(int)Math.pow(2, N)];
        for(int i=1;i<=N;i++){
            Arrays.fill(dist[i],INF);
            dist[i][i] = 0;
        }

        // get edges
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                int w = Integer.parseInt(st.nextToken());
                if(w != 0)
                    dist[i][j] = w;
            }
        }




        bw.flush();
        bw.close();
        br.close();
    }

    

    public static int add(int bit, int idx){
        int mask = 1<<idx;
        return bit|mask;
    }

    public static int remove(int bit, int idx){
        int mask = 1;
        for(int i=1;i<idx;i++){
            mask *= 2;
        }
        mask = mask^MAX_MASK;
        return bit&mask;
    }

    public static boolean check(int bit, int idx){
        int mask = 1;
        for(int i=1;i<idx;i++){
            mask *= 2;
        }
        int res = mask&bit;
        return res>0?true:false;
    }

    public static int toggle(int bit, int idx){
        int res;
        if(check(bit,idx))
            res = remove(bit,idx);
        else
            res = add(bit,idx);
        return res;
    }

    public static void printBit(int bit){
        boolean[] res = new boolean[20];
        
        // calculate result
        for(int i=0;i<20;i++){
            if(bit%2 == 1)
                res[20-i-1] = true;
            bit /= 2;
        }

        // print it
        for(int i=0;i<20;i++){
            if(res[i])
                System.out.print("1");
            else
                System.out.print("0");
        }
        System.out.println();
    }
}
