import java.lang.StringBuilder;

class Solution {
    public String solution(int n, int t, int m, int p) {        
        StringBuilder answer = new StringBuilder();
        StringBuilder all = new StringBuilder();
        // make all
        for(int i=0;i<t*m;i++){
            all.append(decimalToNJinSu(i, n));
        }
        // System.out.println(all.toString());
        
        int mod = convertOrderToMod(m,p);
        // System.out.println(mod);
        
        for(int i=0;i<all.length();i++){
            if(i % m == mod){
                answer.append(all.charAt(i));
                if(answer.length() == t)
                    break;
            }
                
        }
        
        return answer.toString();
    }
    
    static int convertOrderToMod(int m, int p){
        return p - 1;
    }
    
    static String decimalToNJinSu(int num, int N){
        if(num == 0)
            return "0";
        
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            int r = num % N;
            if(r < 10){
                sb.insert(0, r);    
            }else{
                char ch = (char)(r - 10 + 'A');
                sb.insert(0, ch);
            }
            
            num /= N;
        }
        
        return sb.toString();
    }
}