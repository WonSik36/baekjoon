/*
    baekjoon online judge
    problem number 2960
    https://www.acmicpc.net/problem/2960
    Sieve of Eratosthenes
*/
import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;

public class IsPrime{
    public static void main(String[] args)throws IOException{ 
        Scanner sc = new Scanner(System.in);;
        int N = sc.nextInt();

        boolean[] sieve = new boolean[N+1];
        Arrays.fill(sieve, true);
        sieve[0] = false; sieve[1] = false;
        int sqrt = (int)Math.sqrt(N);

        int prime = 2;
        while(prime<=sqrt){
            int num = 2*prime;
            while(num <= N){
                if(sieve[num] == true){
                    // printArray(sieve);
                    sieve[num] = false;
                }
                num += prime;
            }
            prime = nextPrimeNum(sieve, prime);
        }

        System.out.println(sieve[N]);
        sc.close();
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
