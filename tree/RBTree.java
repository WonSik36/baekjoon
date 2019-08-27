/*
    Balanced Binary Search Tree
    ref: https://zeddios.tistory.com/237

    1.Root Property: root node color is black
    2.External Property: every external node is black
    3.Internal Property: red node's chid is black
    4.Depth Property: Every leaf node has same black depth
    ** do not insert same key
    To execute this program
    1. cd C:\Users\wonsik\Documents
    2. javac .\baekjoon\tree\RBElement.java .\baekjoon\tree\Element.java .\baekjoon\tree\Color.java .\baekjoon\tree\RBTree.java
    3. java baekjoon.tree.RBTree
*/
package baekjoon.tree;

import java.io.IOException;

public class RBTree{
    private RBElement root;

    public static void main(String[] args)throws IOException{
        RBTree tree = new RBTree();
        tree.insert(1, 0);
        tree.insert(2, 0);
        tree.insert(5, 0);
        tree.insert(3, 0);
        tree.insert(4, 0);
        
        tree.printInOrderTraversal();
        System.out.println(tree.depth(tree.root));
    }

    public RBTree(){
        root = null;
    }

    public void insert(int key, int value){
        // find lacation where input element locate
        RBElement e = root;
        RBElement p = null;
        RBElement input = new RBElement(key,value,null,null,null);
        input.setColor(Color.RED);

        while(e!=null){
            int cmp = e.compareTo(input);
            if(cmp==1){
                p = e;
                e = e.getLChild();
            }
            else if(cmp==-1){
                p = e;
                e = e.getRChild();
            }
        }

        if(p==null){
            // input is root node
            root = input;
            insertCase1(input);
        }
        else{
            if(p.compareTo(input)==1)
                p.setLChild(input);
            else
                p.setRChild(input);
            input.setParent(p);
            insertCase2(input);
        }
    }

    // case1: there is no element in tree
    public void insertCase1(RBElement e){
        if(e.getParent() == null){
            e.setColor(Color.BLACK);
            return;
        }
        else
            insertCase2(e);
    }

    // case2: parent node color is black
    public void insertCase2(RBElement e){
        if(e.getParent().getColor() == Color.BLACK)
            return;
        else
            insertCase3(e);
    }

    // case3: parent node and uncle node color is red
    public void insertCase3(RBElement e){
        RBElement u = e.getUncle();
        if(u!=null && u.getColor()==Color.RED){
            e.getParent().setColor(Color.BLACK);
            u.setColor(Color.BLACK);
            RBElement g = e.getGrandParent();
            g.setColor(Color.RED);
            // recursively check down to up
            insertCase1(g);
        }else
            insertCase4(e);
    }

    /* case4: parent node color is red but uncle color is black
       and tree is under state
            g               g
           / \             / \
          p   u           u   p
           \                 /
            e               e

    */
    public void insertCase4(RBElement e){
        RBElement p = e.getParent();
        RBElement g = e.getGrandParent();
        if(e==p.getRChild() && p==g.getLChild()){
            rotateRR(e.getParent());
            e = e.getLChild();
        }
        else if(e==p.getLChild() && p==g.getRChild()){
            rotateLL(e.getParent());
            e = e.getRChild();
        }
        
        insertCase5(e);
    }

    /* case5: parent node color is red but uncle color is black
       and tree is under state
            g               g
           / \             / \
          p   u           u   p
         /                     \
        e                       e

    */
    public void insertCase5(RBElement e){
        RBElement p = e.getParent();
        RBElement g = e.getGrandParent();
        p.setColor(Color.BLACK);
        g.setColor(Color.RED);
        if(e == p.getLChild())
            rotateLL(g);
        else
            rotateRR(g);
        if(root == g)
            root = g.getParent();
    }

    public int delete(int key){return 1;}

    public int search(int key){
        RBElement e = root;
        RBElement target = new RBElement(key, 0);

        while(e!=null){
            if(e.getKey() == key)
                break;
            int cmp = e.compareTo(target);
            if(cmp==1)
                e = e.getLChild();
            else if(cmp==-1)
                e = e.getRChild();
        }

        if(e==null)
            return -1;
        else
            return e.getValue();
    }

    /* tree is under state
        e
         \
          c
           \ 
            cc
    */
    public void rotateRR(RBElement e){
        RBElement p = e.getParent();
        RBElement c = e.getRChild();
        if(p!=null){
            if(p.getLChild() == e)
                p.setLChild(c);
            else
                p.setRChild(c);
        }
        c.setParent(p);
        e.setRChild(c.getLChild());
        if(c.getLChild()!=null)
            c.getLChild().setParent(e);
        
        c.setLChild(e);
        e.setParent(c);
    }

    /* tree is under state
            e
           /
          c
         / 
        cc
    */
    public void rotateLL(RBElement e){
        RBElement p = e.getParent();
        RBElement c = e.getLChild();
        if(p!=null){
            if(p.getLChild() == e)
                p.setLChild(c);
            else
                p.setRChild(c);
        }
        c.setParent(p);
        e.setLChild(c.getRChild());
        if(c.getRChild()!=null)
            c.getRChild().setParent(e);
        
        c.setRChild(e);
        e.setParent(c);
    }

    public void printInOrderTraversal()throws IOException{
        // print key
        System.out.print("Key: ");
        printInOrderTraversalKey(root);
        System.out.println();

        // print color
        System.out.print("Color: ");
        printInOrderTraversalColor(root);
        System.out.println();
    }

    // print key by In-order traversal
    public void printInOrderTraversalKey(RBElement e)throws IOException{
        if(e == null)
            return;
        printInOrderTraversalKey(e.getLChild());
        System.out.print(e.getKey()+" ");
        printInOrderTraversalKey(e.getRChild());
    }

    // print color by In-order traversal
    public void printInOrderTraversalColor(RBElement e)throws IOException{
        if(e == null)
            return;
        printInOrderTraversalColor(e.getLChild());
        System.out.print(e.getColor()+" ");
        printInOrderTraversalColor(e.getRChild());
    }

    public int depth(RBElement e){
        if(e==null)
            return 0;
        int left = depth(e.getLChild());
        int right = depth(e.getRChild());
        return left>right?(left+1):(right+1);
    }
}