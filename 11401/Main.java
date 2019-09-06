/*
    baekjoon online judge
    problem number 11401
    https://www.acmicpc.net/problem/11401
    https://onsil-thegreenhouse.github.io/programming/problem/2018/04/02/problem_combination/

    this problem uses Bezout's identity and extended euclidean algorithm
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

public class Main{
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

        // find A by using (A*B)%C = ((A%C)*(B%C))%C
        long A = factorial(N);

        // find x by using extended euclidean algorithm
        long B = (factorial(N-K)*factorial(K))%mod;
        long[] res =  extendedEuclidean(B, mod);
        long x = res[1];
        // System.out.printf("GCD: %d, s: %d, t: %d\n",res[0],res[1],res[2]);
        // System.out.printf("(%d)*(%d) + (%d)*(%d) = %d\n",B,res[1],mod,res[2],B*res[1]+mod*res[2]);

        // calculate A*x %p
        long ret = ((A%mod)*(x%mod))% mod;
        // ret can be minus value than plus mod
        if(ret<0)
            ret+=mod;

        bw.write(Long.toString(ret)+"\n");
        bw.flush();
        bw.close();
        br.close();
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
}