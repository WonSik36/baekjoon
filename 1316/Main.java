/*
    baekjoon online judge
    problem number 1316
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String inputStr = br.readLine();
        //String outputStr = "";
        int num = Integer.parseInt(inputStr);
        int numOfGroupWord = num;
        for(int i=0;i<num;i++){
            inputStr = br.readLine();

            char[] alphaArr = new char[26];
            for(int j=0;j<inputStr.length();j++){
                if(alphaArr[inputStr.charAt(j)-'a']==0)
                    alphaArr[inputStr.charAt(j)-'a'] = 1;
                else if(inputStr.charAt(j)!=inputStr.charAt(j-1)){
                    numOfGroupWord--;
                    break;
                }
            }
        }
        
        System.out.println(numOfGroupWord);
    }
}