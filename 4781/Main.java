/*
    baekjoon online judge
    problem number 4781
    https://www.acmicpc.net/problem/4781

    Dynamic Programming
    I firstly thought this is Knapsack problem.
    But it doesn't need 2d array
    And also converting double to int needs some trick(100*double+0.5)
    Or use BigDecimal

    reference: https://jaimemin.tistory.com/622
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.math.BigDecimal;

public class Main{
    static int N;
    static int M;
    static ArrayList<Pair> list;
    static int[] memo;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            // M = (int)(Double.parseDouble(st.nextToken())*100+0.5);
            M = BigDecimal.valueOf(Double.parseDouble(st.nextToken())).multiply(BigDecimal.valueOf(100)).intValue();

            if(N==0 && M==0)
                break;

            list = new ArrayList<Pair>();
            memo = new int[M+1];
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                int calories = Integer.parseInt(st.nextToken());
                // int price = (int)(Double.parseDouble(st.nextToken())*100+0.5);
                int price = BigDecimal.valueOf(Double.parseDouble(st.nextToken())).multiply(BigDecimal.valueOf(100)).intValue();
                list.add(new Pair(calories,price));
            }

            bw.write(Integer.toString(dp())+"\n");
        }
        
        bw.close();
        br.close();
    }

    static int dp(){
        for(int i=1;i<=M;i++){
            
            for(int j=0;j<list.size();j++){
                Pair tmp = list.get(j);
                int calories = tmp.calories;
                int price = tmp.price;

                if(price > i)
                    continue;
                

                memo[i] = Max(memo[i], memo[i-price]+calories);
            }
        }

        return memo[M];
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }
}

class Pair{
    public int calories;
    public int price;

    public Pair(int calories, int price){
        this.calories = calories;
        this.price = price;
    }
}