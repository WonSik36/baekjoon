/*
    baekjoon online judge
    problem number 1966
    https://www.acmicpc.net/problem/1966
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputStr = br.readLine();
        int num = Integer.parseInt(inputStr);
                
        for(int i=0;i<num;i++){
            inputStr = br.readLine();
            String[] arr = inputStr.split(" ");
            int N = Integer.parseInt(arr[0]);
            int M = Integer.parseInt(arr[1]);
            PrintQueue pq = new PrintQueue(N, M);

            inputStr = br.readLine();
            arr = inputStr.split(" ");
            pq.setPrintArray(Arrays.stream(arr).mapToInt(Integer::parseInt).toArray());
            //pq.printArray();
            int re = pq.findOrder();

            bw.write(Integer.toString(re)+"\n");
        }
        bw.flush();
        bw.close(); 
    }

    public static class PrintQueue{
        private int[] priority;
        private int[] arr;
        private int length;
        private int target;
        private int cur;

        public PrintQueue(int len, int target){
            priority = new int[10];
            arr = new int[len];
            length = len;
            this.target = target;
            cur = -1;
        }

        public void setPrintArray(int[] arr){
            System.arraycopy(arr, 0, this.arr, 0, arr.length);
            for(int i=0;i<arr.length;i++){
                priority[arr[i]]++;
            }
        }

        public int findOrder()throws IOException{
            int count = 0;
            for(int i=9; i>0; i--){
                //System.out.println("Priority["+i+"]: "+priority[i]);
                while(priority[i] > 0){
                    if(next() == i){
                        //System.out.println("cur:"+cur+" arr[cur]:"+arr[cur]);
                        priority[i]--;
                        count++;
                        if(cur == target)
                            return count;
                    }
                }
            }
            return -1;
        }

        public int next(){
            if(cur == length-1){
                cur = 0;
                return arr[cur];
            }
            else
                return arr[++cur];
        }

        public void printArray()throws IOException{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            for(int i=0;i<length;i++){
                bw.write(Integer.toString(arr[i])+" ");
            }
            bw.write("\n");
            bw.flush();
        }
    }
}
