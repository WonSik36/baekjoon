/*
    baekjoon online judge
    problem number 10597
    https://www.acmicpc.net/problem/10597

    BackTracking problem
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Main{
    static final int MAX_NUM = 50;
    static boolean flag = false;

    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        dfs(str, bw);

        bw.close();
        br.close();
    }

    public static void dfs(String str, BufferedWriter bw)throws IOException{
        boolean[] visited = new boolean[MAX_NUM+1];
        List<Integer> seq = new ArrayList<Integer>();
        StringBuilder origin = new StringBuilder(str);

        _dfs(visited, seq, origin, bw);
    }

    public static void _dfs(boolean[] visited, List<Integer> seq, StringBuilder origin, BufferedWriter bw)throws IOException{
        // printList(seq);

        // if other nodes find answer
        if(flag)
            return;

        // check sequence was made
        if(origin.length() == 0){
            // check sequence has 1~N
            if(isValid(seq.size(), visited)){
                // print sequence
                for(int i=0;i<seq.size();i++){
                    bw.write(Integer.toString(seq.get(i)));
                    bw.write(" ");
                }
                bw.write("\n");
                flag = true;
            }
            return;
        }


        // if string starts with 0
        if(origin.charAt(0) == '0')
            return;

        // take 1 digit number
        int num = Integer.parseInt(origin.substring(0, 1));
        if(!visited[num]){
            visited[num] = true;
            seq.add(num);
            origin.delete(0, 1);
            _dfs(visited,seq,origin,bw);
            origin.insert(0, Integer.toString(num));
            seq.remove(seq.size()-1);
            visited[num] = false;
        }

        // if string length is 1
        if(origin.length()<2)
            return;
        
        // take 2 digit number
        num = Integer.parseInt(origin.substring(0, 2));
        if(num<=MAX_NUM && !visited[num]){
            visited[num] = true;
            seq.add(num);
            origin.delete(0, 2);
            _dfs(visited,seq,origin,bw);
            origin.insert(0, Integer.toString(num));
            seq.remove(seq.size()-1);
            visited[num] = false;
        }
    }

    // check the sequence has 1~N
    public static boolean isValid(int cnt, boolean[] visited){
        for(int i=1;i<=cnt;i++){
            // if not visited
            if(!visited[i])
                return false;
        }

        for(int i=cnt+1;i<=MAX_NUM;i++){
            // if visited
            if(visited[i])
                return false;
        }

        return true;
    }

    public static void printList(List<Integer> list){
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i)+" ");
        }
        System.out.println();
    }
}
