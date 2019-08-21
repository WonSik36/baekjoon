/*
    baekjoon online judge
    problem number 11047
    https://www.acmicpc.net/problem/11047
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
        
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] coin = new int[N];
        for(int i=0;i<N;i++){
            str = br.readLine();
            coin[i] = Integer.parseInt(str);
        }
        int coins = numOfCoins(N,coin,target);
        bw.write(Integer.toString(coins));
        bw.flush();
        bw.close();
    }

    static int numOfCoins(int N, int[] coin, int target)throws IOException{
        int usedCoin = 0;
        for(int i=N-1;i>=0;i--){
            // String str = String.format("$%d activated", coin[i]);
            // System.out.println(str);
            if(target == 0){
                // str = String.format("target is zero");
                // System.out.println(str);
                break;
            }
            if(coin[i] > target){
                // str = String.format("coin %d is bigger than target %d",coin[i],target);
                // System.out.println(str);
                continue;
            }
            int cur = coin[i];
            while(cur<=target){
                cur += coin[i];
                usedCoin++;
            }
            cur -= coin[i];
            target -= cur;
            // str = String.format("cur: %d, usedCoin: %d, target: %d",cur,usedCoin,target);
            // System.out.println(str);
        }
        return usedCoin;
    }
}