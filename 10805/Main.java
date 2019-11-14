/*
    baekjoon online judge
    problem number 10805
    https://www.acmicpc.net/problem/10805
    problem strategy ref: https://ravor.tistory.com/16
    code ref: https://young02.tistory.com/31
    light level reference

    Dynamic Programming Problem
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final int INF = 1000000000;
    static int[][] dp = new int[51][51];
    static int[][][][] dpL = new int[51][51][51][51];
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h1 = Integer.parseInt(st.nextToken());
        int w1 = Integer.parseInt(st.nextToken());
        int h2 = Integer.parseInt(st.nextToken());
        int w2 = Integer.parseInt(st.nextToken());

        // initialize dp
        initDP();
        // print2DArray(dp);

        int min = cutL(h1,w1,h2,w2);

        bw.write(Integer.toString(min)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int Min(int a, int b){
        return a<b?a:b;
    }

    static void initDP(){
        for(int i=1;i<=50;i++){
            for(int j=i;j<=50;j+=i){
                dp[i][j] = j/i;
                dp[j][i] = j/i;
            }
        }
    }

    static void print2DArray(int[][] arr){
        for(int i=1;i<arr.length;i++){
            for(int j=1;j<arr.length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }

    static int cutL(int h1, int w1, int h2, int w2){
        // if memoized one is exist than return it
        if(dpL[h1][w1][h2][w2] != 0)
            return dpL[h1][w1][h2][w2];

        if(dpL[w1][h1][w2][h2] != 0)
            return dpL[h1][w1][h2][w2];

        int min = INF;

        // cut vertically
        {
            // i: 1 ~ w2-1
            for(int i=1;i<w2;i++){
                int R = cutRect(h1-h2,i);
                int L = cutL(h1,w1-i,h2,w2-i);
                min = Min(min, R+L);
            }   

            // i: w2
            min = Min(min, cutRect(h1-h2,w2)+cutRect(h1,w1-w2));

            // i: w2+1 ~ w1-1
            for(int i=w2+1;i<w1;i++){
                int R = cutRect(h1,w1-i);
                int L = cutL(h1,i,h2,w2);
                min = Min(min, R+L);
            }
        }

        // cut horizontally
        {
            // i: 1 ~ h2-1
            for(int i=1;i<h2;i++){
                int R = cutRect(i,w1-w2);
                int L = cutL(h1-i,w1,h2-i,w2);
                min = Min(min, R+L);
            }   

            // i: h2
            min = Min(min, cutRect(h2,w1-w2)+cutRect(h1-h2,w1));

            // i: h2+1 ~ h1-1
            for(int i=h2+1;i<h1;i++){
                // 2cases
                int R = cutRect(h1-i,w1);
                int L = cutL(i,w1,h2,w2);
                min = Min(min, R+L);
            }
        }

        // memoize min value
        dpL[h1][w1][h2][w2] = min;
        dpL[w1][h1][w2][h2] = min;

        return min;
    }

    static int cutRect(int h, int w){
        // if memoized one is exist than return it
        if(dp[h][w] != 0)
            return dp[h][w];

        if(dp[w][h] != 0)
            return dp[w][h];

        int min = INF;
        // cut vertically
        for(int i=1;i<=w/2;i++){
            min = Min(min, cutRect(h,i)+cutRect(h,w-i));
        }

        // cut horizontally
        for(int i=1;i<=h/2;i++){
            min = Min(min, cutRect(i,w)+cutRect(h-i,w));
        }

        // memoize min value
        dp[h][w] = min;
        dp[w][h] = min;

        return min;
    }

}
