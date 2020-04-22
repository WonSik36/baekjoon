/*
    baekjoon online judge
    problem number 2618
    https://www.acmicpc.net/problem/2618

    Dynamic Programming + Shortest Path Reverse Tracking

    high reference: http://wookje.dance/2019/01/22/boj-2618/

    dp[i][j]: cop1 solved ith incidents lastly, cop2 solved jth incidents lastly
    i > j, dp[i][j] = dp[i-1][j] + dist(i-1 to i)
                    or Max(dp[0~(i-2)][i-1] + dist(0~(i-2) to i))
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main{
    static int N;
    static int M;
    static List<Pos> incidents;
    static int[][] memo;
    static Pos cop1;
    static Pos cop2;
    static boolean[][] traceBool;
    static int[][] traceIdx;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        initialize();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            incidents.add(new Pos(y,x));
        }
        memo[1][0] = distance(0, 1, true);
        memo[0][1] = distance(0, 1, false);

        // dynamic programming
        for(int i=0;i<=M;i++){
            for(int j=0;j<=M;j++){
                if(i == j)
                    continue;
                
                if(i-1 == j){
                    for(int k=0;k<=i-2;k++)
                        calculate(i,j,k,j,k,i,true);
                }else if(i == j-1){
                    for(int k=0;k<=j-2;k++)
                        calculate(i,j,i,k,k,j,false);
                }else if(i > j){
                    calculate(i,j,i-1,j,i-1,i,true);
                }else if(i < j){
                    calculate(i,j,i,j-1,j-1,j,false);
                }
            }
        }

        // get result(min value)
        int result = 1000000000;
        boolean flag = true;
        int idx = 0;
        for(int i=0;i<M;i++){
            if(result > memo[i][M]){
                result = memo[i][M];
                idx = i;
                flag = false;
            }

            if(result > memo[M][i]){
                result = memo[M][i];
                idx = i;
                flag = true;
            }
        }
        bw.write(Integer.toString(result)+"\n");

        // Shortest Path Reverse Tracking
        if(flag)
            trace(M, idx, bw);
        else
            trace(idx, M, bw);

        printArr(memo);
        printArr(traceBool);
        printArr(traceIdx);

        bw.close();
        br.close();
    }

    static void trace(int i, int j, BufferedWriter bw) throws IOException{
        if(i == 0 && j == 0)
            return;

        if(traceBool[i][j]){
            trace(traceIdx[i][j], j, bw);
            bw.write("1\n");
        }else{
            trace(i, traceIdx[i][j], bw);
            bw.write("2\n");
        }
    }

    static void initialize(){
        memo = new int[M+1][M+1];
        traceBool = new boolean[M+1][M+1];
        traceIdx = new int[M+1][M+1];
        incidents = new ArrayList<Pos>();

        incidents.add(new Pos(0,0));     // garbage value

        for(int i=0;i<=M;i++){
            Arrays.fill(memo[i], 1000000000);
        }

        cop1 = new Pos(1,1);
        cop2 = new Pos(N,N);

        traceBool[0][1] = false;
        traceBool[1][0] = true;
    }

    static int distance(int i, int j, boolean isCop1){
       if(i == 0){
            if(isCop1)
                return cop1.getDistance(incidents.get(j));
            else
                return cop2.getDistance(incidents.get(j));
       }

        return incidents.get(i).getDistance(incidents.get(j));
    }

    static void calculate(int i, int j, int n, int m, int a, int b, boolean isCop1){
        if(memo[i][j] > memo[n][m] + distance(a, b, isCop1)){
            memo[i][j] = memo[n][m] + distance(a, b, isCop1);
            traceBool[i][j] = isCop1;
            traceIdx[i][j] = isCop1?n:m;
        }
    }

    
    static void printArr(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.printf("%d ", arr[i][j]);
            }
            System.out.println();
        }   
    }
    
    static void printArr(boolean[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                if(arr[i][j])
                    System.out.print("1 ");
                else
                    System.out.print("2 ");
            }
            System.out.println();
        }   
    }
}

class Pos{
    public int y;
    public int x;

    public Pos(int y, int x){
        this.y = y;
        this.x = x;
    }

    public int getDistance(Pos that){
        int gapY = Math.abs(this.y - that.y);
        int gapX = Math.abs(this.x - that.x);
        return gapY+gapX;
    }
}