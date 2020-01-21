/*
    baekjoon online judge
    problem number 6593
    https://www.acmicpc.net/problem/6593

    BFS Problem
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringBuilder sb;
        StringTokenizer st;
        boolean[][][] map;
        boolean[][][] visited;
        int Z,Y,X;
        Point start = null, end = null;
        Queue<Point> q;

        while(true){
            st = new StringTokenizer(br.readLine());
            Z = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
    
            if(X==0 && Y==0 && Z==0)
                break;

            map = new boolean[Z][Y][X];
            
            /* get map */
            for(int i=0;i<Z;i++){
                for(int j=0;j<Y;j++){
                    String str = br.readLine();
    
                    for(int k=0;k<X;k++){
                        // space[i,j,k] is wall
                        if(str.charAt(k) == '#'){
                            map[i][j][k] = false;
                            continue;    
                        }
    
                        // space[i,j,k] is empty
                        if(str.charAt(k) == '.'){
    
                        }else if(str.charAt(k) == 'S'){
                            start = new Point(i,j,k,0);
                        }else if(str.charAt(k) == 'E')
                            end = new Point(i,j,k,0);
    
                        else
                            throw new AssertionError("Unknown Input");
    
                        map[i][j][k] = true;
                    }
                }
                br.readLine();
            }

            /* BFS */
            visited = new boolean[Z][Y][X];
            q = new LinkedList<Point>();
            q.add(start);
            boolean flag = false;
            while(!q.isEmpty()){
                Point first = q.poll();

                if(end.equals(first)){
                    flag = true;
                    sb = new StringBuilder("Escaped in ");
                    sb.append(Integer.toString(first.dist));
                    sb.append(" minute(s).\n");
                    
                    bw.write(sb.toString());
                    break;
                }

                // check is this point is wall
                if(!map[first.z][first.y][first.x])
                    continue;

                // check visited before
                if(visited[first.z][first.y][first.x])
                    continue;

                visited[first.z][first.y][first.x] = true;

                // add new Point to queue
                if(first.z > 0)
                    q.add(new Point(first.z-1,first.y,first.x,first.dist+1));
                if(first.z < Z-1)
                    q.add(new Point(first.z+1,first.y,first.x,first.dist+1));
                if(first.y > 0)
                    q.add(new Point(first.z,first.y-1,first.x,first.dist+1));
                if(first.y < Y-1)
                    q.add(new Point(first.z,first.y+1,first.x,first.dist+1));
                if(first.x > 0)
                    q.add(new Point(first.z,first.y,first.x-1,first.dist+1));
                if(first.x < X-1)
                    q.add(new Point(first.z,first.y,first.x+1,first.dist+1));
            }


            // if there is no path to end point
            if(!flag)
                bw.write("Trapped!\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void printMap(boolean[][][] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                for(int k=0;k<arr[0][0].length;k++){
                    if(arr[i][j][k])
                        System.out.print(".");
                    else
                        System.out.print("#");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static class Point{
        int x;
        int y;
        int z;
        int dist;

        public Point(int z, int y, int x, int dist){
            this.z = z;
            this.y = y;
            this.x = x;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object obj) {
            Point that = (Point)obj;
            
            if(this.x == that.x && this.y == that.y && this.z == that.z)
                return true;
            else
                return false;
        }
    }
}
