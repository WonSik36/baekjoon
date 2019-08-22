/*
    baekjoon online judge
    problem number 1931
    https://www.acmicpc.net/problem/1931
    https://kim6394.tistory.com/67
    Activity Selection Problem
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Comparable;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int N = Integer.parseInt(str);
        ArrayList<Meeting> schedule = new ArrayList<Meeting>();

        for(int i=0;i<N;i++){
            str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int start = Integer.parseInt(st.nextToken());
            int finish = Integer.parseInt(st.nextToken());
            Meeting meeting = new Meeting(start, finish);
            schedule.add(meeting);
        }
        // schedule is sorted by finish ascending order
        Collections.sort(schedule);
        // printArrayList(schedule);

        // count maxNum
        int fidx = schedule.get(0).finish;
        int maxNum = 1;
        for(int i=1;i<N;i++){
            if(fidx <= schedule.get(i).start){
                fidx = schedule.get(i).finish;
                maxNum++;
            }
        }

        bw.write(Integer.toString(maxNum));
        bw.flush();
        bw.close();
    }

    static void printArrayList(ArrayList<Meeting> meeting)throws IOException{
        for(int i=0;i<meeting.size();i++){
            System.out.print(meeting.get(i).finish+" ");
        }
        System.out.println();

        for(int i=0;i<meeting.size();i++){
            System.out.print(meeting.get(i).start+" ");
        }
        System.out.println();
    }

    public static class Meeting implements Comparable<Meeting>{
        public int start;
        public int finish;
        public Meeting(int start, int finish){
            this.start = start;
            this.finish = finish;
        }

        @Override
        public int compareTo(Meeting meeting){
            if(this.finish > meeting.finish)
                return 1;
            else if(this.finish < meeting.finish)
                return -1;
            /* if input is 
                10 10
                3 10
                4 10
                10 10
                than under line needed
            */
            else if(this.start > meeting.start)
                return 1;
            else if(this.start < meeting. start)
                return -1;
            else
                return 0;
        }
    }
}