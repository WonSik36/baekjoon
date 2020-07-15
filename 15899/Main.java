/*
    baekjoon online judge
    problem number 15899
    https://www.acmicpc.net/problem/15899

    Merge Sort Tree
    high reference:
    https://justicehui.github.io/ps/2019/07/25/BOJ15899/
    https://suuntree.tistory.com/242
    https://pasdfq.blog.me/221319699850
    https://kks227.blog.me/221400656663

    Upper Bound
    https://m.blog.naver.com/occidere/221045300639

    do not use Stream for speed
    do not use boxing or unboxing of primitive type for speed
    recommend to use array not list
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{
    static final int MOD = 1000000007;

    static int N;
    static int M;
    static int C;

    static List<List<Integer>> g;
    static int[] color;
    static int[] in;
    static int[] out;
    
    static List<List<Integer>> tree;
    static int treeSize;
    static int pos = 0;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    
        treeSize = getTreeSize(N);

        g = new ArrayList<>();
        for(int i=0;i<=N;i++){
            g.add(new ArrayList<>());
        }

        color = new int[N+1];
        in = new int[N+1];
        out = new int[N+1];

        Arrays.fill(in, -1);

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            color[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            g.get(start).add(end);
            g.get(end).add(start);
        }

        dfs(1);
        initTree();

        int result = 0;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int target = Integer.parseInt(st.nextToken());
            int targetColor = Integer.parseInt(st.nextToken());

            int ret = range(target, targetColor);

            result += ret;
            result %= MOD;
        }

        bw.write(Integer.toString(result)+"\n");

        bw.close();
        br.close();
    }

    static int getTreeSize(int n){
        int height = (int)Math.ceil(baseLog(n,2));
        int size = (int)Math.pow(2,height+1);

        return size;
    }

    static double baseLog(double x, double base){
        return Math.log(x)/Math.log(base);
    }

    static void dfs(int cur){
        in[cur] = pos++;

        List<Integer> adj = g.get(cur);
        for(int node : adj){
            if(in[node] != -1)
                continue;

            dfs(node);
        }

        out[cur] = pos - 1;
    }

    static void initTree(){
        tree = new ArrayList<>(treeSize);
        for(int i=0;i<treeSize;i++){
            tree.add(new ArrayList<>());
        }

        for(int i=1;i<=N;i++){
            tree.get(in[i]+treeSize/2).add(color[i]);
        }

        // important part: merge sort tree use merge sort algorithm's merge function
        for(int i=treeSize/2-1;i>=1;i--){
            merge(tree.get(i), tree.get(i*2), tree.get(i*2+1));
        }
    }

    static void merge(List<Integer> dst, List<Integer> src1, List<Integer> src2){
        int idx1 = 0, idx2 = 0;

        while(idx1 < src1.size() && idx2 < src2.size()){
            // important part: do not unboxing object to primitive type
            if(src1.get(idx1).compareTo(src2.get(idx2)) < 0){
                dst.add(src1.get(idx1));
                idx1++;
            }else{
                dst.add(src2.get(idx2));
                idx2++;
            }
        }

        if(idx1 < src1.size()){
            for(;idx1<src1.size();idx1++){
                dst.add(src1.get(idx1));
            }
        }else{
            for(;idx2<src2.size();idx2++){
                dst.add(src2.get(idx2));
            }
        }
    }

    static int range(int target, int targetColor){
        return _range(1,0,treeSize/2-1,in[target],out[target],targetColor);
    }

    static int _range(int node, int s, int e, int l, int r, int c){

        if(r<s || l>e)
            return 0;

        if(l<=s && e<=r){
            List<Integer> treeNode = tree.get(node);
            return upperBound(treeNode, c);
        }

        int mid = (s+e) / 2;
        return _range(node*2, s, mid, l, r, c) + _range(node*2+1, mid+1, e, l, r, c);
    }

    static int upperBound(List<Integer> list, int target){
        int l = 0;
        int r = list.size();

        while(l < r){
            int mid = (l+r) / 2;

            if(list.get(mid) <= target){
                l = mid + 1;
            }else{
                r = mid;
            }
        }

        return r;
    }
}
