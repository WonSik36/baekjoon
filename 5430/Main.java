/*
    baekjoon online judge
    problem number 5430
    https://www.acmicpc.net/problem/5430
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        
        for(int i=0;i<num;i++){
            String command = br.readLine();
            int len = Integer.parseInt(br.readLine());
            String inputArr = br.readLine();
            // nothing is in array
            if(len == 0){
                if(command.contains("D"))
                    bw.write("error\n");
                else
                    bw.write("[]\n");
            }else{
                //"[1,2,3]" -> [1,2,3]
                inputArr = inputArr.replace("[", "");
                inputArr = inputArr.replace("]", "");
                String[] arrs = inputArr.split(",");
                int[] arr = Arrays.asList(arrs).stream().mapToInt(Integer::parseInt).toArray();
                //flag for knowing error occur
                MyQueue deq = new MyQueue(arr);
                boolean flag = true;
                for(int j=0;j<command.length();j++){
                    //deq.printQueue();
                    if(command.charAt(j)=='R')
                        deq.rotate();
                    else{
                        // command.charAt(j) == 'D'
                        int re = deq.pop();
                        //nothing is in array
                        if(re == -1){
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag == false)
                    bw.write("error\n");
                else{
                    bw.write("[");
                    int size = deq.size();
                    if(size == 0){
                        bw.write("]\n");
                    }else{
                        for(int j=0;j<size-1;j++){
                            String outputStr = String.format("%d,", deq.pop());
                            bw.write(outputStr);
                        }
                        String outputStr = String.format("%d]\n", deq.pop());
                        bw.write(outputStr);
                    }
                }
            }
        }
        bw.flush();
        bw.close();
    }

    static class MyQueue{
        private int num;
        private MyElement head;
        private MyElement tail;
        private boolean flag;   // for rotate

        public MyQueue(){
            num = 0;
            head = null;
            tail = null;
            flag = true;
        }

        public MyQueue(int n){
            this();

            for(int i=1;i<=n;i++){
                pushBack(i);
            }
        }

        public MyQueue(int[] nums){
            this();

            for(int i=0;i<nums.length;i++){
                pushBack(nums[i]);
            }
        }

        public void pushFront(int n){
            MyElement newElement = new MyElement(n, tail, head);
            if(isEmpty()){
                tail = newElement;
            }
            else{
                tail.setNext(newElement);
                head.setPrev(newElement);
            }
            head = newElement;
            num++;
        }

        public void pushBack(int n){
            MyElement newElement = new MyElement(n, tail, head);
            if(isEmpty()){
                head = newElement;
            }
            else{
                tail.setNext(newElement);
                head.setPrev(newElement);
            }
            tail = newElement;
            num++;
        }

        public int popFront(){
            int re = -1;
            if(isEmpty())
                return re;
            else if(size() == 1){
                re = head.getValue();
                head = null;
                tail = null;
            }
            else{
                re = head.getValue();
                head = head.getNext();
                tail.setNext(head);
                head.setPrev(tail);
            }
            num--;
            return re;
        }

        public int popBack(){
            int re = -1;
            if(isEmpty())
                return re;
            else if(size() == 1){
                re = tail.getValue();
                head = null;
                tail = null;
            }
            else{
                re = tail.getValue();
                tail = tail.getPrev();
                tail.setNext(head);
                head.setPrev(tail);
            }
            num--;
            return re;
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

        public int front(){
            if(isEmpty())
                return -1;
            else
                return head.getValue();
        }

        public int back(){
            if(isEmpty())
                return -1;
            else
                return tail.getValue();
        }
        //1 2 3 4 5
        // -> 2 3 4 5 1
        public void moveLeft(){
            head = head.getNext();
            tail = tail.getNext();
        }

        //1 2 3 4 5
        // -> 5 1 2 3 4 
        public void moveRight(){
            head = head.getPrev();
            tail = tail.getPrev();
        }

        public int indexOf(int target){
            int i;
            MyElement cur = head;
            for(i=0;i<num;i++){
                if(cur.getValue() == target)
                    break;
                cur = cur.getNext();
            }

            if(i == num)
                return -1;
            else
                return i;
        }

        public void rotate(){
            flag = !flag;
        }

        public int pop(){
            int re;
            if(flag)
                re = popFront();
            else
                re = popBack();
            return re;
        }

        public void printQueue()throws IOException{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            if(flag){
                for(int i=0;i<num;i++){
                    bw.write(Integer.toString(front())+" ");
                    moveLeft();
                }
            }else{
                for(int i=0;i<num;i++){
                    bw.write(Integer.toString(back())+" ");
                    moveRight();
                }
            }
            bw.write("\n");
            bw.flush();
        }
    }

    static class MyElement{
        private int v;
        private MyElement next;
        private MyElement prev;

        MyElement(int n, MyElement prev, MyElement next){
            v = n;
            this.prev = prev;
            this.next = next;
        }

        public MyElement getNext(){
            return next;
        }

        public void setNext(MyElement e){
            next = e;
        }

        public MyElement getPrev(){
            return prev;
        }

        public void setPrev(MyElement e){
            prev = e;
        }

        public int getValue(){
            return v;
        }

        public void setValue(int n){
            v = n;
        }
    }
}
