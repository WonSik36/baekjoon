/*
    baekjoon online judge
    problem number 10804
    https://www.acmicpc.net/problem/10804
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Stack;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] arr = new int[21];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=1;i<=20;i++){
            arr[i] = i;
        }

        for(int i=0;i<10;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // push array element to stack from (from) to (to)
            for(int j=from;j<=to;j++){
                stack.push(arr[j]);
            }

            // insert element to array from (from) to (to)
            for(int j=from;j<=to;j++){
                arr[j] = stack.pop();
            }

            if(!stack.isEmpty())
                throw new AssertionError("stack is not empty");
        }

        for(int i=1;i<=20;i++)
            bw.write(Integer.toString(arr[i])+" ");
        bw.write("\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
