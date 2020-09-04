import java.lang.StringBuilder;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

class Solution {
    public static void main(String[] args){
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        Solution sol = new Solution();
        int[][] res = sol.solution(nodeinfo);

        for(int[] arr : res){
            for(int node: arr){
                System.out.printf("%d ",node);
            }
            System.out.println();
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        // make list of input and sort
        List<Node> list = IntStream.range(0, nodeinfo.length)
                .mapToObj(i -> new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]))
                .sorted(comparingInt((Node n) -> n.y)
                        .reversed()
                        .thenComparingInt(n -> n.x))
                .collect(toList());
        
        // categorize node by using queue
        Queue<Node> queue = new LinkedList<>();
        queue.add(list.get(0));
        List<Queue<Node>> listOfQueue = new ArrayList<>();
        for(int i=1, level = list.get(0).y;i<list.size();i++){
            Node tmp = list.get(i);
            if(tmp.y == level){
                queue.add(tmp);
            }else{
                listOfQueue.add(queue);
                queue = new LinkedList<>();
                queue.add(tmp);
                level = tmp.y;
            }
        }
        listOfQueue.add(queue);

        // make queue using dfs
        Node root = listOfQueue.get(0).poll();
        dfs(root, 0, 1000000, listOfQueue);

        // prefix order
        List<Integer> preList = new ArrayList<>();
        prefix(root, preList);

        // postfix order
        List<Integer> postList = new ArrayList<>();
        postfix(root, postList);

        int[] prefix = preList.stream().mapToInt(i -> i).toArray();
        int[] postfix = postList.stream().mapToInt(i -> i).toArray();

        int[][] res = new int[2][];
        res[0] = prefix;
        res[1] = postfix;

        return res;
    }

    void prefix(Node cur, List<Integer> list){
        list.add(cur.order);
        
        if(cur.left != null)
            prefix(cur.left, list);

        if(cur.right != null)
            prefix(cur.right, list);
    }

    void postfix(Node cur, List<Integer> list){
        if(cur.left != null)
            postfix(cur.left, list);

        if(cur.right != null)
            postfix(cur.right, list);

        list.add(cur.order);
    }

    void dfs(Node cur, int level, int maxRight, List<Queue<Node>> listOfQueue){
        if(level+1 == listOfQueue.size())
            return;

        Queue<Node> childQ = listOfQueue.get(level+1);
        
        Node l = childQ.peek();
        if(l == null)
            return;

        if(cur.x > l.x){
            childQ.poll();
            dfs(l, level+1, cur.x, listOfQueue);
            cur.left = l;
        }

        Node r = childQ.peek();
        if(r == null)
            return;

        if(cur.x < r.x && r.x < maxRight){
            childQ.poll();
            dfs(r, level+1, maxRight, listOfQueue);
            cur.right = r;
        }
    }
}

class Node{
    public int order;
    public int x;
    public int y;
    public Node left;
    public Node right;

    public Node(int order, int x, int y){
        this.order = order;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("order: ");
        sb.append(order);
        sb.append(" y: ");
        sb.append(y);
        sb.append(" x: ");
        sb.append(x);

        return sb.toString();
    }
}