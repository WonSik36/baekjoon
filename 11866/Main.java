/*
    baekjoon online judge
    problem number 11866,1158
    https://www.acmicpc.net/problem/11866
    https://www.acmicpc.net/problem/11866
    problem 11866 is same with 1158
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputStr = br.readLine();
        String[] arr = inputStr.split(" ");
        int N = Integer.parseInt(arr[0]);
        int K = Integer.parseInt(arr[1]);
        
        MyQueue queue = new MyQueue();
        
        for(int i=1;i<=N;i++){
            queue.push(i);
        }
        
        queue.first();
        bw.write("<");
        for(int i=0;i<N-1;i++){
            for(int j=0;j<K-1;j++)
                queue.next();
            int num = queue.pop();
            bw.write(Integer.toString(num)+", ");
        }
        int num = queue.pop();
        bw.write(Integer.toString(num)+">\n");

        bw.flush();
        bw.close(); 
    }

    static class MyQueue{
        private int num;
        private MyElement head;
        private MyElement tail;
        private MyElement cur;
        private MyElement prev;

        public MyQueue(){
            num = 0;
            // head is dummy node
            head = new MyElement(-1, null);
            tail = null;
            cur = null;
            prev = null;
        }
        
        //first() make cur to point head(first one)
        // and prev to point tail
        //so after first, next() occur K-1 time
        public void first(){
            cur = head.getNext();
            prev = tail;
        }

        public void next()throws IOException{
            cur = cur.getNext();
            prev = prev.getNext();
            //System.out.println("current number is "+cur.getValue());
            //System.out.println("previous number is "+prev.getValue()+"\n");
        }

        public boolean isEmpty(){
            if(num == 0)
            return true;
            else
            return false;
        }
        
        public int size(){
            return num;
        }

        public void push(int n){
            if(isEmpty()){
                MyElement newElement = new MyElement(n, null);
                head.setNext(newElement);
                newElement.setNext(newElement);
                tail = newElement;
            }
            else{
                MyElement newElement = new MyElement(n, head.getNext());
                tail.setNext(newElement);
                tail = newElement;
            }
            num++;
        }

        //after pop() cur is cur.next
        //so next() should occur K-1 time 
        public int pop()throws IOException{
            //printQueue();
            if(isEmpty())
                return -1;
            else if(size() == 1){
                int re = cur.getValue();
                head.setNext(null);
                tail = null;
                cur = null;
                prev = null;
                num--;
                return re;
            }
            else{
                int re = cur.getValue();
                if(cur == head.getNext())
                    head.setNext(cur.getNext());
                prev.setNext(cur.getNext());
                cur = cur.getNext();
                num--;
                return re;
            }
        }

        public void printQueue()throws IOException{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            MyElement element = head.getNext();

            for(int i=0;i<num;i++){
                String str = String.format("%d ", element.getValue());
                bw.write(str);
                element = element.getNext();
            }
            bw.write("\n");
            bw.flush();
        }
    }

    static class MyElement{
        private int v;
        private MyElement next;

        MyElement(int n, MyElement next){
            v = n;
            this.next = next;
        }

        public MyElement getNext(){
            return next;
        }

        public void setNext(MyElement e){
            next = e;
        }

        public int getValue(){
            return v;
        }

        public void setValue(int n){
            v = n;
        }
    }
}
