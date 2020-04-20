/*
    baekjoon online judge
    problem number 14002
    https://www.acmicpc.net/problem/14002

    Longest Incresing Subsequence

    Reference:
    LIS: https://www.crocus.co.kr/583
    Get LIS Array: https://www.crocus.co.kr/681
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.lang.StringBuilder;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        List<Integer> sequence = new ArrayList<Integer>();
        List<Pair> lis = new ArrayList<Pair>();

        /* get LIS's length */
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(st.nextToken());

            int idx = Collections.binarySearch(sequence, num);
            if(idx < 0)
                idx = (-1)*(idx+1);
            lis.add(new Pair(idx, num));

            if(idx == sequence.size()){
                sequence.add(num);
            }else{
                sequence.set(idx, num);
            }
        }

        int length = sequence.size();
        bw.write(Integer.toString(length)+"\n");
        
        /* get LIS contents */
        Stack<Integer> stack = new Stack<Integer>();
        int idx = length-1;
        for(int i=lis.size()-1;i>=0;i--){
            Pair tmp = lis.get(i);
            
            if(idx == tmp.idx){
                stack.add(tmp.value);
                idx--;
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
            sb.append(" ");
        }
        sb.append("\n");
        bw.write(sb.toString());

        bw.close();
        br.close();
    }
}

class Pair {
    public int idx;
    public int value;

    public Pair(int idx, int value){
        this.idx = idx;
        this.value = value;
    }
}