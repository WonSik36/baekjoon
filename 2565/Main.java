/*
    baekjoon online judge
    problem number 2565
    https://www.acmicpc.net/problem/2565
    LIS problem
    look at 11053
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
        WirePole wp = new WirePole();

        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            StringTokenizer st = new StringTokenizer(inputStr);
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            wp.addWire(left, right);
        }
        
        // wire number - LIS number
        int result = wp.len - wp.getLISLen();
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }

    static class Wire{
        int left;
        int right;
        Wire next;

        Wire(int left, int right){
            this.left = left;
            this.right = right;
            next = null;
        }

        void setNext(Wire w){
            next = w;
        }

        Wire getNext(){
            return next;
        }
    }

    static class WirePole{
        Wire head;
        int len;

        WirePole(){
            head = new Wire(0,0);
            len = 0;
        }

        // add wire sortedly by wire's left position
        void addWire(int left, int right){
            Wire before = head;
            Wire cur = head.getNext();
            Wire newWire = new Wire(left, right);

            while(cur!=null){
                if(newWire.left < cur.left)
                    break;
                before = cur;
                cur = cur.getNext();
            }

            before.setNext(newWire);
            newWire.setNext(cur);
            len++;
        }

        // get LIS length by criteria of wire's right position
        int getLISLen(){
            int[] Rarr = new int[len];
            Wire cur = head.getNext();
            for(int i=0;i<len;i++){
                Rarr[i] = cur.right;
                cur = cur.getNext();
            }

            int[] copy = new int[len];
            int length = 0;
            for(int i=0;i<len;i++){
                int idx = getLowerBound(copy, length, Rarr[i]);
                copy[idx] = Rarr[i];
                //LIS[i] = idx;
                if(idx == length)
                    length++;   
            }

            return length;
        }
    }

    // it uses binary search algorithm
    static int getLowerBound(int[] arr, int len, int target){
        if(len == 0)
            return 0;
        int[] copy = Arrays.copyOfRange(arr, 0, len);
        int first = 0;
        int last = len-1;
        int mid = 0;

        while(first <= last){
            mid = (first+last)/2;
            if(copy[mid] == target)
                return mid;
            else if(copy[mid]>target)
                last = mid-1;
            else
                first = mid+1;
            
        }
        return first;   // if target is larget than arr[n] and smaller than arr[n+1] it return n+1
        //if target is larger than all element in the array than return array length
    }
}
