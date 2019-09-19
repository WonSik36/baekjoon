/*
    baekjoon online judge
    problem number 15650
    https://www.acmicpc.net/problem/15650
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.HashSet;

public class Main{
    static BufferedWriter bw;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Graph g = new Graph(N, new BackTrace(){
            public int init(ArrayList<Integer> list, int vertex){
                int ret = 0;
                for(int i=vertex;i<=N;i++){
                    list.add(i);
                    ret++;
                }
                return ret;
            }
            public void Coloring(int cur, boolean[] arr){}
            public void printPath(int[] path){
                try{
                    for(int i=0;i<path.length;i++){
                        bw.write(Integer.toString(path[i])+" ");
                    }
                    bw.write("\n");
                }catch(IOException e){
                    throw new RuntimeException(e);
                }
            }
            public boolean isDone(){
                return false;
            }
        });
        g.backTrace(M);

        bw.flush();
        bw.close();
        br.close();
    }

    public static String intArr2Str(int[] arr){
        String ret = "";
        for(int i=0;i<arr.length;i++)
            ret += (arr[i]+" ");
        return ret;
    }

    public static class Graph{
        private ArrayList<ArrayList<Integer>> adjList;
        private BackTrace bt;
        private int vertex;
        private int edge;

        public Graph(int N, BackTrace bt){
            vertex = N;
            edge = 0;
            this.bt = bt;
            adjList = new ArrayList<ArrayList<Integer>>();
            // index 0 is not using
            adjList.add(new ArrayList<Integer>());
            for(int i=1;i<=N;i++){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                edge += this.bt.init(temp, i);
                adjList.add(temp);
            }
        }

        public String toString(){
            String ret = "";
            for(int i=1;i<adjList.size();i++){
                ArrayList<Integer> temp = adjList.get(i);
                ret += ("Node["+i+"]: ");
                for(int j=0;j<temp.size();j++){
                    ret+=(temp.get(j)+" ");
                }
                ret += "\n";
            }
            return ret;
        }

        public void backTrace(int cnt){
            for(int i=1;i<=vertex;i++){
                _backTrace(cnt, i);
            }
        }

        public void _backTrace(int cnt, int start){
            // initialize
            int[] path = new int[cnt];
            boolean[] color = new boolean[vertex+1];
            Arrays.fill(color, true);

            DFS(start, cnt, path, color);
        }

        /*
            this function execute Depth First Search
            vertex: current node number
            from: node which call current function
            cnt: number of count if count is 0 than this is last call
            path: the path from begining node to right before node
            color: the flags of already visited node
        */
        public void DFS(int vertex, int cnt, int[] path, boolean[] color){
            if(bt.isDone())
                return;
            path[path.length - cnt] = vertex;
            if(cnt == 1){
                bt.printPath(path);
                return;
            }
            bt.Coloring(vertex, color);
            ArrayList<Integer> temp = adjList.get(vertex);
            Iterator it = temp.iterator();
            while(it.hasNext()){
                int node = (int)it.next();
                if(!color[node])
                    continue;
                DFS(node,cnt-1,path,color);
            }
            //by using under line color & path can be used again
            color[vertex] = true;
        }

        public int getVertex(){
            return vertex;
        }

        public int getEdge(){
            return edge;
        }
    }

    public static interface BackTrace{
        public int init(ArrayList<Integer> list, int vertex);
        public void Coloring(int cur, boolean[] arr);
        public void printPath(int[] path);
        public boolean isDone();
    }
}