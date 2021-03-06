/*
    baekjoon online judge
    problem number 11066
    https://www.acmicpc.net/problem/11066
    https://js1jj2sk3.tistory.com/3
    http://melonicedlatte.com/algorithm/2018/03/22/051337.html
    
    Kruth's Optimization
    1) C[a][c]+C[b][d]<=C[a][d]+C[b][c] (a<=b<=c<=d)
    2) C[b][c] <= C[a][d] (a<=b<=c<=d)

    if 1,2 is hold
    and dp recurrence is form dp[i][j] = min(i<k<j){dp[i][k]+dp[k+1][j]} + C[i][j]
    than A[i][j-1]<=A[i][j]<=A[i+1][j] (where A take k which make dp[i][j] to be minimum)
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static int[][] cost;
    public static int[][] A;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        for(int i=0;i<num;i++){
            //get array
            inputStr = br.readLine();
            int chapter = Integer.parseInt(inputStr);
            int[] arr = new int[chapter];
            inputStr = br.readLine();
            StringTokenizer st = new StringTokenizer(inputStr);
            for(int j=0;j<chapter;j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }
            // get minimum value
            cost = new int[chapter][chapter];
            A = new int[chapter][chapter];
            int min = getMinCost(arr);
            bw.write(Integer.toString(min)+"\n");
            //printArray(cost);
            //printArray(A);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int getMinCost(int[] arr)throws IOException{
        /*
            initialize X compoment
            0 X 0 0 0
            0 0 X 0 0
            0 0 0 X 0
            0 0 0 0 X
            0 0 0 0 0        
        */
        for(int i=0;i<arr.length;i++){
            A[i][i] = i;
        }
        for(int d=1;d<arr.length;d++){
            for(int i=0;i+d<arr.length;i++){
                int j = i+d;
                cost[i][j] = 2147483647;
                // System.out.println("d: "+d+" i: "+i+" j: "+j);
                // k+1<=j should be in condition 
                // because if it isn't it occur ArrayIndexOutOfBoundsException
                for(int k=A[i][j-1];(k<=A[i+1][j])&&(k+1<=j);k++){
                    // System.out.println("k: "+k);
                    int re = cost[i][k] + cost[k+1][j] + sumOfRange(arr, i, j);
                    if(re < cost[i][j]){
                        cost[i][j] = re;
                        A[i][j] = k;
                    }
                }
            }
        }
        return cost[0][arr.length-1];
    }

    static int sumOfRange(int[] arr, int first, int last){
        int sum = 0;
        for(int i=first;i<=last;i++){
            sum += arr[i];
        }
        return sum;
    }

    static void printArray(int[][] arr)throws IOException{
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++)
                System.out.print(arr[i][j]+" ");
            System.out.println();
        }
    }
}