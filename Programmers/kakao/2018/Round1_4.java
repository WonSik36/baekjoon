import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;

class Solution {
    static final int START = 540;   // 09:00
    static final int END = 1439;        // 23:59

    public String solution(int n, int t, int m, String[] timetable) {
        // System.out.printf("n: %d, t: %d, m: %d\n", n,t,m);

        int[] timeTable = Arrays.stream(timetable).mapToInt(x -> timeStr2Int(x)).sorted().toArray();
        
        Queue<Integer> queue = new LinkedList<>();
        for(int time : timeTable){
            queue.add(time);
            // System.out.println(time);
        }
        
        int nIdx = 0;
        int total = 0;
        int answer = 0;
        for(int i=START; i<=END && !queue.isEmpty(); i+=t, nIdx++){
            // System.out.println("Now time is "+ timeInt2Str(i));
            int first = queue.peek();
            
            while(total < m && first <= i){
                first = queue.poll();
                total++;

                if(total == m){
                    answer = first - 1;
                }

                // System.out.println("Crew arrived at "+timeInt2Str(first));
                if(queue.isEmpty())
                    break;

                first = queue.peek();
            }

            if(nIdx >= n-1){
                if(total < m){
                    answer = i;
                }
                break;
            }
                
            total = 0;
        }

        if(nIdx < n - 1){
            return timeInt2Str(START + (n-1)*t);
        }
        
        // System.out.println("Answer is "+timeInt2Str(answer));

        return timeInt2Str(answer);
    }
    
    static int timeStr2Int(String str){
        String[] strArr = str.split(":");
        
        int[] intArr = new int[2];
        
        for(int i=0;i<2;i++){
            if(strArr[i].charAt(0) == '0'){
                intArr[i] = strArr[i].charAt(1) - '0';
            }else{
                intArr[i] = Integer.parseInt(strArr[i]);
            }
        }
        
        return intArr[0]*60 + intArr[1];
    }
    
    static String timeInt2Str(int time){
        int hour = time / 60;
        int min = time % 60;
        
        return String.format("%02d:%02d", hour, min);
    }
}