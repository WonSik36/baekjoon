/*
    baekjoon online judge
    problem number 12015
    https://www.acmicpc.net/problem/12015
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static boolean DEBUG = false;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.asList(br.readLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();
        
        int ret = getLengthOfLIS(arr); 
        bw.write(Integer.toString(ret));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getLengthOfLIS(int[] arr){
        int[] copy = new int[arr.length];
        int len = 0;

        for(int i=0;i<arr.length;i++){
            int idx = search(copy, arr[i], len);
            copy[idx] = arr[i];
            if(idx == len)
                len++;
            printArray(copy);
        }

        return len;
    }

    public static int search(int[] arr, int target, int len){
        if(len == 0)
            return 0;
        int left = 0, right = len-1;

        while(left <= right){
            int mid = (left+right)/2;

            if(arr[mid] == target)
                return mid;
            else if(arr[mid] > target)
                right = mid-1;
            else
                left = mid+1;
        }
        return left;
    }

    public static void print(int num){
        if(DEBUG)
            System.out.println(num);
    }

    public static void print(String str){
        if(DEBUG)
            System.out.print(str);
    }

    public static void print(String str, int... ints){
        if(DEBUG){
            String[] spl = str.split("%d");
            String output = spl[0];
            int idx = 1;
            for(int number:ints){
                output += number;
                if(idx < spl.length)
                    output += spl[idx++];
            }
            System.out.print(output);
        }
    }

    public static void printArray(int[] arr){
        if(DEBUG){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
    }
}
