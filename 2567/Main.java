/*
    baekjoon online judge
    problem number 2567
    https://www.acmicpc.net/problem/2567

    divide it row and column
    if two adjacent Point is different than this is boarder line
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
    static final int BIG_LEN = 102;
    static final int SML_LEN = 10;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        boolean[][] mat = new boolean[BIG_LEN][BIG_LEN];
        int N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            colorMat(x+1,y+1,mat);
        }

        int line = calculateMat(mat);

        bw.write(Integer.toString(line)+"\n");
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
        int line = 0;
        
        for(int i=0;i<BIG_LEN-1;i++){
            for(int j=0;j<BIG_LEN-1;j++){
                if(mat[i][j]!=mat[i][j+1])
                    line++;
                if(mat[j][i]!=mat[j+1][i])
                    line++;
            }
        }

        return line;
    }
}
