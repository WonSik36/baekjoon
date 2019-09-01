/*
    baekjoon online judge
    problem number 17298
    https://www.acmicpc.net/problem/17298
    from 0 to N
    compare input to head in stack
    if input is bigger than head than head should be popped
    else push input to head 
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Stack;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> order = new Stack<Integer>();
        
        String str = br.readLine();
        int N = Integer.parseInt(str);
        int[] arr = new int[N];

        str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        for(int i=0;i<N;i++){
            int input = Integer.parseInt(st.nextToken());
            if(stack.isEmpty()){
                stack.push(input);
                order.push(i);
            }else{
                while(!stack.isEmpty() && input > stack.peek()){
                    stack.pop();
                    arr[order.pop()] = input;
                }
                stack.push(input);
                order.push(i);
            }
        }
        while(!stack.isEmpty()){
            stack.pop();
            arr[order.pop()] = -1;
        }

        for(int i=0;i<N;i++){
            bw.write(Integer.toString(arr[i])+" ");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
}