import java.util.Comparator;
import java.lang.Comparable;
import java.lang.ClassCastException;

public class PriorityQueue<E extends Comparable<E>>{
    private static boolean DEBUG = false;
    private static final int INIT_SIZE = 4;
    private int size;
    private int sizeOfArr;
    private Object[] arr;
    private Comparator<? super E> comparator; 

    public static void main(String[] args){
        // test1: E should extends comparable
        PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>();
        System.out.println("Integer succeed");

        PriorityQueue<String> pq2 = new PriorityQueue<String>();
        System.out.println("String succeed");

        // test2: PriorityQueue can get Comparator
        PriorityQueue<Person> pq3 = new PriorityQueue<Person>(
            new Comparator<Person>() {
                @Override
                public int compare(Person p1, Person p2){
                    if(p1.age > p2.age)
                        return 1;
                    else if(p1.age == p2.age)
                        return 0;
                    else
                        return -1;
                }
            });
        System.out.println("Person succeed");

        // test3: add test & double size test
        pq1.add(1);
        pq1.add(3);
        pq1.add(5);
        pq1.add(4);
        pq1.add(9);
        pq1.add(1);
        pq1.add(3);
        pq1.add(10);

        // test4: poll test & half size test
        System.out.println(pq1.poll());
        System.out.println(pq1.poll());
        System.out.println(pq1.poll());
        System.out.println(pq1.poll());
        System.out.println(pq1.poll());
        System.out.println(pq1.poll());
        System.out.println(pq1.poll());
        System.out.println(pq1.poll());

        // test5: comparator test
        pq3.add(new Person(2,"herry"));
        pq3.add(new Person(4,"fox"));
        pq3.add(new Person(8,"aloeah"));
        pq3.add(new Person(6,"kirin"));
        pq3.add(new Person(10,"looksford"));
        pq3.add(new Person(1,"hqfnv"));
        pq3.add(new Person(3,"g3r"));
        pq3.add(new Person(1,"123r"));

        System.out.println(pq3.poll());
        System.out.println(pq3.poll());
        System.out.println(pq3.poll());
        System.out.println(pq3.poll());
        System.out.println(pq3.poll());
        System.out.println(pq3.poll());
        System.out.println(pq3.poll());
        System.out.println(pq3.poll());
    }

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
        printArray(toArray());
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
            // System.out.printf("arr[%d]:%d, arr[%d]:%d, comp: %d\n",idx,arr[idx],cidx,arr[cidx],comp);
            if(comp == -1){
                arr[idx] = arr[cidx];
                idx = cidx;
            }else
                break;
        }
        arr[idx] = e;
        printArray(toArray());
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
        print("Double Size of Array because current size is ");
        print(size());
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
        print("Half Size of Array because current size is ");
        print(size());
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

    public static class Person implements Comparable<Person>{
        int age;
        String name;

        Person(){
            age = 10;
            name = null;
        }

        Person(int age, String name){
            this.age = age;
            this.name = name;
        }

        @Override
        public int compareTo(Person p){
            return this.name.compareTo(p.name);
        }

        @Override
        public String toString(){
            return "Name: "+this.name +" age: "+this.age+"\n";
        }

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

    public static void printArray(Object[] arr){
        if(DEBUG){
            for(int i=0;i<arr.length;i++){
                System.out.print(arr[i].toString()+" ");
            }
            System.out.println();
        }
    }

}