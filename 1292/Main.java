/*
    baekjoon online judge
    problem number 1292
    https://www.acmicpc.net/problem/1292
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
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int result = getSum(end) - getSum(start-1);

        bw.write(Integer.toString(result)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getSum(int n){
        if(n==0)
            return 0;
        int idx = 1;
        int sum = 1;
        
        // get arr[n]'s number
        // idx = arr[n]'s number
        // arr[sum] = idx, arr[sum+1] = idx+1
        while(sum <n){
            idx++;
            sum = (idx+1)*idx/2;
        }
        // System.out.printf("N: %d, idx: %d, sum: %d\n",n,idx,sum);
        // reinitialize sum which is idx * gap(which is between n to arr[x] = idx-1)
        if(sum > n) 
            sum = idx*(n - idx*(idx-1)/2);
        // or is idx*idx
        else
            sum = idx*idx;

        // sum lower values
        for(int i=1;i<idx;i++){
            sum += i*i;
        }
        return sum;
    }
}
