/*
    baekjoon online judge
    problem number 1359
    https://www.acmicpc.net/problem/1359

    Backtracking
    Make Combination and Compare It
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Main{
    static int N;
    static int M;
    static int K;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        List<Set<Integer>> my = new ArrayList<>();
        List<Set<Integer>> resort = new ArrayList<>();

        backtrack(my, resort);

        // printList(my);
        // printList(resort);

        int parent = my.size() * resort.size();
        int child = compare(my, resort);

        double res = child / (double)parent;
        bw.write(Double.toString(res)+"\n");
        
        bw.close();
        br.close();
    }

    static int compare(List<Set<Integer>> my, List<Set<Integer>> resort){
        int res = 0;

        for(Set<Integer> mySet : my){
            for(Set<Integer> resortSet : resort){
                // System.out.print("mySet: ");
                // printSet(mySet);
                // System.out.print("resortSet: ");
                // printSet(resortSet);

                
                int idx = 0;
                
                Iterator<Integer> myIt = mySet.iterator();
                while(myIt.hasNext()){
                    int myNum = myIt.next();
                    // System.out.printf("myNum: %d\n", myNum);
                    
                    
                    Iterator<Integer> resortIt = resortSet.iterator();
                    while(resortIt.hasNext()){
                        int resortNum = resortIt.next();
                        // System.out.printf("resortNum: %d\n", resortNum);

                        if(myNum == resortNum){
                            idx++;
                            // System.out.printf("%d %d %d\n", myNum, resortNum, idx);
                        }
                    }
                    
                }

                if(idx >= K){
                    res++;
                    // System.out.printf("hit: %d\n", res);
                }
            }   
        } 

        return res;
    }

    static void backtrack(List<Set<Integer>> my, List<Set<Integer>> resort){
        Set<Integer> set = new HashSet<>();

        for(int i=1;i<=N;i++){
            set.add(i);

            _backtrack(i+1, set, my, resort);

            set.remove(i);
        }
    }

    static void _backtrack(int cur, Set<Integer> set, List<Set<Integer>> my, List<Set<Integer>> resort){
        if(set.size() == M){
            my.add(new HashSet<>(set));
            resort.add(new HashSet<>(set));
            return;
        }

        if(cur > N){
            return;
        }


        for(int i=cur;i<=N;i++){
            set.add(i);

            _backtrack(i+1, set, my, resort);

            set.remove(i);
        }
    }

    static void printList(List<Set<Integer>> list){
        for(Set<Integer> set : list){
            Iterator<Integer> it = set.iterator();

            while(it.hasNext()){
                System.out.printf("%d ", it.next());
            }
            System.out.println();
        }
    }

    static void printSet(Set<Integer> set){
        Iterator<Integer> it = set.iterator();

        while(it.hasNext()){
            System.out.printf("%d ", it.next());
        }
        System.out.println();
    }
}
