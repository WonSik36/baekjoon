/*
    baekjoon online judge
    problem number 2747
    https://www.acmicpc.net/problem/2747
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        final int maxNum = 45;
        int[] fiboArr = new int[maxNum+1];
        boolean[] flag = new boolean[maxNum+1];

        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        
        int result = fibo(num,fiboArr,flag);
        bw.write(Integer.toString(result)+"\n");

        bw.flush();
        bw.close();
    }

    public static int fibo(int num,int[] fiboArr, boolean[] flag){
        if(flag[num] == true)
            return fiboArr[num];
        
        if(num==0)
            return 0;
        else if(num==1)
            return 1;
        else{
            int result = fibo(num-1,fiboArr,flag) + fibo(num-2,fiboArr,flag);
            fiboArr[num] = result;
            flag[num] = true;
            return result;
        }
    }
}
