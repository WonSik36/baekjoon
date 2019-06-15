/*
    baekjoon online judge
    problem number 10845
    https://www.acmicpc.net/problem/10845
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
        int num = Integer.parseInt(inputStr);
        
        MyQueue queue = new MyQueue();
        
        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            //st.statement(inputStr);

            String[] arr = inputStr.split(" ");
            String t1 = arr[0];
            int n;
            String outputStr;

            switch(t1){
                case "push":
                    int t2 = Integer.parseInt(arr[1]);
                    queue.push(t2);
                    break;

                case "pop":
                    n = queue.pop();
                    outputStr = String.format("%d\n", n);
                    bw.write(outputStr);
                    break;

                case "size":
                    n = queue.size();
                    outputStr = String.format("%d\n", n);
                    bw.write(outputStr);
                    break;

                case "empty":
                    if(queue.isEmpty())
                        n = 1;
                    else
                        n = 0;

                    outputStr = String.format("%d\n", n);
                    bw.write(outputStr);
                    break;

                case "front":
                    n = queue.front();
                    outputStr = String.format("%d\n", n);
                    bw.write(outputStr);
                    break;

                case "back":
                    n = queue.back();
                    outputStr = String.format("%d\n", n);
                    bw.write(outputStr);
                    break;
            }
        }
        bw.flush();
        bw.close(); 
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

        public void push(int n){
            MyElement newElement = new MyElement(n, tail, null);
            if(isEmpty())
                head = newElement;
            else{
                tail.next = newElement;
            }
            tail = newElement;
            num++;
        }

        public boolean isEmpty(){
            if(num == 0)
                return true;
            else
                return false;
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

        public int pop(){
            if(isEmpty())
                return -1;
            else{
                int re = head.getValue();
                head = head.getNext();
                num--;
                if(!isEmpty())
                    head.setPrev(null);
                return re;
            }
        }

        public int size(){
            return num;
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
