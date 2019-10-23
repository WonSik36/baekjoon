/*
    baekjoon online judge
    problem number 2571
    https://www.acmicpc.net/problem/2571

    scan whole range vertically
    scan length is from 1 to no detection
*/

// import java.io.FileReader;
// import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    static final int BIG_LEN = 100;
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
            colorMat(x,y,mat);
        }

        boolean[][] narrow = narrowMat(mat);
        int max = calculateMat(narrow);

        bw.write(Integer.toString(max)+"\n");
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

    public static boolean[][] narrowMat(boolean[][] Mat){
        // get left top x, left top y, right bottom x, right bottom y
        int ltx=BIG_LEN, lty=BIG_LEN, rbx=0, rby=0;
        for(int i=0;i<BIG_LEN;i++){
            for(int j=0;j<BIG_LEN;j++){
                if(Mat[i][j]){
                    if(i < lty)
                        lty = i;
                    if(i > rby)
                        rby = i;
                    if(j < ltx)
                        ltx = j;
                    if(j > rbx)
                        rbx = j;
                }
            }
        }

        // copy original mat to narrowed mat
        int height = rby-lty+1;
        int width = rbx-ltx+1;
        boolean[][] ret = new boolean[height][width];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                ret[i][j] = Mat[i+lty][j+ltx];
            }
        }

        return ret;
    }

    public static int calculateMat(boolean[][] mat){
        int max = 0;
        int height = mat.length;
        int width = mat[0].length;
        // int CNT = 1;

        // nested 4 loop...
        for(int k=1;k<=width;k++){
            boolean flag = false;
            for(int j=0;j<width-k+1;j++){
                int cnt = 0;
                for(int i=0;i<height;i++){
                    // System.out.printf("Count[%d]: Mat[%d][%d] ~ [%d][%d] check\n",CNT++,i,j,i,j+k-1);
                    if(isHorizonLine(i, j, k, mat)){
                        flag = true;
                        cnt++;
                    }else{
                        if(cnt!=0 && cnt*k>max){
                            max = cnt*k;
                        }
                        cnt = 0;
                    }
                }
                if(cnt!=0 && cnt*k>max){
                    max = cnt*k;
                }
            }
            // k is too long so there is no matching rectangle
            if(!flag)
                break;
        }

        return max;
    }

    public static boolean isHorizonLine(int y, int x, int len, boolean[][] mat){
        for(int i=0;i<len;i++){
            if(!mat[y][x+i])
                return false;
        }
        return true;
    }
}
