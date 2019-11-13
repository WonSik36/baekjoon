/*
    baekjoon online judge
    problem number 17825
    https://www.acmicpc.net/problem/17825
    ref: https://eine.tistory.com/entry/백준-17825번-주사위-윷놀이-문제-풀이 [아인스트라세의 소프트웨어 블로그]
    
    brute-force problem

    ref says it take 4^10 calculation which is 2^20 = 2097152
    So it can be solved by brute-force search (100,000,000 can be calculated in 1 sec)
    ref solve problem by using bit masking
    but I solve problem by using dfs
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final int numOfHorse = 4;
    static final int numOfPlace = 33;
    static int[] dice = new int[11];
    static int[][] place = {
        {0,1,2,3,4,5},
        {2,2,3,4,5,9},
        {4,3,4,5,9,10},
        {6,4,5,9,10,11},
        {8,5,9,10,11,12},
        {10,6,7,8,20,29},
        {13,7,8,20,29,30},
        {16,8,20,29,30,31},
        {19,20,29,30,31,32},
        {12,10,11,12,13,14},
        {14,11,12,13,14,15},
        {16,12,13,14,15,16},
        {18,13,14,15,16,17},
        {20,18,19,20,29,30},
        {22,15,16,17,24,25},
        {24,16,17,24,25,26},
        {26,17,24,25,26,27},
        {28,24,25,26,27,28},
        {22,19,20,29,30,31},
        {24,20,29,30,31,32},
        {25,29,30,31,32,32},
        {26,20,29,30,31,32},
        {27,21,20,29,30,31},
        {28,22,21,20,29,30},
        {30,23,22,21,20,29},
        {32,26,27,28,31,32},
        {34,27,28,31,32,32},
        {36,28,31,32,32,32},
        {38,31,32,32,32,32},
        {30,30,31,32,32,32},
        {35,31,32,32,32,32},
        {40,32,32,32,32,32},
        {0,32,32,32,32,32}
    };

    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // get dice input
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<10;i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        // initialize position of janggi and number of janggi in place
        int[] pos = new int[numOfHorse];
        int[] horseNumOfPlace = new int[numOfPlace];

        int res = dfs(pos, horseNumOfPlace, 0, 0);

        bw.write(Integer.toString(res)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int dfs(int[] pos, int[] horseNumOfPlace, int cnt, int score){
        if(cnt == 10)
            return score;

        int max = 0;
        for(int i=0;i<4;i++){
            int curPos = pos[i];
            int nextPos = place[curPos][dice[cnt]];

            // if other horse is already existed in next pos than continue
            if(nextPos!=0 && nextPos!=32 && horseNumOfPlace[nextPos]>0)
                continue;

            // update pos and horseNumOfPlace
            pos[i] = nextPos;
            horseNumOfPlace[curPos]--;
            horseNumOfPlace[nextPos]++;

            // get max value
            max = Max(max, dfs(pos,horseNumOfPlace,cnt+1,score+place[pos[i]][0]));

            // recover pos and horseNumOfPlace
            pos[i] = curPos;
            horseNumOfPlace[curPos]++;
            horseNumOfPlace[nextPos]--;
        }

        return max;
    }

    public static int Max(int a, int b){
        return a>b?a:b;
    }
}
