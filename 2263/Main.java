/*
    baekjoon online judge
    problem number 2263
    https://www.acmicpc.net/problem/2263

    Tree Traversal
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.lang.StringBuilder;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        StringBuilder preOrder = new StringBuilder();
        int[] inOrder = new int[N];
        int[] postOrder = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            inOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            postOrder[i] = Integer.parseInt(st.nextToken());
        }

        calcPreOrder(0, N-1, 0, N-1, preOrder, inOrder, postOrder);
        preOrder.append("\n");

        bw.write(preOrder.toString());
        bw.close();
        br.close();
    }

    // start1 for inOrder indexing
    // start2 for postOrder indexing
    static void calcPreOrder(int start1, int end1, int start2, int end2, StringBuilder preOrder, int[] inOrder, int[] postOrder){
        // System.out.printf("start1: %d, end1: %d, start2:%d, end2:%d\n", start1, end1, start2, end2);
        int value = postOrder[end2];
        if(end1 - start1 == 0){
            preOrder.append(value);  
            preOrder.append(' ');
            return;
        }

        preOrder.append(value);
        preOrder.append(' ');

        int idx = findIdx(start1, value, inOrder);
        
        if(idx != start1)
            calcPreOrder(start1, idx-1, start2, idx-1-start1+start2, preOrder, inOrder, postOrder);

        if(idx != end1)
            calcPreOrder(idx+1, end1, idx-start1+start2, end2-1, preOrder, inOrder, postOrder);
    }

    static int findIdx(int start, int value, int[] inOrder){
        for(int i=start;i<inOrder.length;i++){
            if(inOrder[i] == value)
                return i;
        }

        throw new RuntimeException();
    }
}
