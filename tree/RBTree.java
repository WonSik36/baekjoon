/*
    Balanced Binary Search Tree
    ref: https://en.wikipedia.org/wiki/Red%E2%80%93black_tree

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
import java.util.StringTokenizer;

public class RBTree{
    private RBElement root;

    public static void main(String[] args)throws IOException{
        RBTree tree = new RBTree();
        for(int i=1;i<=100;i++)
            tree.insert(i,i);
        for(int i=1;3*i<=100;i++)
            tree.delete(3*i);


        tree.printPreOrder();
        System.out.println(tree.depth());
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

    public int delete(int key)throws IOException{
        // find node which has input key
        RBElement e = findElement(root, key);
        // there is no node that has input key
        if(e==null)
            return -1;
        // there is node that has input key
        else{
            int value = e.getValue();
            // replace has replaceable node which has 1 or 0 child
            RBElement replace = (RBElement)RBElement.findReplaceableNode(e);
            // e's children are both leaf node
            if(replace == null){
                deleteNodeHas2Leaf(e);
            }else{
                // e has a child that is not leaf node
                e.copy(replace);
                // replace's children are both leaf node
                if(replace.getLChild()==null && replace.getRChild()==null){
                    deleteNodeHas2Leaf(replace);
                // replace has 1 child that is not leaf node                   
                }else{
                    RBElement child;
                    if(replace.getLChild()!=null)
                        child = replace.getLChild();
                    else
                        child = replace.getRChild();
                    // replace replace node with it's child node
                    RBElement p = replace.getParent();
                    child.setParent(p);
                    if(p.getLChild() == replace)
                        p.setLChild(child);
                    
                    else
                        p.setRChild(child);
                    
                    deleteCase0(child);
                }
            }
            deleteLeafNode();
            return value;
        }
    }

    // case0: e is red and parent is black
    public void deleteCase0(RBElement e)throws IOException{
        // System.out.println("case 0");
        RBElement p = e.getParent();
        // if replace node color is red than it doesn't matter
        // but replace node color is black than it need to take a look
        if(p.getColor() == Color.BLACK){
            // child color is red than change it to black
            if(e.getColor() == Color.RED)
                e.setColor(Color.BLACK);
            // replace color and child color are both red than take a look
            else
                deleteCase1(e);
        }
    }

    // case1: node is root node than it acts nothing
    // first time it pass because e's previous parent has parent
    // but it recursively called by deleteCase3
    public void deleteCase1(RBElement e)throws IOException{
        // System.out.println("case 1");
        if(e.getParent() != null)
            deleteCase2(e);
        // else
            // System.out.println("activate1");
    }

    // case2: e's sibling is red
    // if case2 occur than case4 automatically occur
    public void deleteCase2(RBElement e)throws IOException{
        // System.out.println("case 2");
        RBElement p = e.getParent();
        RBElement s = e.getSibling();

        if(s.getColor() == Color.RED){
            // System.out.println("activate2");
            p.setColor(Color.RED);
            s.setColor(Color.BLACK);
            if(e == p.getLChild())
                rotateRR(p);
            else
                rotateLL(p);
            if(p==root)
                root = s;
        }
        deleteCase3(e);
    }

    // case3: parent & sibling & sibling's both children are black
    public void deleteCase3(RBElement e)throws IOException{
        // System.out.println("case 3");
        RBElement p = e.getParent();
        RBElement s = e.getSibling();

        if(p.getColor() == Color.BLACK && s.getColor() == Color.BLACK &&
        ((s.getLChild() == null && s.getRChild() == null)
        ||((s.getLChild()!=null && s.getLChild().getColor() == Color.BLACK) 
        && (s.getRChild()!=null && s.getRChild().getColor() == Color.BLACK)))){
            // System.out.println("activate3");
            s.setColor(Color.RED);
            deleteCase1(p);
        }else
            deleteCase4(e);
    }

    // case4: parent is red but siblig and sibling's both children are black
    // if case2 occur than case4 automatically occur
    public void deleteCase4(RBElement e)throws IOException{
        // System.out.println("case 4");
        RBElement p = e.getParent();
        RBElement s = e.getSibling();
    
        if(p.getColor() == Color.RED && s.getColor() == Color.BLACK &&
        ((s.getLChild() == null && s.getRChild() == null)
        ||((s.getLChild()!=null && s.getLChild().getColor() == Color.BLACK) 
        && (s.getRChild()!=null && s.getRChild().getColor() == Color.BLACK)))){
            p.setColor(Color.BLACK);
            s.setColor(Color.RED);
            // System.out.println("activate4");
        }else
            deleteCase5(e);
    }

    // case5: sibling is black and its child has different color
    // if case5 occur than case6 automatically occur
    public void deleteCase5(RBElement e)throws IOException{
        // System.out.println("case 5");
        RBElement p = e.getParent();
        RBElement s = e.getSibling();

        if(e == p.getLChild() && (s.getLChild()!=null && s.getLChild().getColor() == Color.RED) && 
        ((s.getRChild() == null)||(s.getRChild().getColor() == Color.BLACK))){
            // System.out.println("activate5");
            s.setColor(Color.RED);
            s.getLChild().setColor(Color.BLACK);
            rotateLL(s);
        }else if(e == p.getRChild() && (s.getRChild()!= null && s.getRChild().getColor() == Color.RED)
            &&((s.getLChild() == null) || (s.getLChild().getColor() == Color.BLACK ))){
            // System.out.println("activate5");
            s.setColor(Color.RED);
            s.getRChild().setColor(Color.BLACK);
            rotateRR(s);
        }
        deleteCase6(e);
    }

    // case6: if e is left than sibling's right child is red
    //        or e is right than sibling's left child is red
    // if case5 occur than case6 automatically occur
    public void deleteCase6(RBElement e)throws IOException{
        // System.out.println("case 6");
        RBElement p = e.getParent();
        RBElement s = e.getSibling();
        if(e == p.getLChild()){
            // System.out.println("activate6");
            s.getRChild().setColor(Color.BLACK);
            rotateRR(p);
        }else{
            // System.out.println("activate6");
            s.getLChild().setColor(Color.BLACK);
            rotateLL(p);
        }
        if(p==root)
            root = s;
    }

    // delete a node which has two leaf node
    public void deleteNodeHas2Leaf(RBElement e)throws IOException{
        // System.out.println("case -1");
        // e is root node and only node in the tree
        if(e==root)
            root=null;
        else{
            e.setKey(-1);
            deleteCase0(e);
        }
    }

    public void deleteLeafNode()throws IOException{
        RBElement e = findAllNode(root, -1);
        if(e == null)
            return;
        // System.out.println("delete leaf node");
        RBElement p = e.getParent();
        if(p.getLChild() == e)
        p.setLChild(null);
        else
        p.setRChild(null);
    }

    public RBElement findAllNode(RBElement e, int key){
        return __findAllNode(root, key);
    }

    public RBElement __findAllNode(RBElement e, int key){
        if(e==null)
            return null;
        RBElement a = __findAllNode(e.getLChild(), key);
        RBElement b = __findAllNode(e.getRChild(), key);

        if(e.getKey() == key)
            return e;
        else if(a!=null)
            return a;
        else if(b!=null)
            return b;
        return null;
    }

    public int search(int key){
        RBElement e = findElement(root, key);

        if(e==null)
            return -1;
        else
            return e.getValue();
    }

    public RBElement findElement(RBElement e, int key){
        RBElement idx = e;
        RBElement target = new RBElement(key, 0);
        // find node which has input key
        while(idx!=null){
            int cmp = idx.compareTo(target);
            if(cmp==1)
                idx = idx.getLChild();
            else if(cmp==-1)
                idx = idx.getRChild();
            else
                break;
        }
        return idx;
    }

    /* tree is under RR state
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

    /* tree is under LL state
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

    public void printPreOrder()throws IOException{
        // print key
        System.out.print("Key: ");
        printKeyPreOrder(root);
        System.out.println();

        // print color
        System.out.print("Color: ");
        printColorPreOrder(root);
        System.out.println();

        // print parent
        System.out.print("Parent: ");
        printParentPreOrder(root);
        System.out.println();
    }

    // print key by pre-order traversal
    public void printKeyPreOrder(RBElement e)throws IOException{
        if(e == null)
            return;
        System.out.print(e.getKey()+" ");
        printKeyPreOrder(e.getLChild());
        printKeyPreOrder(e.getRChild());
    }

    // print color by pre-order traversal
    public void printColorPreOrder(RBElement e)throws IOException{
        if(e == null)
            return;
        System.out.print(e.getColor()+" ");
        printColorPreOrder(e.getLChild());
        printColorPreOrder(e.getRChild());
    }

    // print color by pre-order traversal
    public void printParentPreOrder(RBElement e)throws IOException{
        if(e == null)
            return;
        if(e.getParent() == null)
            System.out.print("-1 ");
        else
            System.out.print(e.getParent().getKey()+" ");
        printParentPreOrder(e.getLChild());
        printParentPreOrder(e.getRChild());
    }

    public int depth(){
        return __depth(root);
    }

    public int __depth(RBElement e){
        if(e==null)
            return 0;
        int left = __depth(e.getLChild());
        int right = __depth(e.getRChild());
        return left>right?(left+1):(right+1);
    }
}