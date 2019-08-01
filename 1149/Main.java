/*
    baekjoon online judge
    problem number 1149
    https://www.acmicpc.net/problem/1149
    Dynamic Programming
    https://m.blog.naver.com/occidere/220785383050
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    static final int R = 0;
    static final int G = 1;
    static final int B = 2;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int[][] costs = new int[num+1][3];  // sum of 1st to Nth houses entire minimum costs
        // costs[0] should be {0,0,0} because firstly it takes zero cost

        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            StringTokenizer st = new StringTokenizer(inputStr);
            int redCost = Integer.parseInt(st.nextToken());
            int greenCost = Integer.parseInt(st.nextToken());
            int blueCost = Integer.parseInt(st.nextToken());

            // costs[i+1] means cost of current(i+1) color plus minimum cost of 1st to ith costs
            costs[i+1][R] = redCost + minExceptI(costs[i], R);
            costs[i+1][G] = greenCost + minExceptI(costs[i], G);
            costs[i+1][B] = blueCost + minExceptI(costs[i], B);
        }

        // for(int i=0;i<num+1;i++){
        //     String str = String.format("%d %d %d",costs[i][R], costs[i][G],costs[i][B]);
        //     System.out.println(str);
        // }

        int min = Min(costs[num]);
        bw.write(Integer.toString(min)+"\n");
        bw.flush();
        bw.close();
    }

    static int Min(int[] arr){
        int min = 1000000;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<min)
                min = arr[i];
        }

        return min;
    }

    static int minExceptI(int[] arr, int i){
        int min = 1000000;
        for(int j=0;j<arr.length;j++){
            if(arr[j]<min && j!=i)
                min = arr[j];
        }

        return min;
    }
}