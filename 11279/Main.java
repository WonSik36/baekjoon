/*
    baekjoon online judge
    problem number 11279
    https://www.acmicpc.net/problem/11279
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.Comparator;
import java.lang.Comparable;
import java.lang.ClassCastException;

public class Main{
    static boolean DEBUG = false;
    public static void main(String[] args)throws IOException{
        if(args.length!=0 && args[0].equals("-d"))
            DEBUG = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            int input = Integer.parseInt(br.readLine());
            if(input == 0){
                int ret = (pq.peek() == null)?0:pq.poll();
                bw.write(Integer.toString(ret)+"\n");
            }else    
                pq.add(input);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void print(int num){
        if(DEBUG)
            System.out.println(num);
    }

    public static void print(String str){
        if(DEBUG)
            System.out.print(str);
    }

    public static void print(String str, int... ints){
        if(DEBUG){
            String[] spl = str.split("%d");
            String output = spl[0];
            int idx = 1;
            for(int number:ints){
                output += number;
                if(idx < spl.length)
                    output += spl[idx++];
            }
            System.out.print(output);
        }
    }

    public static void printArray(int[] arr){
        if(DEBUG){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        }
    }

    public static class PriorityQueue<E extends Comparable<E>>{
        private static final int INIT_SIZE = 16;
        private int size;
        private int sizeOfArr;
        private Object[] arr;
        private Comparator<? super E> comparator; 
    
        public PriorityQueue(){
            size = 0;
            sizeOfArr = INIT_SIZE;
            arr = new Object[sizeOfArr+1];
            comparator = null;
        }
    
        public PriorityQueue(Comparator<? super E> comparator){
            this();
            this.comparator = comparator;
        }
    
        @SuppressWarnings("unchecked")
        public void add(E e){
            if(size >= sizeOfArr)
                doubleSizeOfArr();
            arr[++size] = e;
            int idx = size;
            while(idx > 1){
                int pidx = getParent(idx);
                int comp = compare(e, (E)arr[pidx]);
    
                if(comp == 1){
                    arr[idx] = arr[pidx];
                    idx = pidx;
                }else
                    break;
            }
            arr[idx] = e;
        }
    
        @SuppressWarnings("unchecked")
        public E peek(){
            if(isEmpty()) return (E)null;
            return (E)arr[1];
        }
    
        @SuppressWarnings("unchecked")
        public E poll(){
            if(isEmpty()) return (E)null;
            if(size <= sizeOfArr/4)
                halfSizeOfArr();
            E ret = (E)arr[1];
            E e = (E)arr[size];
            arr[1] = arr[size];
            size--;
    
            int idx = 1;
            int cidx = 0;
            while(idx < size && ((cidx = getMaxChildIdx(idx)) != -1)){
                int comp = compare(e,(E)arr[cidx]);
                if(comp == -1){
                    arr[idx] = arr[cidx];
                    idx = cidx;
                }else
                    break;
            }
            arr[idx] = e;
            return ret;
        }
    
        public int size(){
            return size;
        }
    
        public boolean isEmpty(){
            return (size == 0)?true:false;
        }
    
        public Object[] toArray(){
            Object[] ret = new Object[size];
            for(int i=0;i<size;i++){
                ret[i] = arr[i+1];
            }
            return ret;
        }
    
        private void doubleSizeOfArr(){
            sizeOfArr *= 2;
            Object[] newArr = new Object[sizeOfArr+1];
            for(int i=1;i<=size;i++){
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
    
        private void halfSizeOfArr(){
            if(sizeOfArr <= INIT_SIZE)
                return;
            sizeOfArr /= 2;
            Object[] newArr = new Object[sizeOfArr+1];
            for(int i=1;i<=size;i++){
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
    
        private int getParent(int idx){
            return idx/2;
        }
    
        private int getLchild(int idx){
            return idx*2;
        }
    
        private int getRchild(int idx){
            return idx*2+1;
        }
        
        @SuppressWarnings("unchecked")
        private int getMaxChildIdx(int idx){
            if(getLchild(idx)>size)
                return -1;
            else if(getRchild(idx)>size)
                return getLchild(idx);
            else{
                int comp = compare((E)arr[getLchild(idx)], (E)arr[getRchild(idx)]);
                if(comp == 1)
                    return getLchild(idx);
                else
                    return getRchild(idx);
            }
        }
    
        private int compare(E e1, E e2){
            if(comparator == null){
                return e1.compareTo(e2);
            }else{
                return comparator.compare(e1, e2);
            }
        }
    }
}
