/*
    baekjoon online judge
    problem number 1021
    https://www.acmicpc.net/problem/1021
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = br.readLine();
        String[] arr = inputStr.split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        
        inputStr = br.readLine();
        arr = inputStr.split(" ");
        int[] targets = Arrays.asList(arr).stream().mapToInt(Integer::parseInt).toArray();
        
        MyQueue queue = new MyQueue(N);
        int re = queue.takeCommand(targets);
        System.out.println(re);
    }

    static class MyQueue{
        private int num;
        private MyElement head;
        private MyElement tail;

        public MyQueue(){
            num = 0;
            head = null;
            tail = null;
        }

        public MyQueue(int n){
            this();

            for(int i=1;i<=n;i++){
                pushBack(i);
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

        public int takeCommand(int[] targets){
            int count = 0;
            
            for(int i=0;i<targets.length;i++){
                int left = indexOf(targets[i]);
                int right = num - left;

                if(left > right){
                    count += right;
                    for(int j=0;j<right;j++)
                        moveRight();
                    popFront();
                }
                else{
                    count += left;
                    for(int j=0;j<left;j++)
                        moveLeft();
                    popFront();
                }
            }

            return count;
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
