/*
    baekjoon online judge
    problem number 1629
    https://www.acmicpc.net/problem/1629
    https://onsil-thegreenhouse.github.io/programming/problem/2018/03/29/problem_math_power/

    (A*B)&C = ((A%C)*(B%C))%C
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
        
        String inputStr = br.readLine();
        String[] arr = inputStr.split(" ");
        int A = Integer.parseInt(arr[0]);
        int B = Integer.parseInt(arr[1]);
        int C = Integer.parseInt(arr[2]);
        
        long re1 = calcPowRecur(A, B, C);
        long re2 = calcPow(A, B, C);
        
        bw.write(Long.toString(re1)+"\n");
        bw.write(Long.toString(re2)+"\n");
        bw.flush();
        bw.close();
    }

    public static long calcPowRecur(int a, int b, int c){
        if(b == 0)
            return 1;
        else{
            long temp = calcPowRecur(a, b/2, c);
            if(b%2 == 0)
                return temp * temp % c;
            else
                return temp * temp * a % c; 
                // this line can cause overflow
                // ex) 100000000 3 1000000000
                // this function answer 206896640
                // but 0 is right answer
                // because (int) * (int) * (int) can cause overflow in long variable
        }
    }
    
    public static long calcPow(int a, int b, int c){
        long result = 1;
        long multiply = a%c;

        while(b > 0){
            if(b%2 == 1){
                result *= multiply;
                result %= c;
            }
            multiply = multiply * multiply % c;
            b /= 2;
        }
        return result;
    }
}
