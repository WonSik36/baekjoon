/*
    baekjoon online judge
    problem number 1079
    https://www.acmicpc.net/problem/1079

    Backtracking
    reference: https://j2wooooo.tistory.com/79
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static int N;
    static int[] member;
    static int[][] arr;
    static int mafia;
    static int result = 0;
    static boolean isMafiaWin = false;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        member = new int[N];
        arr = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            member[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mafia = Integer.parseInt(br.readLine());

        boolean[] visited = new boolean[N];
        // mafia kill citizen in night
        if(N % 2 == 0)
            killCitizenInNight(0, visited);
        else
            backtrack(-1, 0, visited);

        bw.write(Integer.toString(result)+"\n");

        bw.close();
        br.close();
    }

    static void backtrack(int target, int cnt, boolean[] visited){
        if(cnt == N/2){
            isMafiaWin = true;
            result = Max(result, cnt);
            return;
        }

        int maxIdx = updateIndex(target, visited);

        // System.out.printf("target: %d, cnt: %d\n", target, cnt);
        // printArr(visited);
        // printArr(member);

        // mafia was killed in morning
        if(maxIdx == mafia){
            result = Max(result, cnt);
            retrieveIndex(target, visited);
            return;
        }

        // kill citizen in morning
        visited[maxIdx] = true;

        // mafia kill citizen in night
        killCitizenInNight(cnt, visited);
        if(isMafiaWin)
            return;

        visited[maxIdx] = false;

        retrieveIndex(target, visited);
    }

    static int Max(int a, int b){
        return a>b?a:b;
    }

    static void printArr(int[] arr){
        for(int v : arr){
            System.out.printf("%d ",v);
        }
        System.out.println();
    }

    static void printArr(boolean[] arr){
        for(boolean v : arr){
            if(v)
                System.out.print("0 ");
            else
                System.out.print("1 ");
        }
        System.out.println();
    }

    static void killCitizenInNight(int cnt, boolean[] visited){
        for(int i=0;i<N;i++){
            if(i == mafia || visited[i]){
                continue;
            }

            visited[i] = true;
            backtrack(i, cnt+1, visited);
            visited[i] = false;

            if(isMafiaWin)
                return;
        }
    }

    static int updateIndex(int target, boolean[] visited){
        int maxIdx = 0;
        int max = 0;

        // nobody was killed
        if(target == -1){
            for(int i=0;i<N;i++){
                if(visited[i])
                    continue;

                if(member[i] > max){
                    maxIdx = i;
                    max = member[i];
                }
            }
        }else{
            for(int i=0;i<N;i++){
                if(visited[i])
                    continue;

                member[i] += arr[target][i];
                if(member[i] > max){
                    maxIdx = i;
                    max = member[i];
                }
            }
        }

        return maxIdx;
    }

    static void retrieveIndex(int target, boolean[] visited){
        if(target == -1)
            return;

        for(int i=0;i<N;i++){
            if(visited[i])
                continue;

            member[i] -= arr[target][i];
        }
    }
}
