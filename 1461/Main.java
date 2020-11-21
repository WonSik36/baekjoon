/*
    baekjoon online judge
    problem number 1461
    https://www.acmicpc.net/problem/1461
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparingInt;

public class Main{
    static int N;
    static int M;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Integer> plusBooks = new ArrayList<>();
        List<Integer> minusBooks = new ArrayList<>();

        List<Integer> books = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).collect(toList());
        for(int i=0;i<N;i++){
            if(books.get(i) < 0)
                minusBooks.add((-1)*books.get(i));
            else
                plusBooks.add(books.get(i));
        }

        minusBooks.sort(comparingInt((Integer x)->x).reversed());
        plusBooks.sort(comparingInt((Integer x)->x).reversed());

        // printList(minusBooks);
        // printList(plusBooks);

        int sum = 0;
        if(minusBooks.size() == 0) {
            sum = calcSteps(minusBooks, plusBooks);
        } else if(plusBooks.size() == 0) {
            sum = calcSteps(plusBooks, minusBooks);
        } else if(minusBooks.get(0) < plusBooks.get(0)) {
            sum = calcSteps(minusBooks, plusBooks);
        } else {
            sum = calcSteps(plusBooks, minusBooks);
        }

        bw.write(Integer.toString(sum)+"\n");

        bw.close();
        br.close();
    }

    static void printList(List<Integer> list) {
        for(int i=0;i<list.size();i++){
            System.out.printf("%d ", list.get(i));
        }
        System.out.println();
    }

    static int calcSteps(List<Integer> smallerBooks, List<Integer> biggerBooks) {
        int sum = 0;
        int finalStep = biggerBooks.get(0); 

        Queue<Integer> queue = new LinkedList<>(smallerBooks);
        while(!queue.isEmpty()) {
            sum += 2*queue.peek();

            for(int i=0;i<M && !queue.isEmpty(); i++) {
                queue.poll();
            }
        }

        queue.addAll(biggerBooks);
        while(!queue.isEmpty()) {
            sum += 2*queue.peek();

            for(int i=0;i<M && !queue.isEmpty(); i++) {
                queue.poll();
            }
        }

        return sum - finalStep;
    }
}
