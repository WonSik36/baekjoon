/*
    baekjoon online judge
    problem number 4013
    https://www.acmicpc.net/problem/4013

    Application
    SCC(Strongly Connected Component) Algorithm
    And Tree & DP
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
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;
import java.lang.StringBuilder;

// import java.io.FileReader;

public class Main{
    static int V;
    static int E;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // BufferedReader br = new BufferedReader(new FileReader("./test/small/small-dag-0.in"));
        // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int[] weight = new int[V];
        List<List<Integer>> g = Stream.generate(() -> (new ArrayList<Integer>())).limit(V).collect(toList());

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            g.get(start).add(end);
        }

        for(int i=0;i<V;i++){
            weight[i] = Integer.parseInt(br.readLine());
        }

        st = new StringTokenizer(br.readLine()); 
        int start = Integer.parseInt(st.nextToken()) - 1;

        List<Integer> restaurant = Arrays.stream(br.readLine().split(" "))
                .mapToInt(x -> (Integer.parseInt(x)-1))
                .boxed()
                .collect(toList());

        Stack<Integer> order = dfs(g);

        List<List<Integer>> t = transpose(g);

        int[] scc = dfs(t, order);

        int sccNum = Arrays.stream(scc).max().orElse(-1) + 1;

        int[] sccWeight = getSccWeight(scc, weight, sccNum);
        boolean[] isRestaurant = getIsRestaurant(scc, restaurant, sccNum);

        List<List<Integer>> tree = makeSccTree(g, scc, sccNum);

        int result = dp(scc[start], tree, sccWeight, isRestaurant);

        bw.write(Integer.toString(result)+"\n");

        bw.close();
        br.close();
    }

    static Stack<Integer> dfs(List<List<Integer>> g){
        Stack<Integer> order = new Stack<Integer>();
        boolean[] visited = new boolean[V];

        for(int i=0;i<V;i++){
            if(visited[i])
                continue;

            _dfs(i, g, visited, order);
        }

        return order;
    }

    static void _dfs(int cur, List<List<Integer>> g, boolean[] visited, Stack<Integer> order){
        visited[cur] = true;
        List<Integer> adj = g.get(cur);

        for(int num : adj){
            if(visited[num])
                continue;

            _dfs(num, g, visited, order);
        }

        order.add(cur);
    }

    static List<List<Integer>> transpose(List<List<Integer>> g){
        List<List<Integer>> t = Stream.generate(() -> (new ArrayList<Integer>())).limit(V).collect(toList());

        for(int i=0;i<g.size();i++){
            List<Integer> adj = g.get(i);
            
            for(int node : adj){
                t.get(node).add(i);
            }
        }

        return t;
    }

    static int[] dfs(List<List<Integer>> t, Stack<Integer> order){
        int[] scc = new int[V];
        Arrays.fill(scc, -1);
        int sccIdx = 0;

        while(!order.isEmpty()){
            int last = order.pop();

            if(scc[last] != -1)
                continue;

            _dfs(last, t, scc, sccIdx);
            sccIdx++;
        }

        return scc;
    }

    static void _dfs(int cur, List<List<Integer>> t, int[] scc, final int sccIdx){
        scc[cur] = sccIdx;

        List<Integer> adj = t.get(cur);
        for(int node : adj){
            if(scc[node] != -1)
                continue;

            _dfs(node, t, scc, sccIdx);
        }
    }

    static int[] getSccWeight(int[] scc, int[] weight, int sccNum){
        int[] sccWeight = new int[sccNum];

        for(int i=0;i<scc.length;i++){
            sccWeight[scc[i]] += weight[i];
        }

        return sccWeight;
    }

    static boolean[] getIsRestaurant(int[] scc, List<Integer> restaurant, int sccNum){
        boolean[] isRestaurant = new boolean[sccNum];

        for(int node : restaurant){
            isRestaurant[scc[node]] = true;
        }

        return isRestaurant;
    }

    static List<List<Integer>> makeSccTree(List<List<Integer>> g, int[] scc, int sccNum){
        List<List<Integer>> sccTree = Stream.generate(() -> (new ArrayList<Integer>())).limit(sccNum).collect(toList());

        for(int i=0;i<g.size();i++){
            List<Integer> adj = g.get(i);
            for(int node : adj){
                if(scc[i] == scc[node])
                    continue;

                sccTree.get(scc[i]).add(scc[node]);
            }
        }

        return sccTree;
    }

    static int dp(int start, List<List<Integer>> tree, int[] sccWeight, boolean[] isRestaurant){
        int[] memo = new int[tree.size()];
        Arrays.fill(memo, -1);

        int res = _dp(start, tree, sccWeight, isRestaurant, memo);

        if(res < 0)
            return 0;
        return res;
    }

    static int _dp(int cur, List<List<Integer>> tree, int[] sccWeight, boolean[] isRestaurant, int[] memo){
        if(memo[cur] != -1)
            return memo[cur];

        int max = -1000;
        if(isRestaurant[cur])
            max = 0;
        
        List<Integer> adj = tree.get(cur);
        for(int node : adj){
            max = Math.max(max, _dp(node, tree, sccWeight, isRestaurant, memo));
        }

        if(max == -1000){
            return memo[cur] = -1000;
        }if(max == 0){
            return memo[cur] = sccWeight[cur];
        } else {
            max += sccWeight[cur];
            return memo[cur] = max;
        }
    }

    static void printGraph(List<List<Integer>> g){
        for(int i=0;i<g.size();i++){
            System.out.printf("Node[%d]: ", i);
            printList(g.get(i));
        }
    }

    static void printList(List<Integer> list){
        for(int i=0;i<list.size();i++){
            System.out.printf("%d ", list.get(i));
        }
        System.out.println();
    }
}