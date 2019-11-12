/*
    baekjoon online judge
    problem number 13303
    https://www.acmicpc.net/problem/13303
    https://handongproblemsolvers.github.io/2019/11/08/Week_11_Contest_Problem_Solving/#%EC%9E%A5%EC%95%A0%EB%AC%BC-%EA%B2%BD%EA%B8%B0

    1. when many Points meet a wall than result can be only two
    2. checking all points make algorithm N^2, so use binnary search -> SortedSet.subSet()
    3. SortedSet uses compareTo -> check y, and len
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
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main{
    static final long INF = 1000000000000L;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int startY = Integer.parseInt(st.nextToken());
        int endX = Integer.parseInt(st.nextToken());

        List<Wall> list = new ArrayList<Wall>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int yl = Integer.parseInt(st.nextToken());
            int yh = Integer.parseInt(st.nextToken());

            list.add(new Wall(x, yl, yh));
        }

        // sort wall by x and y
        Collections.sort(list);

        // get all path without duplication
        SortedSet<Point> set = new TreeSet<Point>();
        set.add(new Point(0,startY,0));
        for(int i=0;i<N;i++){
            Wall tmpWall = list.get(i);
            Iterator<Point> it = set.subSet(new Point(0,tmpWall.yl,-INF), new Point(0,tmpWall.yh+1,INF)).iterator();
            long upMin = INF;  // upper side wall's minimum dist
            long downMin = INF; // under side wall's minimum dist

            while(it.hasNext()){
                Point tmpPnt = it.next();
                if(tmpPnt.y > tmpWall.yl && tmpPnt.y < tmpWall.yh){
                    upMin = Min(upMin, tmpPnt.len+tmpWall.yh-tmpPnt.y);
                    downMin = Min(downMin, tmpPnt.len+tmpPnt.y-tmpWall.yl);
                    it.remove();
                }
            }

            // leave only two point which has minimum length
            set.add(new Point(tmpWall.x,tmpWall.yh,upMin));
            set.add(new Point(tmpWall.x,tmpWall.yl,downMin));
        }

        // get minimum len
        Iterator<Point> it = set.iterator();
        long min = INF;
        long minNum = 0;
        while(it.hasNext()){
            Point tmp = it.next();

            if(min > tmp.len){
                min = tmp.len;
                minNum = 1;
            }else if(min == tmp.len){
                minNum++;
            }
        }

        // pring minimum length and y value
        bw.write(Long.toString(min+endX)+"\n");
        bw.write(Long.toString(minNum)+" ");
        it = set.iterator();
        while(it.hasNext()){
            Point tmp = it.next();
            if(tmp.len == min)
                bw.write(Integer.toString(tmp.y)+" ");
        }
        bw.write("\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static long Min(long a, long b){
        return a<b?a:b;
    }

    public static class Point implements Comparable<Point>{
        public int x;
        public int y;
        public long len;

        public Point(int x, int y, long len){
            this.x = x;
            this.y = y;
            this.len = len;
        }

        @Override
        public int compareTo(Point that){
            if(this.y < that.y)
                return -1;
            if(this.y > that.y)
                return 1;
                
            if(this.len < that.len)
                return -1;
            if(this.len > that.len)
                return 1;
            
            return 0;
        }
    }

    public static class Wall implements Comparable<Wall>{
        public int x;
        public int yl;
        public int yh;

        public Wall(int x, int yl, int yh){
            this.x = x;
            this.yl = yl;
            this.yh = yh;
        }

        @Override
        public int compareTo(Wall that){
            if(this.x < that.x)
                return -1;
            if(this.x > that.x)
                return 1;

            // this.x == that.x
            if(this.yl < that.yl)
                return -1;
            if(this.yl > that.yl)
                return 1;

            // this.x == that.x && this.yl == that.yl
            if(this.yh < that.yh)
                return -1;
            if(this.yh > that.yh)
                return 1;

            throw new AssertionError("same wall");
        }
    }
}
