/*
    ACM-ICPC Regional 2018
    Problem F
*/
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./3.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        Integer[] arr = new Integer[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.valueOf(st.nextToken());
        }
        
        boolean[] mark = new boolean[400001];
        int[] kValue = new int[400001];
        int sum = 0;
        for(int k=2;k<n-1;k++){
            sum += arr[k];
            for(int l=k+1;l<n;l++){
                sum += arr[l];
                mark[sum] = true;
                kValue[sum] = k;
                sum -= arr[l];
            }
            sum -= arr[k];
        }

        sum = 0;
        boolean flag = false;
        for(int i=0;i<n-3 && !flag;i++){
            sum += arr[i];
            for(int j=i+1;j<n-2 && !flag;j++){
                sum += arr[j];
                if((w-sum>0 && w-sum <= 400000)&& (mark[w-sum] && kValue[w-sum]>j))
                    flag = true;
                sum -= arr[j];
            }
            sum -= arr[i];
        }

        if(flag)
            bw.write("YES\n");
        else
            bw.write("NO\n");

        // bw.write(Integer.toString(count)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}