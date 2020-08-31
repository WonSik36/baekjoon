import java.lang.StringBuilder;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] res = new String[n];
        
        for(int i=0;i<n;i++){
            StringBuilder line = new StringBuilder();
            int a1 = arr1[i];
            int a2 = arr2[i];
            
            for(int j=0;j<n;j++, a1/=2, a2/=2){
                if(a1 % 2 == 0 && a2 % 2 == 0){
                    line.insert(0, ' ');
                }else{
                    line.insert(0, '#');
                } 
            }
            
            res[i] = line.toString();
        }
        
        return res;
    }
}