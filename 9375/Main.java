/*
    baekjoon online judge
    problem number 9375
    https://www.acmicpc.net/problem/9375
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        String str = br.readLine();
        int N = Integer.parseInt(str);

        for(int i=0;i<N;i++){
            map.clear();
            str = br.readLine();
            int clothNum = Integer.parseInt(str);
            for(int j=0;j<clothNum;j++){
                str = br.readLine();
                String cloth = str.split(" ")[1];
                int value = 1;
                if(map.containsKey(cloth)){
                    value = map.get(cloth);
                    value++;
                }
                map.put(cloth, value);
            }
            if(map.isEmpty())
                bw.write("0\n");
            else if(map.size()==1){
                bw.write(Integer.toString(clothNum)+"\n");
            }else{
                ArrayList<Integer> list = new ArrayList<Integer>(map.values());
                int ret = 1;
                for(int k=0;k<list.size();k++){
                    ret *= (list.get(k).intValue()+1);
                }
                bw.write(Integer.toString(ret-1)+"\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}