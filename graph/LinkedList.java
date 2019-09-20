/*
    To execute this program
    1. cd C:\Users\wonsik\Documents
    2. javac .\baekjoon\graph\Node.java .\baekjoon\graph\LinkedList.java
    3. java baekjoon.graph.LinkedList
*/

package baekjoon.graph;

import java.util.Iterator;
import java.lang.Iterable;

public class LinkedList implements Iterable<Node>{
    private int size;
    private Node head;

    public static void main(String[] args){
        LinkedList list = new LinkedList();

        // test1
        System.out.println("Test1 start");
        list.addEdge(1, 1);
        list.addEdge(2, 2);
        list.addEdge(3, 3);
        list.addEdge(4, 4);
        list.addEdge(5, 5);

        System.out.print(list.toString());
        System.out.println("Test1 complete\n");

        // test2
        System.out.println("Test2 start");
        Iterator it = list.iterator();
        while(it.hasNext()){
            System.out.println(((Node)it.next()).getNodeNum());
        }
        System.out.println("Test2 complete\n");

        // test3
        System.out.println("Test3 start");
        it = list.iterator();
        while(it.hasNext()){
            int nodeNum = ((Node)it.next()).getNodeNum();
            if(nodeNum%2 == 1)
                it.remove();
        }
        System.out.print(list.toString());
        System.out.println("Test3 complete\n");

        // test4
        System.out.println("Test4 start");
        it = list.iterator();
        try{
            it.remove();
        }catch(RuntimeException e){
            it = null;
        }
        if(it == null)
            System.out.println("Test4 complete\n");
        else
            System.out.println("Test4 failed\n");
    }

    public LinkedList(){
        this.size = 0;
        this.head = null;
    }

    public void addEdge(int nodeNum, int weight){
        Node newNode = new Node(nodeNum, weight);

        if(isEmpty()){
            head = newNode;
            size++;
            return;
        }

        Node prev = head;
        Node cur = head.getNext();

        while(cur!=null){
            prev = cur;
            cur = cur.getNext();
        }
        prev.setNext(newNode);
        size++;
    }

    public boolean isEmpty(){
        return (size==0)?true:false;
    }

    public int size(){
        return size;
    }

    public Iterator<Node> iterator(){
        return new Iterator<Node>(){
            Node prev = null;
            Node cur = null;
            Node next = head;

            public boolean hasNext(){
                if(next == null)
                    return false;
                return true;
            }
            public Node next(){
                prev = cur;
                cur = next;
                next = next.getNext();
                return cur;
            }
            public void remove(){
                if(prev == cur)
                    throw new RuntimeException("remove twice in a row");
                if(cur==head){
                    head = next;
                    cur = head;
                    next = next.getNext();
                }else{
                    prev.setNext(next);
                    cur = prev;
                }
            }
        };
    }

    public String toString(){
        String ret = "";
        Node cur = head;

        while(cur != null){
            String str = String.format("Node#: %d, Weight: %d\n",cur.getNodeNum(),cur.getWeight());
            ret += str;
            cur = cur.getNext();
        }

        return ret;
    }
}