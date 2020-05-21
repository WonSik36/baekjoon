/*
    baekjoon online judge
    problem number 1106
    https://www.acmicpc.net/problem/1106

    Knapsack Problem with Not Tight Bound And Duplicated Choice
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.lang.Comparable;

public class Main{
    static final int MAX_COST = 100000;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        List<City> list = new ArrayList<City>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int client = Integer.parseInt(st.nextToken());

            list.add(new City(cost, client));
        }

        // Collections.sort(list);
        int[][] memo = new int[N+1][MAX_COST+1];

        for(int i=1;i<=N;i++){
            City cur = list.get(i-1);

            for(int j=1;j<=MAX_COST;j++){
                if(cur.cost > j){
                    memo[i][j] = memo[i-1][j];
                    continue;
                }

                /* wrong answer */
                // memo[i][j] = Max(memo[i][j], memo[i-1][j-cur.cost] + cur.client);
                // memo[i][j] = Max(memo[i][j], memo[i][j-cur.cost] + cur.client);

                /* Right Answer */
                // for(int k=0;k<=i;k++){
                //     memo[i][j] = Max(memo[i][j], memo[i-k][j-cur.cost] + cur.client);
                // }

                memo[i][j] = memo[i-1][j];
                memo[i][j] = Max(memo[i][j], memo[i-1][j-cur.cost] + cur.client);
                memo[i][j] = Max(memo[i][j], memo[i][j-cur.cost] + cur.client);
            }
        }

        // printArr(memo);

        int res = Integer.MAX_VALUE;
        for(int i=1;i<=N;i++){
            for(int j=1;j<=MAX_COST;j++){
                if(memo[i][j] >= C){
                    res = Min(res, j);
                    break;
                }
            }
        }

        bw.write(Integer.toString(res)+"\n");

        bw.close();
        br.close();
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }

    static int Min(int a, int b){
        return a<b?a:b;
    }

    static void printArr(int[][] arr){
        for(int[] a: arr){
            for(int v=0; v<100; v++){
                System.out.printf("%d ", a[v]);
            }
            System.out.println();
        }
    }
}

class City implements Comparable<City>{
    public int cost;
    public int client;

    public City(int cost, int client){
        this.cost = cost;
        this.client = client;
    }

    @Override
    public int compareTo(City that){
        return Double.compare(this.client / (double)this.cost, that.client / (double)that.cost);
    }
}