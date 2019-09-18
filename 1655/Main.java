/*
    baekjoon online judge
    problem number 1655
    https://www.acmicpc.net/problem/1655
    https://www.crocus.co.kr/625
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.lang.NullPointerException;

public class Main{
    static boolean DEBUG = false;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        MidQueue mq = new MidQueue();

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(br.readLine());
            mq.add(num);
            bw.write(Integer.toString(mq.peek())+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class MidQueue{
        private PriorityQueue<Integer> top;
        private PriorityQueue<Integer> bottom;
        private int size;

        MidQueue(){
            top = new PriorityQueue<Integer>();
            bottom = new PriorityQueue<Integer>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer n1, Integer n2){
                        if(n1 > n2)
                            return -1;
                        else if(n1 == n2)
                            return 0;
                        else
                            return 1;
                    }
                });
            size = 0;
        }

        public void add(int num){
            if(isEmpty()){
                bottom.add(num);
                this.size++;
                return;
            }
            
            if(bottom.peek() < num)
                top.add(num);
            else
                bottom.add(num);
            balance();
            this.size++;
        }

        public int peek(){
            return bottom.peek();
        }

        public int poll(){
            if(isEmpty())
                throw new NullPointerException("Queue is Empty");
            int ret = bottom.poll();
            balance();
            this.size--;
            return ret;
        }
        
        public boolean isEmpty(){
            return size==0?true:false;
        }

        private void balance(){
            int gap = top.size() - bottom.size();
            while(gap!=0 && gap!=-1){
                if(gap < 0){
                    int temp = bottom.poll();
                    top.add(temp);
                }else{
                    int temp = top.poll();
                    bottom.add(temp);
                }
                gap = top.size() - bottom.size();
            }
        }

        public String toString(){
            String str = String.format("Top head: %d, Bottom head: %d\nTop size: %d, Bottom size: %d\n"
            ,top.peek(),bottom.peek(),top.size(),bottom.size());
            return str;
        }
    }
    public static void print(int num){
        if(DEBUG)
            System.out.println(num);
    }

    public static void print(String str){
        if(DEBUG)
            System.out.print(str);
    }

    public static void print(String str, int... ints){
        if(DEBUG){
            String[] spl = str.split("%d");
            String output = spl[0];
            int idx = 1;
            for(int number:ints){
                output += number;
                if(idx < spl.length)
                    output += spl[idx++];
            }
            System.out.print(output);
        }
    }

    public static void printArray(int[] arr){
        if(DEBUG){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
    }
}
