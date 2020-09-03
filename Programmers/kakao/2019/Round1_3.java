import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import static java.util.Comparator.comparingInt;

class Solution {
    public static void main(String[] args){
        String[][] input = {{"100", "ryan", "music", "2"}, {"200", "apeach", "math", "2"}, {"300", "tube", "computer", "3"}, {"400", "con", "computer", "4"}, {"500", "muzi", "music", "3"}, {"600", "apeach", "music", "2"}};
        Solution sol = new Solution();

        int res = sol.solution(input);
        System.out.println(res);
    }

    public int solution(String[][] relation) {
        int row = relation.length;
        int col = relation[0].length;

        List<List<Integer>> superKeys = getSuperKeys(col, relation);

        List<List<Integer>> candidateKeys = getCandidateKeys(superKeys);

        // System.out.println("super keys");
        // printKeySet(superKeys);
        // System.out.println("candidate keys");
        // printKeySet(candidateKeys);

        return candidateKeys.size();
    }

    private void printKeySet(List<List<Integer>> keySet){
        for(List<Integer> keys : keySet){
            for(int key: keys){
                System.out.printf("%d ", key);
            }
            System.out.println();
        }
    }

    private List<List<Integer>> getCandidateKeys(List<List<Integer>> superKeys){
        List<List<Integer>> candidateKeys = new ArrayList<>(superKeys);

        candidateKeys.sort(comparingInt((List<Integer> keys) -> keys.size()));

        List<Integer> removeIdx = new ArrayList<>();
        for(int i=0;i<candidateKeys.size()-1;i++){
            List<Integer> keys = candidateKeys.get(i);
            for(int j=i+1;j<candidateKeys.size();j++){
                List<Integer> target = candidateKeys.get(j);

                boolean flag = true;
                for(int key : keys){
                    flag &= target.contains(key);
                }

                if(flag)
                    removeIdx.add(j);
            }
        }

        // System.out.println("candidate keys before remove");
        // printKeySet(candidateKeys);

        // for(int idx: removeIdx){
        //     System.out.printf("%d ", idx);
        // }
        // System.out.println();

        Iterator<List<Integer>> it = candidateKeys.iterator();
        for(int i=0, size=candidateKeys.size(); i < size; i++){
            it.next();
            if(removeIdx.contains(i))
                it.remove();
        }

        return candidateKeys;
    }

    private List<List<Integer>> getSuperKeys(int col, String[][] relation){
        List<List<Integer>> superkeys = new ArrayList<>();
        LinkedList<Integer> superKey = new LinkedList<>();

        backtrack(0, col, superKey, relation, superkeys);

        return superkeys;
    }

    private void backtrack(int cur, final int col, LinkedList<Integer> superKey, String[][] relation, List<List<Integer>> superKeys){
        if(cur == col){
            if(isValidSuperKey(superKey, relation))
                superKeys.add(new ArrayList<>(superKey));
            return;
        }

        superKey.addLast(cur);
        backtrack(cur+1, col, superKey, relation, superKeys);
        superKey.removeLast();

        backtrack(cur+1, col, superKey, relation, superKeys);
    }

    private boolean isValidSuperKey(List<Integer> superKey, String[][] relation){
        for(int i=0;i<relation.length-1;i++){
            String[] row = relation[i];
            for(int j=i+1;j<relation.length;j++){
                String[] target = relation[j];

                boolean flag = true;
                for(int attr: superKey){
                    flag &= row[attr].equals(target[attr]);
                }

                if(flag)
                    return false;
            }
        }

        return true;
    }
}