/*
    baekjoon online judge
    problem number 6549
    https://www.acmicpc.net/problem/6549
    https://www.acmicpc.net/blog/view/12
    solve by stack but after solving 2042(segment tree)
    try segment tree solving
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Stack;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        
        while(true){
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int N = Integer.parseInt(st.nextToken());
            if(N == 0)
                break;
            int[] arr = new int[N];
            for(int i=0;i<N;i++)
                arr[i] = Integer.parseInt(st.nextToken());
            bw.write(Long.toString(getMaxRectSize(arr))+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static long getMaxRectSize(int[] arr){
        Stack<Integer> st = new Stack<Integer>();
        long max = 0;
        int[] leftLen = new int[arr.length];

        for(int i=0;i<arr.length;i++){
            if(st.isEmpty())
                st.push(i);
            else{
                int accumulated = 0;
                while(!st.isEmpty()){
                    int idx = st.peek();
                    if(arr[idx] > arr[i]){
                        long size = (i-idx+leftLen[idx])*(long)arr[idx];
                        if(size>max)
                            max = size;
                        leftLen[i]++;
                        st.pop();
                        accumulated += leftLen[idx];
                    }else{
                        break;
                    }
                }
                leftLen[i]+=accumulated;
                st.push(i);
            }
        }

        while(!st.isEmpty()){
            int idx = st.pop();
            long size = (arr.length-idx+leftLen[idx])*(long)arr[idx];
            if(size>max)
                max = size;
        }

        // for(int i=0;i<arr.length;i++)
        //     System.out.print(leftLen[i]+" ");
        //     System.out.println();
        return max;
    }
}