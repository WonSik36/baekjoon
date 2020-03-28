/*
    baekjoon online judge
    problem number 2632
    https://www.acmicpc.net/problem/2632

    get number of cases takes O(N^2) time complexity
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] caseA = new int[1000001];
        int[] caseB = new int[1000001];

        int target = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[] arrA = new int[A];
        int[] arrB = new int[B];


        for(int i=0;i<A;i++){
            arrA[i] = Integer.parseInt(br.readLine());
        }   
        for(int i=0;i<B;i++){
            arrB[i] = Integer.parseInt(br.readLine());
        }   

        getCase(arrA, caseA);
        getCase(arrB, caseB);

        // printArray(caseA,100);
        // printArray(caseB,100);

        long result = 0;
        for(int i=0;i<=target;i++){
            result += caseA[i]*caseB[target-i];
        }

        bw.write(Long.toString(result)+"\n");
        bw.close();
        br.close();
    }

    static void getCase(int[] arr, int[] cases){
        int sum;
        cases[0] = 1;

        for(int i=0;i<arr.length;i++){
            sum = 0;

            for(int j=0, idx=i;j<arr.length-1;j++){
                sum += arr[idx];
                cases[sum]++;
                idx = getNextIdx(idx, arr);
            }
        }

        sum = 0;
        for(int i=0;i<arr.length;i++){
            sum += arr[i];
        }
        cases[sum]++;
    }

    static int getNextIdx(int idx, int[] arr){
        return idx+1==arr.length?0:idx+1;
    }

    static void printArray(int[] arr, int bound){
        for(int i=0;i<=bound;i++){
            System.out.printf("%d ",arr[i]);
        }
        System.out.println();
    }
}
