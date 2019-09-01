/*
    baekjoon online judge
    problem number 2004
    https://www.acmicpc.net/problem/2004
    it ask how many 10 is in combination(n,k)
    and it can change how many 2 & 5 in combination(n,k)
    https://jaimemin.tistory.com/908
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int[] factor = new int[]{2,5};
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] sumN = new int[factor.length];
        int[] sumK = new int[factor.length];

        for(int i=0;i<factor.length;i++){
            sumN[i] += howManyN(N, factor[i]);
            sumK[i] += howManyN(K, factor[i]);
            sumK[i] += howManyN(N-K, factor[i]);
        }
        
        bw.write(Integer.toString(Min(sumN[0]-sumK[0], sumN[1]-sumK[1])));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int howManyN(int T, int n){
        int ret = 0;
        while(T != 0){
            ret += T/n;
            T /= n;
        }
        return ret;
    }

    public static int Min(int a, int b){
        return a<b?a:b;
    }
}