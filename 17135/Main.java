/*
    baekjoon online judge
    problem number 17135
    https://www.acmicpc.net/problem/17135
    https://lcs11244.tistory.com/46
    
    shallow reference level
    use dfs to choose archer pos
    use bfs to choose enemy which will be attacked

    2d array clone method is not deep copy
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main{
    static final int INF = 100000;
    static final int CNT = 3;
    static int N;
    static int M;
    static int D;
    static int[][] map;

    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] archer = new int[CNT];
        Arrays.fill(archer,-1);
        int max = DFS(0,3,archer);

        bw.write(Integer.toString(max)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // hidden input: 
    //      M: columns
    // input: 
    //      cur: current position
    //      cnt: count
    //      archer: archer positions
    // output:
    //      max: maximum number of enemy killed
    static int DFS(int cur, int cnt, int[] archer){
        if(cnt==0){
            return simulate(archer);
        }

        int max = 0;
        for(int i=cur;i<=M-cnt;i++){
            archer[CNT-cnt] = i;
            max = Max(max,DFS(i+1,cnt-1,archer));
            archer[CNT-cnt] = -1;
        }

        return max;
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }


    // hidden input
    //      N: rows
    //      M: columns
    //      D: attack distance of archer
    //      map: original map
    // input:
    //      archer: position of archer
    // output:
    //      total: total number of enemy killed
    static int simulate(int[] archer){
        int total = 0;
        int[][] mapDup = new int[N][M];
        copy2DArray(map, mapDup);

        for(int i=0;i<N;i++){
            total += attack(mapDup,archer);
            moveOneRow(mapDup);
        }

        return total;
    }

    // hidden input
    //      N: rows
    //      M: columns
    //      D: attack distance of archer
    //      map: original map
    // input:
    //      map: map of game
    //      archer: position of archer
    // output:
    //      total: total number of enemy killed
    static int attack(int[][] mapDup, int[] archer){
        List<Pos> enemies = new ArrayList<Pos>();

        // get enemies to kill
        for(int i=0;i<CNT;i++){
            Pos enemy = getNearestEnemyOrNull(new Pos(archer[i],N,0), mapDup);
            if(enemy == null)
                continue;
            else{
                if(!enemies.contains(enemy))
                    enemies.add(enemy);
            }
        }

        // kill enemies
        for(int i=0;i<enemies.size();i++){
            Pos tmp = enemies.get(i);
            mapDup[tmp.y][tmp.x] = 0;
        }

        // return number of killed enemies
        return enemies.size();
    }

    // use bfs to find nearest enemy
    static Pos getNearestEnemyOrNull(Pos archer, int[][] mapDup){
        LinkedList<Pos> list = new LinkedList<Pos>();
        boolean[][] visited = new boolean[N][M];
        list.add(new Pos(archer.x,archer.y-1,1));
        
        while(!list.isEmpty()){
            Pos top = list.removeFirst();

            // over attackable distance
            if(top.cnt > D)
                continue;

            // visited before
            if(visited[top.y][top.x])
                continue;
            
            // check bfs visited certain pos
            visited[top.y][top.x] = true;
            
            // if enemy is in pos than return it
            if(mapDup[top.y][top.x] == 1)
                return top;

            if(top.x > 0)
                list.addLast(new Pos(top.x-1,top.y,top.cnt+1));
            if(top.y > 0)
                list.addLast(new Pos(top.x,top.y-1,top.cnt+1));
            if(top.x < M-1)
                list.addLast(new Pos(top.x+1,top.y,top.cnt+1));
        }

        return null;
    }

    static class Pos{
        public int x;
        public int y;
        public int cnt;

        public Pos(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object obj){
            Pos that = (Pos)obj;
            if(this.x==that.x && this.y==that.y)
                return true;
            
            return false;
        }
    }

    // input:
    //      map: map of game
    static void moveOneRow(int[][] mapDup){
        for(int i=mapDup.length-1;i>0;i--){
            for(int j=0;j<mapDup[0].length;j++){
                mapDup[i][j] = mapDup[i-1][j];
            }
        }
        Arrays.fill(mapDup[0],0);
    }

    static void print2DArray(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    static void copy2DArray(int[][] src, int[][] dst) {
    	if(src.length!=dst.length || src[0].length!=dst[0].length)
    		throw new AssertionError("array length doesn't match");
    	
    	for(int i=0;i<src.length;i++) {
    		for(int j=0;j<src[0].length;j++) {
    			dst[i][j] = src[i][j];
    		}
    	}
    }
}
