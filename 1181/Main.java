/*
    baekjoon online judge
    problem number 1181
    https://www.acmicpc.net/problem/1181

    Because Main.Dictionary.add takes lot of time, Time Over rise 4 times.
    Sorting right after insertion takes O(n^2),
    so after inserting all element, sorting it. 
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.LinkedList;
import java.util.Collections;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Dictionary dt = new Dictionary(50);

        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        
        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            dt.add(inputStr);
        }
        dt.sort();
        dt.printAll();
        //bw.write(outputStr);
    }

    static class Dictionary{
        private LinkedList<String>[] list;
        private int maxLen;

        public Dictionary(int n){
            maxLen = n+1;
            list = new LinkedList[maxLen];
            // for(int i=0;i<maxLen;i++){
            //     list[i] = new LinkedList<String>();
            // }
        }

        public void add(String str){
            int len = str.length();
            if(list[len] == null)
                list[len] = new LinkedList<String>();
            /*
            int i;
            int size = list[len].size();
            for(i=0;i<size;i++){
                int comp = compare(str, list[len].get(i)); 
                if(comp == -1){
                    list[len].add(i,str);
                    break;
                }else if(comp == 0){
                    break;
                }
            }
            if(i == size)
                list[len].add(str);
            */
            list[len].add(str);
        }

        public void sort(){
            for(int i=1;i<maxLen;i++){
                if(list[i]!=null)
                    Collections.sort(list[i]);
            }
        }

        public void printAll() throws IOException{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            for(int i=0;i<maxLen;i++){
                if(list[i] != null){
                    String before = null;
                    for(int j=0;j<list[i].size();j++){
                        if(!list[i].get(j).equals(before)){
                            bw.write(list[i].get(j));
                            bw.write("\n");
                        }
                        before = list[i].get(j);
                    }
                }
            }

            bw.close();
        }

        private int compare(String str1, String str2){
            for(int i=0;i<str1.length();i++){
                int comp = str1.charAt(i) - str2.charAt(i);
                if(comp < 0)
                    return -1;
                else if(comp > 0)
                    return 1;
            }

            return 0;
        }
    }
}
