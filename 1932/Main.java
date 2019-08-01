/*
    baekjoon online judge
    problem number 15552
    https://www.acmicpc.net/problem/15552
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.lang.ArrayIndexOutOfBoundsException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int[][] inputArr = new int[num][num];

        // make input triangle format to array format
        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            StringTokenizer st = new StringTokenizer(inputStr);
            for(int j=0;j<=i;j++){
                inputArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=num-1;i>=0;i--){
            for(int j=i;j>=0;j--){
                int max = 0;
                try{
                    int left = getLeftChild(inputArr, i, j);
                    int right = getRightChild(inputArr, i, j);
                    max = left>right ? left : right;
                    //System.out.println("i: "+i+" j: "+j+" max: "+max);
                }catch(ArrayIndexOutOfBoundsException e){
                    //System.out.println("ArrayIndexOutOfBoundException Occured: "+i+" "+j);
                }finally{
                    // String str = String.format("i: %d, j: %d, arr[i][j]: %d, max: %d", i,j,inputArr[i][j],max);
                    // System.out.println(str);
                    inputArr[i][j] += max;
                }
            }
        }

        bw.write(Integer.toString(inputArr[0][0]));
        bw.flush();
        bw.close();
    }

    static int getLeftChild(int[][] arr, int col, int row)throws ArrayIndexOutOfBoundsException{
        return arr[col+1][row];
    }

    static int getRightChild(int[][] arr, int col, int row)throws ArrayIndexOutOfBoundsException{
        return arr[col+1][row+1];
    }
}