/*
    baekjoon online judge
    problem number 10773
    https://www.acmicpc.net/problem/10773
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Stack;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int N = Integer.parseInt(str);
        Stack<Integer> st = new Stack<Integer>();
        for(int i=0;i<N;i++){
            str = br.readLine();
            int input = Integer.parseInt(str);
            if(input==0)
                st.pop();
            else   
                st.add(input);
        }
        int sum = 0;
        while(!st.isEmpty())
            sum += st.pop();

        bw.write(Integer.toString(sum));
        bw.flush();
        bw.close();
    }
}