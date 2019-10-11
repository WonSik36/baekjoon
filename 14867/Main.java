/*
    baekjoon online judge
    problem number 14867
    https://www.acmicpc.net/problem/14867
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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int cnt = calculate(A,B,a,b);

        bw.write(Integer.toString(cnt)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }


    /* there can be 9 state of input and result table is like below
    *
    * O: need to calculate, X: it is impossible
    * B\A       Max     Empty   middle
    * Max       2       1       O
    * Empty     1       0       O
    * middle    O       O       X
    */ 
    public static int calculate(int A, int B, int a, int b){
        if(A < B){
            int temp = A;
            A = B;
            B = temp;
            temp = a;
            a = b;
            b = temp;
        }

        if(a == 0){
            if(b == 0)
                return 0;
            else if(b == B)
                return 1;
        }else if(a == A){
            if(b == 0)
                return 1;
            else if(B == b)
                return 2;
        }else if(a !=A && b != B && b != 0){
            return -1;
        }

        int first = getCountByMoveB2A(A, B, a, b);
        int second = getCountByMoveB2A(B, A, b, a);
        // System.out.println(first+" "+second);

        // A -> B

        if(first == -1 && second == -1)
            return -1;
        else if(first == -1){
            return second;
        }else if(second == -1){
            return first;
        }

        if(first < second){
            return first;
        }else{
            return second;
        }
    }

    // A >= B
    public static int getCountByMoveB2A(int A, int B, int a, int b){
        Bucket.initCnt();
        Bucket bucketA = new Bucket(A);
        Bucket bucketB = new Bucket(B);
        
        // B -> A
        while(!(bucketA.isFull() && bucketB.isEmpty())){
            if(bucketA.isFull()){
                bucketA.empty();
                if(bucketA.getCur() == a && bucketB.getCur() == b){
                    return Bucket.getCnt();
                }
            }

            if(bucketB.isEmpty()){
                bucketB.fill();
                if(bucketA.getCur() == a && bucketB.getCur() == b){
                    return Bucket.getCnt();
                }        
            }

            bucketB.move(bucketA);
            if(bucketA.getCur() == a && bucketB.getCur() == b){
                return Bucket.getCnt();
            }
        }

        return -1;
    }

    public static class Bucket{
        private int cur;
        private int max;
        private static int cnt = 0;

        public static void initCnt(){
            cnt = 0;
        }

        public int getMax(){
            return max;
        }

        public int getCur(){
            return cur;
        }

        public static int getCnt(){
            return cnt;
        }

        public Bucket(int max){
            this.max = max;
            this.cur = 0;
        }

        public boolean isFull(){
            return this.cur == this.max;
        }

        public boolean isEmpty(){
            return this.cur == 0;
        }

        public void fill(){
            this.cur = max;
            cnt++;
        }

        public void empty(){
            this.cur = 0;
            cnt++;
        }

        private void add(int n){
            this.cur += n;
            cnt++;
        }

        public void move(Bucket bucket){
            if(bucket.getCur() + this.cur > bucket.getMax()){
                this.cur = bucket.getCur() + this.cur - bucket.getMax();
                bucket.fill();
            }else{
                bucket.add(this.cur);
                this.cur = 0;
            }
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
