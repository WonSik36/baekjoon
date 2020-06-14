/*
    baekjoon online judge
    problem number 14725
    https://www.acmicpc.net/problem/14725

    Trie Algorithm
    reference: https://twpower.github.io/187-trie-concept-and-basic-problem
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.Map;
import java.util.TreeMap;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;

        Node root = new Node(0);

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            root.add(st);
        }

        bw.write(root.toString());

        bw.close();
        br.close();
    }
}

class Node {
    private final int level;
    private final Map<String, Node> child;

    public Node(int level){
        this.level = level;
        this.child = new TreeMap<>();
    }

    public Map<String, Node> child(){
        return this.child;
    }

    public void add(StringTokenizer st){
        if(!st.hasMoreTokens())
            return;
        String value = st.nextToken();

        if(child.containsKey(value)){
            child.get(value).add(st);
        }else{
            child.put(value, new Node(this.level+1));
            child.get(value).add(st);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String, Node> entry : child.entrySet()){
            // print --
            for(int j=0;j<level;j++){
                sb.append("--");
            }

            // print child
            sb.append(entry.getKey());
            sb.append("\n");
            sb.append(entry.getValue());
        }

        return sb.toString();
    }
}