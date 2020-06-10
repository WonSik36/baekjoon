/*
    baekjoon online judge
    problem number 1302
    https://www.acmicpc.net/problem/1302
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Map<String, Integer> map = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String str = br.readLine();

            if(map.containsKey(str)){
                map.put(str, map.get(str)+1);
            }else{
                map.put(str, 1);
            }
        }

        int max = 0;
        String maxStr = null;
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(max < entry.getValue()){
                max = entry.getValue();
                maxStr = entry.getKey();
            }else if(max == entry.getValue() && entry.getKey().compareTo(maxStr) < 0){
                max = entry.getValue();
                maxStr = entry.getKey();
            }
        }

        bw.write(maxStr);
        bw.write("\n");

        bw.close();
        br.close();
    }
}
