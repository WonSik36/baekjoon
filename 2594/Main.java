/*
    baekjoon online judge
    problem number 2594
    https://www.acmicpc.net/problem/2594
    https://elixir.bootlin.com/linux/v4.6/source/mm/memblock.c#L520

    solve by using linked list
    reference to memblock_add_range
    which allow adding of overlap value
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Iterator;
import java.lang.Iterable;

public class Main{
    static final int START_TH = 600;
    static final int END_TH = 1320;
    static final int START_GAP = 10;
    static final int END_GAP = 10;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        LinkedList list = new LinkedList();
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String startStr = st.nextToken();    
            String endStr = st.nextToken();
            int start = hhmm2Minute(startStr,true);
            int end = hhmm2Minute(endStr,false);

            list.add(start,end);
        }

        int max = getMaxBreakTime(list);
    
        bw.write(Integer.toString(max)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getMaxBreakTime(LinkedList list){
        Iterator<Pair> it = list.iterator();
        Pair prev = null;
        Pair cur = it.next();
        // store first ride's start time to amuesment park start time
        int max = cur.start - START_TH; 

        // compare time(from current ride's start time to previous ride's end time) to max time
        while(it.hasNext()){
            prev = cur;
            cur = it.next();
            if(cur.start - prev.end > max)
                max = cur.start - prev.end;
        }

        // compare time(from last ride's end time to amuesment park end time) to max time
        if(END_TH - cur.end > max)
            max = END_TH - cur.end;
        
        return max;
    }

    public static int hhmm2Minute(String hhmm, boolean isStart){
        if(hhmm.length() != 4)
            throw new RuntimeException("hhmm format exception: "+hhmm);

        int h = Integer.parseInt(hhmm.substring(0,2));
        int m = Integer.parseInt(hhmm.substring(2,4));
        
        int time;
        if(isStart)
            time = h*60+m - START_GAP;
        else 
            time = h*60+m + END_GAP;

        return rangeThreshold(time);
    }

    public static String minute2hhmm(int min){
        int h = min / 60;
        int m = min % 60;
        String hh = String.format("%02d",h);
        String mm = String.format("%02d",m);
        
        return hh+mm;
    }

    public static int rangeThreshold(int time){
        if(time>END_TH)
            time = END_TH;
        else if(time<START_TH)
            time = START_TH;

        return time;
    }

    public static class LinkedList implements Iterable<Pair>{
        private Pair head;
        private int size;

        public LinkedList(){
            head = new Pair(0,0); // dummy
            size = 0;
        }

        // add new Pair to list orderly
        // if it overlap with order Pair than transform it
        public void add(int start, int end){
            Pair prev = head;
            Pair cur = head.next;
            Pair added = new Pair(start, end);

            // count overlapped rides and insert it
            while(cur != null){
                // if current pair is over the added pair than break
                if(cur.start >= added.end)
                    break;
                // if current pair is under the added pair than continue
                else if(cur.end <= added.start){
                    prev = cur;
                    cur = cur.next;    
                    continue;
                }
                // if current pair is overlapped by the added pair than size plus one
                // and insert under part of added pair to list
                else if(cur.start > added.start){
                    Pair inserted = new Pair(added.start, cur.start);
                    inserted.next = prev.next;
                    prev.next = inserted;

                    size++;
                }
                // update start for insert added.start
                added.start = Min(added.end, cur.end);
                prev = cur;
                cur = cur.next;
            }
            // if added's top part was left
            // than insert it to list
            if(added.start < added.end){
                added.next = prev.next;
                prev.next = added;
                size++;
            }
        }

        public Iterator<Pair> iterator(){
            return new Iterator<Pair>(){
                Pair cur = head;

                public boolean hasNext(){
                    if(cur.next == null)
                        return false;
                    return true;
                }
                public Pair next(){
                    cur = cur.next;
                    return cur;
                }
                public void remove(){}
            };
        }

        public int size(){
            return this.size;
        }
    }

    public static class Pair{
        public int start;
        public int end;
        public Pair next;

        public Pair(int start, int end){
            this.start = start;
            this.end = end;
            this.next = null;
        }

        public Pair(int start, int end, Pair next){
            this.start = start;
            this.end = end;
            this.next = next;
        }
    }

    public static int Min(int a, int b){
        return a<b?a:b;
    }
}
