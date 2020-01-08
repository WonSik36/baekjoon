/*
    baekjoon online judge
    problem number 1396
    https://www.acmicpc.net/problem/1396
    https://handongproblemsolvers.github.io/2019/11/23/Week_13_Contest_Problem_Solving/#%ED%81%AC%EB%A3%A8%EC%8A%A4%EC%B9%BC%EC%9D%98-%EA%B3%B5
    https://m.blog.naver.com/kks227/221410398513

    deep level reference

    Parallel Binary Search Algorithm
    https://m.blog.naver.com/kks227/221410398513

    Kruskal Algorithm
    https://m.blog.naver.com/ndb796/221230994142

    2 things I learned
    1. In binary search with parametric search there are neat code
    2. Do not use String.format instead use StringBuilder
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Edge> edges = new ArrayList<Edge>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(start, end, weight));
        }

        // for kruskal
        Collections.sort(edges);

        int Q = Integer.parseInt(br.readLine());
        ArrayList<Query> queries = new ArrayList<Query>();

        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;

            queries.add(new Query(start,end,0,M+1));    // lo=0, hi=M+1 | 0 and M+1 can't be answer 
        }

        while(true){
            boolean flag = false;
            ArrayList<ArrayList<Integer>> mq = makeMultiQueue(M);   // queue idx means edge idx+1 because 0 used by lo
            // get mid of each queries
            for(int i=0;i<queries.size();i++){
                Query tmp = queries.get(i);

                // if tmp.lo == tmp.hi than this query's binary search is end
                if(tmp.lo+1 < tmp.hi){
                    flag = true;
                    mq.get((tmp.lo+tmp.hi)/2).add(i);   // take idx of query
                }
            }

            // binary search is done
            if(!flag)
                break;

            UnionFind uf = new UnionFind(N);

            // in kruskal algorithm add edges by ascending order
            for(int i=0;i<edges.size();i++){
                Edge e = edges.get(i);
                uf.merge(e.start, e.end);

                /* 
                    mq index has range from 1 to M
                    edge index has range from 0 to M-1
                    so edge idx 0 is pare with mq idx 1
                */
                int mid = i+1;
                for(int j=0;j<mq.get(mid).size();j++){
                    int idx = mq.get(mid).get(j);   // index of queries
                    Query q = queries.get(idx);

                    // if two nodes in same union
                    if(uf.hasSameRoot(q.start, q.end)){
                        q.hi = mid;
                        q.temperature = e.weight;
                        q.size = uf.size[uf.getRoot(q.start)];
                    }else{
                        q.lo = mid;
                    }
                }
            }
        }

        for(int i=0;i<queries.size();i++){
        	// there was no connection
            if(queries.get(i).lo == M)
                bw.write("-1\n");
            else{
                StringBuilder sb = new StringBuilder();
                sb.append(Integer.toString(queries.get(i).temperature));
                sb.append(" ");
                sb.append(Integer.toString(queries.get(i).size));
                sb.append("\n");
                bw.write(sb.toString());
            }
        }

        bw.close();
        br.close();
    }

    public static ArrayList<ArrayList<Integer>> makeMultiQueue(int M){
        ArrayList<ArrayList<Integer>> mq = new ArrayList<ArrayList<Integer>>();

        // mq use idx 1~M which can be answer edge idx
        for(int i=0;i<=M;i++){
            mq.add(new ArrayList<Integer>());
        }

        return mq;
    }
    
    static class Edge implements Comparable<Edge>{
        public int start;
        public int end;
        public int weight;

        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge that){
            if(this.weight < that.weight)
                return -1;
            else if(this.weight == that.weight)
                return 0;
            else
                return 1;
        }
    }

    static class UnionFind {
        public int[] root;
        public int[] size;
        public int[] depth;

        public UnionFind(int N){
            root = new int[N];
            for(int i=0;i<N;i++){
                root[i] = i;
            }
            size = new int[N];
            Arrays.fill(size,1);
            depth = new int[N];

        }
        
        public int getRoot(int idx){
            if(root[idx] == idx)
                return idx;
            else
                return root[idx] = getRoot(root[idx]);
        }

        public void merge(int a, int b){
            a = getRoot(a);
            b = getRoot(b);

            if(a == b)
                return;

            size[a] += size[b];
            root[b] = a;
        }

        public boolean hasSameRoot(int a, int b){
            a = getRoot(a);
            b = getRoot(b);

            if(a == b)
                return true;
            else
                return false;
        }
    }

    static class Query {
        public int start;
        public int end;
        public int lo;
        public int hi;
        public int temperature; // result
        public int size;        // result

        public Query(int start, int end, int lo, int hi){
            this.start = start;
            this.end = end;
            this.lo = lo;
            this.hi = hi;
            this.temperature = 0;
            this.size = 0;
        }
    }
}
