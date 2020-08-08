/*
    baekjoon online judge
    problem number 14891
    https://www.acmicpc.net/problem/14891

    Simulation
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    static final boolean NORTH = true;
    static final boolean SOUTH = false;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        Gear[] gears = new Gear[4];
        for(int i=0;i<4;i++){
            boolean[] tooth = new boolean[8];
            String str = br.readLine();
            for(int j=0;j<8;j++){
                tooth[j] = str.charAt(j)=='1' ? SOUTH : NORTH;
            }

            gears[i] = new Gear(tooth);
        }

        gears[0].setNext(gears[1]);
        gears[1].setNext(gears[2]);
        gears[2].setNext(gears[3]);
        
        gears[3].setPrev(gears[2]);
        gears[2].setPrev(gears[1]);
        gears[1].setPrev(gears[0]);

        int K = Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            gears[idx].rotate(dir);
        }

        int res = 0;
        for(int i=0, score=1;i<4;i++, score<<=1){
            if(gears[i].top() == SOUTH)
                res += score;
        }

        bw.write(Integer.toString(res)+"\n");

        bw.close();
        br.close();
    }
}

class Gear {
    private boolean[] tooth;
    private Gear prev;
    private Gear next;

    public Gear(boolean[] tooth){
        this.tooth = tooth;
        this.prev = null;
        this.next = null;
    }

    public void setPrev(Gear prev){
        this.prev = prev;
    }

    public void setNext(Gear next){
        this.next = next;
    }

    public void rotate(int dir){
        if(dir == 1){
            rotate(true, false, false);
        }else{
            rotate(false, false, false);
        }
    }

    private void rotate(boolean dir, boolean fromLeft, boolean fromRight){
        if(prev != null && !fromLeft && prev.right()!= this.left()){
            prev.rotate(!dir, false, true);
        }

        if(next != null && !fromRight && this.right()!= next.left()){
            next.rotate(!dir, true, false);
        }

        if(dir){
            boolean tmp = tooth[7];
            for(int i=7;i>0;i--){
                tooth[i] = tooth[i-1];
            }
            tooth[0] = tmp;
        }else{
            boolean tmp = tooth[0];
            for(int i=0;i<7;i++){
                tooth[i] = tooth[i+1];
            }
            tooth[7] = tmp;
        }
    }

    public boolean top(){
        return tooth[0];
    }

    public boolean right(){
        return tooth[2];
    }

    public boolean left(){
        return tooth[6];
    }
}