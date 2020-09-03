import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparingInt;

class Solution {
    public static void main(String[] args){
        int[] food_times = {1,1,1,1};
        long k = 4;

        Solution sol = new Solution();
        int res = sol.solution(food_times, k);

        System.out.println(res);
    }

    public int solution(int[] food_times, long k) {
        List<Food> list = IntStream.range(0, food_times.length)
            .mapToObj(idx -> new Food(idx, food_times[idx]))
            .sorted(comparingInt((Food f) -> f.count))
            .collect(toList());

        List<Food> lastIteration = new ArrayList<>();
        for(int i=0, prev=0; i<list.size(); i++){
            if(list.get(i).count == prev)
                continue;

            long iter = ((long)list.get(i).count - prev) * (list.size() - i);

            if(k >= iter){
                k -= iter;
            }else{
                for(int j=i;j<list.size();j++){
                    lastIteration.add(list.get(j));
                }
                break;
            }
            prev = list.get(i).count;
        }

        if(lastIteration.size() == 0)
            return -1;
        
        while(k > lastIteration.size()){
            k -= lastIteration.size();
        }

        lastIteration.sort(comparingInt((Food f) -> f.order));
        // printList(lastIteration);
        int res = -1;
        for(int i=0; k>=0; i=(i+1)%lastIteration.size()){
            if(k == 0){
                res = lastIteration.get(i).order;
                break;
            }

            k--;
        }

        return res + 1;
    }

    private void printList(List<Food> list){
        for(Food f: list){
            System.out.println(f.toString());
        }
    }
}

class Food {
    public int order;
    public int count;

    public Food(int order, int count){
        this.order = order;
        this.count = count;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Order: ");
        sb.append(order);
        sb.append(" Count: ");
        sb.append(count);

        return sb.toString();
    }
}