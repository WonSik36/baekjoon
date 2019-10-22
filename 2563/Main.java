/*
    baekjoon online judge
    problem number 2563
    https://www.acmicpc.net/problem/2563
    https://jaimemin.tistory.com/678

    I thought to this problem too complicatedly
    approach it 2d array
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
    static final int BIG_LEN = 100;
    static final int SML_LEN = 10;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        boolean[][] mat = new boolean[100][100];
        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            colorMat(x,y,mat);
        }

        int size = calculateMat(mat);

        bw.write(Integer.toString(size)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void colorMat(int x, int y, boolean[][] mat){
        for(int i=y;i<y+SML_LEN;i++){
            for(int j=x;j<x+SML_LEN;j++){
                mat[i][j] = true;
            }
        }
    }

    public static int calculateMat(boolean[][] mat){
        int size = 0;
        
        for(int i=0;i<BIG_LEN;i++){
            for(int j=0;j<BIG_LEN;j++){
                if(mat[i][j])
                    size++;
            }
        }

        return size;
    }
}
