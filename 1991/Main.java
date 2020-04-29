/*
    baekjoon online judge
    problem number 1991
    https://www.acmicpc.net/problem/1991

    Tree Traversal
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.StringBuilder;
import java.util.Collections;
import java.util.Comparator;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = null;

        int N = Integer.parseInt(br.readLine());
        List<Node> tree = new ArrayList<Node>();

        for(int i=0;i<N;i++){
            str = br.readLine();

            int cur = str.charAt(0) - 'A';
            int left = -1;
            int right = -1;
            if(str.charAt(2) != '.') left = str.charAt(2) - 'A';
            if(str.charAt(4) != '.') right = str.charAt(4) - 'A';

            tree.add(new Node(cur, left, right));
        }

        Collections.sort(tree, new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                if(n1.cur < n2.cur)
                    return -1;
                else if(n1.cur > n2.cur)
                    return 1;
                else
                    return 0;
            }
        });
        
        // printTree(tree);

        StringBuilder preOrder = new StringBuilder();
        StringBuilder inOrder = new StringBuilder();
        StringBuilder postOrder = new StringBuilder();
        
        dfs(0, tree, preOrder, inOrder, postOrder);
        
        preOrder.append("\n");
        inOrder.append("\n");
        postOrder.append("\n");

        bw.write(preOrder.toString());
        bw.write(inOrder.toString());
        bw.write(postOrder.toString());

        bw.close();
        br.close();
    }

    static void dfs(int cur, List<Node> tree, StringBuilder preOrder, StringBuilder inOrder, StringBuilder postOrder){
        preOrder.append((char)(cur + 'A'));

        if(tree.get(cur).left != -1)
            dfs(tree.get(cur).left, tree, preOrder, inOrder, postOrder);

        inOrder.append((char)(cur + 'A'));

        if(tree.get(cur).right != -1)
            dfs(tree.get(cur).right, tree, preOrder, inOrder, postOrder);

        postOrder.append((char)(cur + 'A'));
    }

    static void printTree(List<Node> tree){
        for(int i=0;i<tree.size();i++){
            int left = '.';
            int right = '.';

            if(tree.get(i).left != -1) left = tree.get(i).left+'A';
            if(tree.get(i).right != -1) right = tree.get(i).right+'A';

            System.out.printf("%c %c %c\n", (i+'A'), left, right);
        }
    }
}

class Node {
    public int cur;
    public int left;
    public int right;

    public Node(int cur, int left, int right){
        this.cur = cur;
        this.left = left;
        this.right = right;
    }
}