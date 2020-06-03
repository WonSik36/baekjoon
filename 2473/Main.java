/*
    baekjoon online judge
    problem number 2473
    https://www.acmicpc.net/problem/2473

    3 Sum Algorithm
    reference:
    https://jaimemin.tistory.com/679
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        List<Long> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            long num = Long.parseLong(st.nextToken());
            list.add(num);
        }

        Collections.sort(list); // important
        long min = Long.MAX_VALUE;
        long[] result = new long[3];
        boolean flag = false;

        // 3 sum algorithm
        for(int i=0;i<N-2;i++){
            int j = i+1;
            int k = N-1;

            if(flag)
                break;

            while(j < k){
                long sum = list.get(i) + list.get(j) + list.get(k);
                // System.out.printf("i: %d, j: %d, k: %d\n",i,j,k);
                // System.out.printf("sum: %d, arr[i]: %d, arr[j]: %d, arr[k]: %d\n",sum,list.get(i),list.get(j),list.get(k));

                if(Math.abs(sum) < Math.abs(min)){
                    result[0] = list.get(i);
                    result[1] = list.get(j);
                    result[2] = list.get(k);
                    min = sum;
                }

                if(sum > 0){
                    k--;
                }else if(sum < 0){
                    j++;
                }else{
                    flag = true;
                    break;
                }
            }
        }

        Arrays.sort(result);
        for(long num : result){
            bw.write(Long.toString(num));
            bw.write(" ");
        }
        bw.write("\n");

        bw.close();
        br.close();
    }
}
