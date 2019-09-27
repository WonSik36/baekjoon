
public class Practice{
    public static void main(String[] args){

        for(int N=1;N<=100;N++){
            int[] arr = new int[(N/5+1)*2+1];
            System.out.println(((N/5+1)*2+1));
            for(int i=0;i<arr.length-2;i++){
                arr[i+2] = i+2;
            }
        }
    }
}