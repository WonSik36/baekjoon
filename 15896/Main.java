/*
    baekjoon online judge
    problem number 15896
    https://www.acmicpc.net/problem/15896

    Bit Caculating

    high reference:
    https://docs.google.com/presentation/d/1y4f_ZCcWgCZocPZozsaFZpn2AJSx3ZtPwEFM3h7NurU/edit?usp=sharing
    http://blog.njw.kr/221459439369
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.lang.StringBuilder;

// import java.io.FileReader;

public class Main{
    static final int MOD = 1999;
    static final int DIGIT = 30;

    static int N;
    static int[] A;
    static int[] B;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./test4.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // long start = System.nanoTime();

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        B = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            B[i] = Integer.parseInt(st.nextToken());
        }


        bw.write(Integer.toString(andAndPlus()));
        bw.write(" ");
        bw.write(Integer.toString(plusAndAnd()));
        bw.write("\n");

        // long end = System.nanoTime();

        // long time = end - start;
        // double seconds = time / 1000000000.0;
        // System.out.println("Time :" + seconds);

        bw.close();
        br.close();
    }

    static int andAndPlus(){
        int[] cntA = new int[DIGIT];
        int[] cntB = new int[DIGIT];
        

        // calc cntA, cntB
        for(int i=0;i<N;i++){
            for(int j=0, firstBit=1; j<DIGIT; j++, firstBit <<= 1){
                if((A[i] & firstBit) > 0)
                    cntA[j]++;
                if((B[i] & firstBit) > 0)
                    cntB[j]++;
            }
        }

        // printArr(cntA);
        // printArr(cntB);

        // get result
        int sum = 0;
        for(int i=0, digit=1; i<DIGIT; i++,digit<<=1){
            int tmp = ((cntA[i] % MOD) * (cntB[i] % MOD)) % MOD;
            sum += tmp * (digit % MOD);
            sum %= MOD;
        }

        return sum;
    }

    static int plusAndAnd(){
        int[][] minA = new int[DIGIT][2];
        int[][] maxA = new int[DIGIT][2];
        int[][] minB = new int[DIGIT][2];
        int[][] maxB = new int[DIGIT][2];

        for(int i=0;i<DIGIT;i++){
            Arrays.fill(minA[i], Integer.MAX_VALUE);
            Arrays.fill(minB[i], Integer.MAX_VALUE);
            Arrays.fill(maxA[i], Integer.MIN_VALUE);
            Arrays.fill(maxB[i], Integer.MIN_VALUE);
        }

        for(int i=0, firstBit=1, mask=1; i<DIGIT; i++, firstBit <<= 1, mask = (mask<<1)+1){
            for(int j=0;j<N;j++){
                int aFirst = (A[j] & firstBit) == 0 ? 0 : 1;
                int bFirst = (B[j] & firstBit) == 0 ? 0 : 1;

                minA[i][aFirst] = Math.min(minA[i][aFirst], A[j] & mask);
                maxA[i][aFirst] = Math.max(maxA[i][aFirst], A[j] & mask);
                minB[i][bFirst] = Math.min(minB[i][bFirst], B[j] & mask);
                maxB[i][bFirst] = Math.max(maxB[i][bFirst], B[j] & mask);
            }
        }

        int[] resArr = new int[DIGIT];
        Arrays.fill(resArr, 1);
        for(int i=0, firstBit=1; i<DIGIT; i++, firstBit<<=1){
            // check 2^i <= minA[i][0] + minB[i][0]
            if(minA[i][0] != Integer.MAX_VALUE && minB[i][0] != Integer.MAX_VALUE
                    && ((minA[i][0] + minB[i][0]) & firstBit) == 0)
                    resArr[i] = 0;

            // check maxA[i][1] + maxB[i][0] < 2^(i+1)
            if(maxA[i][1] != Integer.MIN_VALUE && maxB[i][0] != Integer.MIN_VALUE
                    && ((maxA[i][1] + maxB[i][0]) & firstBit) == 0)
                    resArr[i] = 0;

            // check maxA[i][0] + maxB[i][1] < 2^(i+1)
            if(maxA[i][0] != Integer.MIN_VALUE && maxB[i][1] != Integer.MIN_VALUE
                    && ((maxA[i][0] + maxB[i][1]) & firstBit) == 0)
                    resArr[i] = 0;

            // check 3*2^i <= minA[i][1] + minB[i][1]
            if(minA[i][1] != Integer.MAX_VALUE && minB[i][1] != Integer.MAX_VALUE
                    && ((minA[i][1] + minB[i][1]) & firstBit) == 0)
                    resArr[i] = 0;
        }

        int res = 0;
        for(int i=0, digit=1; i<DIGIT; i++, digit<<=1){
            if(resArr[i] == 1)
                res += digit;
        }

        return res;
    }

    static void printArr(int[] arr){
        for(int a : arr){
            System.out.printf("%d ", a);
        }
        System.out.println();
    }
}
