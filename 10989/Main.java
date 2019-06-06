/*
    baekjoon online judge
    problem number 10989
    https://www.acmicpc.net/problem/10989
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.LinkedList;

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
        countingSort(arr);
        
        ////radix sort cause out of memory runtime error
        //Runtime r = Runtime.getRuntime();
        //radixSort(arr);
        //System.out.println(r.totalMemory() - r.freeMemory());
    }

    public static void countingSort(int[] arr){
        int[] sorted = new int[arr.length];
        int[] count = new int[10001];
        
        //counting
        for(int i=0;i<arr.length;i++){
            count[arr[i]]++;
        }
        //calculate cumulative sum
        for(int i=1;i<count.length;i++){
            count[i] += count[i-1];
        }
        //sort by cumulative sum
        for(int i=arr.length-1;i>=0;i--){
            --count[arr[i]];
            sorted[count[arr[i]]] = arr[i];
        }
        //copy and paste
        for(int i=0;i<arr.length;i++){
            arr[i] = sorted[i];
        }
    }

    public static void radixSort(int[] arr){
        LinkedList<Integer>[] count = new LinkedList[10];
        int[] bucket = new int[arr.length];
        int mod = 10, dev = 1;
        int maxDigit = getMaxDigit(arr);

        //init count array of linked list
        for(int i=0;i<10;i++){
            count[i] = new LinkedList<Integer>();
        }
        //copy and paste
        for(int i=0;i<arr.length;i++){
            bucket[i] = arr[i];
        }
        //radix sort
        for(int i=0;i<maxDigit;i++,mod*=10,dev*=10){
            //divide to count
            for(int j=0;j<bucket.length;j++){
                count[(bucket[j]%mod)/dev].add(bucket[j]);
            }
            //return to bucket
            int pos = 0;
            for(int k=0;k<10;k++){
                while(!count[k].isEmpty())
                    bucket[pos++] = count[k].poll();
            }
        }
        for(int i=0;i<arr.length;i++){
            arr[i] = bucket[i];
        }
    }

    public static int getMaxDigit(int[] arr){
        int max = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>max)
                max = arr[i];
        }

        return (int)Math.log10((double)max)+1;
    }
}