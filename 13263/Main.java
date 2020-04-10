/*
    baekjoon online judge
    problem number 13263
    https://www.acmicpc.net/problem/13263

    Convex Hull Trick
    Cond1: dp[i] = min(dp[j] + b[j] * a[i]), j < i
    Cond2: b[j] >= b[j+1]
    O(N^2) -> O(NlgN)

    +Cond3: a[i] <= a[i+1]
    O(N^2) -> O(N)

    High reference:
    https://justicehui.github.io/hard-algorithm/2019/01/25/CHT/
    https://justicehui.github.io/ps/2019/01/25/BOJ13263/
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[] height = new int[N];
        int[] cost = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            height[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            cost[i] = Integer.parseInt(st.nextToken());

        long[] dp = new long[N];
        CHT cht = new CHT();
        cht.addFunc(cost[0], 0);
        
        for(int i=1;i<N;i++){
            dp[i] = cht.query(height[i]);
            cht.addFunc(cost[i], dp[i]);
        }

        // cht.printFuncList();

        bw.write(Long.toString(dp[N-1])+"\n");
        bw.close();
        br.close();
    }
}

class CHT{
    private List<Func> funcList;
    private int idx;    // using for indexing x value -> x is added increasingly

    public CHT(){
        this.funcList = new ArrayList<Func>();
        this.idx = 0;
    }

    public void addFunc(int inclination, long yIntercept){
        funcList.add(new Func(inclination, yIntercept));
        while(funcList.size()>2 &&
                crossXPoint(funcList.size()-3, funcList.size()-2) > crossXPoint(funcList.size()-2, funcList.size()-1)){
            
            funcList.remove(funcList.size()-2);
        }
    }

    // calculate dp[i]
    public long query(int x){
        while(idx+1 < funcList.size() && crossXPoint(idx, idx+1) <= x)
            idx++;

        return (long)x*funcList.get(idx).inclination + funcList.get(idx).yIntercept;
    }

    // calculate x point of crossing functions
    private double crossXPoint(int idx1, int idx2){
        double yInter = funcList.get(idx2).yIntercept - funcList.get(idx1).yIntercept;
        double incl = funcList.get(idx1).inclination - funcList.get(idx2).inclination;
        return yInter / incl;
    }

    public void printFuncList(){
        for(int i=0;i<funcList.size();i++){
            System.out.println(funcList.get(i).toString());
        }
    }
}

class Func{
    public int inclination;
    public long yIntercept;

    public Func(int inclination, long yIntercept){
        this.inclination = inclination;
        this.yIntercept = yIntercept;
    }

    public String toString(){
        return inclination+" "+yIntercept;
    }
}