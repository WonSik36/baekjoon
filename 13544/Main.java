/*
    baekjoon online judge
    problem number 13544
    https://www.acmicpc.net/problem/13544

    Merge Sort Tree
    reference: https://kks227.blog.me/221400656663
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

public class Main{
    static int N;
    static int[] sequence;
    static int M;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        MergeSortTree tree = new MergeSortTree(sequence);
        // tree.print();

        M = Integer.parseInt(br.readLine());
        int res = 0;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int a = (res ^ Integer.parseInt(st.nextToken())) - 1;
            int b = (res ^ Integer.parseInt(st.nextToken())) - 1;
            int c = res ^ Integer.parseInt(st.nextToken());

            // System.out.printf("a:%d, b:%d, c:%d\n",a,b,c);
            res = tree.range(a, b, c);
            bw.write(Integer.toString(res));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }
}

class MergeSortTree {
    private int height;
    private int size;
    private List<List<Integer>> tree;

    public MergeSortTree(int[] leaf){
        height = calcHeight(leaf.length);
        size = calcSize(height);
        // System.out.printf("height: %d, size: %d\n", height, size);
        tree = Stream.generate(() -> new ArrayList<Integer>()).limit(size).collect(toList());
        initTree(leaf, tree);
    }

    private void initTree(int[] leaf, List<List<Integer>> tree){
        for(int i=0; i<leaf.length; i++){
            tree.get(i+size/2).add(leaf[i]);
        }

        for(int i=size/2-1; i >= 1; i--){
            merge(tree.get(i), tree.get(2*i), tree.get(2*i+1));
        }
    }

    private void merge(List<Integer> dst, List<Integer> src1, List<Integer> src2){
        int idx1 = 0, idx2 = 0;

        while(idx1 < src1.size() && idx2 < src2.size()){
            if(src1.get(idx1).compareTo(src2.get(idx2)) < 0){
                dst.add(src1.get(idx1));
                idx1++;
            }else{
                dst.add(src2.get(idx2));
                idx2++;
            }
        }

        if(idx1 < src1.size()){
            for(;idx1<src1.size();idx1++)
                dst.add(src1.get(idx1));
        }else{
            for(;idx2<src2.size();idx2++)
                dst.add(src2.get(idx2));
        }
    }

    public int range(final int l, final int r, final int target){
        return range(1, 0, size/2-1, l, r, target);
    }

    private int range(int nodeNum, int s, int e, final int l, final int r, final int target){
        // System.out.printf("nodeNum: %d, s: %d, e: %d, l: %d, r: %d, target: %d\n", nodeNum, s, e, l ,r, target);
        if(r < s || e < l){
            // System.out.printf("return: 0\n");
            return 0;
        }
        
        if(l<=s && e <= r){
            List<Integer> node = tree.get(nodeNum);
            int ret = node.size() - upperBound(node, target);
            // System.out.printf("return: %d\n", ret);
            return ret;
        }
        
        int mid = (s + e) >> 1;
        int ret = range(nodeNum*2, s, mid, l, r, target) + range(nodeNum*2+1, mid+1, e, l, r, target);
        // System.out.printf("return: %d\n", ret);
        return ret;
    }

    public void print(){
        for(List<Integer> list : tree){
            System.out.print("[");
            for(int v : list){
                System.out.printf("%d ", v);
            }
            System.out.println("]");
        }
    }

    private static int upperBound(List<Integer> list, int target){
        int l = -1, r = list.size();
        Integer targetObj = Integer.valueOf(target);

        while(l+1 < r){
            int mid = (l+r) >> 1;

            if(list.get(mid).compareTo(targetObj) <= 0){
                l = mid;
            }else{
                r = mid;
            }
        }

        return r;
    }

    private static int calcHeight(int leafNodeNum){
        return baseLog(leafNodeNum, 2);
    }

    private static int calcSize(int height){
        return (int)Math.pow(2, height+1);
    }

    private static int baseLog(int a, int b){
        return (int)Math.ceil(Math.log(a) / Math.log(b));
    }
}