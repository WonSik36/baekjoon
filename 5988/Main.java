/*
    baekjoon online judge
    problem number 5988
    https://www.acmicpc.net/problem/5988

    Using Collectors to make Set(HashSet)
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main{
    static final String EVEN = "even\n";
    static final String ODD = "odd\n";

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Set<Character> evens = Arrays.stream(new Character[]{'0', '2', '4', '6', '8'})
                .map(Character::valueOf)
                .collect(Collectors.toCollection(HashSet::new));

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String num = br.readLine();
            char lastDigit = num.charAt(num.length()-1);

            if(evens.contains(lastDigit))
                bw.write(EVEN);
            else
                bw.write(ODD);
        }

        bw.close();
        br.close();
    }
}
