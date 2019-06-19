/*
    baekjoon online judge
    problem number 10866
    https://www.acmicpc.net/problem/10866
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        
        MyQueue queue = new MyQueue();
        String[] arr = new String[num];

        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            arr[i] = inputStr;
        }

        queue.takeCommand(arr);
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

        public void takeCommand(String[] arr)throws IOException{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            for(int i=0;i<arr.length;i++){
                String[] token = arr[i].split(" ");
                int n;
                switch(token[0]){
                    case "push_front":
                        pushFront(Integer.parseInt(token[1]));
                        break;

                    case "push_back":
                        pushBack(Integer.parseInt(token[1]));
                        break;

                    case "pop_front":
                        n = popFront();
                        bw.write(Integer.toString(n)+"\n");
                        break;

                    case "pop_back":
                        n = popBack();
                        bw.write(Integer.toString(n)+"\n");
                        break;

                    case "size":
                        n = size();
                        bw.write(Integer.toString(n)+"\n");
                        break;

                    case "empty":
                        if(isEmpty())
                            n = 1;
                        else
                            n = 0;
                        bw.write(Integer.toString(n)+"\n");
                        break;

                    case "front":
                        n = front();
                        bw.write(Integer.toString(n)+"\n");
                        break;

                    case "back":
                        n = back();
                        bw.write(Integer.toString(n)+"\n");
                        break;
                }
            }
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
