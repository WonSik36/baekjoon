/*
    baekjoon online judge
    problem number 1976
    https://www.acmicpc.net/problem/1976

    Union Find

    reference: https://lmcoa15.tistory.com/99
    I thought given graph is directional
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
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] set = makeSet(N);

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                if(Integer.parseInt(st.nextToken()) == 1)
                    mergeSet(i, j, set);
            }
        }

        st = new StringTokenizer(br.readLine());
        boolean flag = true;
        int start;
        int end = Integer.parseInt(st.nextToken());
        for(int i=1;i<M;i++){
            start = end;
            end = Integer.parseInt(st.nextToken());

            if(findRoot(start, set) != findRoot(end, set)){
                flag = false;
                break;
            }
        }

        if(flag)
            bw.write("YES");
        else
            bw.write("NO");

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
