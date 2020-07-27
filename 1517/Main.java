/*
    baekjoon online judge
    problem number 1517
    https://www.acmicpc.net/problem/1517

    Merge Sort Algorithm
    reference: https://justicehui.github.io/ps/2019/04/23/BOJ1517/
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static long res;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long res = mergeSort(arr);
        bw.write(Long.toString(res));
        bw.write("\n");

        bw.close();
        br.close();
    }

    static long mergeSort(int[] arr){
        res = 0;

        mergeSort(arr, 0, arr.length-1);

        return res;
    }

    static void mergeSort(int[] arr, int l, int r){
        
        if(l < r){
            int mid = (l+r) / 2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid+1, r);
            merge(arr, l, r);
        }
    }

    static void merge(int[] arr, final int l, final int r){
        int mid = (l+r) / 2;
        int[] tmp = new int[r-l+1];
        int s = l, e = mid+1;
        int idx = 0;

        int dup = 0;
        while(s <= mid && e <= r){
            if(arr[s] <= arr[e]){
                tmp[idx++] = arr[s++];
                res += dup;
            }else{
                tmp[idx++] = arr[e++];
                dup++;
            }
        }

        if(s > mid){
            for(;e<=r;e++){
                tmp[idx++] = arr[e];
            }
        }else{
            for(;s<=mid;s++){
                tmp[idx++] = arr[s];
                res += dup;
            }
        }

        System.arraycopy(tmp, 0, arr, l, tmp.length);
    }
}
