/*
    baekjoon online judge
    problem number 11053
    https://www.acmicpc.net/problem/11053
    https://jason9319.tistory.com/113
    https://seungkwan.tistory.com/m/8
    LIS(Longest Increasing Subsequence) Problem which Big O is NlogN
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int[] arr = new int[num];
        inputStr = br.readLine();
        StringTokenizer st = new StringTokenizer(inputStr);
        //get input array
        for(int i=0;i<num;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // the main algorithm
        // copy's length is LIS's length
        // LIS is for actual LIS array
        int[] copy = new int[num];
        //int[] LIS = new int[num];
        int len = 0;
        for(int i=0;i<num;i++){
            int idx = getLowerBound(copy, len, arr[i]);
            copy[idx] = arr[i];
            //LIS[i] = idx;
            if(idx == len)
                len++;   
        }
        // print real LIS
        //printLIS(arr, LIS, len);
        bw.write(Integer.toString(len)+"\n");
        bw.flush();
        bw.close();
    }

    // it uses binary search algorithm
    static int getLowerBound(int[] arr, int len, int target){
        if(len == 0)
            return 0;
        int[] copy = Arrays.copyOfRange(arr, 0, len);
        int first = 0;
        int last = len-1;
        int mid = 0;

        while(first <= last){
            mid = (first+last)/2;
            if(copy[mid] == target)
                return mid;
            else if(copy[mid]>target)
                last = mid-1;
            else
                first = mid+1;
            
        }
        return first;   // if target is larget than arr[n] and smaller than arr[n+1] it return n+1
        //if target is larger than all element in the array than return array length
    }

    // it find out LIS's element
    // arr for input array
    // LIS is array that has arr[i]'s index in copy
    // idx indicate arr, LIS's index
    // len indicate LIS's length 
    static void backTrace(int[] arr, int[] LIS, int idx, int len)throws IOException{
        if(len<0)
            return;
        else{
            if(LIS[idx] == len){
                backTrace(arr, LIS, idx-1, len-1);
                System.out.print(arr[idx]+" ");
            }else{
                backTrace(arr, LIS, idx-1, len);
            }

        }
    }

    static void printLIS(int[] arr, int[] LIS, int len)throws IOException{
        backTrace(arr, LIS, arr.length-1, len-1);
        System.out.println();
    }

}
