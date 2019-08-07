/*
    baekjoon online judge
    problem number 11054
    https://www.acmicpc.net/problem/11054
    it has relation with 11053
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int[] arr = new int[num];
        inputStr = br.readLine();
        StringTokenizer st = new StringTokenizer(inputStr);
        for(int i=0;i<num;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int max = getDescArrLen(Arrays.copyOfRange(arr, 1, num), arr[0])+1;
        // String outputStr = String.format("0: %d",max);
        // System.out.println(outputStr);
        for(int i=1;i<num-1;i++){
            int asc = getAscArrLen(Arrays.copyOfRange(arr, 0, i), arr[i]);
            int desc = getDescArrLen(Arrays.copyOfRange(arr, i+1, num), arr[i]);
            int len = asc+desc+1;
            // outputStr = String.format("%d: %d+1+%d = %d",i,asc,desc,len);
            // System.out.println(outputStr);
            if(max < len)
                max = len;
        }
        int last = getAscArrLen(Arrays.copyOfRange(arr, 0, num-1), arr[num-1])+1;
        max = last>max?last:max;
        // outputStr = String.format("%d: %d",num-1,last);
        // System.out.println(outputStr);

        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }

    static int getAscArrLen(int[] arr, int pivot){
        if(arr.length==0)
            return 0;
        int[] dp = new int[arr.length];
        if(arr[0]<pivot)
            dp[0] = 1;
        else
            dp[0] = 0;
        int maxLen = dp[0];

        for(int i=1;i<arr.length;i++){
            int max = 0;
            if(arr[i]>=pivot)
                continue;
            for(int j=i-1;j>=0;j--){
                if(arr[i]>arr[j] && dp[j]>max){
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
            if(maxLen<dp[i])
                maxLen = dp[i];
        }

        return maxLen;
    }

    static int getDescArrLen(int[] arr, int pivot){
        if(arr.length==0)
            return 0;
        int[] dp = new int[arr.length];
        if(arr[0]<pivot)
            dp[0] = 1;
        else
            dp[0] = 0;
        int maxLen = dp[0];

        for(int i=1;i<arr.length;i++){
            int max = 0;
            if(arr[i]>=pivot)
                continue;
            for(int j=i-1;j>=0;j--){
                if(arr[i]<arr[j] && dp[j]>max){
                    max = dp[j];
                }
            }
            dp[i] = max + 1;
            if(maxLen<dp[i])
                maxLen = dp[i];
        }

        return maxLen;
    }
}
