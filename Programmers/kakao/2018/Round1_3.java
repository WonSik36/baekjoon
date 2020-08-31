import java.util.LinkedList;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        LinkedList<String> cache = new LinkedList<>();
        int res = 0;
        for(int i=0;i<cities.length;i++){
            String str = cities[i].toLowerCase();
            // hit
            if(cache.contains(str)){
                res += 1;
                
                cache.remove(str);
            // miss
            }else{
                res += 5;
            }
            
            cache.add(str);
            if(cache.size() > cacheSize){
                cache.poll();
            }
        }
        
        
        return res;
    }
}