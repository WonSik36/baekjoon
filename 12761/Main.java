/*
    baekjoon online judge
    problem number 12761
    https://www.acmicpc.net/problem/12761
    https://jaimemin.tistory.com/947
    using BFS and LinkedList
    when using ArrayList timeout happened because of remove(0) method
*/
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int min = BFS(A,B,N,M);

        bw.write(Integer.toString(min));
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(int A, int B, int N, int M){
        LinkedList<Node> list = new LinkedList<Node>();
        boolean[] visited = new boolean[100001];
        Node tmp = null;

        list.addLast(new Node(N,0));
        while(true){
            tmp = list.remove();
            
            if(tmp.level == M)
                break;
            else if(tmp.level < 0 || tmp.level > 100000)
                continue;
            else if(visited[tmp.level])
                continue;
            else{
                visited[tmp.level] = true;
                list.addLast(new Node(tmp.level+1, tmp.cnt+1));
                list.addLast(new Node(tmp.level-1, tmp.cnt+1));
                list.addLast(new Node(tmp.level+A, tmp.cnt+1));
                list.addLast(new Node(tmp.level-A, tmp.cnt+1));
                list.addLast(new Node(tmp.level+B, tmp.cnt+1));
                list.addLast(new Node(tmp.level-B, tmp.cnt+1));
                list.addLast(new Node(tmp.level*A, tmp.cnt+1));
                list.addLast(new Node(tmp.level*B, tmp.cnt+1));
            }
        }

        return tmp.cnt;
    }

    public static class Node{
        public int level;
        public int cnt;

        Node(int level, int cnt){
            this.level = level;
            this.cnt = cnt;
        }
    }
}
