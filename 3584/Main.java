/*
    baekjoon online judge
    problem number 3584
    https://www.acmicpc.net/problem/3584

    Lowest Common Ancestor
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;
        List<Node> list = null;

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++){
            int N = Integer.parseInt(br.readLine());

            list = new ArrayList<>();
            for(int j=0;j<N;j++){
                list.add(new Node(j));
            }

            for(int j=0;j<N-1;j++){
                st = new StringTokenizer(br.readLine());

                int parent = Integer.parseInt(st.nextToken()) - 1;
                int child = Integer.parseInt(st.nextToken()) - 1;

                list.get(parent).childs.add(child);
                list.get(child).parent = parent;
            }

            int root = -1;
            for(Node n : list){
                if(n.parent == -1){
                    root = n.num;
                    break;
                }
            }

            if(root == -1)
                throw new AssertionError("No Root Value");

            // bfs
            list.get(root).level = 0;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(root);
            while(!queue.isEmpty()){
                int first = queue.poll();

                for(int node : list.get(first).childs){
                    list.get(node).level = list.get(first).level + 1;
                    queue.add(node);
                }
            }

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            if(list.get(x).level > list.get(y).level){
                int tmp = x;
                x = y;
                y = tmp;
            }

            while(list.get(x).level < list.get(y).level){
                y = list.get(y).parent;
            }

            while(x != y){
                x = list.get(x).parent;
                y = list.get(y).parent;
            }

            bw.write(Integer.toString(x+1));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }
}

class Node {
    public final int num;
    public int level;
    public int parent;
    public List<Integer> childs;

    public Node(int num){
        this.num = num;
        this.level = -1;
        this.parent = -1;
        this.childs = new ArrayList<>();
    }
}