/*
    baekjoon online judge
    problem number 1624
    https://www.acmicpc.net/problem/1624
    https://handongproblemsolvers.github.io/2019/11/17/Week_12_Contest_Problem_Solving/#%EB%8D%B0%ED%81%AC-%EC%86%8C%ED%8A%B8

    deep reference level
    dynamic programming and greedy algorithm

    if input is 1 3 5 7 9 2 4 6 8
    than there can be deque 1 2 3 / 4 5 6 7 / 8 9
    it need to find out 1~3 is possible in given array
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Arrays;

public class Main{
    static final int MAX_INF = 10000;
    static final int MIN_INF = -10000;

    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        Set<Integer> set = new HashSet<Integer>();

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
            set.add(arr[i]);
        }

        // list of number ordered by ascending order and not duplicated
        List<Integer> list = new ArrayList<Integer>(set);
        Collections.sort(list);
        int[] dp = new int[N];
        Arrays.fill(dp, MAX_INF);

        for(int i=0;i<list.size();i++){
            // if value from 0 to i in list can afford to one deque
            if(check(0,i,arr,list)){
                dp[i] = 1;
                continue;
            }
            
            for(int j=i;j>0;j--){
                // if value from j to i in list can't afford to one deque
                if(!check(j,i,arr,list))
                    break;
                
                // if value j~i in list can afford to one deque
                dp[i] = Min(dp[i], dp[j-1]+1);
            }
        }

        bw.write(Integer.toString(dp[list.size()-1])+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // check i~j can afford one deque
    public static boolean check(int i, int j, int[] arr, List<Integer> list){
        if(j-i<2)
            return true;
        
        int a = list.get(i);
        int b = list.get(j);
        int head = MAX_INF;
        int tail = MIN_INF;

        for(int k=0;k<arr.length;k++){
            // number is not in a~b
            if(a>arr[k] || b<arr[k])
                continue;

            // number is in head~tail which is in deque
            if(head<arr[k] && tail>arr[k])
                return false;

            head = Min(head, arr[k]);
            tail = Max(tail, arr[k]);
        }

        return true;
    }

    public static int Min(int a, int b){
        return a<b?a:b;
    }

    public static int Max(int a, int b){
        return a>b?a:b;
    }
}
