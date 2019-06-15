/*
    baekjoon online judge
    problem number 2504
    https://www.acmicpc.net/problem/2504
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
        MyStack st = new MyStack();
        boolean flag = true;
        int num;
        if(inputStr.length() % 2 != 0)
            flag = false;
        else{
            for(int i=0;i<inputStr.length();i++){
                if(!flag)
                    break;
                char ch = inputStr.charAt(i);
                switch(ch){
                    // ( is 0
                    case '(': 
                        st.push(0);
                        break;

                    // [ is 1
                    case '[':
                        st.push(1);
                        break;
                    
                    case ')':
                        num = st.pop();
                        if(num == 0){     // if n == '('
                            if(isOp(st.peep())){    //if top of the stack has operand than push it
                                st.push(2);
                            }else{  // if top of the stack has number than plus and push it
                                st.push(st.pop() + 2);
                            }
                        }else if(num == 1){    //if n == '['
                            flag = false;
                        }else{  // if n == number
                            int nextNum = st.pop();
                            if(nextNum != 0)
                                flag = false;
                            else{   //if VPS shape like '(X)'
                                if(isOp(st.peep())){    //if top of the stack has operand than push it
                                    st.push(num*2);
                                }else{  // if top of the stack has number than plus and push it. It means VPS shape like 'n(X)'
                                    st.push(st.pop() + num*2);
                                }
                            }
                        }
                        break;
                    
                    case ']':
                        num = st.pop();
                        if(num == 1){     // if n == '['
                            if(isOp(st.peep())){    //if top of the stack has operand than push it
                                st.push(3);
                            }else{  // if top of the stack has number than plus and push it
                                st.push(st.pop() + 3);
                            }
                        }else if(num == 0){    //if n == '['
                            flag = false;
                        }else{  // if n == number
                            int nextNum = st.pop();
                            if(nextNum != 1)
                                flag = false;
                            else{   //if VPS shape like '(X)'
                                if(isOp(st.peep())){    //if top of the stack has operand than push it
                                    st.push(num*3);
                                }else{  // if top of the stack has number than plus and push it. It means VPS shape like 'n(X)'
                                    st.push(st.pop() + num*3);
                                }
                            }
                        }
                        break;
                }
            }
        }

        if(flag)
            bw.write(st.pop()+"\n");
        else
            bw.write("0\n");
        bw.flush();
        bw.close(); 
    }

    public static boolean isOp(int n){
        if(n == 0 || n == 1)
            return true;
        else
            return false;
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
            //System.out.println(n+" is pushed");
        }

        public boolean isEmpty(){
            if(num == 0)
                return true;
            else
                return false;
        }

        public int peep(){
            if(isEmpty())
               return 0;
            else
                return head.getValue();
        }

        public int pop(){
            if(isEmpty())
                return 0;
            else{
                int re = head.getValue();
                this.head = head.getNext();
                num--;
                //System.out.println(re+" is poped");
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
