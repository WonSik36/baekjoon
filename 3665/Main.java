/*
    baekjoon online judge
    problem number 3665
    https://www.acmicpc.net/problem/3665

    Topological Sort
    case 1: Normal case -> print topological sort result
    case 2: Cycle case -> "Impossible"
    case 3: Multiple Node which's parent number is 0 -> Not happen

    Practice Stream Library Function
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.StringBuilder;

public class Main{
    static int N;
    static int M;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int totalCase = Integer.parseInt(br.readLine());

        for(int i=0;i<totalCase;i++){
            N = Integer.parseInt(br.readLine());
            int[] rank = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)-1).toArray();
            

            List<Pair> list = new ArrayList<>();
            M = Integer.parseInt(br.readLine());
            for(int j=0;j<M;j++){
                int[] pair = Arrays.stream(br.readLine().split(" ")).mapToInt(x -> Integer.parseInt(x)-1).toArray();
                list.add(new Pair(pair[0], pair[1]));
            }

            try{
                List<Integer> res = topo(rank, list);
                for(int num: res){
                    bw.write(Integer.toString(num+1));
                    bw.write(" ");
                }
                bw.write("\n");
            }catch(RuntimeException e){
                bw.write(e.getMessage());
                bw.write("\n");
            }
        }


        bw.flush();
        bw.close();
        br.close();
    }

    static List<Integer> topo(int[] rank, List<Pair> list){
        List<Integer> res = new ArrayList<>();
        int[] numOfParent = new int[N];

        /** initialize graph **/
        List<List<Integer>> g = new ArrayList<>();
        for(int i=0;i<N;i++){
            g.add(new ArrayList<>());
        }

        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                g.get(rank[i]).add(rank[j]);
                numOfParent[rank[j]]++;
            }
        }


        /* update graph by using given pair */
        for(Pair p: list){
            int start = p.start;
            int end = p.end;
            
            // swap start to end
            if(g.get(p.start).contains(p.end)){
                int tmp = start;
                start = end;
                end = tmp;
            }
            
            Iterator<Integer> it = g.get(end).iterator();
            while(it.hasNext()){
                if(it.next() == start){
                    it.remove();
                    break;
                }
            }
            g.get(start).add(end);
            numOfParent[start]--;
            numOfParent[end]++;
        }


        int idx = findParentNumZero(numOfParent);
        int cnt = N;
        boolean flag;
        while(cnt > 0){
            res.add(idx);
            cnt--;
            flag = true;

            if(cnt <= 0)
                break;

            for(int end: g.get(idx)){
                numOfParent[end]--;

                if(numOfParent[end] == 0){
                    idx = end;
                    flag = false;
                }
            }

            if(flag)
                throw new RuntimeException("IMPOSSIBLE");
        }

        return res;
    }

    static int findParentNumZero(int[] numOfParent){
        int res = -1;
        for(int i=0;i<N;i++){
            if(numOfParent[i] == 0){
                res = i;
            }
        }

        if(res == -1)
            throw new RuntimeException("IMPOSSIBLE");

        return res;
    }

    static void printArr(int[] rank){
        for(int num: rank){
            System.out.print(num);
            System.out.print(" ");
        }
        System.out.println();
    }

    static void printGraph(List<List<Integer>> g){
        int idx = 0;
        for(List<Integer> adj: g){
            System.out.printf("adj %d: ",idx++);
            for(int num: adj){
                System.out.printf("%d ", num);
            }
            System.out.println();
        }
    }

    static void printList(List<?> list){
        for(Object obj : list){
            System.out.print(obj);
        }
    }
}

class Pair {
    public int start;
    public int end;

    public Pair(int start, int end){
        this.start = start;
        this.end = end;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("start: ");
        sb.append(start);
        sb.append(", end: ");
        sb.append(end);
        sb.append("\n");
        
        return sb.toString();
    }
}