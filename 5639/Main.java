/*
    baekjoon online judge
    problem number 5639
    https://www.acmicpc.net/problem/5639

    Binary Tree Traversal
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = null;

        List<Integer> preOrder = new ArrayList<Integer>();
        Stack<Integer> postOrder = new Stack<Integer>();

        // while(!(str = br.readLine()).equals("")){   // cmd
        while((str = br.readLine()) != null){   // eof
            preOrder.add(Integer.parseInt(str));
        }

        int N = preOrder.size();

        preOrder2PostOrder(0, N-1, preOrder, postOrder);

        while(!postOrder.isEmpty()){
            bw.write(Integer.toString(postOrder.pop()));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    static void preOrder2PostOrder(int start, int end, List<Integer> preOrder, Stack<Integer> postOrder){
        // System.out.printf("start: %d, end: %d\n",start,end);

        int value = preOrder.get(start);
        postOrder.push(value);

        if(start == end)
            return;

        int idx = findIdx(start+1, value, preOrder);
        
        if(idx == start+1 || idx == end+1)
            preOrder2PostOrder(start+1, end, preOrder, postOrder);
        else{
            preOrder2PostOrder(idx, end, preOrder, postOrder);
            preOrder2PostOrder(start+1, idx-1, preOrder, postOrder);
        }
    }

    static int findIdx(int start, int value, List<Integer> preOrder){
        for(int i=start;i<preOrder.size();i++){
            if(preOrder.get(i) > value)
                return i;
        }

        return preOrder.size();
    }
}
