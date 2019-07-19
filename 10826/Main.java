/*
    baekjoon online judge
    problem number 10826
    https://www.acmicpc.net/problem/10826
    string calculator
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.StringBuilder;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        
        String result = fibo(num);
        StringBuilder sb = new StringBuilder();
        sb.append(result);
        bw.write(sb.reverse()+"\n");

        bw.flush();
        bw.close();
    }
    // this add function uses little endianness not big endianness
    public static String add(String num1,String num2)throws IOException{
        StringBuilder result = new StringBuilder();
        String n1, n2;
        //System.out.println("num1: "+num1+" num2: "+num2);
        if(num1.length() > num2.length()){
            n1 = num2;
            n2 = num1;
        }else{
            n1 = num1;
            n2 = num2;
        }

        int i=0, carry=0, sum=0;

        for(;i<n1.length();i++){
            sum = n1.charAt(i) + n2.charAt(i) - 2*'0' + carry;
            carry = sum / 10;
            sum %= 10;
            //System.out.println("i: "+i+" sum: "+sum+" carry: "+carry);
            result.append(sum);
        }

        for(;i<n2.length();i++){
            sum = n2.charAt(i) - '0' + carry;
            carry = sum / 10;
            sum %= 10;
            //System.out.println("i: "+i+" sum: "+sum+" carry: "+carry);
            result.append(sum);
        }
        if(carry == 1)
            result.append(carry);

        
        //System.out.println("result: "+result.toString());
        return result.toString();
    }
    
    public static String fibo(int n)throws IOException{
        String num1 = "0";
        String num2 = "1";        
        
        if(n == 0)
            return num1;
        else if(n == 1)
            return num2;
        else{
            String temp;
            for(int i=1; i<n; i++){
                temp = num1;
                num1 = num2;
                num2 = add(temp,num2);
            }
            return num2;
        }
    }
    
}
