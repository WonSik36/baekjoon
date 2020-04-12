/*
    baekjoon online judge
    problem number 14889
    https://www.acmicpc.net/problem/14889

    Backtracking
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Main{
    static int N;
    static int[][] arr;
    static int GAP = Integer.MAX_VALUE;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<N;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = backtrack();

        bw.write(Integer.toString(result)+"\n");
        bw.close();
        br.close();
    }

    static int backtrack(){
        List<Integer> team1 = new ArrayList<Integer>();
        List<Integer> team2 = new ArrayList<Integer>();

        _backtrack(0, team1, team2);

        return GAP;
    }

    static void _backtrack(int cnt, List<Integer> team1, List<Integer> team2){
        if(cnt == N){
            int overall1 = getOverallOfTeam(team1);
            int overall2 = getOverallOfTeam(team2);
            GAP = Min(GAP, Gap(overall1, overall2));

            return;
        }

        // put cnt player to team1
        if(team1.size() < N/2){
            team1.add(cnt);
            _backtrack(cnt+1, team1, team2);
            team1.remove(team1.size()-1);
        }

        // put cnt player to team2
        if(team2.size() < N/2){
            team2.add(cnt);
            _backtrack(cnt+1, team1, team2);
            team2.remove(team2.size()-1);
        }
    }

    static int getOverallOfTeam(List<Integer> team){
        int overall = 0;

        for(int i=0;i<team.size();i++){
            for(int j=0;j<team.size();j++){
                overall += arr[team.get(i)][team.get(j)];
            }
        }

        return overall;
    }

    static int Min(int a, int b){
        return a<b?a:b;
    }

    static int Gap(int a, int b){
        return (a-b)>(b-a)?(a-b):(b-a);
    }
}
