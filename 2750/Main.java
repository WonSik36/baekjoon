/*
    baekjoon online judge
    problem number 2750
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
        insertionSort(arr);
        //selectionSort(arr);
        //bubbleSort(arr);
    }

    public static void insertionSort(int[] arr){
        
        for(int i=1;i<arr.length;i++){
            for(int j=0;j<i;j++){
                if(arr[j] > arr[i]){
                    int temp = arr[i];
                    for(int k=i;k>j;k--){
                        arr[k] = arr[k-1];
                    }
                    arr[j] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr){

        for(int i=0;i<arr.length;i++){
            int min = i;
            for(int j=i;j<arr.length;j++){
                if(arr[j] < arr[min])
                    min = j;
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }

    public static void bubbleSort(int[] arr){
        
        for(int i=arr.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
