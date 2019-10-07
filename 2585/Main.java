/*
    baekjoon online judge
    problem number 2585
    https://www.acmicpc.net/problem/2585
    https://m.blog.naver.com/kyo20111/221493084423
    Use Binary Search and BFS
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main{
    static Point start = new Point(0,0,0);
    static final Point end = new Point(10000,10000,0);
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Point> list = new ArrayList<Point>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Point(x,y,K));
        }

        int min = binarySearch(N,K,list);

        bw.write(Integer.toString(min));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int binarySearch(int N, int K, ArrayList<Point> list){
        start.cnt = K;
        list.add(start);
       
        // distance between (0,0) and (10000,10000) is 14142.xx(which's power is 200000000)
        int res = _binarySearch(N,K,list,1,getDistancePower(start, end));
        return getFuel((int)Math.ceil(Math.sqrt(res)));
    }

    public static int _binarySearch(int N, int K, ArrayList<Point> list, int left, int right){
        if(left > right)
        return -1;
        int mid = (left+right)/2;

        // if it is possible to go start to end by using mid fuel tank
        if(BFS(N,K,list,mid)){
            //than recalculate minimum
            int min = _binarySearch(N, K, list, left, mid-1);
            if(min == -1){
                return mid;
            }else{
                return min;
            }
        // else recalculate it
        }else{
            return _binarySearch(N, K, list, mid+1, right);
        }
    }

    public static boolean BFS(int N, int K, ArrayList<Point> list, int dist){
        LinkedList<Point> bfsList = new LinkedList<Point>();
        bfsList.add(start);
        boolean[] visited = new boolean[N+1];
        visited[0] = true;

        while(!bfsList.isEmpty()){
            Point temp = bfsList.removeFirst();

            if(temp.cnt <0){
                throw new RuntimeException("temp count: "+temp.cnt);
            }else if(getDistancePower(temp, end) <= dist){
                return true;
            }else if(temp.cnt == 0)
                continue;

            
            for(int i=1;i<list.size();i++){
                Point tmpDst = list.get(i);
                int dist2Dst = getDistancePower(temp, tmpDst);
                
                // if distance between temp and tmpDst <= distance
                // and tmpDst was not visited than add tmpDst to list of BFS
                // first condition is simple
                // but second condition is not simple
                // just think about radar, or circle
                // you draw circle which's middle point is in temp circle
                // so you don't need to check visited circle
                if(dist2Dst<= dist && !visited[i]){
                    visited[i] = true;
                    bfsList.add(new Point(tmpDst.x, tmpDst.y, temp.cnt-1));
                }
            }
        }
        
        return false;
    }

    public static class Point{
        public int x;
        public int y;
        public int cnt;

        public Point(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public boolean equals(Object obj){
            Point p = (Point)obj;
            if(this.x == p.x && this.y == p.y){
                return true;
            }else{
                return false;
            }
        }
    }

    public static int getDistancePower(Point p1, Point p2){
        int distX = p1.x - p2.x;
        int distY = p1.y - p2.y;
        int dist = distX*distX + distY*distY;

        return dist;
    }

    public static int getFuel(int dist){
        if(dist%10 == 0){
            return dist/10;
        }else{
            return dist/10 + 1;
        }
    }
}
