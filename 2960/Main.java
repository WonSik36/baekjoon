/*
    baekjoon online judge
    problem number 2960
    https://www.acmicpc.net/problem/2960
    Sieve of Eratosthenes
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] sieve = new boolean[N+1];
        Arrays.fill(sieve, true);
        sieve[0] = false; sieve[1] = false;
        int sqrt = (int)Math.sqrt(N);
        int idx = 0;
        int target = 0;

        int prime = 2;
        while(prime<=N && target==0){
            int num = prime;
            while(num <= N){
                if(sieve[num] == true){
                    // printArray(sieve);
                    sieve[num] = false;
                    idx++;
                    if(idx == K){
                        target = num;
                        break;
                    }
                }
                num += prime;
            }
            prime = nextPrimeNum(sieve, prime);
        }

        bw.write(Integer.toString(target)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int nextPrimeNum(boolean[] arr, int num){
        while(num<arr.length-1){
            num++;
            if(arr[num] == true)
                break;
        }
        return num;
    }

    public static void printArray(boolean[] arr){
        for(int i=1;i<arr.length;i++)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
}
