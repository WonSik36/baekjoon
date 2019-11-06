/*
    baekjoon online judge
    problem number 2541
    https://www.acmicpc.net/problem/2541

    it was not about dynamic programming & bit mask
    you just need to find out rule of set
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
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        
        Point.setRule(a,b);

        Point[] inputs = new Point[5];
        for(int i=0;i<5;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            inputs[i] = new Point(x,y);
        }

        for(int i=0;i<5;i++){
            boolean res = inputs[i].getResult();
            if(res)
                bw.write("Y\n");
            else
                bw.write("N\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class Point{
        private static int X;
        private static int Y;
        private static int GAP;

        private int x;
        private int y;
        private int gap;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
            this.gap = x-y;
        }

        public static void setRule(int X, int Y){
            Point.X = X;
            Point.Y = Y;  
            Point.GAP = X-Y;
            
            while(Point.GAP%2==0 && Point.GAP!=0){
                Point.GAP /= 2;
            }
        }

        public boolean getResult(){
            // both have 0 gap 
            if(gap==0 && GAP==0)
                return true;

            // only one gap is 0
            if((gap==0 && GAP!=0) || (gap!=0 && GAP==0))
                return false;

            //different sign
            if((gap<0 && GAP>0) || (gap>0 && GAP<0))
                return false;

            if(gap%GAP == 0)
                return true;
            else
                return false;
        }

    }
}
