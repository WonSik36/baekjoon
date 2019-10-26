/*
    baekjoon online judge
    problem number 11723
    https://www.acmicpc.net/problem/11723
    bit mask
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
    static final int MAX_MASK = 1048575;
    static final int MIN_MASK = 0;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int bit = 0;
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int num;
            switch(command){
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    bit = add(bit,num);
                    // printBit(bit);
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    bit = remove(bit,num);
                    // printBit(bit);
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken());
                    if(check(bit,num))
                        bw.write("1\n");
                    else
                        bw.write("0\n");
                    // printBit(bit);
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    bit = toggle(bit, num);
                    // printBit(bit);
                    break;
                case "all":
                    bit = MAX_MASK;
                    // printBit(bit);
                    break;
                case "empty":
                    bit = MIN_MASK;
                    // printBit(bit);
                    break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static int add(int bit, int idx){
        int mask = 1;
        for(int i=1;i<idx;i++){
            mask *= 2;
        }
        return bit|mask;
    }

    public static int remove(int bit, int idx){
        int mask = 1;
        for(int i=1;i<idx;i++){
            mask *= 2;
        }
        mask = mask^MAX_MASK;
        return bit&mask;
    }

    public static boolean check(int bit, int idx){
        int mask = 1;
        for(int i=1;i<idx;i++){
            mask *= 2;
        }
        int res = mask&bit;
        return res>0?true:false;
    }

    public static int toggle(int bit, int idx){
        int res;
        if(check(bit,idx))
            res = remove(bit,idx);
        else
            res = add(bit,idx);
        return res;
    }

    public static void printBit(int bit){
        boolean[] res = new boolean[20];
        
        // calculate result
        for(int i=0;i<20;i++){
            if(bit%2 == 1)
                res[20-i-1] = true;
            bit /= 2;
        }

        // print it
        for(int i=0;i<20;i++){
            if(res[i])
                System.out.print("1");
            else
                System.out.print("0");
        }
        System.out.println();
    }
}
