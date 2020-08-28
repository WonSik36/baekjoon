class Solution {
    public static void main(String[] args){
        String[] input = {"go", "gone", "guild"};
        Solution sol = new Solution();
        System.out.println(sol.solution(input));
    }

    public int solution(String[] words) {
        Node root = new Node(0);
        root.skip = false;
        for(int i=0;i<words.length;i++){
            root.add(words[i]);
        }

        // root.print();

        int sum = 0;
        for(int i=0;i<words.length;i++){
            int num = root.find(words[i]);
            // System.out.println(num);
            sum += num;
        }

        return sum;
    }
}

class Node {
    Node[] children;
    int totalChild;
    int level;
    boolean skip;

    public Node(int level){
        this.children = new Node[26];
        this.totalChild = 0;
        this.level = level;
        this.skip = true;
    }
    
    public void add(String str){
        Node cur = this;

        for(int i=0;i<str.length();i++){
            int key = str.charAt(i) - 'a';

            if(cur.children[key] == null)
                cur.children[key] = new Node(cur.level+1);
            else
                cur.children[key].skip = false;

            cur.totalChild++;
            cur = cur.children[key];
        }
    }

    public int find(String str){
        Node cur = this;

        for(int i=0;i<str.length();i++){
            if(cur.skip)
                return i;
            
            int key = str.charAt(i) - 'a';

            cur = cur.children[key];
        }

        return str.length();
    }

    public void print(){
        for(int i=0;i<26;i++){
            if(this.children[i] == null)
                continue;
            
            for(int j=0;j<this.level;j++)
                System.out.print("-");
            System.out.println((char)(i+'a'));
            this.children[i].print();
        }
    }
}