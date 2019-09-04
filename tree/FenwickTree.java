/*
    Fenwick Tree of sum
    ref: https://www.acmicpc.net/blog/view/21
    Fenwick Tree is binary indexed tree
    update operation take logN
    sum operation take logN

    To implement fenwick tree you should know number's last 1's order (binary)
    3 = 11      1
    5 = 101     1
    6 = 110     2
    8 = 1000    4

    L[i] means i's last 1 represent
    L[3] = 1
    L[5] = 1
    L[6] = 2
    L[8] = 8
    if there is arr[], tree[i] represet sum of a[i] to (a[i]+L[i]-1)

    1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
    1   3   5   7   9    11    13    15
    --2     --6     --10       ---14
    ------4         --------12
    --------------8 --------------------16
    
    and L[i] is i&-i because
    -num = ~num + 1
     num = 100110101110101100000000000
    ~num = 011001010001010011111111111
    -num = 011001010001010100000000000
    num & -num = 000000000000000100000000000 which is last 1 represet and so L[i]
*/

public class FenwickTree{
    private int[] treeArr;
    private int[] originArr;
    public int height;
    public int length;
    
    public static void main(String[] args){
        int[] arr = new int[16];
        for(int i=0;i<arr.length;i++)
            arr[i] = i+1;

        FenwickTree tree = new FenwickTree(arr);
        tree.print();
        System.out.println(tree.sum(0,8));
        System.out.println(tree.sum(3,5));
        System.out.println(tree.sum(11,16));
    }

    public FenwickTree(int[] arr){
        treeArr = new int[arr.length+1];
        originArr = new int[arr.length+1];
        length = arr.length;
        height = (int)baseLog(length, 2)+1;
        init(arr);
    }

    public void init(int[] arr){
        for(int i=0;i<length;i++){
            update(i+1,arr[i]);
        }
    }

    public void update(int idx, int ch){
        int diff = ch - originArr[idx];
        originArr[idx] = ch;
        __update(idx, diff);
    }

    // update idx node to upper node 
    public void __update(int idx, int diff){
        while(idx <= length){
            // System.out.print(treeArr[idx]+ " ");
            treeArr[idx] += diff;
            idx += (idx & (-idx));
        }
        // System.out.println();
    }

    public int sum(int start, int end){
        return __sum(end) - __sum(start-1);
    }

    public int __sum(int idx){
        int ret = 0;
        while(idx>0){
            ret += treeArr[idx];
            idx -= (idx &(-idx));
        }
        return ret;
    }

    public void print(){
        printLinearly();
    }

    public void printLinearly(){
        for(int i=1;i<=length;i++)
            System.out.print(treeArr[i]+" ");
        System.out.println();
    }

    public static double baseLog(double x, double base){
        return Math.log(x)/Math.log(base);
    }
}