/*
    baekjoon online judge
    problem number 2613
    https://www.acmicpc.net/problem/2613
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

import static java.util.stream.Collectors.toList;

public class Main{
    static int N;
    static int M;
    static List<Integer> list;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = Arrays.stream(br.readLine().split(" ")).map(Integer::valueOf).collect(toList());

        int sum = list.stream().mapToInt(x -> x).sum();
        int max = list.stream().mapToInt(x -> x).max().getAsInt();

        List<Integer> successList = new ArrayList<>();
        int l = max-1, r = sum+1;
        while(l+1 < r) {
            int mid = (l+r) / 2;

            Result res = isAvailable(mid);
            if(res.isSuccess) {
                // System.out.println("success: "+mid);
                r = mid;
                successList = res.successList;
            } else {
                l = mid;
            }
        }

        if(successList.size() < M) {
            successList = flatList(successList);
        }

        bw.write(Integer.toString(r)+"\n");
        for(int size: successList) {
            bw.write(Integer.toString(size));
            bw.write(" ");
        }
        bw.write("\n");

        bw.flush();
        bw.close();
        br.close();
    }

    static List<Integer> flatList(List<Integer> successList) {
        int gap = M - successList.size();

        List<Integer> res = new ArrayList<>();
        for(int size: successList) {
            while(gap > 0 && size > 1) {
                size--;
                gap--;
                res.add(1);
            }
            res.add(size);
        }

        return res;
    }

    static Result isAvailable(int max) {
        List<Integer> successList = new ArrayList<>();

        int sum = 0, size = 0;
        for(int i=0; i < N; i++){
            if(sum+list.get(i) > max) {
                successList.add(size);
                sum = 0;
                size = 0;
            }
            sum += list.get(i);
            size++;
        }
        successList.add(size);

        if(successList.size() > M)
            return new Result(false, null);
        else
            return new Result(true, successList);
    }

    static class Result {
        public boolean isSuccess;
        public List<Integer> successList;

        public Result(boolean isSuceess, List<Integer> list) {
            this.isSuccess = isSuceess;
            this.successList = list;
        }
    }
}
