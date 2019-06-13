/*
    baekjoon online judge
    problem number 1874
    https://www.acmicpc.net/problem/1874
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
        int[] arr = new int[num];
        for(int i=0;i<arr.length;i++){
            inputStr = br.readLine();
            num = Integer.parseInt(inputStr);
            arr[i] = num;
        }
        
        MyStack st = new MyStack();
        st.sequence(arr);
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

        // parameter arr is input sequence
        public void sequence(int arr[])throws IOException{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            int size = arr.length;
            boolean[] op = new boolean[size*2];

            // i for op, j for arr
            int i = 0, j = 0;
            // num for ascending order of numbers
            int num = 1;
            // flag for input array is possible or not
            boolean flag = true;
            
            while(true){
                //stack is empty
                if(isEmpty()){
                    if(i == size*2)
                        break;
                    else{
                        op[i++] = true;
                        push(num++);
                    }
                //last element is equal to sequence
                }else if(peep() == arr[j]){
                    op[i++] = false;
                    pop();
                    j++;
                //last element is not equal to sequence
                }else if(peep() < arr[j]){
                    op[i++] = true;
                    push(num++);
                //imposible sequence
                }else{
                    flag = false;
                    break;
                }
            }

            if(flag){
                for(int k=0;k<op.length;k++){
                    if(op[k])
                        bw.write("+\n");
                    else
                        bw.write("-\n");
                }
            }else{
                bw.write("NO\n");
            }
            bw.flush();
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
