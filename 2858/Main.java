/*
    baekjoon online judge
    problem number 2858
    https://www.acmicpc.net/problem/2858
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());   // red is edge
        int B = Integer.parseInt(st.nextToken());   // brown is inner space

        int rowsPlusCols = (R-4)/2; // sum of rows and cols of Brown
        int L = 0, W = 0;
        for(int i=1;i<=rowsPlusCols/2;i++){
            if(i * (rowsPlusCols-i) == B){
                L = rowsPlusCols-i;
                W = i;
                break;
            }
        }

        if(L==0 && W==0)
            throw new RuntimeException("L and W are not updated");

        String res = Integer.toString(L+2) +" "+Integer.toString(W+2)+"\n";
        bw.write(res);
        bw.flush();
        bw.close();
        br.close();
    }
}
