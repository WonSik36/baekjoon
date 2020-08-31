import java.lang.StringBuilder;
import java.util.List;
import java.util.Arrays;
import java.util.StringTokenizer;
import static java.util.stream.Collectors.toList;

class Solution {
    public String solution(String m, String[] musicInfo) {
        m = MusicInfo.parseContent(m);
        int[] fail = getFail(m);
        List<MusicInfo> list = Arrays.stream(musicInfo).map(MusicInfo::parseString).collect(toList());

        MusicInfo res = null;
        for(MusicInfo music : list){
            // System.out.printf("Compare %s to %s\n", m, music.content);
            if(match(music.content, m, fail)){
                if(res==null || res.length < music.length)
                    res = music;
            }
        }

        if(res == null)
            return "(None)";
        return res.title;
    }

    boolean match(String T, String P, int[] fail){

        for(int i=0,j=0;i<T.length();i++){
            while(j>0 && T.charAt(i) != P.charAt(j))
                j = fail[j-1];

            if(T.charAt(i) == P.charAt(j)){
                if(j == P.length()-1)
                    return true;
                else
                    j++;
            }
        }

        return false;
    }

    int[] getFail(String P){
        int[] fail = new int[P.length()];

        for(int i=1, j=0;i<fail.length;i++){
            while(j>0 && P.charAt(i) != P.charAt(j))
                j = fail[j-1];

            if(P.charAt(i) == P.charAt(j))
                fail[i] = ++j;
        }

        return fail;
    }
}

class MusicInfo {
    public int length;
    public String title;
    public String content;

    public MusicInfo(int length, String title, String content){
        this.length = length;
        this.title = title;
        this.content = content;
    }

    public static MusicInfo parseString(String info){
        StringTokenizer st = new StringTokenizer(info, ",");
        int start = parseTime(st.nextToken());
        int end = parseTime(st.nextToken());
        int length = end - start;

        String title = st.nextToken();
        String content = parseContent(st.nextToken());

        if(content.length() < length){
            StringBuilder sb = new StringBuilder(content);
            while(sb.length() < length){
                sb.append(content);
            }

            content = sb.toString();
        }else if(content.length() > length){
            content = content.substring(0, length);
        }

        return new MusicInfo(end-start, title, content);
    }

    static int parseTime(String str){
        StringTokenizer st = new StringTokenizer(str, ":");

        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        return h*60+m;
    }

    static String parseContent(String content){
        content = content.replace("C#", "H");
        content = content.replace("D#", "I");
        content = content.replace("F#", "J");
        content = content.replace("G#", "K");
        content = content.replace("A#", "L");

        return content;
    }
}