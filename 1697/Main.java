/*
    baekjoon online judge
    problem number 1697
    https://www.acmicpc.net/problem/1697
    BFS has property that it visits each node shortest path
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.LinkedList;

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
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int min = BFS(start, end);

        bw.write(Integer.toString(min)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(int start, int end){
        LinkedList<Pair> list = new LinkedList<Pair>();
        list.add(new Pair(start, 0));
        boolean[] visited = new boolean[100001];

        while(!list.isEmpty()){
            Pair temp = list.removeFirst();
            int cur = temp.cur;
            int cnt = temp.cnt;

            if(visited[cur])
                continue;
            else if(cur == end)
                return cnt;

            visited[cur] = true;
            if(cur>0)
                list.add(new Pair(cur-1,cnt+1));
            if(cur<100000)
                list.add(new Pair(cur+1,cnt+1));
            if(cur<=50000)
                list.add(new Pair(cur*2,cnt+1));
        }
        return -1;
    }

    public static class Pair{
        public int cur;
        public int cnt;

        Pair(int cur, int cnt){
            this.cur = cur;
            this.cnt = cnt;
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
