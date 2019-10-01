//@EXPECTED_RESULTS@: CORRECT
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.io.FileReader;
import java.io.FileWriter;


public class Main {
	public static void main(String[] args)throws Exception{
        new Main().run();
    }
	
	
    int[] getArray(String line){
        String[] array=line.split(" ");
        int[] res=new int[array.length];
        for(int i=0;i<res.length;i++)
            res[i]=Integer.parseInt(array[i]);
        return res;
    }
    
    void getArrays(int n, BufferedReader reader, int[]... ar) throws Exception{
        for(int i=0;i<n;i++){
            String[] ss=reader.readLine().split(" ");
            for(int j=0;j<ss.length;j++)
                ar[j][i]=Integer.parseInt(ss[j]);
        }
     }
    
    class Edge{
    	int to;
    	long cost;
		public Edge(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
    }
    List<Edge>[] iniGraph(int[] from, int[] to, int[] cost, int[] profits){
    	int n=profits.length+1;
    	List<Edge>[] graph=new List[n];
    	for(int i=0;i<n;graph[i++]=new ArrayList<Edge>());
    	for(int i=0;i<from.length;i++){
    		//System.out.println(n+" "+from[i]);
    		graph[from[i]].add(new Edge(to[i], profits[to[i]-1]-cost[i]));
    	}
    	return graph;
    }
    
    List<Integer> foundWay;
    long[] visited;
    int[] next;
   long makeDFS(List<Edge>[] graph){
    	visited=new long[graph.length];
    	Arrays.fill(visited, Long.MIN_VALUE);
    	next=new int[graph.length];
    	Arrays.fill(next, -1);
    	return dfs(graph,0);
    }
   
   List<Integer> makeWay(int start){
	   List<Integer> list=new LinkedList<Integer>();
	   while(start!=-1){
		   list.add(start);
		   start=next[start];   
	   }
	   return list;
   }
   
   long dfs(List<Edge>[] graph, int curent){
	   visited[curent]=0L;
	   next[curent]=-1;
	   for(Edge e:graph[curent]){
		   long best=visited[e.to];
	        if(best==Long.MIN_VALUE)
			   best=dfs(graph, e.to);
	        best+=e.cost;
	        if(best>visited[curent]){
				   visited[curent]=best;
				   next[curent]=e.to;
			   }
	   }
	   return visited[curent];
	   
   }
   

	
	 void withStreams(BufferedReader reader, PrintWriter writer) throws Exception{
		 int t=Integer.parseInt(reader.readLine());
	        for(int i=0;i<t;i++){
	        	String[] ss=reader.readLine().split(" ");
	        	int n=Integer.parseInt(ss[0]);
	        	int m=Integer.parseInt(ss[1]);
	        	int[] profits=getArray(reader.readLine());
	        	
	        	int[] start=new int[m+1];
	        	int[] end=new int[m+1];
	        	int[] cost=new int[m+1];
	        	

	        	getArrays(m, reader, start, end, cost);
	        	
	        	//add node 0 - the real start!
	        	start[m]=0;
	        	end[m]=1;
	        	cost[m]=0;
	        	
	        	long profit=makeDFS(iniGraph(start, end, cost, profits));
	        	List<Integer> way=makeWay(0);
	        	writer.println(profit+" "+(way.size()-1));
	            int cnt=-1;
	            for(int cave:way){
	            	cnt++;
	            	if(cnt==0) continue;
	            	if(cnt!=1) writer.print(" ");
	            	writer.print(cave);
	            }
	            writer.println();
	        }
	 }
	 
	 void run() throws Exception{
	        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
			// BufferedReader reader = new BufferedReader(new FileReader("./antiBF.in"));
        	// PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("../2.out")));
	        withStreams(reader, writer);
	        writer.flush();
	        writer.close();
	        reader.close();
	    }

}

