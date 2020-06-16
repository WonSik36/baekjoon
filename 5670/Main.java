/*
    baekjoon online judge
    problem number 5670
    https://www.acmicpc.net/problem/5670

    Trie Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.lang.StringBuilder;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        while(true){
            String str = br.readLine();
            if(str == null || str.equals(""))
                break;

            int N = Integer.parseInt(str);
            String[] arr = new String[N];
            for(int i=0;i<N;i++){
                arr[i] = br.readLine();
            }

            String res = String.format("%.2f\n", trie(arr));
            bw.write(res);
        }

        bw.close();
        br.close();
    }

    static double trie(String[] arr){
        Node root = new Node(0);

        for(int i=0;i<arr.length;i++){
            root.add(arr[i].toCharArray());
        }

        // System.out.println(root.toString());

        int sum = 0;
        for(int i=0;i<arr.length;i++){
            int num = root.find(arr[i].toCharArray());
            sum += num;
        }

        return (double)sum / arr.length;
    }


}

class Node {
    private static final int HASH_NUM = 26;
    private final int level;
    private final Node[] child;
    private boolean isFinal;
    private int numOfChild;

    public Node(int level){
        this.level = level;
        this.child = new Node[HASH_NUM];
        isFinal = false;
        numOfChild = 0;
    }

    public void add(char[] chars){
        if(chars.length == this.level){
            this.isFinal = true;
            return;
        }

        int key = chars[this.level] - 'a';
        if(child[key] == null){
            child[key] = new Node(this.level + 1);
            numOfChild++;
        }

        child[key].add(chars);
    }

    public int find(char[] chars){
        if(chars.length == this.level && isFinal){
            return 0;
        }

        int key = chars[this.level] - 'a';
        if(child[key] == null){
            throw new AssertionError("Not Matching");
        }

        if(level != 0 && numOfChild == 1 && !isFinal){
            return child[key].find(chars);
        }else{
            return child[key].find(chars) + 1;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<HASH_NUM;i++){
            if(child[i] == null)
                continue;

            for(int j=0;j<this.level;j++){
                sb.append("--");
            }

            sb.append((char)(i+'a'));
            sb.append("\n");
            sb.append(child[i].toString());
        }

        return sb.toString();
    }
}