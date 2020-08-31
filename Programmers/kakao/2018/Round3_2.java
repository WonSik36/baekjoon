import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

class Solution {
    Map<String, Integer> dict;
    int index = 1;
    
    public int[] solution(String msg) {
        StringBuilder sb = new StringBuilder();
        List<Integer> res = new ArrayList<>();
        dict = new HashMap<>();
        
        for(int i=0;i<26;i++){
            sb.append((char)(i+'A'));
            dict.put(sb.toString(), index++);
            sb.deleteCharAt(0);
        }
        
        for(int i=0;i<msg.length();i++){
            sb = new StringBuilder();
            
            int j = i;
            System.out.println("charAt: "+msg.charAt(j));

            while(true){
                if(j >= msg.length()){
                    i = msg.length();
                    res.add(dict.get(sb.toString()));
                    break;
                }

                sb.append(msg.charAt(j++));
                
                if(!dict.containsKey(sb.toString())){
                    System.out.printf("Map add: <%s,%d>\n", sb.toString(), index);
                    dict.put(sb.toString(), index++);

                    int last = sb.length()-1;

                    i += last-1;
                    sb.deleteCharAt(last);
                    res.add(dict.get(sb.toString()));
                    
                    break;
                }
            }
        }
            
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}