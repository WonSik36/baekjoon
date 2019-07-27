/*
    baekjoon online judge
    problem number 7568
    https://www.acmicpc.net/problem/7568
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        Person[] pArr = new Person[num];

        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            String[] inputArr = inputStr.split(" ");
            int w = Integer.parseInt(inputArr[0]);
            int h = Integer.parseInt(inputArr[1]);
            pArr[i] = new Person(w,h);
        }

        for(int i=0;i<num;i++){
            for(int j=i+1;j<num;j++){
                int res = compareDungChi(pArr[i], pArr[j]);

                switch(res){
                    case 1:
                        pArr[j].rank++;
                        break;
                    case -1:
                        pArr[i].rank++;
                        break;
                    case 0:
                        break;
                }
            }
        }
        String result = "";
        for(int i=0;i<num;i++){
            result += (pArr[i].rank + " ");
        }
        result += "\n";
        bw.write(result);
        bw.flush();
        bw.close();
    }

    static class Person{
        int height;
        int weight;
        int rank;

        Person(int h, int w){
            height = h;
            weight = w;
            rank = 1;
        }

    }

    public static int compareDungChi(Person p1, Person p2){
        if(p1.height > p2.height && p1.weight > p2.weight)
            return 1;
        else if(p1.height < p2.height && p1.weight < p2.weight)
            return -1;
        else
            return 0;
    }
}
