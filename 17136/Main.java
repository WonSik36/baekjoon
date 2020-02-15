/*
    baekjoon online judge
    problem number 17136
    https://www.acmicpc.net/problem/17136

    BackTracking and Brute Force Problem

    high reference
    https://jaimemin.tistory.com/1249

    I made a mistake at first but didn't notice it and looked for a reference.
    The reference solved the problem in the same way as I did.
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
    static final int N = 10;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st;
        boolean[][] arr = new boolean[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<N;j++){
                if(st.nextToken().equals("0"))
                    arr[i][j] = false;
                else
                    arr[i][j] = true;
            }
        }

        Confetti cf = new Confetti(arr);
        int res = cf.dfs(N);

        bw.write(Integer.toString(res));
        bw.write("\n");

        bw.close();
        br.close();
    }

    public static void printArr(boolean[][] arr){
        System.out.println();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(arr[i][j])
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
    }

}

class Confetti{
    private boolean[][] arr;
    private int[] numOfCF = {5,5,5,5,5};
    private int result;

    public Confetti(boolean[][] arr){
        this.arr = arr;
    }

    public int dfs(int N){
        result = 30;    // 25 is maximum result

        _dfs(0,0,N);

        if(result == 30)
            return -1;
        else
            return result;
    }

    private void _dfs(int y, int x, int N){
        // System.out.printf("pos y,x: %d,%d\n", y,x);

        if(!isValidCFNum() || result<=countUsedCF())
            return;

        // all position was checked
        if(y==N){
            // System.out.printf("result: %d, count: %d\n",result, countUsedCF());
            result = Min(result, countUsedCF());
            return;
        }

        // if pos: y,x is false
        if(!arr[y][x])
            nextPos(y, x, N);

        for(int scale=Min(5, Min(N-x, N-y));scale>0;scale--){
            if(checkNbyNConfetti(y, x, scale)){
                makeFalseNbyNConfetti(y, x, scale);

                nextPos(y, x, N);

                makeTrueNbyNConfetti(y, x, scale);
            }
        }
    }

    private void nextPos(int y, int x, int N){
        if(x == N-1)
            _dfs(y+1,0,N);
        else
            _dfs(y,x+1,N);
    }

    private boolean checkNbyNConfetti(int y, int x, int scale){
        for(int i=0;i<scale;i++){
            for(int j=0;j<scale;j++){
                if(!arr[y+i][x+j])
                    return false;
            }
        }

        return true;
    }

    private void makeTrueNbyNConfetti(int y, int x, int scale){
        numOfCF[scale-1]++;
        makeBoolNbyNConfetti(y, x, scale, true);
    }

    private void makeFalseNbyNConfetti(int y, int x, int scale){
        numOfCF[scale-1]--;
        makeBoolNbyNConfetti(y, x, scale, false);
    }

    private void makeBoolNbyNConfetti(int y, int x, int scale, boolean bool){
        for(int i=0;i<scale;i++){
            for(int j=0;j<scale;j++){
                arr[y+i][x+j] = bool;
            }
        }
    }

    private boolean isValidCFNum(){
        for(int i=0;i<numOfCF.length;i++){
            if(numOfCF[i] < 0)
                return false;
        }

        return true;
    }

    private int Min(int a, int b){
        return a<b?a:b;
    }

    private int countUsedCF(){
        int cnt = 0;
        for(int i=0;i<numOfCF.length;i++){
            cnt += (5 - numOfCF[i]);
        }

        return cnt;
    }

    public static void printArr(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.printf("%d ",arr[i]);
        }
        System.out.println();
    }
}