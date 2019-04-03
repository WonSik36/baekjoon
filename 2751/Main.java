/*
    baekjoon online judge
    problem number 2751
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int[] arr = new int[num];
        
        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            arr[i] = Integer.parseInt(inputStr);
        }
        
        sortArr(arr);

        for(int i=0;i<num;i++){
            bw.write(Integer.toString(arr[i]));
            bw.write("\n");
        }
        //bw.write(outputStr);
        bw.flush();
        bw.close();
    }

    public static void sortArr(int[] arr){
        //startMergeSort(arr);
        //startHeapSort(arr);
        startQuickSort(arr);
    }

    public static void startMergeSort(int[] arr){
        int[] sorted = new int[arr.length];
        mergeSort(arr, sorted, 0, arr.length-1);   
    }

    public static void mergeSort(int[] arr, int[] sorted, int m, int n) {
        int middle;
        if (m < n) {
            middle = (m + n) / 2;
            mergeSort(arr, sorted, m, middle);
            mergeSort(arr, sorted, middle + 1, n);
            merge(arr, sorted, m, middle, n);
        }
    }

    public static void merge(int[] arr, int[] sorted, int m, int middle, int n) {
        int i, j, k, t;
 
        i = m;
        j = middle + 1;
        k = m;
 
        while (i <= middle && j <= n) {
            if (arr[i] <= arr[j])
                sorted[k] = arr[i++];
            else
                sorted[k] = arr[j++];
            k++;
        }
 
        if (i > middle) {
            for (t = j; t <= n; t++, k++)
                sorted[k] = arr[t];
        } else {
            for (t = i; t <= middle; t++, k++)
                sorted[k] = arr[t];
        }
 
        for (t = m; t <= n; t++)
            arr[t] = sorted[t];
 
    }

    public static void startHeapSort(int[] arr){
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        int[] heapArr = new int[arr.length+1];
        int heapSize = 0;
 
        for (int i = 0; i < arr.length; i++) {
            int j = ++heapSize;
 
            while ((j != 1) && (arr[i] > heapArr[j / 2])) {
                heapArr[j] = heapArr[j / 2];
                j /= 2;
            }
 
            heapArr[j] = arr[i];
        }
 
        for (int i = arr.length - 1; i >= 0; i--) {
            int parent = 1, child = 2;
            int item = heapArr[1], tmp = heapArr[heapSize--];
    
            while (child <= heapSize) {
                if ((child < heapSize) && (heapArr[child] < heapArr[child + 1]))
                    child++;
    
                if (tmp >= heapArr[child])
                    break;
    
                heapArr[parent] = heapArr[child];
                parent = child;
                child *= 2;
            }
    
            heapArr[parent] = tmp;
            arr[i] = item;
        }
    }

    public static void startQuickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }

    public static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int p = partition(arr, begin, end);
            quickSort(arr, begin, p - 1);
            quickSort(arr, p + 1, end);
        }
    }
 
    public static int partition(int arr[], int begin, int end) {
        int left = begin;
        int right = end;
 
        int pivot = arr[(left + right) / 2];
        
        // System.out.println("pivot: "+pivot);
        // for(int i=0;i<arr.length;i++){
        //     System.out.print(arr[i]+" ");
        // }
        // System.out.println();
        // for(int i=begin;i<=end;i++){
        //     System.out.print(arr[i]+" ");
        // }
        // System.out.println();
        while (left < right) {
            while ((arr[left] < pivot) && (left < right))
                left++;
            while ((arr[right] > pivot) && (left < right))
                right--;
 
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        //System.out.println("left: "+left);
        // int temp = arr[pivot];
        // arr[pivot] = arr[right];
        // arr[right] = temp;
 
        return left;
    }
}
