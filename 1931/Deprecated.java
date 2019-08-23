/*
    baekjoon online judge
    problem number 1931
    https://www.acmicpc.net/problem/1931
    Activity Selection Problem
    *** It take much time using quick sort so time over happened ***
    *** And need to be update sort on finish time is equl ***
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Random;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int N = Integer.parseInt(str);
        int[] start = new int[N];
        int[] finish = new int[N];

        for(int i=0;i<N;i++){
            str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            start[i] = Integer.parseInt(st.nextToken());
            finish[i] = Integer.parseInt(st.nextToken());
        }
        // start is sorted by finish's sorted order
        quickSort(finish, start, 0, N-1);
        // printArray(finish);
        // printArray(start);
        int fidx = finish[0];
        int maxNum = 1;
        for(int i=1;i<N;i++){
            if(fidx <= start[i]){
                fidx = finish[i];
                maxNum++;
            }
        }

        bw.write(Integer.toString(maxNum));
        bw.flush();
        bw.close();
    }

    // array2 is sorted by array1's sorted order
    public static void quickSort(int[] arr1, int[] arr2, int begin, int end) {
        if (begin < end) {
            int p = partition(arr1, arr2, begin, end);
            quickSort(arr1, arr2, begin, p - 1);
            quickSort(arr1, arr2, p + 1, end);
        }
    }
 
    public static int partition(int[] arr1, int[] arr2, int begin, int end) {
        int left = begin;
        int right = end;
        int pidx = getPivot(left, right);
        int pivot = arr1[pidx];

        while (left < right) {
            while ((arr1[left] < pivot) && (left < right))
                left++;
            while ((arr1[right] > pivot) && (left < right))
                right--;
 
            if (left < right) {
                int temp = arr1[left];
                arr1[left] = arr1[right];
                arr1[right] = temp;
                // this is added line
                temp = arr2[left];
                arr2[left] = arr2[right];
                arr2[right] = temp;
            }
        }
        return left;
    }

    static int getPivot(int first, int last){
        Random r = new Random();

        return r.nextInt(last-first+1) + first;
    }

    static void printArray(int[] arr)throws IOException{
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}