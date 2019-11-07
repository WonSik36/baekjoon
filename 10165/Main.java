/*
    baekjoon online judge
    problem number 10165
    https://www.acmicpc.net/problem/10165
    https://justicehui.github.io/koi/2019/07/08/BOJ10165/

    sweeping algorithm problem
    high level reference

    All routes can be classified as passing m-1 and 0 or non-passing. 
    Let non-passing routes be the group A and the routes that passes is the group B.
    If there are routes a,b in A, and c,d in B, than there are 3 possible cases.
        1. a contain b (Group A)
        2. c contain d (Group B)
        3. c contain a (Group A,B)

    1. If there are routes a,b which relate a.start<=b.start<b.end<=a.end in A group than b can be removed.
    2. If there are routes c,d which relate c.start<=d.start<(d.end+M)<=(c.end+M) in B group than d can be removed.
    3. If there are routes a,c which relate 0<=a.start<a.end<=c.end in A,B group than a can be removed.

    To perform these task
    1. sort all routes by start in ascending order, and cach max end of group B
    2. Transfer to other list from first to end, compare with end of last in list (case 1,2)
    3. check every routes in transfered list which end is bigger than cached end (case 3)
    4. sort transfered list by number of route
    5. print route number
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        Bus.setLastStation(N);
        Routes routes = new Routes();

        // phase 1
        for(int i=1;i<=M;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            routes.addBus(new Bus(start,end,i));
        }

        List<Bus> res = routes.getBuses();
        Iterator<Bus> it = res.iterator();
        while(it.hasNext()){
            bw.write(Integer.toString(it.next().getRouteNum())+" ");
        }

        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static class Bus implements Comparable<Bus>{
        private static int lastStation;
        private static int cachedMaxInGroupB = 0; // see above annotation
        private int start;
        private int end;
        private int routeNum;

        // phase 1
        Bus(int start, int end, int routeNum){
            this.start = start;
            if(start>end){
                if(cachedMaxInGroupB < end)
                    cachedMaxInGroupB = end;
                this.end = end+lastStation;
            }
            else
                this.end = end;
            this.routeNum = routeNum;
        }

        /**
         * @param lastStation the lastStation to set
         */
        public static void setLastStation(int lastStation) {
            Bus.lastStation = lastStation;
        }

        public int getStart(){
            return this.start;
        }

        public int getEnd(){
            return this.end;
        }

        public int getRouteNum(){
            return this.routeNum;
        }


        @Override
        public int compareTo(Bus that){
            if(this.start > that.start)
                return 1;
            else if(this.start < that.start)
                return -1;
            else {
            	if(this.end > that.end)
            		return -1;
            	else if(this.end < that.end)
            		return 1;
            	else return 0;
            }
        }
    }

    public static class Routes{
        private List<Bus> list;

        public Routes(){
            list = new ArrayList<Bus>();
        }

        public void addBus(Bus bus){
            list.add(bus);
        }

        public List<Bus> getBuses(){
            List<Bus> res = new ArrayList<Bus>();
            Collections.sort(list);
            Iterator<Bus> it = list.iterator();

            // phase 2
            // Transfer to other list from first to end, compare with end of last in list (case 1,2)
            while(it.hasNext()){
                Bus tmp = it.next();

                if(res.isEmpty() || res.get(res.size()-1).getEnd() < tmp.getEnd())
                    res.add(tmp);
            }

            // phase 3
            // check every routes in transfered list which end is bigger than cached end (case 3)
            it = res.iterator();
            while(it.hasNext()){
                Bus tmp = it.next();

                if(tmp.getEnd() <= Bus.cachedMaxInGroupB)
                    it.remove();
            }

            // phase 4
            Collections.sort(res, new Comparator<Bus>(){
                @Override
                public int compare(Bus o1, Bus o2) {
                    if(o1.getRouteNum() < o2.getRouteNum())
                        return -1;
                    else if(o1.getRouteNum() > o2.getRouteNum())
                        return 1;
                    else
                        throw new AssertionError("Duplicated Bus");
                }
            });

            return res;
        }
    }
}
