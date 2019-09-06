/*
    baekjoon online judge
    problem number 11401
    https://www.acmicpc.net/problem/11401
    https://onsil-thegreenhouse.github.io/programming/problem/2018/04/02/problem_combination/

    this problem uses Fermat's little theorem and extended euclidean algorithm
    this problem produces nCk % p = N!/((N-K)!K!) %p = AB^-1 %p
    where A = N!, B = (N-K)!K!, p = 1,000,000,007
    p is prime number proved by prob.2960

    because p is prime number GCD(B,p) = 1
    so Bezout's identity ax+by = GCD(B,p) * n can be applied
    Bx + py = 1 -> (Bx + py) %p = 1 -> Bx = 1(mod p)

    so AB^-1 %p = AB^-1*1 %p = AB^-1*Bx %p = A*x %p
    where x can be found by using extended euclidean algorithm
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Test{
    public static final long mod = 1000000007;
    public static long[] fact;
    public static long[] factorial;
    static long x, y, gcd, temp;

    public static void main(String[] args)throws Exception{
        fact = new long[4000001];
        factorial = new long[4000001];

        try{
            for(int i=4000000;i>0;i++){
                for(int j=0;j<=i;j++){
                    long res1 = main1(i,j);
                    long res2 = main2(i,j);
                    String str = String.format("N: %d, K: %d, res1: %d, res2: %d",i,j,res1,res2);
                    if(res1 != res2){
                        throw new Exception(str);
                    }
                    System.out.println(str);
                }
            }
            System.out.println("No Exception");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static long main1(int N, int K){
            long A = factorial(N);
            long B = factorial(N-K)*factorial(K)%mod;
            long[] res =  extendedEuclidean(B, mod);
            long x = res[1];
            // System.out.printf("GCD: %d, s: %d, t: %d\n",res[0],res[1],res[2]);
            // System.out.printf("(%d)*(%d) + (%d)*(%d) = %d\n",B,res[1],mod,res[2],B*res[1]+mod*res[2]);
            // calculate A*x %p
            long ret = ((A%mod)*(x%mod))% mod;
            // ret can be minus value than plus mod
            if(ret<0)
                ret+=mod;
            // String str = String.format("N: %d, K: %d, Result: %d\n", N,K,ret);

        return ret;
    }

    public static long main2(int N, int K){
        long p = 1000000007;

        factorial[0] = 1;
        factorial[1] = 1;
        // factorial 구하기
        if(factorial[N] == 0){
            System.out.println("call factorial calculate");
            for(int i=2; i<=N; i++) factorial[i] = (factorial[i-1]*i)%p;
        }
        long denominator = (factorial[K]*factorial[N-K])%p;

        euclidean(p, denominator);
        long result = ((factorial[N]%p)*(y%p))%p;
        if(result<0) result += p;
        return result;
    }

    // find s*a+t*b = GCD(a,b)
    public static long[] extendedEuclidean(long a, long b){
        boolean flag = true;
        if(b>a){
            long tmp = a;
            a = b;
            b = tmp;
            flag = false;
        }

        long[] r = new long[]{a,b};
        long[] s = new long[]{1,0};
        long[] t = new long[]{0,1};

        while(r[1]>0){
            long Q = r[0] / r[1];

            long R = r[0] - Q*r[1];
            r[0] = r[1];
            r[1] = R;

            long S = s[0] - Q*s[1];
            s[0] = s[1];
            s[1] = S;

            long T = t[0] - Q*t[1];
            t[0] = t[1];
            t[1] = T;
        }

        // s*a+t*b = GCD(a,b)
        if(flag)
            return new long[]{r[0],s[0],t[0]};
        else
            return new long[]{r[0],t[0],s[0]};
    }

    public static long factorial(int n){
        if(fact[n]!=0)
            return fact[n];
        System.out.println("call __factorial");
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

    public static void euclidean(long B, long p){
        if(B%p>0){
            euclidean(p, B%p);
            temp = y;
            y = x - (B/p)*y;
            x = temp;
        }else{
            x = 0;
            y = 1;
            gcd = p;
        }
    }
}