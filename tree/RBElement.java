/*
    Balanced Binary Search Tree
    when you javac or java command should have to write directory name
    https://stackoverflow.com/a/32598172
*/
package baekjoon.tree;

public class RBElement extends Element{
    private Color color;
    private RBElement parent;
    private RBElement lChild;
    private RBElement rChild;

    public RBElement(int key, int value){
        super(key,value);
    }

    public RBElement(int key, int value, Element left, Element right){
        super(key,value,left,right);
    }

    public RBElement(int key, int value, Element left, Element right, Element parent){
        super(key,value,left,right,parent);
    }

    public void setColor(Color c){
        color = c;
    }

    public Color getColor(){
        return color;
    }

    public void setChild(RBElement left, RBElement right){
        lChild = left;
        rChild = right;
    }

    public void setLChild(RBElement left){
        lChild = left;
    }

    public RBElement getLChild(){
        return lChild;
    }

    public void setRChild(RBElement right){
        rChild = right;
    }

    public RBElement getRChild(){
        return rChild;
    }

    public void setParent(RBElement parent){
        this.parent = parent;
    }

    public RBElement getParent(){
        return parent;
    }

    public RBElement getGrandParent(){
        if(parent!=null)
            return parent.getParent();
        else
            return null;
    }

    public RBElement getUncle(){
        RBElement g = getGrandParent();
        if(g==null)
            return null;
        else{
            if(g.getLChild() == parent)
                return g.getRChild();
            else
                return g.getLChild();
        }
    }

    public RBElement getSibling(){
        if(parent.getLChild()==this)
            return parent.getRChild();
        else
            return parent.getLChild();
    }
}