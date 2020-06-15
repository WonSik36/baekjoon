/*
    baekjoon online judge
    problem number 14425
    https://www.acmicpc.net/problem/14425

    Trie Algorithm
    Hash Map is very slower than Array
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Node root = new Node(0);
        for(int i=0;i<N;i++){
            root.add(br.readLine().toCharArray());
        }
        // System.out.println(root.toString());

        int res = 0;
        for(int i=0;i<M;i++){
            String str = br.readLine();

            if(root.find(str.toCharArray()))
                res++;
        }

        bw.write(Integer.toString(res)+"\n");

        bw.close();
        br.close();
    }
}

class Node {
    private final int level;
    private final Node[] child;
    private boolean last;

    public Node(int level){
        this.level = level;
        this.child = new Node[26];
        this.last = false;
    }

    public boolean find(char[] chars){
        if(chars.length == this.level)
            return false;

        int key = chars[this.level] - 'a';
        
        if(child[key] != null){
            // System.out.printf("key: %c, level: %d\n", key, level);
            if(this.level == chars.length-1 && this.child[key].last)
                return true;

            return child[key].find(chars);
        }else{
            return false;
        }
    }

    public void add(char[] chars){
        if(chars.length == this.level){
            this.last = true;
            return;
        }

        int key = chars[this.level] - 'a';

        if(child[key] != null){
            child[key].add(chars);
        }else{
            child[key] = new Node(this.level + 1);
            child[key].add(chars);
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<child.length;i++){
            if(child[i] == null)
                continue;

            for(int j=0;j<this.level;j++)
                sb.append("--");

            sb.append((char)(i + 'a'));
            sb.append("\n");
            sb.append(child[i].toString());
        }

        return sb.toString();
    }
}