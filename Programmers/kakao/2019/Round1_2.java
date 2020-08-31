import java.util.List;
import java.util.ArrayList;
import static java.util.Comparator.comparingDouble;

class Solution {

    public static void main(String[] args){
        int N = 4;
        int[] stages = {4,4,4,4,4};

        Solution sol = new Solution();
        int[] res = sol.solution(N, stages);

        for(int r: res){
            System.out.print(r+" ");
        }
        System.out.println();
    }
    public int[] solution(int N, int[] stages) {
        int[] fail= new int[N];
        int[] reach = new int[N];
        
        for(int i=0;i<stages.length;i++){
            for(int j=0;j<stages[i] && j<N;j++){
                reach[j]++;
            }
            if(stages[i]-1 < N)
                fail[stages[i]-1]++;
        }
        
        List<Stage> list = new ArrayList<>();
        for(int i=0;i<N;i++){
            if(reach[i] == 0){
                list.add(new Stage(i+1, 0.0));
                continue;
            }   
            
            list.add(new Stage(i+1, (double)fail[i]/reach[i]));
        }
        
        list.sort(comparingDouble((Stage s) -> s.percent)
                .reversed()
                .thenComparingInt((Stage s) -> s.stage));
        
        int[] res = list.stream().mapToInt((Stage s) -> s.stage).toArray();
        
        return res;
    }
}

class Stage {
    public int stage;
    public double percent;
    
    public Stage(int stage, double percent){
        this.stage = stage;
        this.percent = percent;
    }
}