import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    static final int TYPE_ENTER = 1;
    static final int TYPE_LEAVE = 2;
    
    static final String ENTER = "님이 들어왔습니다.";
    static final String LEAVE = "님이 나갔습니다.";
    
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        List<Record> list = new ArrayList<>();
        
        for(int i=0;i<record.length;i++){
            String[] token = record[i].split(" ");
            
            switch(token[0]){
                case "Enter":
                    list.add(new Record(1, token[1]));
                    map.put(token[1],token[2]);
                    break;
                    
                case "Leave":
                    list.add(new Record(2, token[1]));
                    break;
                        
                case "Change":
                    map.put(token[1], token[2]);
            }
        }
        
        String[] res = new String[list.size()];
        for(int i=0;i<list.size();i++){
            Record cur = list.get(i);
            
            if(cur.type == 1){
                res[i] = map.get(cur.uid)+ENTER;
            }else{
                res[i] = map.get(cur.uid)+LEAVE;
            }
        }
        
        return res;
    }
}

class Record{
    public int type;
    public String uid;
    
    public Record(int type, String uid){
        this.type = type;
        this.uid = uid;
    }
}