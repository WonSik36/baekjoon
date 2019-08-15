/*
    baekjoon online judge
    problem number 11066
    https://www.acmicpc.net/problem/11066
    https://js1jj2sk3.tistory.com/3
    http://melonicedlatte.com/algorithm/2018/03/22/051337.html
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
            cost = new int[arr.length][arr.length];
            int min = getMinCost(arr,0,arr.length-1);
            bw.write(Integer.toString(min)+"\n");
            //printArray(cost);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int getMinCost(int[] arr, int first, int last)throws IOException{
        // String str = String.format("first: %d, last: %d",first,last);
        // System.out.println(str);
        if(cost[first][last]!=0){
            // str = String.format("cost[%d][%d] exist!: %d",first,last,cost[first][last]);
            // System.out.println(str);
            return cost[first][last];
        }else if(first == last){
            // this is very important
            cost[first][last] = 0;
            // str = String.format("%d == %d: %d",first,last,cost[first][last]);
            // System.out.println(str);
            return cost[first][last];
        }else if(last-first == 1){
            cost[first][last] = arr[first]+arr[last];
            // str = String.format("cost[%d][%d] doesn't exist!: %d",first,last,cost[first][last]);
            // System.out.println(str);
            return cost[first][last];
        }else{
            // maximum number is 44880000
            int min = 2147483647;
            for(int i=0;i<last-first;i++){
                int re = getMinCost(arr, first, first+i) + getMinCost(arr, first+i+1, last);
                if(min>re)
                    min = re;
            }
            // this is very important
            cost[first][last] = min + sumOfRange(arr, first, last);
            // str = String.format("cost[%d][%d] doesn't exist!: %d, sum: %d",first,last,cost[first][last],sumOfRange(arr, first, last));
            // System.out.println(str);
            return cost[first][last];
        }
    }

    static int sumOfRange(int[] arr, int first, int last){
        int sum = 0;
        for(int i=first;i<last+1;i++){
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