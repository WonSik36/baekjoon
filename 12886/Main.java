/*
    baekjoon online judge
    problem number 12886
    https://www.acmicpc.net/problem/12886

    Breadth Frist Search Algorithm

    Using 2d array to visiting check
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int[] arr = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        arr[1] = Integer.parseInt(st.nextToken());
        arr[2] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        
        boolean[][] visited = new boolean[1000][1000];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(arr);

        boolean flag = false;
        while(!queue.isEmpty()){
            int[] first = queue.poll();

            if(first[0] == first[1] && first[1] == first[2]){
                flag = true;
                break;
            }

            if(visited[first[0]][first[1]])
                continue;
            visited[first[0]][first[1]] = true;

            for(int i=0;i<3;i++){
                int[] tmp = new int[3];
                int nextI = (i+1)%3;
                
                tmp[(i+2)%3] = first[(i+2)%3];
                if(first[i] > first[nextI]){
                    tmp[i] = first[i] - first[nextI];
                    tmp[nextI] = 2*first[nextI];
                }else{
                    tmp[nextI] = first[nextI] - first[i];
                    tmp[i] = 2*first[i];
                }
                Arrays.sort(tmp);

                queue.add(tmp);
            }
        }

        if(flag){
            bw.write("1\n");
        }else{
            bw.write("0\n");
        }

        bw.close();
        br.close();
    }
}