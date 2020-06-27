/*
    baekjoon online judge
    problem number 13511
    https://www.acmicpc.net/problem/13511

    Application of LCA(Lowest Common Ancestor) Algorithm
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
import java.util.Stack;

public class Main{
    static final int MAX_IDX = 18;
    
    static int N;
    static int M;
    
    static int[][] ancestor;
    static long[][] sum;
    static int[] level;
    static List<List<Node>> g;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        init();

        for(int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            g.get(u).add(new Node(v, weight, -1));
            g.get(v).add(new Node(u, weight, -1));
        }

        // dfs, update level, sparse table[0]
        dfs(0);

        // update sparse table
        updateTable();

        M = Integer.parseInt(br.readLine());
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());

            int u, v, k;
            long res = 0;
            switch(op){
                case 1:
                    u = Integer.parseInt(st.nextToken()) - 1;
                    v = Integer.parseInt(st.nextToken()) - 1;
                    res = query1(u, v);
                    break;
                case 2:
                    u = Integer.parseInt(st.nextToken()) - 1;
                    v = Integer.parseInt(st.nextToken()) - 1;
                    k = Integer.parseInt(st.nextToken()) - 1;
                    res = query2(u, v, k);
                    break;
                default:
                    throw new AssertionError("Unknown OP");
            }

            bw.write(Long.toString(res));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    static void init(){
        ancestor = new int[MAX_IDX][N];
        for(int i=0;i<MAX_IDX;i++){
            Arrays.fill(ancestor[i], -1);
        }

        sum = new long[MAX_IDX][N];

        level = new int[N];
        Arrays.fill(level, -1);

        g = new ArrayList<>();
        for(int i=0;i<N;i++){
            g.add(new ArrayList<>());
        }
    }

    static void dfs(int root){
        level[root] = 0;
        Stack<Node> st = new Stack<>();
        st.add(new Node(root, 0, 0));

        while(!st.isEmpty()){
            Node last = st.pop();

            List<Node> adj = g.get(last.num);
            for(Node n : adj){
                if(level[n.num] != -1)
                    continue;

                level[n.num] = last.level + 1;
                sum[0][n.num] = n.weight;
                ancestor[0][n.num] = last.num;
                
                st.add(new Node(n.num, -1, last.level+1));
            }
        }
    }

    static void updateTable(){
        for(int i=0;i<MAX_IDX-1;i++){
            for(int j=0;j<N;j++){
                if(ancestor[i][j] != -1){
                    ancestor[i+1][j] = ancestor[i][ancestor[i][j]];
                    sum[i+1][j] = sum[i][j] + sum[i][ancestor[i][j]];
                }
            }
        }
    }

    static long query1(int u, int v){
        long res = 0;

        if(level[u] > level[v]){
            int tmp = u;
            u = v;
            v = tmp;
        }

        for(int idx=0, gap = level[v] - level[u]; gap > 0; idx++, gap /= 2){
            if(gap % 2 == 1){
                res += sum[idx][v];
                v = ancestor[idx][v];
            }
        }

        if(u != v){
            for(int idx=MAX_IDX-1; idx >= 0; idx--){
                if(ancestor[idx][u] != -1 && ancestor[idx][u] != ancestor[idx][v]){
                    res += sum[idx][u];
                    res += sum[idx][v];

                    u = ancestor[idx][u];
                    v = ancestor[idx][v];
                }
            }

            res += sum[0][u];
            res += sum[0][v];
        }

        return res;
    }

    static long query2(final int U, final int V, final int K){
        long res = 0;
        int u = U, v = V, k;
        boolean flag = false;

        // U's level is bigger than V
        if(level[u] > level[v]){
            int tmp = u;
            u = v;
            v = tmp;
            flag = true;
        }

        for(int idx=0, gap = level[v] - level[u]; gap > 0; idx++, gap /= 2){
            if(gap % 2 == 1){
                v = ancestor[idx][v];
            }
        }

        // u is ancestor of v
        if(u == v){
            // U is ancestor of V
            u = U; 
            v = V;
            k = level[V] - level[U] - K;

            // U's level is bigger than V
            // V is ancestor of U
            if(flag){
                int tmp = u;
                u = v;
                v = tmp;
                k = K;
            }

            for(int idx=0; k > 0; idx++, k /= 2){
                if(k % 2 == 1){
                    v = ancestor[idx][v];
                }
            }

            res = v;
        }else{
            // find lca
            for(int idx=MAX_IDX-1; idx >= 0; idx--){
                if(ancestor[idx][u] != -1 && ancestor[idx][u] != ancestor[idx][v]){
                    u = ancestor[idx][u];
                    v = ancestor[idx][v];
                }
            }

            int lca = ancestor[0][u];

            // 3 cases
            // first case: K'th node is in path of U to LCA
            if(level[U] - level[lca] > K){
                u = U; 
                k = K;
                for(int idx=0; k > 0; idx++, k /= 2){
                    if(k % 2 == 1){
                        u = ancestor[idx][u];
                    }
                }
    
                res = u;
            // second case: K'th node is LCA
            }else if(level[U] - level[lca] == K){
                res = lca;

            // third case: K'th node is in path of LCA to U
            }else{
                v = V;
                k = (level[V] - level[lca]) - (K - (level[U]-level[lca]));
                for(int idx=0; k > 0; idx++, k /= 2){
                    if(k % 2 == 1){
                        v = ancestor[idx][v];
                    }
                }
    
                res = v;
            }
        }

        return res + 1;
    }
}

class Node {
    public int num;
    public int weight;
    public int level;

    public Node(int num, int weight, int level){
        this.num = num;
        this.weight = weight;
        this.level = level;
    }
}