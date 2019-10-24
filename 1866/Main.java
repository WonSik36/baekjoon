/*
    baekjoon online judge
    problem number 1866
    https://www.acmicpc.net/problem/1866
    https://justicehui.github.io/koi/2019/01/15/BOJ1866/

    high level reference
    using dp, prefix sum
    by using prefix sum, there is no need to use loop for calculating sum of i~j
    instead just use sum[i] - sum[j-1]
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

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // get 1st line
        int N = Integer.parseInt(br.readLine());
        int[] box  = new int[N+1];
        // get 2nd line
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            box[i] = Integer.parseInt(st.nextToken());
        }
        // get 3rd line
        st = new StringTokenizer(br.readLine());
        int truck = Integer.parseInt(st.nextToken());
        int helicopter = Integer.parseInt(st.nextToken());

        int min = getMinCost(box, truck, helicopter);

        bw.write(Integer.toString(min)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getMinCost(int[] box, int truck, int helicopter){
        int N = box.length-1;
        int[] sum = new int[box.length];
        int[] dp = new int[box.length];

        // initialize sum for prefix sum
        Arrays.sort(box);
        for(int i=1;i<=N;i++){
            sum[i] = sum[i-1]+box[i];
        }

        // calculate dp
        // dp[i] = min(dp[i-1]+box[i]*truck, minOfNumbers(dp[j-1]+costOfMid2(j~i)+hellicopter))
        for(int i=1;i<=N;i++){
            dp[i] = dp[i-1]+box[i]*truck;
            for(int j=1;j<=i;j++){
                int mid = (i+j)/2;
                // ((mid-j)*box[mid] - (sum[mid-1]-sum[j-1])) = |box[mid] - box[j]| + |box[mid] - box[j+1]| + ... + |box[mid] - box[mid-1]|
                int underMidCost = ((mid-j)*box[mid] - (sum[mid-1]-sum[j-1])) * truck;
                // ((sum[i]-sum[mid]) - (i-mid)*box[mid]) = |box[i] - box[mid]| + |box[i-1] - box[mid]| + ... + |box[mid+1] - box[mid]|
                int overMidCost = ((sum[i]-sum[mid]) - (i-mid)*box[mid])*truck;
                dp[i] = Min(dp[i], dp[j-1]+underMidCost+overMidCost+helicopter);
            }
        }

        return dp[N];
    }

    public static int Min(int a, int b){
        return a<b?a:b;
    }
}
