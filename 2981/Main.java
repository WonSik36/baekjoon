/*
    baekjoon online judge
    problem number 2981
    https://www.acmicpc.net/problem/2981
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.lang.Integer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int N = Integer.parseInt(str);
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            str = br.readLine();
            arr[i] = Integer.parseInt(str);
        }
        Arrays.sort(arr);
        
        // if x is M
        // than a[0] = x*Q(0)+R a[N-1] = x*Q(N-1)+R
        // a[N-1]-a[0] = x(Q(N-1)-Q(0)) = X
        // find factor of a[N-1]-a[0] and test it
        int X = arr[N-1]-arr[0];
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(int i=2;i<=X/2;i++){
            if(X%i == 0){
                set.add(i);
            }
        }
        set.add(X);
        // for(Iterator i=set.iterator();i.hasNext();){
        //     int a = ((Integer)i.next()).intValue();
        //     System.out.print(a+" ");
        // }
        // System.out.println();
        for(Iterator i=set.iterator();i.hasNext();){
            boolean flag = true;
            int x = ((Integer)i.next()).intValue();
            int R = arr[0] % x;
            for(int j=1;j<N;j++){
                if(arr[j]%x != R)
                    flag = false;
            }
            if(flag)
                bw.write(Integer.toString(x)+" ");
        }
        
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
