/*
    baekjoon online judge
    problem number 2261
    https://www.acmicpc.net/problem/2261

    for closest pair problem
    https://casterian.net/archives/92

    for comparable and comparator
    https://gmlwjd9405.github.io/2018/09/06/java-comparable-and-comparator.html
    comparable is for basic sort, comparator is for designated sort
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import java.lang.Comparable;

public class MainSub{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int N = Integer.parseInt(str);
        ArrayList<Point> list = new ArrayList<Point>();
        for(int i=0;i<N;i++){
            str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Point(x,y));
        }


        bw.write(Integer.toString(getMinDistance(list)));
        bw.flush();
        bw.close();
        br.close();
    }

    public static class Point implements Comparable<Point>{
        public int X;
        public int Y;

        public Point(int x, int y){
            this.X = x;
            this.Y = y;
        }

        @Override
        public int compareTo(Point p){
            if(this.X > p.X)
                return 1;
            else if(this.X < p.X)
                return  -1;
            else{
                if(this.Y > p.Y)
                    return 1;
                else if(this.Y < p.Y)
                    return -1;
                else
                    return 0;
            }
        }
    }

    public static int getMinDistance(ArrayList<Point> list){
        // sort list by position x
        Collections.sort(list);
        // printList(list);
        return _getMinDistance(list,0,list.size()-1);
    }

    public static int _getMinDistance(ArrayList<Point> list, int start, int end){
        if(start == end)
        return Integer.MAX_VALUE;
        else if(start+1 == end)
        return (int)getDistancePow(list.get(start), list.get(end));
        else if(start+2 == end)
        return Min(getDistancePow(list.get(start), list.get(start+1)), 
                getDistancePow(list.get(start+1), list.get(end)),
                getDistancePow(list.get(start), list.get(end)));
        

        // get shortest distance by dividing 2 parts
        // System.out.printf("index: %d-%d\n",start,end);
        int d1 = _getMinDistance(list, start, (start+end)/2);
        int d2 = _getMinDistance(list, (start+end)/2+1, end);

        int d = d1<d2?d1:d2;
        // get mid line by using (n/2)th (n/2+1)th element's x pos 
        // and get sub list of original list when element is in mid-d ~ mid+d
        int mid = (list.get((start+end)/2).X + list.get((start+end)/2+1).X)/2;
        ArrayList<Point> subList = inRange(list, mid, d, start, end);
        // printList(subList);

        // sort list by position y
        Collections.sort(subList, new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2){
                if(p1.Y > p2.Y)
                    return 1;
                else if(p1.Y < p2.Y)
                    return  -1;
                else{
                    if(p1.X > p2.X)
                        return 1;
                    else if(p1.X < p2.X)
                        return -1;
                    else
                        return 0;
                }
            }
        });
        // from first pos to last pos get distance between pos
        // printList(subList);
        for(int i=0;i<subList.size()-1;i++){
            int j = i+1;
            while((j <subList.size()) && ((int)Math.pow(subList.get(j).Y - subList.get(i).Y,2) < d)){
                int compDist = getDistancePow(subList.get(i), subList.get(j));
                // System.out.printf("compare sublist[%d : %d]: %d, given dist: %d\n",i,j,compDist,d);
                if(compDist < d)
                    d = compDist;
                j++;
            }
        }

        return d;
    }

    public static int getDistancePow(Point p1, Point p2){
        int dx = p1.X - p2.X;
        int dy = p1.Y - p2.Y;

        return (int)Math.pow(dx,2)+(int)Math.pow(dy,2);
    }

    public static  ArrayList<Point> inRange(ArrayList<Point> list, int mid, int dist, int first, int last){
        int sqrtDist = (int)Math.ceil(Math.sqrt(dist));
        int start = mid - sqrtDist;
        int end = mid + sqrtDist;
        int left = 0;
        int right = 0;
        // get left idx of array
        for(int i=(first+last)/2;i>=first;i--){
            if(start <= list.get(i).X && end >= list.get(i).X){
                left = i;
            }
        }
        //get right idx of array
        for(int i=(first+last)/2+1;i<=last;i++){
            if(start <= list.get(i).X && end >= list.get(i).X){
                right = i;
            }
        }

        // it returns sub array at least has list[0]
        ArrayList<Point> sub = new ArrayList<Point>(list.subList(left, right+1));
        // System.out.printf("mid: %d, dist: %d, first: %d, last: %d ",mid,dist,first,last);
        // printList(sub);
    
        return sub;
    }

    public static void printList(ArrayList<Point> list){
        _printList(list, 0, list.size()-1);
    }

    public static void _printList(ArrayList<Point> list, int start, int end){
        for(int i=start;i<=end;i++){
            System.out.printf("(%d,%d) ", list.get(i).X,list.get(i).Y);
        }
        System.out.println();
    }

    public static int Min(int a, int b, int c){
        if(a<b){
            if(a<c)
                return a;
            else 
                return c;
        }else{
            if(b<c)
                return b;
            else 
                return c;
        }
    }
}