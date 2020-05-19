/*
    baekjoon online judge
    problem number 1145
    https://www.acmicpc.net/problem/1145

    Greatest Common Denominator
    & Least Common Multiple
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main{
    static int MIN_VALUE = Integer.MAX_VALUE;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[5];

        for(int i=0;i<arr.length;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> list = new ArrayList<Integer>();
        backtrack(0, arr, list);

        list.add(arr[0]);
        backtrack(0, arr, list);
        list.remove(0);

        bw.write(Integer.toString(MIN_VALUE)+"\n");

        bw.close();
        br.close();
    }

    static void backtrack(int idx, int[] arr, List<Integer> list){
        if(idx == 4 && list.size() < 3)
            return;

        if(list.size() == 3){
            int LCM = lcm(list.get(0), list.get(1));
            LCM = lcm(LCM, list.get(2));
            MIN_VALUE = Min(MIN_VALUE, LCM);
            return;
        }
        
        backtrack(idx+1, arr, list);

        list.add(arr[idx+1]);
        backtrack(idx+1, arr, list);
        list.remove(list.size()-1);
    }

    static int Min(int a, int b){
        return a<b?a:b;
    }

    static int lcm(int a, int b){
        int GCD = gcd(a, b);
        
        return a*b/GCD;
    }

    static int gcd(int a, int b){
        int r;

        while(b != 0){
            r = a%b;
            a = b;
            b = r;
        }

        return a;
    }
}
