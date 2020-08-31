import java.lang.Comparable;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public String[] solution(String[] files) {
        int N = files.length;
        List<File> list = new ArrayList<>();
        for(String file : files){
            list.add(new File(file));
        }
        
        for(int i=N-1;i>=0;i--){
            for(int j=0; j<i;j++){
                if(list.get(j).compareTo(list.get(j+1)) > 0)
                    swap(j,j+1,list);
            }
        }
        
        // for(File f : list){
        //     System.out.println(f);
        // }
        
        return list.stream().map((File f) -> (f.origin)).toArray(String[]::new);
    }
    
    void swap(int a,int b,List<File> list){
        File tmp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, tmp);
    }
}

class File implements Comparable<File>{
    public String origin;
    private String head;
    private String number;
    private String tail;
    
    public File(String origin){
        this.origin = origin;
        parse(origin);
    }
    
    private void parse(String origin){
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for(;idx<origin.length();idx++){
            char ch = origin.charAt(idx);
            
            if(!(ch >= '0' && ch <= '9')){
                sb.append(ch);
            }else{
                break;
            }
        }
        this.head = sb.toString();
        
        sb = new StringBuilder();
        for(; idx<origin.length();idx++){
            char ch = origin.charAt(idx);
            
            if(ch >= '0' && ch <= '9'){
                sb.append(ch);
            }else{
                break;
            }
        }
        this.number = sb.toString();
        
        sb = new StringBuilder();
        for(; idx<origin.length();idx++){
            char ch = origin.charAt(idx);
            sb.append(ch);
        }
        
        if(sb.length() > 0)
            this.tail = sb.toString();
    }
    
    @Override
    public int compareTo(File that){
        String thisHead = this.head.toLowerCase();
        String thatHead = that.head.toLowerCase();
        
        int c = thisHead.compareTo(thatHead);
        
        if(c != 0)
            return c;
        
        int thisNumber = Integer.parseInt(this.number);
        int thatNumber = Integer.parseInt(that.number);
        
        if(thisNumber < thatNumber){
            return -1;
        }else if(thisNumber > thatNumber){
            return 1;
        }else{
            return 0;
        }

    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("origin: ");
        sb.append(origin);
        sb.append(", head: ");
        sb.append(head);
        sb.append(", number: ");
        sb.append(number);
        sb.append(", tail: ");
        if(tail != null)
            sb.append(tail);

        return sb.toString();
    }
}