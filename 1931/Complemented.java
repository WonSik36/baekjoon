/*
    baekjoon online judge
    problem number 1931
    https://www.acmicpc.net/problem/1931
    Activity Selection Problem
    *** It take much time using quick sort so time over happened ***
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Random;

public class Complemented{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        int N = Integer.parseInt(str);
        int[] start = new int[N];
        int[] finish = new int[N];

        for(int i=0;i<N;i++){
            str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            start[i] = Integer.parseInt(st.nextToken());
            finish[i] = Integer.parseInt(st.nextToken());
        }

        Schedule sc = new Schedule(N, start, finish);
        // schdule is sorted by finish's sorted order
        sc.quickSort();
        sc.print();
        int fidx = sc.meetings[0].finish;
        int maxNum = 1;
        for(int i=1;i<N;i++){
            if(fidx <= sc.meetings[i].start){
                fidx = sc.meetings[i].finish;
                maxNum++;
            }
        }

        bw.write(Integer.toString(maxNum));
        bw.flush();
        bw.close();
    }

    public static class Meeting{
        public int start;
        public int finish;
        public Meeting next;

        public Meeting(int start, int finish){
            this.start = start;
            this.finish = finish;
        }
    }

    public static class Schedule{
        public int length;
        public Meeting[] meetings;

        public Schedule(int len, int[] start, int[] finish){
            length = len;
            meetings = new Meeting[len];
            for(int i=0;i<len;i++){
                meetings[i] = new Meeting(start[i],finish[i]);
            }
        }

        public void print()throws IOException{
            System.out.print("Start: ");
            for(int i=0;i<length;i++){
                System.out.print(meetings[i].start+" ");
            }
            System.out.println();

            System.out.print("Finish: ");
            for(int i=0;i<length;i++){
                System.out.print(meetings[i].finish+" ");
            }
            System.out.println();
        }

        public void quickSort() throws IOException{
            quickSortStart(0, length-1);
            print();
            quickSortFinish(0, length-1);
        }

        public void quickSortFinish(int begin, int end) throws IOException{
            if (begin < end) {
                int p = partitionFinish(begin, end);
                quickSortFinish(begin, p - 1);
                quickSortFinish(p + 1, end);
            }
        }
    
        public int partitionFinish(int begin, int end) throws IOException{
            int left = begin;
            int right = end;
            int pidx = getPivot(left, right);
            int pivot = meetings[pidx].finish;

            while (left < right) {
                System.out.println("partition finish");
                while ((meetings[left].finish < pivot) && (left < right))
                    left++;
                while ((meetings[right].finish > pivot) && (left < right))
                    right--;
    
                if (left < right) {
                    Meeting temp = meetings[left];
                    meetings[left] = meetings[right];
                    meetings[right] = temp;
                }
            }
            return left;
        }

        public void quickSortStart(int begin, int end) throws IOException{
            if (begin < end) {
                int p = partitionStart(begin, end);
                quickSortStart(begin, p - 1);
                quickSortStart(p + 1, end);
            }
        }

        public int partitionStart(int begin, int end) throws IOException{
            int left = begin;
            int right = end;
            int pidx = getPivot(left, right);
            int pivot = meetings[pidx].start;

            while (left < right) {
                while ((meetings[left].start <= pivot) && (left < right))
                    left++;
                while ((meetings[right].start >= pivot) && (left < right))
                    right--;
    
                if (left < right) {
                    Meeting temp = meetings[left];
                    meetings[left] = meetings[right];
                    meetings[right] = temp;
                }
            }
            return left;
        }
    }

    static int getPivot(int first, int last){
        Random r = new Random();

        return r.nextInt(last-first+1) + first;
    }
}