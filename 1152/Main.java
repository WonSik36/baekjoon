/*
    baekjoon online judge
    problem number 1152
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputStr = br.readLine();
        StringTokenizer st = new StringTokenizer(inputStr);

        System.out.println(st.countTokens());
    }
}