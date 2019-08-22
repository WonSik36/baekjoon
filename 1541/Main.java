/*
    baekjoon online judge
    problem number 1541
    https://www.acmicpc.net/problem/1541
    http://blog.naver.com/occidere/220833166496
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.IOException;
public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        String tmp = "";
        int a=0,b=0;
        StringTokenizer plus, minus;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '-'){
                minus = new StringTokenizer(str.substring(i+1).replaceAll("[+-]"," "));
                while(minus.hasMoreTokens())
                    b += Integer.parseInt(minus.nextToken());
                break;
            }else
                tmp += str.charAt(i);
        }
        plus = new StringTokenizer(tmp,"+");
        while(plus.hasMoreTokens())
            a += Integer.parseInt(plus.nextToken());

        int result = a-b;
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
}