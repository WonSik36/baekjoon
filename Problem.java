class Solution {
    public static void main(String[] args){
        String[] input = {"aaaaa", "aaaab", "aaabb", "aabbb", "abbbb","bbbbb","aaabc","aabcc"};
        Solution sol = new Solution();
        System.out.println(sol.solution(input));
    }

    public int solution(String[] words) {
        Node root = new Node(0);
        for(int i=0;i<words.length;i++){
            root.add(words[i]);
        }

        int sum = 0;
        for(int i=0;i<words.length;i++){
            int num = root.find(words[i]);
            System.out.println(num);
            sum += num;
        }

        return sum;
    }
}

class Node {
    Node[] children;
    int totalChild;
    boolean last;
    int level;
    
    public Node(int level){
        this.children = new Node[26];
        this.totalChild = 0;
        this.last = false;
        this.level = level;
    }

    public int find(String word){
        if(word.length() == this.level)
            return 0;

        int key = word.charAt(this.level) - 'a';
        // if(this.level!=0 && totalChild==1 && !this.last){
        if(totalChild==1 && !this.last){
            return children[key].find(word);
        }else{
            return children[key].find(word) + 1;
        }
    }
    
    public void add(String word){
        if(word.length() == this.level){
            this.last = true;
            return;
        }

        int key = word.charAt(this.level) - 'a';
        if(this.children[key] == null){
            this.children[key] = new Node(this.level+1);
        }
        this.totalChild++;

        children[key].add(word);
    }
}