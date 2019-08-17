/*
    baekjoon online judge
    problem number 11049
    https://www.acmicpc.net/problem/11049
    reference to 11066
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static int[][] cost;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int[] matrix = new int[num+1];
        cost = new int[num][num];
        int a,b = 0;
        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            StringTokenizer st = new StringTokenizer(inputStr);
            matrix[i] = a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
        }
        matrix[num] = b;
        // for(int i=0;i<=num;i++){
        //     System.out.print(matrix[i]+" ");
        // }
        // System.out.println();
        int min = getMinCost(matrix);
        //printArray(cost);

        bw.write(Integer.toString(min)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static int getMinCost(int[] arr)throws IOException{
        for(int d=1;d<arr.length-1;d++){
            for(int i=0;i+d<arr.length-1;i++){
                int j = i+d;
                cost[i][j] = 2147483647; // output is smaller than 2^31-1
                for(int k=i;k<j;k++){
                    int re = cost[i][k] + cost[k+1][j] + arr[i]*arr[k+1]*arr[j+1];
                    // String str = String.format("cost[%d][%d]= cost[%d][%d]+cost[%d][%d]+%d",i,j,i,k,k+1,j,arr[i]*arr[k+1]*arr[j+1]);
                    // System.out.println(str);
                    if(cost[i][j] > re)
                        cost[i][j] = re;
                }
            }
        }
        return cost[0][arr.length-2];
    }

    static void printArray(int[][] arr)throws IOException{
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++)
                System.out.print(arr[i][j]+" ");
            System.out.println();
        }
    }
}