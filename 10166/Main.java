/*
    baekjoon online judge
    problem number 10166
    https://www.acmicpc.net/problem/10166
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int seat = getSeat(start, end);

        bw.write(Integer.toString(seat)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getSeat(int start, int end){
        int[] seats = new int [end+1];

        return getSeat1ToEnd(end, seats) - getSeatNotIncluded(start,end,seats);
    }

    // get first seat to end seat
    public static int getSeat1ToEnd(int end, int[] seats){
        int sum = 0;
        for(int i=1;i<=end;i++){
            seats[i] = 0;
            for(int j=0;j<i;j++){
                if(GCD(i,j) == 1)
                    seats[i]++;
            }
            // System.out.printf("%d: %d\n",i,seats[i]);
            sum += seats[i];
        }

        return sum;
    }

    // get number of not included seat between 1 to start-1
    public static int getSeatNotIncluded(int start,int end,int[] seats){
        int sum = 0;
        for(int i=1;i<start;i++){
            boolean flag = false;
            for(int j=start; j<=end;j++){
                if(j%i == 0){
                    flag = true;
                    break;
                } 
            }
            if(!flag)
                sum += seats[i];
        }
        return sum;
    }

    public static int GCD(int x, int y){
        while(y != 0){
            int q = x % y;
            x = y;
            y = q;
        }

        return x;
    }
}