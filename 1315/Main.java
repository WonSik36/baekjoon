/*
    baekjoon online judge
    problem number 1315
    https://www.acmicpc.net/problem/1315
    https://handongproblemsolvers.github.io/2019/10/28/Week_10_Contest_Problem_Solving/

    I thought I had to save the state of the quest I solved.
    But N is 100, than state can be N^100.
    However, I did not need to memoize my current point 
    by subtracting my current points from the total stats I earned.
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    static boolean DEBUG = false;
    static int N;
    static ArrayList<Quest> list;
    static int[][] dp;
    static int MAX_PNT = 1000;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<Quest>();
        dp = new int[MAX_PNT+1][MAX_PNT+1];

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int STR = Integer.parseInt(st.nextToken());
            int INT = Integer.parseInt(st.nextToken());
            int PNT = Integer.parseInt(st.nextToken());

            list.add(new Quest(STR,INT,PNT));
        }

        int total = DFS(1,1);

        bw.write(Integer.toString(total)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // hidden input
    // N: total number of quest, list: list of quest, dp: memoization of quest solved at (i,j)
    // input
    // s: STR, i: INT
    public static int DFS(int s, int i){
    	// saturate stat
    	if(s > MAX_PNT)
    		s = MAX_PNT;
    	if(i > MAX_PNT)
    		i = MAX_PNT;
        
        // use memoized value
        if(dp[s][i]!=0)
            return dp[s][i];

        int point = 0;
        int total = 0;
        for(int k=0;k<list.size();k++){
            Quest q = list.get(k);
            if(q.STR <= s || q.INT <= i){
                point += q.PNT;
                total++;
            }
        }

        point -= (s+i-2); // init stat is (1,1)

        // if solved all quest or no quest compare to before state  
        if(total == N || point == 0) {
        	dp[s][i] = total;
            return dp[s][i];
            
        // else calculate DFS
        }else {        	
        	for(int k=0;k<=point;k++){
        		total = Max(total, DFS(s+k, i+point-k));
        		if(total == N)
        			return N;
        	}
        	
        	dp[s][i] = total;
        	return total;
        }
    }

    public static int Max(int a, int b){
        return a>b?a:b;
    }

    public static class Quest{
        public int STR;
        public int INT;
        public int PNT;

        public Quest(int STR, int INT, int PNT){
            this.STR = STR;
            this.INT = INT;
            this.PNT = PNT;
        }
    }
}
