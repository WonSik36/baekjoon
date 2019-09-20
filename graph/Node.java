package baekjoon.graph;

public class Node{
    private int nodeNum;
    private int weight;
    private Node next;

    public Node(int nodeNum, int weight){
        this.nodeNum = nodeNum;
        this.weight = weight;
        next = null;
    }

    Node(int nodeNum, int weight, Node next){
        this(nodeNum, weight);
        this.next = next;
    }

    public int getNodeNum(){
        return nodeNum;
    }

    public void setNodeNum(int nodeNum){
        this.nodeNum = nodeNum;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node next){
        this.next = next;
    }
}