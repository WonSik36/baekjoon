/*
    baekjoon online judge
    problem number 1135
    https://www.acmicpc.net/problem/1135

    Tree with Greedy Algorithm
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
import java.util.Collections;
import java.util.Comparator;

public class Main{
    public static void main(final String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        final List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            g.add(new ArrayList<>());
        }

        final StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken(); // -1
        for (int child = 1; child < N; child++) {
            final int parent = Integer.parseInt(st.nextToken());

            g.get(parent).add(child);
        }

        final int[] memo = new int[N];
        Arrays.fill(memo, -1);

        final int result = dfs(0, g, memo);
        bw.write(Integer.toString(result) + "\n");

        bw.close();
        br.close();
    }

    static int dfs(final int cur, final List<List<Integer>> g, final int[] memo) {
        if (g.size() == 0)
            return 0;

        final List<Integer> timeList = new ArrayList<>();
        final List<Integer> adj = g.get(cur);
        for (int i = 0; i < adj.size(); i++) {  // greedy
            timeList.add(dfs(adj.get(i), g, memo));
        }

        Collections.sort(timeList, new Comparator<Integer>() {
            @Override
            public int compare(final Integer o1, final Integer o2) {
                return (-1) * Integer.compare(o1, o2);
            }
        });

        // greedy
        int sum = 0;
        for(int i=0;i<timeList.size();i++){
            sum = Max(sum, timeList.get(i)+i+1);
        }

        return sum;
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }
}
