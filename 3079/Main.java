/*
    baekjoon online judge
    problem number 3079
    https://www.acmicpc.net/problem/3079
    https://wootool.tistory.com/65

    low level reference
    binary search
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Main{
    static final long INF = 9076543219876543210L;
    static List<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // airport search
        int M = Integer.parseInt(st.nextToken());  // number of people

        int min = 2000000000;
        for(int i=0;i<N;i++){
            int in = Integer.parseInt(br.readLine());
            list.add(in);
            min = Min(in,min);
        }

        long maxTime = (long)min*M;

        long minTime = binarySearch(0,maxTime,M);

        bw.write(Long.toString(minTime)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static long binarySearch(long left, long right, final int target){
        if(left > right)
            return INF;

        long mid = (left+right)/2;
        long person = calTimetoPerson(mid);

        if(person >= target){
            long min = binarySearch(left, mid-1, target);
            min = Min(mid,min);
            return min;
        }else{
            return binarySearch(mid+1, right, target);
        }
    }

    public static long calTimetoPerson(long time){
        long sum = 0;
        Iterator<Integer> it = list.iterator();

        while(it.hasNext()){
            sum += time / it.next();
        }

        return sum;
    }

    public static int Min(int a, int b){
        return a<b?a:b;
    }

    public static long Min(long a, long b){
        return a<b?a:b;
    }

}
