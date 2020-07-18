/*
    baekjoon online judge
    problem number 17363
    https://www.acmicpc.net/problem/17363
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
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        
        char[][] input = new char[col][row];

        for(int i=0;i<row;i++){
            String str = br.readLine();
            for(int j=0;j<col;j++){
                input[col - 1 - j][i] = rotate(str.charAt(j));
            }
        }

        for(char[] column : input){
            bw.write(column);
            bw.write("\n");
        }


        bw.close();
        br.close();
    }

    static char rotate(char input){
        char output;
        switch(input){
            case '.':
            case 'O':
                output = input;
                break;
            case '-':
                output = '|';
                break;
            case '|':
                output = '-';
                break;
            case '/':
                output = '\\';
                break;
            case '\\':
                output = '/';
                break;
            case '^':
                output = '<';
                break;
            case '<':
                output = 'v';
                break;
            case 'v':
                output = '>';
                break;
            case '>':
                output = '^';
                break;
            default:
                throw new AssertionError("Unknown Input");
        }

        return output;
    }
}
