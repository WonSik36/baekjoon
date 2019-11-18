/*
    baekjoon online judge
    problem number 9517
    https://www.acmicpc.net/problem/9517
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
    static final int PLAYER = 8;
    static final int EXPLOSION_TIME = 210;
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int curPerson = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int curTime = 0;
        
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String res = st.nextToken();

            curTime += time;
            if(curTime >= EXPLOSION_TIME)
                break;

            if(res.equals("T"))
                curPerson = nextPerson(curPerson);
        }

        bw.write(Integer.toString(curPerson)+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static int nextPerson(int num){
        if(num == PLAYER)
            return 1;
        else
            return ++num;
    }
}
