/*
    baekjoon online judge
    problem number 9663
    https://www.acmicpc.net/problem/9663
    https://wkdtjsgur100.github.io/N-Queen/
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
        
        int N = Integer.parseInt(br.readLine());
        int[] check = new int[N];
        int cases = n_Queen(N,check);

        bw.write(Integer.toString(cases)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int n_Queen(int N, int[] check){
        return _n_Queen(N, 0, check);
    }

    public static int _n_Queen(int N, int cnt, int[] check){
        // finally return 1
        if(cnt == N)
            return 1;

        int cases = 0;
        // check pos i is possible position
        for(int i=0;i<N;i++){
            if(isPossiblePos(i, cnt, check)){
                // if possible than check next row
                check[cnt] = i;
                cases += _n_Queen(N, cnt+1, check);
            }
        }
        return cases;
    }

    // check whether position of idx is possible
    public static boolean isPossiblePos(int idx, int cnt, int[] check){
        // first row
        if(cnt == 0)
            return true;

        // actually we need to check 3 row,column,diagonal
        // but because we use cnt so no need to check column
        for(int i=0;i<cnt;i++){
            // just need to check row and diagonal
            if((check[i] == idx) || (cnt-i == (int)Math.abs(check[i]-idx)))
                return false;
        }
        return true;
    }
}