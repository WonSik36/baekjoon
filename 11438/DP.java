/*
    baekjoon online judge
    problem number 11438
    https://www.acmicpc.net/problem/11438

    LCA(Lowest Common Ancestor) by Dynamic Programming
    reference: 
    http://blog.naver.com/jh20s/221339300027
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{
    static final int MAX_NODE_NUM = 100000;
    static final int MAX_INDEX = calcMaxIndex();

    static int N;
    static int M;

    static List<List<Integer>> g;
    static int[][] memo;
    static int[] level;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;
        
        N = Integer.parseInt(br.readLine());
        memo = new int[MAX_INDEX][N];
        level = new int[N];
        for(int i=0;i<MAX_INDEX;i++){
            Arrays.fill(memo[i], -1);
        }

        g = new ArrayList<>();
        for(int i=0;i<N;i++){
            g.add(new ArrayList<>());
        }

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            g.get(start).add(end);
            g.get(end).add(start);
        }
        
        // update memo[0] & level
        dfs(0);

        // update memo
        for(int i=0;i<MAX_INDEX-1;i++){
            for(int j=0;j<N;j++){
                if(memo[i][j] != -1){
                    memo[i+1][j] = memo[i][memo[i][j]];
                }
            }
        }

        M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int L = Integer.parseInt(st.nextToken()) - 1; 
            int R = Integer.parseInt(st.nextToken()) - 1;

            if(level[L] > level[R]){
                int tmp = L;
                L = R;
                R = tmp;
            }

            for(int gap = level[R] - level[L], idx = 0; gap > 0; idx++, gap /= 2){
                if(gap % 2 == 1)
                    R = memo[idx][R];
            }
            
            if(L != R){
                for(int idx = MAX_INDEX-1; idx >= 0; idx--){
                    if(memo[idx][L] != -1 && memo[idx][L] != memo[idx][R]){
                        L = memo[idx][L];
                        R = memo[idx][R];
                    }
                }

                L = memo[0][L];
            }

            bw.write(Integer.toString(L+1));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    static void dfs(int root){
        level[root] = 0;
        boolean[] visited = new boolean[N];
        visited[root] = true;
        Stack<Node> st = new Stack<>();
        st.add(new Node(root, 0));

        while(!st.isEmpty()){
            Node last = st.pop();

            List<Integer> adj = g.get(last.num);
            for(int num : adj){
                if(visited[num])
                    continue;

                visited[num] = true;
                memo[0][num] = last.num;
                level[num] = last.level + 1;
                st.add(new Node(num, last.level+1));
            }
        }
    }

    static int calcMaxIndex(){
        return (int)Math.ceil(Math.log(MAX_NODE_NUM) / Math.log(2));
    }
}

class Node {
    public int num;
    public int level;

    public Node(int num, int level){
        this.num = num;
        this.level = level; 
    }
}