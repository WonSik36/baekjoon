/*
    baekjoon online judge
    problem number 1717
    https://www.acmicpc.net/problem/1717

    Union Find
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
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] set = makeSet(N);

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if(input == 0){
                // union
                mergeSet(A, B, set);
            }else{
                // check
                if(findRoot(A, set) == findRoot(B, set))
                    bw.write("YES\n");
                else
                    bw.write("NO\n");
            }
        }

        bw.close();
        br.close();
    }

    static int findRoot(int A, int[] set){
        // System.out.printf("set[%d] = %d\n", A, set[A]);
        if(set[A] == -1)
            return A;
        
        return set[A] = findRoot(set[A], set);
    }

    static void mergeSet(int A, int B, int[] set){
        int rootOfA = findRoot(A, set);
        int rootOfB = findRoot(B, set);

        if(rootOfA != rootOfB){
            if(rootOfA < rootOfB)
                set[rootOfB] = rootOfA;
            else
                set[rootOfA] = rootOfB;
        }
    }

    static int[] makeSet(int N){
        int[] ret = new int[N+1];

        for(int i=0;i<=N;i++){
            ret[i] = -1;
        }

        return ret;
    }
}
