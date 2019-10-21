/*
    baekjoon online judge
    problem number 9501
    https://www.acmicpc.net/problem/9501
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
    public static void main(String[] args)throws IOException{
        // BufferedReader br = new BufferedReader(new FileReader("./1.in"));
        // BufferedWriter bw = new BufferedWriter(new FileWriter("./1.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            ArrayList<SpaceShip> list = new ArrayList<SpaceShip>();
            for(int j=0;j<N;j++){
                st = new StringTokenizer(br.readLine());
                int velocity = Integer.parseInt(st.nextToken());
                int fuel = Integer.parseInt(st.nextToken());
                int rate = Integer.parseInt(st.nextToken());
                list.add(new SpaceShip(velocity, fuel, rate));
            }

            // velocity = dist/time
            // rate = fuel/time
            // demanded fuel = (distance to destination) / velocity * rate
            // maximum dist = fuel * velocity / rate
            int cnt = 0;
            for(int j=0;j<N;j++){
                SpaceShip temp = list.get(j);
                int v = temp.velocity;
                int f = temp.fuel;
                int r = temp.rate;
                double maxDist = (double)f*v/r;
                if(maxDist < (double)D)
                    continue;
                cnt++;
            }
            bw.write(Integer.toString(cnt)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static class SpaceShip{
        public int velocity;
        public int fuel;
        public int rate;

        public SpaceShip(int velocity, int fuel, int rate){
            this.velocity = velocity;
            this.fuel = fuel;
            this.rate = rate;
        }
    }
}
