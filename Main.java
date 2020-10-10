import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int res = 2;
        for(int i=0;i<N-1;i++){
            if(i % 2 == 1)
                res = res*2%16769023;
        }

        bw.write(Integer.toString(res));

        bw.close();
        br.close();
    }
}