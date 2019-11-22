/*
    baekjoon online judge
    problem number 8986
    https://www.acmicpc.net/problem/8986
    https://kks227.blog.me/221432986308

    Ternary Search
    it can be applied to convex and concave function

    cf) binary search can be applied to monotonic function

    very deep reference level
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final long INF = (long)1e18;

    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long min = TernarySearch(N,arr);

        bw.write(Long.toString(min)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static long TernarySearch(int N, int[] arr){
        int low = 0, high = arr[N-1];

        // get 3 possible low values
        while(high-low>=3){
            int p = (low*2+high)/3, q = (low+high*2)/3;
            if(calculate(p, arr) < calculate(q, arr))
                high = q;
            else
                low = p;
        }

        long min = INF;
        for(int i=low;i<=high;i++){
            min = Min(min,calculate(i, arr));
        }

        return min;
    }

    public static long calculate(int len, int[] arr){
        long sum = 0;

        for(int i=0;i<arr.length;i++){
            sum += (long)Math.abs((long)len*i - arr[i]);
        }

        return sum;
    }

    public static long Min(long a, long b){
        return a<b?a:b;
    }
}
