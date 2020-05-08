/*
    baekjoon online judge
    problem number 4195
    https://www.acmicpc.net/problem/4195

    Union Find
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        ListSet friendNetwork = new ListSet();
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            friendNetwork.clear();
            int M = Integer.parseInt(br.readLine());

            for(int j=0;j<M;j++){
                st = new StringTokenizer(br.readLine());
    
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();
    
                int size = friendNetwork.merge(friend1, friend2);
                
                bw.write(Integer.toString(size));
                bw.write("\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}

class ListSet {
    private Map<String, Integer> mapping;
    private List<Integer> root;
    private List<Integer> size;

    public ListSet(){
        mapping = new HashMap<String, Integer>();
        root = new ArrayList<Integer>();
        size = new ArrayList<Integer>();
    }

    public int merge(String str1, String str2){
        int A = put(str1);
        int B = put(str2);

        int rootOfA = getRoot(A);
        int rootOfB = getRoot(B);

        if(rootOfA == rootOfB)
            return size.get(rootOfA);

        int sizeOfSet = 0;
        if(rootOfA < rootOfB){
            root.set(rootOfB, rootOfA);
            sizeOfSet = size.get(rootOfA) + size.get(rootOfB);
            size.set(rootOfA, sizeOfSet);
        } else {
            root.set(rootOfA, rootOfB);
            sizeOfSet = size.get(rootOfA) + size.get(rootOfB);
            size.set(rootOfB, sizeOfSet);
        }

        return sizeOfSet;
    }

    private int put(String str){
        if(mapping.containsKey(str))
            return mapping.get(str);

        int value = mapping.size();
        mapping.put(str, value);
        root.add(-1);
        size.add(1);

        return value;
    }

    public void clear(){
        mapping.clear();
        root.clear();
        size.clear();
    }

    private int getRoot(int node){
        if(root.get(node) == -1)
            return node;

        int rootNode = getRoot(root.get(node));
        root.set(node, rootNode);
        
        return rootNode;
    }
}