/*
    baekjoon online judge
    problem number 3671
    https://www.acmicpc.net/problem/3671

    Prime Number
    Brute Force
    Backtracking
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.lang.StringBuilder;
import java.util.Iterator;

public class Main{
    static boolean[] arr = new boolean[10000000];

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // calculate prime numbers
        init();

        int N = Integer.parseInt(br.readLine());
        String str;
        for(int i=0;i<N;i++){
            str = br.readLine();
            int cnt = backtrack(str);
            bw.write(Integer.toString(cnt));
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    static void init(){
        for(int i=2;i<arr.length;i++){
            arr[i] = true;
        }

        for(int i=2;i<=arr.length/2;i++){
            if(!arr[i])
                continue;

            for(int j=2*i;j<arr.length;j+=i){
                arr[j] = false;
            }
        }
    }

    static void printArr(int length){
        for(int i=0;i<length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    static int backtrack(String str){
        char[] numbers = new char[str.length()];
        boolean[] visited = new boolean[str.length()];
        Set<Integer> set = new HashSet<Integer>();
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<numbers.length;i++){
            numbers[i] = str.charAt(i);
        }

        _backtrack(sb, str.length(), numbers, visited, set);

        // printSet(set);
        return set.size();
    }

    static void _backtrack(StringBuilder sb, int cnt, char[] numbers, boolean[] visited, Set<Integer> set){
        if(cnt == 0){
            if(sb.length() != 0){
                int num = Integer.parseInt(sb.toString());
                if(arr[num])
                    set.add(num);
            }
            return;
        }

        for(int i=0;i<visited.length;i++){
            if(visited[i])
                continue;

            visited[i] = true;
            sb.append(numbers[i]);
            _backtrack(sb, cnt-1, numbers, visited, set);
            sb.deleteCharAt(sb.length()-1);
            visited[i] = false;
        }
        _backtrack(sb, cnt-1, numbers, visited, set);
    }

    static void printSet(Set<Integer> set){
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            System.out.print(it.next()+" ");
        }
        System.out.println();
    }
}
