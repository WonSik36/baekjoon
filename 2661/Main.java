/*
    baekjoon online judge
    problem number 2661
    https://www.acmicpc.net/problem/2661

    Back Tracking Problem
    1. The validation process was simpler than I thought.
    2. But I tried to compare all possible results, which led to memory exceeded.
    3. Only need to return the first result.
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.lang.StringBuilder;
import java.lang.CharSequence;

public class Main{
    static final String[] Number = {"0","1","2","3"};

    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        String res = dfs(sb, N);

        bw.write(res+"\n");

        bw.close();
        br.close();
    }

    public static String dfs(StringBuilder sb, int N){
        if(!isValid(sb))
            return null;
        
        if(sb.length() == N){
            return sb.toString();
        }

        for(int i=1;i<=3;i++){
            sb.append(Number[i]);

            String res = dfs(sb,N);
            if(res != null)
                return res;

            sb.deleteCharAt(sb.length()-1);
        }

        return null;
    }

    private static StringBuilder sb1 = new StringBuilder();
    private static StringBuilder sb2 = new StringBuilder();
    // check the string has two same adjacent substring 
    public static boolean isValid(StringBuilder sb){
        if(sb.length() == 1 || sb.length() == 1)
            return true;

        for(int len=1; len <= sb.length()/2; len++){
            copySubStringBuilder(sb, sb1, sb.length()-len, sb.length());
            copySubStringBuilder(sb, sb2, sb.length()-len*2, sb.length()-len);

            if(isEqualStringBuilder(sb1,sb2))
                return false;
        }

        return true;
    }

    public static void copyStringBuilder(StringBuilder src, StringBuilder dst){
        copySubStringBuilder(src, dst, 0, src.length());
    }

    public static void copySubStringBuilder(StringBuilder src, StringBuilder dst, int start, int end){
        // remove contents of destination string
        dst.delete(0, dst.length());

        // append contents of source string to destination string
        dst.append(src.subSequence(start, end));
    }

    public static boolean isEqualStringBuilder(StringBuilder sb1, StringBuilder sb2){
        if(sb1.length() != sb2.length())
            return false;
        
        for(int i=0;i<sb1.length();i++){
            if(sb1.charAt(i) != sb2.charAt(i))
                return false;
        }

        return true;
    }
}
