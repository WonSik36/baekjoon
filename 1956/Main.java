/*
    baekjoon online judge
    problem number 1956
    https://www.acmicpc.net/problem/1956

    detect shortest cycle in graph
    use Floyd-Warhsall Algorithm
    which algo allow negative edges
    assume no negative weight cycle
    
    ref to Floyd-Warhsall Algorithm
    https://www.acmicpc.net/problem/11404
    https://hsp1116.tistory.com/45
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
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N+1][N+1];
        
        // initialize graph
        for(int i=0;i<N+1;i++){
            Arrays.fill(graph[i],INF);
        }

        // get edge
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(graph[start][end] > weight)
                graph[start][end] = weight;
        }

        // apply floyd warshall algorithm
        // dist[i][i] means cycle length start from i
        int[][] dist = floydWarshall(graph);
        
        //print result
        int min = INF;
        for(int i=1;i<=N;i++){
            if(min > dist[i][i])
                min = dist[i][i];
        }
        if(min == INF)
            bw.write("-1\n");
        else
            bw.write(Integer.toString(min)+"\n");
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

        // for test
        // System.out.println("K: 0");
        // print2DArray(d2);

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
            // for test
            // System.out.println("K: "+k);
            // if(k%2 == 1){
            //     print2DArray(d1);
            // }else{
            //     print2DArray(d2);
            // }
        }

        // select last modified one
        if((graph.length-1)%2 == 1){
            dist = d1;
        }else{
            dist = d2;
        }
        
        return dist;
    }

    public static void print2DArray(int[][] arr){

        for(int i=1;i<arr.length;i++){
           for(int j=1;j<arr[0].length;j++){
                if(arr[i][j] == NIL)
                    System.out.print("NIL "); 
                else if(arr[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(arr[i][j]+" ");
           } 
           System.out.println();
        }
        System.out.println();
    }
}