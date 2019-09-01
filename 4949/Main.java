/*
    baekjoon online judge
    problem number 4949
    https://www.acmicpc.net/problem/4949
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.EmptyStackException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            String str = br.readLine();
            if(str.equals("."))
                break;
            else{
                try{
                    if(isBalanced(str))
                        bw.write("yes\n");
                    else
                        bw.write("no\n");
                }catch(EmptyStackException e){
                    bw.write("no\n");
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean isBalanced(String str)throws EmptyStackException{
        Stack<Integer> st = new Stack<Integer>();
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            int tmp;
            switch(c){
                case '(':
                    st.add(0);
                    break;
                case ')':
                    tmp = st.pop();
                    if(tmp !=0)
                        return false;
                    break;
                case '[':
                    st.add(1);
                    break;
                case ']':
                    tmp = st.pop();
                    if(tmp !=1)
                        return false;
                    break;
            }
        }
        if(st.isEmpty())
            return true;
        else
            return false;
    }
}