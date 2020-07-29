/*
    baekjoon online judge
    problem number 12899
    https://www.acmicpc.net/problem/12899

    Application of Segment Tree
    high reference: https://sejinik.tistory.com/103
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    static final int MAX_LEN = 2000000;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        SegmentTree tree = new SegmentTree(MAX_LEN);
        
        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            if(arr[0] == 1){
                tree.append(arr[1]);
            }else{
                int num = tree.nthNum(arr[1]);
                tree.reduce(num);

                bw.write(Integer.toString(num));
                bw.write("\n");
            }
        }

        bw.close();
        br.close();
    }
}

class SegmentTree {
    private int height;
    private int size;
    private int[] tree;

    public SegmentTree(int len){
        height = calcHeight(len+1);
        size = calcSize(height);
        tree = new int[size];
    }

    public void append(int num){
        append(1,1,size/2,num,1);
    }

    public void reduce(int num){
        append(1,1,size/2,num,-1);
    }

    private void append(int nodeNum, int s, int e, int idx, int v){
        if(idx < s || e < idx)
            return;

        if(s == e){
            if(s == idx)
                tree[nodeNum] += v;

            return;
        }

        int mid = (s+e) >> 1;
        append(nodeNum*2, s, mid, idx, v);
        append(nodeNum*2+1, mid+1, e, idx, v);
        tree[nodeNum] = tree[nodeNum*2] + tree[nodeNum*2+1];
    }

    public int nthNum(int n){
        return nthNum(1, 1, size/2, n);
    }  

    private int nthNum(int nodeNum, int s, int e, int n){
        if(s == e)
            return s;

        int mid = (s+e) / 2;
        if(n <= tree[nodeNum*2]){
            return nthNum(nodeNum*2, s, mid, n);
        }else{
            return nthNum(nodeNum*2+1, mid+1, e, n - tree[nodeNum*2]);
        }
    }

    private static int calcHeight(int len){
        return (int)Math.ceil(Math.log(len) / Math.log(2));
    }

    private static int calcSize(int height){
        return (int)Math.pow(2, height+1);
    }
}