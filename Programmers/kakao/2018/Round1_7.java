import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparingInt;

class Solution {

    public int solution(String[] lines) {
        List<Log> orderedByEnd = Arrays.stream(lines)
                .map(str -> {
                    String[] splited = str.split(" ");
                    return new Log(splited[1], splited[2]);
                })
                .collect(toList());
        
        List<Log> orderedByStart = orderedByEnd.stream()
                .sorted(comparingInt((Log l) -> l.start))
                .collect(toList());
                
        int max = 0;

        for(int i=0;i<orderedByEnd.size();i++){
            int cnt = 0;
            Log tmp = orderedByEnd.get(i);
            for(int j=i;j<orderedByEnd.size();j++){
                if(tmp.end > orderedByEnd.get(j).end || tmp.end+999 < orderedByEnd.get(j).start)
                    continue;
                cnt++;
            }
            max = Math.max(max, cnt);
        }
        
        for(int i=0;i<orderedByStart.size();i++){
            int cnt = 0;
            Log tmp = orderedByStart.get(i);
            for(int j=0;j<=i;j++){
                if(tmp.start < orderedByStart.get(j).start || tmp.start-999 > orderedByStart.get(j).end)
                    continue;
                cnt++;
            }
            max = Math.max(max, cnt);
        }
        
        return max;
    }
}

class Log {
    public int start;
    public int end;
    
    public Log(String end, String duration){
        String[] time = end.split(":");
        String[] sec = time[2].split("\\.");
        this.end = Integer.parseInt(time[0])*60*60*1000;
        this.end += Integer.parseInt(time[1])*60*1000;
        this.end += Integer.parseInt(sec[0]) * 1000;
        this.end += Integer.parseInt(sec[1]);
        
        duration = duration.substring(0, duration.length()-1);
        String[] durSplit = duration.split("\\.");
        int dur = Integer.parseInt(durSplit[0])*1000;
        if(durSplit.length > 1)
            dur += Integer.parseInt(durSplit[1]);
        
        this.start = this.end - dur +1;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("start: ");
        sb.append(this.start);
        sb.append(", end: ");
        sb.append(end);
        
        return sb.toString();
    }
}