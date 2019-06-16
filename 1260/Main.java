/*
    baekjoon online judge
    problem number 1260
    https://www.acmicpc.net/problem/1260
*/
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Arrays;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String inputStr = br.readLine();
        StringTokenizer st = new StringTokenizer(inputStr);
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        
        MyGraph gr = new MyGraph(v, e);

        for(int i=0;i<e; i++){
            inputStr = br.readLine();
            st = new StringTokenizer(inputStr);
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            gr.addEdge(first, second);
        }
        //gr.printGraph();
        gr.DFS(start);
        gr.BFS(start);
    }

    static class MyGraph{
        private int vertexNum;
        private int edgeNum;
        private MyVertex[] vertex;
        private boolean[] flag;

        public MyGraph(int vertex, int edge){
            this.vertexNum = vertex;
            this.edgeNum = 0;
            this.vertex = new MyVertex[vertex+1];
            for(int i=0;i<vertex+1;i++)
                this.vertex[i] = new MyVertex();
            this.flag = new boolean[vertex+1];
        }

        public void addEdge(int first, int second){
            vertex[first].addAscending(second);
            vertex[second].addAscending(first);
            edgeNum++;
        }

        public int getVertexNum(){
            return vertexNum;
        }

        public void printGraph()throws IOException{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for(int i=1; i<=vertexNum; i++){
                String str = String.format("Node[%d]: ",i);
                bw.write(str);
                for(int j=0; j<vertex[i].size();j++){
                    str = String.format("%d ",vertex[i].get(j));
                    bw.write(str);
                }
                bw.write("\n");
            }
            bw.flush();
        }

        public void DFS(int start)throws IOException{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            Arrays.fill(flag, false);
            MyStack st = new MyStack();
            
            st.push(start);            
            while(!st.isEmpty()){
                int v = st.pop();
                if(flag[v] == true)
                    continue;
                flag[v] = true;
                bw.write(Integer.toString(v)+" ");

                int size = vertex[v].size();
                for(int i=size-1;i>=0;i--){
                    int num = vertex[v].get(i);
                        st.push(num);
                }
            }
            bw.write("\n");
            bw.flush();
        }

        public void BFS(int start)throws IOException{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            Arrays.fill(flag, false);
            MyQueue qu = new MyQueue();

            qu.push(start);
            while(!qu.isEmpty()){
                int v = qu.pop();
                if(flag[v] == true)
                    continue;
                flag[v] = true;
                bw.write(Integer.toString(v)+" ");

                int size = vertex[v].size();
                for(int i=0;i<size;i++){
                    int num = vertex[v].get(i);
                        qu.push(num);
                }
            }
            bw.write("\n");
            bw.flush();
        }
    }

    static class MyVertex extends LinkedList<Integer>{
        public void addAscending(int o){
            if(this.isEmpty())
                this.add(o);
            else if(this.contains(o));
            else{
                int size = this.size();
                for(int i=0; i<this.size(); i++){
                    if(this.get(i) > o){
                        this.add(i, o);
                        break;
                    }
                }
                //add at last order
                if(this.size() == size)
                    this.add(size, o);
            }
        }
    }

    static class MyQueue{
        private int num;
        private MyElement head;
        private MyElement tail;

        public MyQueue(){
            num = 0;
            head = null;
            tail = null;
        }

        public void push(int n){
            MyElement newElement = new MyElement(n, tail, null);
            if(isEmpty())
                head = newElement;
            else{
                tail.next = newElement;
            }
            tail = newElement;
            num++;
        }

        public boolean isEmpty(){
            if(num == 0)
                return true;
            else
                return false;
        }

        public int front(){
            if(isEmpty())
                return -1;
            else
                return head.getValue();
        }

        public int back(){
            if(isEmpty())
                return -1;
            else
                return tail.getValue();
        }

        public int pop(){
            if(isEmpty())
                return -1;
            else{
                int re = head.getValue();
                head = head.getNext();
                num--;
                if(!isEmpty())
                    head.setPrev(null);
                return re;
            }
        }

        public int size(){
            return num;
        }
    }

    static class MyStack{
        private int num;
        private MyElement head;

        public MyStack(){
            num = 0;
            head = null;
        }

        public void push(int n){
            MyElement newElement = new MyElement(n, head);
            head = newElement;
            num++;
        }

        public boolean isEmpty(){
            if(num == 0)
                return true;
            else
                return false;
        }

        public int peep(){
            if(isEmpty())
                return -1;
            else
                return head.getValue();
        }

        public int pop(){
            if(isEmpty())
                return -1;
            else{
                int re = head.getValue();
                this.head = head.getNext();
                num--;
                return re;
            }
        }

        public int size(){
            return num;
        }
    }

    static class MyElement{
        private int v;
        private MyElement next;
        private MyElement prev;

        public MyElement(int n, MyElement prev, MyElement next){
            v = n;
            this.prev = prev;
            this.next = next;
        }

        public MyElement(int n, MyElement next){
            v = n;
            this.prev = null;
            this.next = next;
        }

        public MyElement getNext(){
            return next;
        }

        public void setNext(MyElement e){
            next = e;
        }

        public MyElement getPrev(){
            return prev;
        }

        public void setPrev(MyElement e){
            prev = e;
        }

        public int getValue(){
            return v;
        }

        public void setValue(int n){
            v = n;
        }
    }
}
