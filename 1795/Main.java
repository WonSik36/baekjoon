/*
    baekjoon online judge
    problem number 1795
    https://www.acmicpc.net/problem/1795

    Breadth First Algorithm
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.lang.StringBuilder;

public class Main{
    static int row;
    static int col;
    static int numOfCountry = 0;

    static int[][][] map;
    static int[] X = {2,1,-1,-2,-2,-1,1,2};
    static int[] Y = {1,2,2,1,-1,-2,-2,-1};

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        List<Pos> posList = new ArrayList<>();
        List<Integer> maxCntList = new ArrayList<>();

        for(int i=0;i<row;i++){
            char[] line = br.readLine().toCharArray();
            for(int j=0;j<col;j++){
                if(line[j] != '.'){
                    posList.add(new Pos(i,j,0));
                    maxCntList.add(line[j] - '0');
                    numOfCountry++;
                }
            }
        }

        map = new int[numOfCountry][row][col];
        for(int i=0;i<numOfCountry;i++){
            bfs(i, posList, maxCntList);
        }

        // printMap(map);

        int res = Integer.MAX_VALUE;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int sum = 0;
                boolean flag = true;
                for(int k=0;k<numOfCountry;k++){
                    if(map[k][i][j] == -1){
                        flag = false;
                        break;
                    }else{
                        sum += map[k][i][j];
                    }
                }

                if(flag){
                    res = Math.min(res, sum);
                }
            }
        }

        if(res == Integer.MAX_VALUE){
            bw.write("-1\n");
        }else{
            bw.write(Integer.toString(res)+"\n");
        }

        bw.close();
        br.close();
    }

    static void bfs(int idx, List<Pos> list, List<Integer> maxCntList){
        Queue<Pos> q = new LinkedList<>();
        q.add(list.get(idx));
        for(int i=0;i<row;i++){
            Arrays.fill(map[idx][i], -1);
        }

        while(!q.isEmpty()){
            Pos first = q.poll();

            if(map[idx][first.y][first.x] != -1)
                continue;
            map[idx][first.y][first.x] = first.cnt;

            // System.out.println(first);

            for(int i=0;i<8;i++){
                if(first.y+Y[i] >= 0 && first.y+Y[i] < row && first.x+X[i] >= 0 && first.x+X[i] < col){
                    q.add(new Pos(first.y+Y[i], first.x+X[i], first.cnt+1));
                }
            }
        }

        for(int i=0;i<map[idx].length;i++){
            for(int j=0;j<map[idx][i].length;j++){
                if(map[idx][i][j] != -1)
                    map[idx][i][j] = (int)Math.ceil((double)map[idx][i][j] / maxCntList.get(idx));
            }
        }
    }

    static void printMap(int[][][] map){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                for(int k=0;k<map[i][j].length;k++){
                    System.out.printf("%d ", map[i][j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}

class Pos {
    public int y;
    public int x;
    public int cnt;

    public Pos(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("y: ");
        sb.append(y);
        sb.append(", x: ");
        sb.append(x);
        sb.append(", cnt: ");
        sb.append(cnt);

        return sb.toString();
    }
}