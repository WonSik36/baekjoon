/*
    baekjoon online judge
    problem number 10814
    https://www.acmicpc.net/problem/10814

    Radix Sort
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Queue<String>> radix = new ArrayList<Queue<String>>();
        for(int i=0;i<=200;i++){
            radix.add(new LinkedList<String>());
        }


        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            radix.get(age).add(name);
        }

        for(int i=1;i<=200;i++){
            Queue<String> q = radix.get(i);
            
            while(!q.isEmpty()){
                bw.write(Integer.toString(i));
                bw.write(" ");
                bw.write(q.poll());
                bw.write("\n");
            }
        }

        bw.close();
        br.close();
    }
}