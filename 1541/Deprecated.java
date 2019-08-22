/*
    baekjoon online judge
    problem number 1541
    https://www.acmicpc.net/problem/1541
    calculator: https://yahma.tistory.com/5
    ** There was more easier way to solve problem **
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.ArrayList;
import java.lang.Character;
import java.lang.Integer;
public class Deprecated{
    static Stack<Character> st;
    static ArrayList<String> list;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new Stack<Character>();
        list = new ArrayList<String>();
        String str = br.readLine();
        infix2Postfix(str);
        // printList();
        int result = evalPostfix();
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }

    public static void infix2Postfix(String str){
        int idx = 0;
        for(int i=1;i<str.length();i++){
            if(!Character.isDigit(str.charAt(i))){
                list.add(str.substring(idx, i));
                idx = i+1;
                if(st.isEmpty())
                    st.push(str.charAt(i));
                else{
                    char op1 = str.charAt(i);
                    char op2 = st.pop();
                    switch(op1){
                        case '+':
                            if(op2 == '+'){
                                list.add(op2+"");
                                st.push(op1);
                            }else{
                                st.push(op2);
                                st.push(op1);
                            }
                            break;
                        case '-':
                            list.add(op2+"");
                            while(!st.isEmpty()){
                                op2 = st.pop();
                                list.add(op2+"");
                            }
                            st.push(op1);
                            break;
                    }
                }
            }
        }
        list.add(str.substring(idx, str.length()));
        while(!st.isEmpty()){
            char op = st.pop();
            list.add(op+"");
        }
    }

    public static int evalPostfix(){
        Stack<Integer> calSt = new Stack<Integer>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals("+")){
                int op2 = calSt.pop();
                int op1 = calSt.pop();
                calSt.push(op1+op2);
            }else if(list.get(i).equals("-")){
                int op2 = calSt.pop();
                int op1 = calSt.pop();
                calSt.push(op1-op2);
            }else{
                calSt.push(Integer.parseInt(list.get(i)));
            }
        }
        return calSt.pop();
    }

    public static void printList()throws IOException{
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println();
    }
}