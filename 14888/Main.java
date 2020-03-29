/*
    baekjoon online judge
    problem number 14888
    https://www.acmicpc.net/problem/14888

    BackTracking Problem
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.lang.StringBuilder;
import java.io.IOException;

public class Main{
    static int N;
    static int[] arr;
    static int[] operator;

    static final int PLUS = 0;
    static final int MINUS = 1;
    static final int MULTIPLY = 2;
    static final int DIVIDE = 3;
    static int MAX_VALUE = -1000000000;
    static int MIN_VALUE = 1000000000;
    static final char[] OPERATOR= {'+','-','*','/'};

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        operator = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            operator[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        backtrack(0, sb);

        bw.write(Integer.toString(MAX_VALUE)+"\n");
        bw.write(Integer.toString(MIN_VALUE)+"\n");
        bw.close();
        br.close();
    }

    public static void backtrack(int cnt, StringBuilder sb){
        // System.out.printf("cnt: %d sb: %s\n", cnt, sb.toString());
        
        if(cnt == N-1){
            String operand = Integer.toString(arr[cnt]);
            sb.append(operand);
            // System.out.printf("sb: %s\n", sb.toString());
            int result = eval(sb.toString());
            MAX_VALUE = Max(MAX_VALUE, result);
            MIN_VALUE = Min(MIN_VALUE, result);
            sb.setLength(sb.length()-operand.length());
            return;
        }

        String operand = Integer.toString(arr[cnt]);
        sb.append(operand);
        for(int i=0;i<4;i++){
            if(operator[i]>0){
                operator[i]--;
                sb.append(OPERATOR[i]);

                backtrack(cnt+1, sb);

                sb.setLength(sb.length()-1);
                operator[i]++;
            }
        }
        sb.setLength(sb.length()-operand.length());
    }

    public static int Max(int a, int b){
        return a>b?a:b;
    }

    public static int Min(int a, int b){
        return a<b?a:b;
    }

    public static int eval(String str){
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        int operator = PLUS;

        for(int i=0;i<str.length();i++){
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                sb.append(str.charAt(i));
            }else{
                int operand = Integer.parseInt(sb.toString());
                sb.setLength(0);
                switch(operator){
                    case PLUS:
                        sum = sum+operand;
                        break;
                    case MINUS:
                        sum = sum-operand;
                        break;
                    case MULTIPLY:
                        sum = sum*operand;
                        break;
                    case DIVIDE:
                        sum = sum/operand;
                        break;
                }

                switch(str.charAt(i)){
                    case '+':
                        operator = PLUS;
                        break;
                    case '-':
                        operator = MINUS;
                        break;
                    case '*':
                        operator = MULTIPLY;
                        break;
                    case '/':
                        operator = DIVIDE;
                        break;
                }
            }
        }

        int operand = Integer.parseInt(sb.toString());
        switch(operator){
            case PLUS:
                sum = sum+operand;
                break;
            case MINUS:
                sum = sum-operand;
                break;
            case MULTIPLY:
                sum = sum*operand;
                break;
            case DIVIDE:
                sum = sum/operand;
                break;
        }

        return sum;
    }
}
