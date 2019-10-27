/*
    baekjoon online judge
    problem number 2098
    https://www.acmicpc.net/problem/2098
    https://hsp1116.tistory.com/40

    high level reference

    Travelling Salesperson Problem
    start from arbitrary one node(1st node in src code), 
    travel whole nodes and finally back to start node
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
    static int N;
    static int MAX_MASK; // 2^N-1
    static final int MIN_MASK = 0;
    static final int FST_NODE = 1;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // get N and make dist, and dp
        N = Integer.parseInt(br.readLine());
        MAX_MASK = (1<<N)-1;
        int[][] dist = new int[N+1][N+1];
        int[][] dp = new int[N+1][(int)Math.pow(2, N)];

        // get edges
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = TSP(FST_NODE,add(0,FST_NODE),dp,dist);

        bw.write(Integer.toString(min)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // cur: current position, visited: visited nodes
    public static int TSP(int cur, int visited, int[][] dp, int[][] dist){
        // System.out.printf("current: %d visited: %s\n",cur,stringBit(visited));
        if(visited == MAX_MASK){
            if(dist[cur][FST_NODE]!=0)
                return dist[cur][FST_NODE];
            else
                return INF;
        }
        
        if(dp[cur][visited]!=0)
            return dp[cur][visited];
        int ret = INF;
        for(int i=1;i<=N;i++){
            if(check(visited,i))
                continue;
            if(dist[cur][i] == 0)
                continue;
            // System.out.println("Check next node "+i);
            ret = Min(ret, saturateSum(TSP(i,add(visited,i),dp,dist),dist[cur][i]));
        }
        // System.out.printf("current: %d visited: %s return: %d\n",cur,stringBit(visited),ret);
        dp[cur][visited] = ret;
        return ret;
    }   

    public static int saturateSum(int a, int b){
        long res = (long)a + b;
        if(res > INF)
            return INF;
        else
            return (int)res;
    }

    public static int Min(int a, int b){
        return a<b?a:b;
    }
    
    public static int add(int bit, int idx){
        int mask = 1<<(idx-1);
        return bit|mask;
    }

    public static boolean check(int bit, int idx){
        int mask = 1<<(idx-1);
        int res = mask&bit;
        return res>0?true:false;
    }

    public static String stringBit(int bit){
        boolean[] res = new boolean[N];
        String ret = "";

        // calculate result
        for(int i=0;i<N;i++){
            if(bit%2 == 1)
                res[N-i-1] = true;
            bit /= 2;
        }

        // print it
        for(int i=0;i<N;i++){
            if(res[i])
                ret+="1";
            else
                ret+="0";
        }
        return ret;
    }

    public static void printBit(int bit){
        boolean[] res = new boolean[N];
        
        // calculate result
        for(int i=0;i<N;i++){
            if(bit%2 == 1)
                res[N-i-1] = true;
            bit /= 2;
        }

        // print it
        for(int i=0;i<N;i++){
            if(res[i])
                System.out.print("1");
            else
                System.out.print("0");
        }
        System.out.println();
    }
}
