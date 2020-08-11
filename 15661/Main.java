/*
    baekjoon online judge
    problem number 15661
    https://www.acmicpc.net/problem/15661

    Brute Force
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

public class Main{
    static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());

        int[][] score = new int[N][];
        for(int i=0;i<N;i++){
            score[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        List<Integer> team1 = new ArrayList<>();
        List<Integer> team2 = new ArrayList<>();
        for(int i=1;i<=N/2;i++){
            team1.clear();
            team2.clear();

            calcScore(0, N, i, team1, N-i, team2, score);
        }

        bw.write(Integer.toString(MIN)+"\n");

        bw.close();
        br.close();
    }

    static void calcScore(final int idx, final int max, final int team1Size, List<Integer> team1,
            final int team2Size, List<Integer> team2, int[][] score){

        if(team1.size() == team1Size){
            calcAndCompare(idx, max, team1, team2, score);
            return;
        }

        if(team2.size() == team2Size){
            calcAndCompare(idx, max, team2, team1, score);
            return;
        }

        team1.add(idx);
        calcScore(idx+1, max, team1Size, team1, team2Size, team2, score);
        team1.remove(team1.size()-1);

        team2.add(idx);
        calcScore(idx+1, max, team1Size, team1, team2Size, team2, score);
        team2.remove(team2.size()-1);
    }

    static void calcAndCompare(final int idx, final int max, List<Integer> maxTeam, List<Integer> notMaxTeam, int[][] score){
        for(int i=idx;i<max;i++){
            notMaxTeam.add(i);
        }

        int score1 = getSum(maxTeam, score);
        int score2 = getSum(notMaxTeam, score);

        // printList("maxTeam", maxTeam);
        // System.out.printf("score of maxTeam: %d\n", score1);
        // printList("notMaxTeam", notMaxTeam);
        // System.out.printf("score of notMaxTeam: %d\n", score2);

        MIN = Math.min(MIN, Math.abs(score1 - score2));

        for(int i=idx;i<max;i++){
            notMaxTeam.remove(notMaxTeam.size()-1);
        }
    }

    static int getSum(List<Integer> teamMember, int[][] score){
        int sum = 0;

        for(int i=0;i<teamMember.size();i++){
            for(int j=0;j<teamMember.size();j++){
                if(i == j)
                    continue;

                sum += score[teamMember.get(i)][teamMember.get(j)];
            }
        }

        return sum;
    }

    static void printList(String teamName, List<Integer> list){
        System.out.print(teamName+": ");
        for(int mem : list){
            System.out.printf("%d ", mem);
        }
        System.out.println();
    }
}
