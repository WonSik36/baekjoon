class Solution {
    public int solution(String dartResult) {
        int result = 0;
        int[] res = new int[3];
        
        for(int i=0, rIdx=0;i<dartResult.length();i++){
            if(dartResult.charAt(i) >= '0' && dartResult.charAt(i) <= '9'){
                if(dartResult.charAt(i+1) == '0'){
                    res[rIdx] = 10;
                    rIdx++;
                    i++;
                    continue;
                }
                    
                res[rIdx] = dartResult.charAt(i) - '0';
                rIdx++;
            }else if(dartResult.charAt(i) == '*'){
                if(rIdx >= 2)
                    res[rIdx-2] *= 2;
                res[rIdx-1] *= 2;
            }else if(dartResult.charAt(i) == '#'){
                res[rIdx-1] *= -1;
            }else{
                switch(dartResult.charAt(i)){
                    case 'S':
                        break;
                    case 'D':
                        res[rIdx-1] = res[rIdx-1]*res[rIdx-1];
                        break;
                    case 'T':
                        res[rIdx-1] = res[rIdx-1]*res[rIdx-1]*res[rIdx-1];
                        break;
                }
            }
        }
        
        for(int r : res){
            System.out.println(r);
            result += r;
        }
        return result;
    }
}