import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.StringBuilder;

class Solution {
    static final int MEDIATE = 65536;

    public int solution(String str1, String str2) {
        List<String> set1 = getSetFromStr(str1);
        List<String> set2 = getSetFromStr(str2);
        
        if(set1.size() == 0 && set2.size() == 0)
            return MEDIATE;
        
        if(set1.size() == 0 || set2.size() == 0)
            return 0;
        
        int inter = interSet(set1, set2);
        // System.out.println("inter: "+inter);
        int merge = mergeSet(set1.size(), set2.size(), inter);
        // System.out.println("merge: "+merge);
        return MEDIATE * inter / merge;
    }

    static void printList(List<String> list){
        System.out.println("Set:");
        for(String str : list){
            System.out.println(str);
        }
    }
    
    static int interSet(final List<String> set1, final List<String> set2){
        Collections.sort(set1);
        Collections.sort(set2);

        // printList(set1);
        // printList(set2);
        
        int cnt = 0;
        for(int l=0,r=0; r < set2.size(); r++){
            String set2Str = set2.get(r);
            // System.out.printf("r: %s\n", set2Str);
            while(l < set1.size() && set1.get(l).compareTo(set2Str) < 0){
                // System.out.printf("l: %s\n", set1.get(l));
                l++;
            }

            if(l < set1.size() && set1.get(l).equals(set2Str)){
                cnt++;
                l++;
                // System.out.println("matched");
            }
        }
        
        return cnt;
    }
    
    static int mergeSet(int A, int B, int inter){
        return A + B - inter;
    }
    
    static List<String> getSetFromStr(String str){
        List<String> res = new ArrayList<>();
        char[] arr = str.toLowerCase().toCharArray();
        
        for(int i=0;i<arr.length-1;i++){
            
            if(isAlpha(arr[i]) && isAlpha(arr[i+1])){
                StringBuilder sb = new StringBuilder();
                sb.append(arr[i]);
                sb.append(arr[i+1]);
                res.add(sb.toString());
            }
        }
        
        return res;
    }
    
    static boolean isAlpha(char ch){
        if(ch >= 'a' && ch <= 'z')
            return true;
        return false;
    }
}