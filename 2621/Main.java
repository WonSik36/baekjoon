/*
    baekjoon online judge
    problem number 2621
    https://www.acmicpc.net/problem/2621

    Implementation
*/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import static java.util.Comparator.comparingInt;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = null;

        List<Card> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            st = new StringTokenizer(br.readLine());
            
            int color = getColor(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            list.add(new Card(color, number));
        }

        int res = 0;
        if(maxSameColor(list) == 5 && isStraight(list)){
            int max = maxNumber(list);
            res = 900 + max;
        }else if(maxSameNumber(list) == 4){
            list.sort(comparingInt((Card c) -> c.number));
            int number = list.get(2).number;
            res = 800 + number;
        }else if(isFullHouse(list)){
            list.sort(comparingInt((Card c) -> c.number));
            int tripleNumber = list.get(2).number;
            int pairNumber = list.get(2).number == list.get(1).number ? list.get(3).number : list.get(1).number;

            res = 700 + 10*tripleNumber + pairNumber;
        }else if(maxSameColor(list) == 5){
            int max = maxNumber(list);
            res = 600 + max;
        }else if(isStraight(list)){
            int max = maxNumber(list);
            res = 500 + max;
        }else if(maxSameNumber(list) == 3){
            list.sort(comparingInt((Card c) -> c.number));
            int number = list.get(2).number;
            res = 400+number;
        }else if(isTwoPair(list)){
            list.sort(comparingInt((Card c) -> c.number));
            int first = 0, second = 0;
            if(list.get(0).number == list.get(1).number){
                if(list.get(2).number == list.get(3).number){
                    first = list.get(0).number;
                    second = list.get(2).number;
                }else{
                    first = list.get(0).number;
                    second = list.get(3).number;
                }
            }else{
                first = list.get(2).number;
                second = list.get(3).number;
            }
            
            res = first > second ? 300 + 10*first + second : 300 + 10*second + first;
        }else if(maxSameNumber(list) == 2){
            list.sort(comparingInt((Card c) -> c.number));
            int number = list.get(0).number;
            for(int i=1;i<list.size();i++){
                int curNumber = list.get(i).number;
                if(curNumber == number)
                    break;
                number = curNumber;
            }

            res = 200 + number;
        }else{
            int max = maxNumber(list);
            res = 100 + max;
        }

        bw.write(Integer.toString(res)+"\n");

        bw.close();
        br.close();
    }

    static int maxSameColor(List<Card> list){
        list.sort(comparingInt((Card c) -> c.color));

        int max = 1;
        int cnt = 1;
        int color = list.get(0).color;

        for(int i=1;i<list.size();i++){
            int curColor = list.get(i).color;
            if(color == curColor){
                cnt++;
                max = Math.max(max, cnt);
            }else{
                color = curColor;
                cnt = 1;
            }
        }

        return max;
    }

    static int maxSameNumber(List<Card> list){
        list.sort(comparingInt((Card c) -> c.number));

        int max = 1;
        int cnt = 1;
        int number = list.get(0).number;

        for(int i=1;i<list.size();i++){
            int curNumber = list.get(i).number;
            if(number == curNumber){
                cnt++;
                max = Math.max(max, cnt);
            }else{
                number = curNumber;
                cnt = 1;
            }
        }

        return max;
    }

    static int maxNumber(List<Card> list){
        return list.stream().mapToInt((Card c) -> c.number).max().getAsInt();
    }

    static boolean isStraight(List<Card> list){
        list.sort(comparingInt((Card c) -> c.number));

        for(int i=0;i<list.size()-1;i++){
            if(list.get(i).number + 1 != list.get(i+1).number)
                return false;
        }

        return true;
    }

    static boolean isFullHouse(List<Card> list){
        list.sort(comparingInt((Card c) -> c.number));
        
        if(sameNumber(list.get(0).number, list.get(1).number, list.get(2).number)
                && sameNumber(list.get(3).number, list.get(4).number))
                return true;

        if(sameNumber(list.get(2).number, list.get(3).number, list.get(4).number)
            && sameNumber(list.get(0).number, list.get(1).number))
                return true;

        return false;
    }

    static boolean isTwoPair(List<Card> list){
        list.sort(comparingInt((Card c) -> c.number));

        if(sameNumber(list.get(0).number, list.get(1).number) 
                && sameNumber(list.get(2).number, list.get(3).number))
                    return true;

        if(sameNumber(list.get(0).number, list.get(1).number) 
                && sameNumber(list.get(3).number, list.get(4).number))
                    return true;

        if(sameNumber(list.get(1).number, list.get(2).number) 
                && sameNumber(list.get(3).number, list.get(4).number))
                    return true;

        return false;
    }

    static int getColor(String color){
        switch(color){
            case "R": 
                return Card.RED;
            case "B":
                return Card.BLUE;
            case "Y":
                return Card.YELLOW;
            case "G":
                return Card.GREEN;
            default:
                throw new AssertionError("Unknown Color");
        }
    }

    static boolean sameNumber(int... numbers){
        for(int i=0;i<numbers.length-1;i++){
            if(numbers[i] != numbers[i+1])
                return false;
        }

        return true;
    }
}

class Card {
    static final int RED = 0;
    static final int BLUE = 1;
    static final int YELLOW = 2;
    static final int GREEN = 3;

    public int color;
    public int number;

    public Card(int color, int number){
        this.color = color;
        this.number = number;
    }
}