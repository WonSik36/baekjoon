/*
    baekjoon online judge
    problem number 1149
    https://www.acmicpc.net/problem/1149
    Dynamic Programming
    Recurssively solve, so memory excess happened
    And it was wrong answer because it ignore 2 options when 1 is chosen
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Deprecated{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        int[][] costs = new int[num][3];

        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            StringTokenizer st = new StringTokenizer(inputStr);
            for(int j=0;j<3;j++)
                costs[i][j] = Integer.parseInt(st.nextToken());
        }
        
        ColorOfHouse[][] result = new ColorOfHouse[num][num];
        calCost(result, 0, num-1, costs);

        bw.write(Integer.toString(result[0][num-1].sum)+"\n");
        bw.flush();
        bw.close();
    }

    static class ColorOfHouse{
        int[] house;
        int sum;
        int len;

        ColorOfHouse(int color, int cost){
            sum = cost;
            house = new int[1];
            house[0] = color;
            len = 1;
        }

        ColorOfHouse(int[] house, int sum, int len){
            this.house = house;
            this.sum = sum;
            this.len = len;
        }

        ColorOfHouse addLeft(int[] costs){
            int min = 1000;
            int index = 0;

            // added one should be not equal to last color of houses and minimum
            for(int i=0;i<costs.length;i++){
                if(costs[i]<min && i!=house[0]){
                    min = costs[i];
                    index = i;
                }
            }

            // copy to new array and change house array to new array
            int[] newHouse = new int[len+1];
            for(int i=0;i<len;i++){
                newHouse[i+1] = house[i];
            }
            newHouse[0] = index;
            
            ColorOfHouse re = new ColorOfHouse(newHouse, sum+min, len+1);
            return re;
        }

        ColorOfHouse addRight(int[] costs){
            int min = 1000;
            int index = 0;

            // added one should be not equal to last color of houses and minimum
            for(int i=0;i<costs.length;i++){
                if(costs[i]<min && i!=house[len-1]){
                    min = costs[i];
                    index = i;
                }
            }

            // copy to new array and change house array to new array
            int[] newHouse = new int[len+1];
            for(int i=0;i<len;i++){
                newHouse[i] = house[i];
            }
            newHouse[len] = index;
            
            ColorOfHouse re = new ColorOfHouse(newHouse, sum+min, len+1);
            return re;
        }

        void print()throws IOException{
            System.out.print("Houses' color: ");
            for(int i=0;i<len;i++){
                System.out.print(house[i]+" ");
            }
            System.out.println("\nsum of cost: "+sum);
        }
    }

    // recurssively called
    // result is for memoization
    // left and right is for divide to subproblem
    // costs is got from user's input which is cost of house painting
    static ColorOfHouse calCost(ColorOfHouse[][] result, int left, int right, int[][] costs)throws IOException{
        // String str = String.format("\nleft: %d, right: %d",left,right);
        // System.out.println(str);
        // if there is ColorOfHouse return it
        if(result[left][right] != null){
            // str = String.format("\nresult[%d][%d] is exist",left,right);
            // System.out.println(str);
            return result[left][right];
        // if this function handle smallest subset
        // make colorOfHouse
        }else if(left == right){
            int color = minIndex(costs[left]);
            ColorOfHouse h = new ColorOfHouse(color, costs[left][color]);
            result[left][right] = h;
            // str = String.format("\nresult[%d][%d]'s color: %d, sum: %d",left,right,color,h.sum);
            // System.out.println(str);
            return h;   
        
        }else{
            ColorOfHouse h1 = calCost(result, left, right-1, costs);
            ColorOfHouse h2 = calCost(result, left+1, right, costs);
            // h1.print();
            // h2.print();
            h1 = h1.addRight(costs[right]);
            h2 = h2.addLeft(costs[left]);
            // System.out.println("\nAfter Add");
            // h1.print();
            // h2.print();

            if(h1.sum<h2.sum){
                result[left][right] = h1;
                //str = "h1.sum < h2.sum, So take h1";
            }else{
                result[left][right] = h2;
                // str = "h1.sum >= h2.sum, So take h2";
            }
            // System.out.println(str);
            return result[left][right];
        }
        
    }

    // return minimum's index
    static int minIndex(int[] arr){
        int min = 1000;
        int index = 0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]<min){
                min = arr[i];
                index = i;
            }
        } 

        return index;
    }
}