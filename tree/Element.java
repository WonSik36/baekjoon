/*
    Balanced Binary Search Tree
*/
package baekjoon.tree;

import java.io.IOException;

public class Element{
    private int key;
    private int value;
    private Element parent;
    private Element lChild;
    private Element rChild;

    public Element(int key, int value){
        this.key = key;
        this.value = value;
    }

    public Element(int key, int value, Element left, Element right){
        this(key,value);
        setChild(left, right);
    }

    public Element(int key, int value, Element left, Element right, Element parent){
        this(key,value);
        setChild(left, right);
        setParent(parent);
    }

    public void setKey(int key){
        this.key = key;
    }

    public int getKey(){
        return key;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public void setChild(Element left, Element right){
        lChild = left;
        rChild = right;
    }

    public void setLChild(Element left){
        lChild = left;
    }

    public Element getLChild(){
        return lChild;
    }

    public void setRChild(Element right){
        rChild = right;
    }

    public Element getRChild(){
        return rChild;
    }

    public void setParent(Element parent){
        this.parent = parent;
    }

    public Element getParent(){
        return parent;
    }

    public int compareTo(Element e){
        if(this.key>e.getKey())
            return 1;
        else if(this.key == e.getKey())
            return 0;
        else
            return -1;
    }

    public void copy(Element e){
        this.key = e.key;
        this.value = e.value;
    }

    // when delete function execute
    // and when there is a target node to delete
    // than it has to be replaced by right down or up node(key value)
    // and under function find replaceable node
    public static Element findReplaceableNode(Element e){
        Element replace;
        if(e.getRChild() != null){
            replace = e.getRChild();
            while(replace.getLChild()!=null)
                replace = replace.getLChild();
        }else if(e.getLChild() != null){
            replace = e.getLChild();
            while(replace.getRChild()!=null)
                replace = replace.getRChild();
        }else{
            return null;
        }
        return replace;
    }
}