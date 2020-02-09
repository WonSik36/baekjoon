/*
    baekjoon online judge
    problem number 1002
    https://www.acmicpc.net/problem/1002

    1.It was necessary to understand the relationship between the distance and the circle between the two points.
    2.It needed to understand the identity of floating value.
    https://howtodoinjava.com/java/basics/correctly-compare-float-double
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
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            Point p1 = new Point(x1,y1,r1);
            Point p2 = new Point(x2,y2,r2);

            // make p1's circle is bigger than p2's circle
            if(p1.r < p2.r){
                Point tmp = p1;
                p1 = p2;
                p2 = tmp;
            }

            double dist1 = Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
            double dist2 = p1.r+p2.r;
            
            // case1: p1 and p2 is same point
            if(p1.x==p2.x && p1.y==p2.y){
                // case1-1: infinite
                if(p1.r == p2.r)
                    bw.write("-1\n");
                // case1-2: no point
                else
                    bw.write("0\n");
            // case2: no meeting point
            }else if(dist1 > dist2)
                bw.write("0\n");
            // case3: one meeting point, and no overlapping section
            else if(equals(dist1, dist2))
                bw.write("1\n");
            else{
                // case4: p1's circle has p2.s circle has overlapping section
                if(dist1+p2.r > p1.r)
                    bw.write("2\n");
                // case5: p1's circle has p2.s circle, and one meeting point
                else if(equals(dist1+p2.r, p1.r))
                    bw.write("1\n");
                // case6: p1's circle has p2.s circle, and no meeting point
                else
                    bw.write("0\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean equals(double x, double y){
        final double threshold = 0.0001;

        if(Math.abs(x-y) < threshold)
            return true;
        else
            return false;
    }
}

class Point{
    public int x;
    public int y;
    public int r;

    public Point(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }
}