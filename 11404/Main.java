/*
    baekjoon online judge
    problem number 11404
    https://www.acmicpc.net/problem/11404
    https://hsp1116.tistory.com/45
    
    Floyd-Warhsall Algorithm
    negative edges are allowed
    assume no negative weight cycle
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final int INF = 100000000;
    static final int NIL = -1;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());
        int[][] graph = new int[N+1][N+1];
        
        // initialize graph
        for(int i=0;i<N+1;i++){
            Arrays.fill(graph[i],INF);
            graph[i][i] = 0;
        }

        // get edge
        for(int i=0;i<E;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(graph[start][end] > weight)
                graph[start][end] = weight;
        }

        // apply floyd warshall algorithm
        int[][] dist = floydWarshall(graph);

        //print result
        for(int i=1;i<N+1;i++){
            for(int j=1;j<N+1;j++){
                bw.write(Integer.toString(dist[i][j])+" ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int[][] floydWarshall(int[][] graph){
        // originally int[N+1][N+1][N+1] d is needed
        // to reduce memory, use two 2-dimension matrix
        int[][] d1,d2;
        int[][] dist,pred;

        //initialize d1,d2,pred
        d1 = new int[graph.length][graph.length];
        d2 = graph;
        pred = new int[graph.length][graph.length];
        for(int i=0;i<graph.length;i++){
            Arrays.fill(pred[i],NIL);
        }
        for(int i=1;i<graph.length;i++){
            for(int j=1;j<graph.length;j++){
                if(i==j) continue;
                if(d2[i][j] != INF){
                    pred[i][j] = i;
                }
            }
        }

        // take N loop
        // each loop insert middle path i
        for(int k=1;k<graph.length;k++){
            // toggle dist and pred depending on i value
            for(int i=1;i<graph.length;i++){
                for(int j=1;j<graph.length;j++){
                    if(k%2==1){
                        if(d2[i][j] > d2[i][k]+d2[k][j]){
                            d1[i][j] = d2[i][k]+d2[k][j];
                            pred[i][j] = k;
                        }else{
                            d1[i][j] = d2[i][j];
                        }
                    }else{
                        if(d1[i][j] > d1[i][k]+d1[k][j]){
                            d2[i][j] = d1[i][k]+d1[k][j];
                            pred[i][j] = k;
                        }else{
                            d2[i][j] = d1[i][j];
                        }
                    }
                }
            }
        }

        if((graph.length-1)%2 == 1){
            dist = d1;
        }else{
            dist = d2;
        }
        
        // if no path between i and j (= dist[i][j] is INF)
        // than change it to 0
        for(int i=1;i<graph.length;i++){
            for(int j=1;j<graph.length;j++){
                if(dist[i][j] == INF)
                    dist[i][j] = 0;
            }
        }
        return dist;
    }
}