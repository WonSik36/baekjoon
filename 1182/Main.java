/*
    baekjoon online judge
    problem number 1182
    https://www.acmicpc.net/problem/1182

    BackTracking Problem
    Eliminate imposible case from brute force
    https://idea-sketch.tistory.com/29
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] remainMax = new int[N];
        int[] remainMin = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        /*
            remainMax[i] is maximum sum value from arr[i+1]~arr[N-1]
            remainMin[i] is minimum sum value from arr[i+1]~arr[N-1]
        */
        remainMax[N-1] = Max(arr[N-1], 0);
        remainMin[N-1] = Min(arr[N-1], 0);
        if(N>1){
            for(int i=N-2;i>=0;i--){
                remainMax[i] = Max(arr[i]+remainMax[i+1], remainMax[i+1]);
                remainMin[i] = Min(arr[i]+remainMin[i+1], remainMin[i+1]);
            }
        }

        int res = backTracking(N, S, arr, remainMax, remainMin);
        // // it is not allowed to empty subsequence(=0)
        if(S == 0)
            res--;
        
        bw.write(Integer.toString(res));
        bw.close();
        br.close();
    }

    public static int backTracking(int N, int target, int[] arr, int[] remainMax, int[] remainMin){
        Queue<Pair> q = new LinkedList<Pair>();

        q.add(new Pair(0,0));
        q.add(new Pair(arr[0],0));

        int cnt = 0;

        while(!q.isEmpty()){
            Pair first = q.poll();

            // selection of subsequence is done
            if(first.cnt == N-1){
                if(first.sum == target)
                    cnt++;
                continue;
            }

            // there is no posibility can make target
            if(first.sum + remainMin[first.cnt+1] > target 
                    || first.sum + remainMax[first.cnt+1] < target)
                continue;
            
            q.add(new Pair(first.sum, first.cnt+1));
            q.add(new Pair(first.sum + arr[first.cnt+1], first.cnt+1));
        }

        return cnt;
    }

    public static int Max(int a, int b){
        return a>b?a:b;
    }

    public static int Min(int a, int b){
        return a<b?a:b;
    }

    public static void printArray(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static class Pair{
        public int sum;
        public int cnt;

        public Pair(int sum, int cnt){
            this.sum = sum;
            this.cnt = cnt;
        }
    }
}
