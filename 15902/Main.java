/*
    baekjoon online judge
    problem number 15902
    https://www.acmicpc.net/problem/15902

    high reference: 
    https://docs.google.com/presentation/d/1y4f_ZCcWgCZocPZozsaFZpn2AJSx3ZtPwEFM3h7NurU/edit?usp=sharing
    https://hellogaon.tistory.com/25

    Dynamic Programming & Number Theory
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{
    static final int MOD = 1000000007;
    static long[] factorial;
    static long[] memo;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int L = Integer.parseInt(br.readLine());
        init(L);
        
        
        br.readLine();
        List<Integer> before = parse(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        
        br.readLine();
        List<Integer> after = parse(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());

        // printList(before);
        // printList(after);

        /* get minimum change */
        int minCalc = 0;
        for(int i=0;i<before.size();i++){
            if(before.get(i).intValue() != after.get(i).intValue())
                minCalc++;
        }
        bw.write(Integer.toString(minCalc));
        bw.write(" ");

        /* get number of cases */
        long cases = factorial[minCalc];
        int len = 0;
        for(int i=0; i < before.size(); i++){
            if(before.get(i).intValue() != after.get(i).intValue()){
                len++;
            }else{
                long child = dp(len);

                // Fermat's little theorem
                long parent = calcPow(factorial[len], MOD-2, MOD);
                cases = (cases * (child * parent % MOD)) % MOD;
                len = 0;
            }
        }

        if(len != 0){
            long child = dp(len);
                
            // Fermat's little theorem
            long parent = calcPow(factorial[len], MOD-2, MOD);
            cases = (cases * (child * parent % MOD)) % MOD;
        }

        bw.write(Long.toString(cases));
        bw.write("\n");

        bw.close();
        br.close();
    }

    static List<Integer> parse(int[] arr){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            if(arr[i] == 1){
                if(i != arr.length-1)
                    list.add(1);  
            }else{
                list.add(0);
                if(i != arr.length-1)
                    list.add(1);
            }
        }

        return list;
    }

    static long dp(int N){
        if(memo[N] != 0)
            return memo[N];

        long ret = 0;
        for(int i=0;i<N;i+=2){
            long tmp = dp(i) * dp(N-1-i) % MOD;
            tmp = tmp * combination(N-1, i) % MOD;
            
            ret = (ret + tmp) % MOD;
        }

        return memo[N] = ret;
    }

    static long combination(int N, int K){
        // calculate A
        long A = factorial[N];
        
        // calculate B
        long B = factorial[N-K] * factorial[K] % MOD;
        
        // calculate B^(p-2)
        long bigB = calcPow(B, MOD-2, MOD); 

        // calculate A*B^(p-2)%p
        long ret = A*bigB%MOD;

        return ret;
    }

    static long calcPow(long a, long b, long c){
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

    static void init(int L){
        factorial = new long[L+1];
        factorial[0] = 1;
        for(int i=1;i<=L;i++){
            factorial[i] = i * factorial[i-1] % MOD;
        }

        memo = new long[L];
        memo[0] = 1;
        memo[1] = 1;
    }

    static void printList(List<Integer> list){
        for(int e : list){
            System.out.printf("%d ", e);
        }
        System.out.println();
    }
}
