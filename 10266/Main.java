/*
    baekjoon online judge
    problem number 10266
    https://www.acmicpc.net/problem/10266

    KMP Algorithm and Hashing
    Circular Strings
    High reference: 
    https://m.blog.naver.com/kks227/220711637476
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final int RADIUS = 360000;
    
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;
        boolean[] input1 = new boolean[RADIUS];
        boolean[] input2 = new boolean[RADIUS];

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int r = Integer.parseInt(st.nextToken());
            input1[r] = true;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int r = Integer.parseInt(st.nextToken());
            input2[r] = true;
        }

        int res = appKmp(input1, input2);

        if(res == -1){
            bw.write("impossible\n");
        }else{
            bw.write("possible\n");
        }

        bw.close();
        br.close();
    }

    static int appKmp(boolean[] arr1, boolean[] arr2){
        // using circular string
        boolean[] doubleArr1 = new boolean[2*RADIUS];
        System.arraycopy(arr1, 0, doubleArr1, 0, RADIUS);
        System.arraycopy(arr1, 0, doubleArr1, RADIUS, RADIUS);
        
        int[] fail = getFailure(arr2);

        for(int i=1,j=0; i<doubleArr1.length; i++){
            while(j > 0 && doubleArr1[i] != arr2[j])
                j = fail[j-1];

            if(doubleArr1[i] == arr2[j]){
                if(j == arr2.length-1)
                    return i - arr2.length + 1;
                else
                    j++;
            }
        }

        return -1;
    }

    static int[] getFailure(boolean[] arr){
        int[] fail = new int[arr.length];

        for(int i=1,j=0; i<arr.length; i++){
            while(j > 0 && arr[i] != arr[j])
                j = fail[j-1];

            if(arr[i] == arr[j])
                fail[i] = ++j;
        }

        return fail;
    }
}
