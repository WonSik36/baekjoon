/*
    baekjoon online judge
    problem number 1086
    https://www.acmicpc.net/problem/1086

    Travelling Salesperson Problem
    but dp is not dp[cur][visited] instead dp[visited][remainder]
    reference
    https://www.acmicpc.net/problem/2098
    https://hsp1116.tistory.com/40
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static int N;
    static int K;
    static int MAX_MASK; // 2^N-1
    static final int MIN_MASK = 0;
    public static void main(String[] args)throws IOException{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        MAX_MASK = (int)Math.pow(2, N) - 1;
        String[] arr = new String[N];
        for(int i=0;i<N;i++){
            arr[i] = br.readLine();
        }
        K = Integer.parseInt(br.readLine());
        
        long res = TSP(arr);
        long total = factorial(N);
        if(res == 0){
            bw.write("0/1\n");
        }else if(res == total){
            bw.write("1/1\n");
        }else{
            long gcd = GCD(res, total);
            res /= gcd; total /= gcd;
    
            bw.write(Long.toString(res)+"/"+Long.toString(total)+"\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }

    /*
        abcd mod k  = (a*10^(len of bcd) + b*10^(len of cd) + c*10^(len of d) + d*10^0) mod k
                    = (((a%k) * (10^(len of bcd)%k)) + ((b%k) * (10^(len of cd)%k)) + ((c%k) * (10^(len of d)%k)) + ((d%k) * (10^0%k))) % k
    
        visit node by using Traveling Salesman Problem Algorithm
        and calculate all node using above equation
    */
    // hidden input: N,K
    public static long TSP(String[] arr){
        long[][] dp = new long[(int)Math.pow(2, N)][K]; // i: visited Node, j: remainder
        int[] mod10Pow = new int[50*15+1]; // 10^idx mod K
        int[] numArr = modArr(arr, mod10Pow);
        int[] lenOfArr = getLenOfArr(arr);
        reset(dp); // fill dp with -1
        Arrays.fill(mod10Pow, -1);
        return _TSP(0,0,0,numArr,dp,mod10Pow,lenOfArr); 
    }

    // hidden input: N,K
    // visited: visited nodes before current node, r: remainder, len: length in decimal
    // arr: array of given input numbers modulo by K, dp: i is cur, j is visited
    // dp[i][j] is right answers, lenOfArr: length of array i
    public static long _TSP(int visited, int r, int len, int[] numArr, long[][] dp, int[] mod10Pow, int[] lenOfArr){

    	if(dp[visited][r] != -1)
            return dp[visited][r];
    	
        // visited all node
        if(visited == MAX_MASK){
            if(r == 0)
                return 1;
            else
                return 0;
        }

        int mod10 = mod10Pow[len];
    	if(mod10 == -1) {
    		mod10 = getPowerOf10(len, K);
    		mod10Pow[len] = mod10;
    	}
        
        long sum = 0;
        for(int i=0;i<N;i++){
            if(check(visited,i))
                continue;
            int tmpR = (r + ((numArr[i])*mod10))%K; 
            sum += _TSP(add(visited,i),tmpR,len+lenOfArr[i],numArr,dp,mod10Pow,lenOfArr);
        }
        dp[visited][r] = sum;
        return sum;
    }
    

    public static int[] modArr(String[] arr, int[] mod10Pow) {
    	int[] ret = new int[arr.length];
    	
    	for(int i=0;i<arr.length;i++) {
    		int res = 0;
    	    for (int j = 0; j < arr[i].length(); j++)
    	        res = (res * 10 + arr[i].charAt(j) - '0') % K;
    	    ret[i] = res;
    	}
    	
    	return ret;
    }

    public static int[] getLenOfArr(String[] arr){
        int[] ret = new int[arr.length];

        for(int i=0;i<arr.length;i++){
            ret[i] = arr[i].length();
        }

        return ret;
    }

    // return given number's length
    public static int getLength(int N){
        int res = 0;
        while(N>0){
            N /= 10;
            res++;
        }
        return res;
    }

    public static void reset(long[][] arr){
        for(int i=0;i<arr.length;i++){
            Arrays.fill(arr[i],-1);
        }
    }

    public static int getPowerOf10(int a, int mod){
        if(a == 0)
            return 1;
        return (int)calcPow(10, a, mod);        
    }

    public static long calcPow(int a, int b, int c){
        long result = 1;
        long multiply = a%c;

        while(b > 0){
            if(b%2 == 1){
                result *= multiply;
                result %= c;
            }
            multiply = multiply * multiply % c;
            b /= 2;
        }
        return result;
    }

    public static long GCD(long a, long b){
        while(b>0){
            long r = a%b;
            a = b;
            b = r; 
        }
        return a;
    }

    public static long factorial(int N){
        if(N == 0)
            return 1;
        else 
            return factorial(N-1)*N;
    }
    
    public static int add(int bit, int idx){
        int mask = 1<<idx;
        return bit|mask;
    }

    public static boolean check(int bit, int idx){
        int mask = 1<<idx;
        int res = mask&bit;
        return res>0?true:false;
    }
}
