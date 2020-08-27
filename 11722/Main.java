/*
    baekjoon online judge
    problem number 11722
    https://www.acmicpc.net/problem/11722

    LIS(Longest Increasing Subsequence)
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.StringBuilder;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> seq = new ArrayList<>();
        int[] lis = new int[N];

        for(int i=0;i<arr.length;i++){
            int idx = getUpperBound(arr[i], seq);
            lis[i] = idx;

            if(idx == seq.size())
                seq.add(arr[i]);
            else
                seq.set(idx, arr[i]);
        }

        int size = seq.size();

        bw.write(Integer.toString(size)+"\n");
        // bw.write(getLIS(arr, lis, size)+"\n");

        bw.close();
        br.close();
    }

    static int getUpperBound(int value, List<Integer> list){
        int l = -1;
        int r = list.size();

        while(l+1 < r){
            int mid = (l+r)>>1;

            if(list.get(mid) <= value){
                r = mid;
            }else{
                l = mid;
            }
        }

        return r;
    }

    static String getLIS(int[] arr, int[] lis, int size){
        StringBuilder sb = new StringBuilder();
        size--;

        for(int i=lis.length-1;i>=0;i--){
            if(lis[i] == size){
                sb.insert(0, " ");
                sb.insert(0, arr[i]);
                size--;
            }
        }

        return sb.toString();
    }
}
