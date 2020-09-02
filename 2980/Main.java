/*
    baekjoon online judge
    problem number 2980
    https://www.acmicpc.net/problem/2980

    Implementation
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
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int length = Integer.parseInt(st.nextToken());

        List<TrafficLight> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int red = Integer.parseInt(st.nextToken());
            int green = Integer.parseInt(st.nextToken());

            list.add(new TrafficLight(pos, red, green));
        }

        int time = 0;
        int pos = 0;
        // System.out.printf("Time: %d, Pos: %d\n", time, pos);
        for(int i=0;i<list.size();i++){
            TrafficLight cur = list.get(i);
            
            if(pos < cur.pos){
                time += (cur.pos - pos);
                pos = cur.pos;
            }

            time += calcWaitTime(cur, time);
            // System.out.printf("Time: %d, Pos: %d\n", time, pos);
        }

        time += (length - pos);
        pos = length;

        bw.write(Integer.toString(time)+"\n");

        bw.close();
        br.close();
    }

    static int calcWaitTime(TrafficLight tl, int curTime){
        boolean isRed = true;
        while(curTime >= 0){
            if(isRed){
                curTime -= tl.red;
                isRed = false;
            }else{
                curTime -= tl.green;
                isRed = true;
            }
        }

        if(isRed){
            return 0;
        }else{
            return (-1) * curTime;
        }
    }
}

class TrafficLight{
    public int pos;
    public int red;
    public int green;

    public TrafficLight(int pos, int red, int green){
        this.pos = pos;
        this.red = red;
        this.green = green;
    }
}