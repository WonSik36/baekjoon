/*
    baekjoon online judge
    problem number 2164
    https://www.acmicpc.net/problem/2164
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayDeque<Integer> ad = new ArrayDeque<Integer>();

        String str = br.readLine();
        int N = Integer.parseInt(str);

        for(int i=1;i<=N;i++){
            ad.addLast(i);
        }

        while(ad.size()!=1){
            ad.removeFirst();
            ad.addLast(ad.removeFirst());
        }

        bw.write(Integer.toString(ad.getFirst()));
        bw.flush();
        bw.close();
        br.close();
    }
}