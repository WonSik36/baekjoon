/*
    baekjoon online judge
    problem number 2473
    https://www.acmicpc.net/problem/2473

    Binary Search
    high reference:
    https://justicehui.github.io/koi/2019/02/27/BOJ2473/
    https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/util/Collections.java
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

        // Binary Search
        for(int i=0;i<N-2;i++){
            if(flag)
                break;

            for(int j=i+1;j<N-1;j++){
                long sum = -1 * (list.get(i) + list.get(j));
                int k = binarySearch(sum, j+1, N-1, list);  // j+1 <= k <= N-1 or -N-1 <= k <= -1

                if(k >= 0){
                    flag = true;
                    result[0] = list.get(i);
                    result[1] = list.get(j);
                    result[2] = list.get(k);
                    break;
                }

                k = -1 * (k+1);

                for(int idx=k-2; idx<=k+2; idx++){
                    if(idx <= j)
                        continue;
                    if(idx >= N)
                        break;

                    long num = list.get(idx);
                    // System.out.printf("i: %d, j: %d, k: %d\n", i, j, idx);
                    // System.out.printf("arr[i]: %d, arr[j]: %d, arr[k]: %d, sum: %d\n", list.get(i), list.get(j), num, num-sum);

                    if(Math.abs(num - sum) < Math.abs(min)){
                        result[0] = list.get(i);
                        result[1] = list.get(j);
                        result[2] = num;
                        min = num - sum;
                    }
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

    static int binarySearch(long target, int lo, int hi, List<Long> list){
        while(lo <= hi){
            int mid = (lo+hi) / 2;

            long value = list.get(mid);
            if(target == value){
                return mid;
            }else if(target < value){
                hi = mid-1;
            }else{
                lo = mid+1;
            }
        }

        return -1*(lo+1);
    }
}
