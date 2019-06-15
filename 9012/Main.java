/*
    baekjoon online judge
    problem number 9012
    https://www.acmicpc.net/problem/9012
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
        
        for(int i=0;i<num;i++){
            MyStack st = new MyStack();
            boolean flag = true;
            inputStr = br.readLine();
            //VPS length should be even
            if(inputStr.length()%2 != 0)
                flag = false;
            else{
                for(int j=0;j<inputStr.length();j++){
                    // case '('
                    if(inputStr.charAt(j) == '(')
                        st.push(0);
                    // case ')'
                    else{
                        // case 
                        if(st.pop() != 0){
                            flag = false;
                            break;
                        }

                    }
                }
                //after loop stack should be empty
                if(!st.isEmpty())
                    flag = false;
            }

            if(flag)
                bw.write("YES\n");
            else
                bw.write("NO\n");
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
