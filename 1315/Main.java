/*
    baekjoon online judge
    problem number 1315
    https://www.acmicpc.net/problem/1315
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
    static boolean DEBUG = false;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        ArrayList<Quest> list = new ArrayList<Quest>();
        int maxPnt = 0;
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int STR = Integer.parseInt(st.nextToken());
            int INT = Integer.parseInt(st.nextToken());
            int PNT = Integer.parseInt(st.nextToken());

            list.add(new Quest(STR,INT,PNT));
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class Quest{
        public int STR;
        public int INT;
        public int PNT;

        public Quest(int STR, int INT, int PNT){
            this.STR = STR;
            this.INT = INT;
            this.PNT = PNT;
        }
    }

    public static void print(int num){
        if(DEBUG)
            System.out.println(num);
    }

    public static void print(String str){
        if(DEBUG)
            System.out.print(str);
    }

    public static void print(String str, int... ints){
        if(DEBUG){
            String[] spl = str.split("%d");
            String output = spl[0];
            int idx = 1;
            for(int number:ints){
                output += number;
                if(idx < spl.length)
                    output += spl[idx++];
            }
            System.out.print(output);
        }
    }

    public static void printArray(int[] arr){
        if(DEBUG){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
    }
}
