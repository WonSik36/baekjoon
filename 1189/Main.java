/*
    baekjoon online judge
    problem number 1189
    https://www.acmicpc.net/problem/1189

    BackTracking Problem
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static int COUNT = 0;
    static boolean[][] visited;
    static boolean[][] map;
    static int R;
    static int C;
    static int K;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new boolean[R][C];
        visited = new boolean[R][C];

        for(int i=0;i<R;i++){
            String row = br.readLine();
            for(int j=0;j<C;j++){
                if(row.charAt(j) == '.')
                    map[i][j] = true;
                else
                    map[i][j] = false;
            }
        }

        visited[R-1][0] = true;
        dfs(R-1,0,1);

        bw.write(Integer.toString(COUNT)+"\n");
        bw.close();
        br.close();
    }

    private static void dfs(int y, int x, int dist){
        // System.out.printf("y:%d, x:%d, dist:%d\n",y,x,dist);
        if(dist > K)
            return;

        if(y==0 && x==C-1){
            if(dist == K){
                // System.out.println("COUNT UP");
                COUNT++;
            }
            return;
        }

        if(x>0 && !visited[y][x-1] && map[y][x-1]){
            visited[y][x-1] = true;
            dfs(y,x-1,dist+1);
            visited[y][x-1] = false;
        }

        if(x<C-1 && !visited[y][x+1] && map[y][x+1]){
            visited[y][x+1] = true;
            dfs(y,x+1,dist+1);
            visited[y][x+1] = false;
        }

        if(y>0 && !visited[y-1][x] && map[y-1][x]){
            visited[y-1][x] = true;
            dfs(y-1,x,dist+1);
            visited[y-1][x] = false;
        }

        if(y<R-1 && !visited[y+1][x] && map[y+1][x]){
            visited[y+1][x] = true;
            dfs(y+1,x,dist+1);
            visited[y+1][x] = false;
        }
    }

    private static void printArr(boolean[][] map){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                if(map[i][j])
                    System.out.print(".");
                else
                    System.out.print("T");
            }
            System.out.println();
        }
    }
}
