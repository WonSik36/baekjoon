/*
    baekjoon online judge
    problem number 15662
    https://www.acmicpc.net/problem/15662

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

        int N = Integer.parseInt(br.readLine());

        Gear[] gears = new Gear[N];
        for(int i=0;i<N;i++){
            boolean[] tooth = new boolean[8];
            String str = br.readLine();
            for(int j=0;j<8;j++){
                tooth[j] = str.charAt(j)=='1' ? SOUTH : NORTH;
            }

            gears[i] = new Gear(tooth);
        }

        for(int i=0;i<N-1;i++){
            gears[i].setNext(gears[i+1]);
            gears[i+1].setPrev(gears[i]);
        }

        int K = Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());

            int idx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            gears[idx].rotate(dir);
        }

        int res = 0;
        for(int i=0;i<N;i++){
            if(gears[i].top() == SOUTH)
                res ++;
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