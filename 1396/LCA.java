/*
    baekjoon online judge
    problem number 1396
    https://www.acmicpc.net/problem/1396
    https://handongproblemsolvers.github.io/2019/11/23/Week_13_Contest_Problem_Solving/#%ED%81%AC%EB%A3%A8%EC%8A%A4%EC%B9%BC%EC%9D%98-%EA%B3%B5

    deep level reference

    Lowest Common Ancestor Algorithm
    https://m.blog.naver.com/kks227/220820773477

    Kruskal Algorithm
    https://m.blog.naver.com/ndb796/221230994142
*/

// import java.io.FileReader;
// import java.io.FileWriter;
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
import java.lang.Comparable;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Edge> list = new ArrayList<Edge>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.add(new Edge(start,end,weight));
        }

        Tree tree = new Tree(N,M,list);

        int Q = Integer.parseInt(br.readLine());
        for(int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if(tree.getRoot(start-1) != tree.getRoot(end-1)){
                bw.write("-1\n");
                continue;
            }

            int lca = tree.getLCA(start-1, end-1);
            bw.write(String.format("%d %d\n", tree.getTemp(lca), tree.getSize(lca)));
        }

        bw.close();
        br.close();
    }

    public static class Tree{
        private int vertex;
        private int height;
        private int[][] parent;
        private int[] depth;
        private int[] temperature;
        private int[] size;
        private int[] root;
        private ArrayList<ArrayList<Integer>> adjList;

        // make tree which has N node, and 2^h height
        public Tree(int N, int M, List<Edge> list){
            vertex = N+M;
            height = (int)Math.ceil((Math.log(vertex)/Math.log(2)));    // 2^(height-1) parent will be root
            parent = new int[vertex][height];
            depth = new int[vertex];
            temperature = new int[vertex];
            size = new int[vertex];
            root = new int[vertex];
            adjList = new ArrayList<ArrayList<Integer>>();

            initField();

            // kruskal algorithm
            Collections.sort(list);
            for(int i=N;i<N+M;i++){
                Edge tmp = list.get(i-N);
                int rootOfStart = find(tmp.getStart()-1);
                int rootOfEnd = find(tmp.getEnd()-1);

                // if current edge makes cycle
                if(rootOfStart == rootOfEnd)
                    continue;

                
                size[i] = size[rootOfStart] + size[rootOfEnd];
                temperature[i] = tmp.getWeight();   // weight was sorted by ascending order
                
                adjList.get(i).add(rootOfStart);    // make graph for tree
                adjList.get(i).add(rootOfEnd);

                merge(i, rootOfStart);              // ith nodes cildren is rootOfStart, and rootOfEnd
                merge(i, rootOfEnd);
            }

            updateDepth(N);
            fillParent();   // fill 2^1, 2^2, 2^3, ..., 2^(height-1) parent
        }

        private void initField(){
            for(int i=0;i<parent.length;i++){
                Arrays.fill(parent[i],-1);
            }

            // becuase of numbering
            // 0 ~ N-1 : input node
            // N ~ N+M-1 : upper node(edges)
            for(int i=0;i<vertex;i++){
                adjList.add(new ArrayList<Integer>());
            }

            Arrays.fill(size,1);
            Arrays.fill(root,-1);
        }

        private int find(int x){
            if(root[x] == -1)
                return x;
            else
                return root[x] = find(root[x]);
        }

        // first parameter a has priority
        private void merge(int a, int b){
            a = find(a);
            b = find(b);

            if(a == b)
                return;
            else
                root[b] = a;
        }

        private void updateDepth(int N){
            for (int i=vertex-1; i>=N; i--)
                if (depth[i] == 0)
                    makeTreeByDFS(i);
        }

        private void makeTreeByDFS(int cur) {
            for (int i=0; i<adjList.get(cur).size(); i++) {
                int next = adjList.get(cur).get(i);
                
                parent[next][0] = cur;
                depth[next] = depth[cur] + 1;
                makeTreeByDFS(next);
            }
        }

        private void fillParent(){
            for(int j=0;j<height-1;j++){
                for(int i=0;i<vertex;i++){
                    if(parent[i][j] != -1)
                        /* 
                            parent[i][j+1] = 2^(j+1) parent 
                            = (2^j + 2^j) parent 
                            = 2^j parent's 2^j parent
                            = parent[parent[i][j]][j];   
                        */
                        parent[i][j+1] = parent[parent[i][j]][j];   
                }
            }
        }

        public int getLCA(int u, int v){
            
            // make depth[u] >= depth[v]
            if(depth[u] < depth[v]){
                int tmp = u;
                u = v;
                v = tmp;
            }

            // make u and v's depth to equal
            // if difference is 11 -> 1011
            // than get 2^0, 2^1, 2^3 parent
            int diff = depth[u] - depth[v];
            for(int i=0; diff!=0; i++){
                if(diff % 2 == 1) 
                    u = parent[u][i];
                diff /= 2;
            }

            if(u == v)
                return u;

            // if u != v
            // try 2^height, 2^height-1, ..., 2^2, 2, 1 height
            // this procedure is same with
            // 1. get k which makes u and v's 2^k parent is not same, but 2^(k+1) parent is same
            // 2. than find lca by looking range 2^k ~ 2^(k+1) parent
            for(int i=height-1; i>=0; i--){
                if(parent[u][i] != -1 && parent[u][i] != parent[v][i]){
                    u = parent[u][i];
                    v = parent[v][i];
                }
            }

            // finally, u and v's immediate parent is same
            // get parent
            u = parent[u][0];

            return u;
        }

        public int getRoot(int idx){
            return find(idx);
        }

        public int getTemp(int idx){
            return temperature[idx];
        }

        public int getSize(int idx){
            return size[idx];
        }
    }

    public static class Edge implements Comparable<Edge>{
        private int start;
        private int end;
        private int weight;

        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getStart(){
            return this.start;
        }

        public void setStart(int start){
            this.start = start;
        }

        public int getEnd(){
            return this.end;
        }

        public void setEnd(int end){
            this.end = end;
        }

        public int getWeight(){
            return this.weight;
        }

        public void setWeight(int weight){
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
}
