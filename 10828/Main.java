/*
    baekjoon online judge
    problem number 10828
    https://www.acmicpc.net/problem/10828
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
        
        MyStack st = new MyStack();
        
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
                    st.push(t2);
                    break;

                case "pop":
                    n = st.pop();
                    outputStr = String.format("%d\n", n);
                    bw.write(outputStr);
                    break;

                case "size":
                    n = st.size();
                    outputStr = String.format("%d\n", n);
                    bw.write(outputStr);
                    break;

                case "empty":
                    if(st.isEmpty())
                        n = 1;
                    else
                        n = 0;

                    outputStr = String.format("%d\n", n);
                    bw.write(outputStr);
                    break;

                case "top":
                    n = st.peep();
                    outputStr = String.format("%d\n", n);
                    bw.write(outputStr);
                    break;
            }
        }
        bw.flush();
        bw.close(); 
    }

    static class MyStack{
        private int num;
        private MyElement head;

        public MyStack(){
            num = 0;
            head = null;
        }

        public void push(int n){
            MyElement newElement = new MyElement(n, head);
            head = newElement;
            num++;
        }

        public boolean isEmpty(){
            if(num == 0)
                return true;
            else
                return false;
        }

        public int peep(){
            if(isEmpty())
                return -1;
            else
                return head.getValue();
        }

        public int pop(){
            if(isEmpty())
                return -1;
            else{
                int re = head.getValue();
                this.head = head.getNext();
                num--;
                return re;
            }
        }

        public int size(){
            return num;
        }

        public void statement(String str) throws IOException{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            String[] arr = str.split(" ");
            String t1 = arr[0];
            int num;
            String outputStr;

            switch(t1){
                case "push":
                    int t2 = Integer.parseInt(arr[1]);
                    push(t2);
                    break;

                case "pop":
                    num = pop();
                    outputStr = String.format("%d\n", num);
                    bw.write(outputStr);
                    break;

                case "size":
                    num = size();
                    outputStr = String.format("%d\n", num);
                    bw.write(outputStr);
                    break;

                case "empty":
                    if(isEmpty())
                        num = 1;
                    else
                        num = 0;

                    outputStr = String.format("%d\n", num);
                    bw.write(outputStr);
                    break;

                case "top":
                    num = peep();
                    outputStr = String.format("%d\n", num);
                    bw.write(outputStr);
                    break;
            }
            bw.flush();
            //bw.close();
        }

    }

    static class MyElement{
        private int v;
        private MyElement next;

        MyElement(int n, MyElement e){
            v = n;
            next = e;
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
