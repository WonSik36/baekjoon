/*
    baekjoon online judge
    problem number 6064
*/
import java.io.InputStreamReader;
import java.awt.im.InputContext;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String inputStr = br.readLine();
        int inputNum = Integer.parseInt(inputStr);
        //System.out.println(triSum(1));
        for(int i=0;i<inputNum;i++){
            inputStr = br.readLine();
            StringTokenizer st = new StringTokenizer(inputStr);
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int k = kaing(M,N,x,y);
            bw.write(Integer.toString(k));
            bw.write("\n");
        }
        
        bw.flush();
        bw.close();
        return;
    }

    public static int kaing(int M, int N, int x, int y){
        //System.out.println(M+" "+N+" "+x+" "+y);
        if(M==N){
            if(x!=y)
                return -1;
            else
                return x;
        }
        else{
            if(M<N){
                int temp=M; M=N; N=temp;
                temp=x; x=y; y=temp;
            }
            // get LCM of M,N
            long maxYear = getLCM(M,N);
            //System.out.println("LCM: "+lcm);
            int i = 0;
            int a=0, b=0;
            while(i<=maxYear){
                //System.out.println("i: "+i+" a: "+a+" b: "+b);
                if(x==a){
                    b = (b+M-1)%N+1;
                    i += M;
                }else{
                    b = (b+x-a-1)%N+1;
                    i += x-a;
                    a=x;
                }
                if(b==y)
                    return i;
            }

            return -1;
        }
    }

    public static int getGCD(int a, int b){
        if(a%b==0)
            return b;

        return getGCD(b,a%b);
    }

    public static long getLCM(int a, int b){
        int gcd = getGCD(a,b);
        long lcm = (long)a*b/gcd;
        return lcm;
    }
}