/*
    baekjoon online judge
    problem number 3036
    https://www.acmicpc.net/problem/3036
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
        
        String str = br.readLine();
        int N = Integer.parseInt(str);
        int[] arr = new int[N];
        str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<N;i++){
            int gcd = getGCD(arr[0],arr[i]);
            String result = String.format("%d/%d",arr[0]/gcd,arr[i]/gcd);
            bw.write(result+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int getGCD(int a, int b){
        if(a<b){
            int temp = a;
            a = b;
            b = temp;
        }
        while(a%b!=0){
            int r = b;
            b = a%b;
            a = r;
        }
        return b;
    }
}