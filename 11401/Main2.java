/*
    baekjoon online judge
    problem number 11401
    https://www.acmicpc.net/problem/11401
    https://onsil-thegreenhouse.github.io/programming/problem/2018/04/02/problem_combination/

    this problem uses Fermat's little theorem
    this problem produces nCk % p = N!/((N-K)!K!) %p = AB^-1 %p
    where A = N!, B = (N-K)!K!, p = 1,000,000,007
    p is prime number proved by prob.2960

    before solving problem Fermat's little theorem is like this
    if p is prime number and a & p are coprime to each others
    a^(p-1) = 1 (mod p)
    
    than AB^-1 %p = AB^-1*1 %p = AB^-1*B^(p-1) %p (where B^(p-1) = 1 (mod p))
    = AB^(p-2) % p
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main2{
    public static final long mod = 1000000007;
    public static long[] fact;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        fact = new long[N+1];

        // calculate A
        long A = factorial(N);
        
        // calculate B
        long B = factorial(N-K) * factorial(K) % mod;
        
        // calculate B^(p-2)
        long bigB = calcPow(B, mod-2, mod); 

        // calculate A*B^(p-2)%p
        long ret = A*bigB%mod;
        bw.write(Long.toString(ret)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }


    public static long factorial(int n){
        if(fact[n]!=0)
            return fact[n];
        // System.out.println("call __factorial");
        return __factorial(n, mod);
    }

    public static long __factorial(int n, long modulo){
        long ret = 1;

        for(int i=2;i<=n;i++){
            ret = (ret * i) % modulo;
            fact[i] = ret;
        }

        return ret;
    }

    public static long calcPow(long a, long b, long c){
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
}