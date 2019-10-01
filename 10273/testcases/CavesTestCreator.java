
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;


public class CavesTestCreator {
	public static void main(String[] args) throws Exception{
		new CavesTestCreator().run();
	}
	
	final int MAXNODECNT=20000;
	final int MAXEDGECNT=100000;
	final int MAXCOST=10000;
	
	void run() throws Exception{
		
		produceFile(new TestCase[]{notIn1()},"notIn1");
		System.out.println("done notInOne");
		
		
		//produceFile(new TestCase[]{antiDAG(), antiDAGBig()},"antiDAG");
		//System.out.println("done antiDAG");

		//produceFile(new TestCase[]{antiDAG2()},"antiDAG2");
		//System.out.println("done antiDAG2");

		TestCase tc_antiBF[] = new TestCase[]{
			antiBF(1), // antiBF(branches)
			antiBF(2),
			antiBF(3),
			antiBFRandomNodeNr(23), // antiBFRandomNodeNr(seed)
			antiBFRandomNodeNr(42),
		};
		TestCase abfme=antiBFMultiEdge();

		produceFile(tc_antiBF, "antiBF");
		System.out.println("done antiBF");

		produceFile(new TestCase[]{abfme},"antiBFMultiEdge");
		System.out.println("done antiBFMultiEdge");
		
		Random r=new Random(2);
		for (int i = 0; i < tc_antiBF.length; i++)
			Collections.shuffle(tc_antiBF[i].edges, r);
		produceFile(tc_antiBF, "antiBFShuffled");
		System.out.println("done antiBFShuffled");

		Collections.shuffle(abfme.edges, r);
		produceFile(new TestCase[]{abfme},"antiBFMultiEdgeShuffled");
		System.out.println("done antiBFMultiEdgeShuffled");
		
		
		//produceFile(new TestCase[]{cavesAbove1()}, "cavesAbove1");
		
		
		//produceFile(new TestCase[]{getMultiLine(new int[]{1,2,3}), getMultiLine(new int[]{3,2,1}), getMultiLine(new int[]{2,1,3}),  getMultiLine(new int[]{2,3,1}), getMultiLine(new int[]{3,1,2}),},"multiedges");
		//System.out.println("done multiline");
		
		//produceFile(new TestCase[]{getMaxEdges(2, MAXEDGECNT)}, "maxEdges");
		//System.out.println("done mEdges");
		
		produceFile(new TestCase[]{getMaxNodes(3)}, "maxNodes");
		System.out.println("done mNodes");
		
		//produceFile(new TestCase[]{getMaxNodesMaxEdges(4)}, "maxNodesEdges");
		//System.out.println("done mNodesEdges");
		
		//produceFile(new TestCase[]{getMaxEdges(2, MAXEDGECNT)}, "maxEdges");
		//System.out.println("done mEdges");
		
		int seed=1;
		produceFile(getRandomCases(10, seed++, 10, 20, 2, 4),"randomSmallSparse");
		produceFile(getRandomCases(10, seed++, 10, 20, 2, 4),"randomSmallSparse2");	
		//System.out.println("done random1");
		
		produceFile(getRandomCases(10, seed++, 10, 20, 20, 40),"randomSmallDense");	
		produceFile(getRandomCases(10, seed++, 10, 20, 20, 40),"randomSmallDense2");	
		//System.out.println("done random2");
		
		produceFile(getRandomCases(10, seed++, 100, 400, 20, 40),"randomN200Sparse");	
		produceFile(getRandomCases(10, seed++, 100, 400, 20, 40),"randomN200Sparse2");	
		//System.out.println("done random3");
		
		
		produceFile(getRandomCases(10, seed++, 100, 400, 50, 100),"randomN200Dense");
		produceFile(getRandomCases(10, seed++, 100, 400, 50, 100),"randomN200Dense2");
		produceFile(getRandomCases(10, seed++, 100, 400, 50, 100),"randomN200Dense3");
		//System.out.println("done random3");
		
		
		produceFile(getRandomCases(2, seed++, 1000, 5000, 10, 20),"randomMiddleSparse");	
		System.out.println("done random3");
		
		produceFile(getRandomCases(2, seed++, 1000, 5000, 5, 15),"randomMiddleSparse2");	
		System.out.println("done random4");
		
		//produceFile(getRandomCases(3, seed++, 1000, 10000, 500, 1000),"randomMiddleDense");	
		//System.out.println("done random4");
		
		produceFile(getRandomCases(10, seed++, 9000, 10000, 2, 3),"randomLargeSparse");	
		produceFile(getRandomCases(5, seed++, 9000, 10000, 4, 6),"randomLargeSparse2");	
		
		System.out.println("done random5");
		
		System.out.println("done!");
		}
	
	TestCase notIn1(){
		int[] profits=new int[]{0,0,1};
		List<int[]> edges=new LinkedList<int[]>();
		edges.add(new int[]{0,1,0});
		edges.add(new int[]{1,2,0});

	 
	    return new TestCase(profits, edges, 1);
		
	}
	
	
	TestCase cavesAbove1(){
		int n=10;
		int[] profits=new int[n];
		Arrays.fill(profits, 10);
		List<int[]> edges=new LinkedList<int[]>();
		 for(int i=0;i<5;i++)
			 edges.add(new int[]{i,i+1,0});
		 edges.add(new int[]{n-1,0,0});
		 
		 return new TestCase(profits, edges, 60);
	}
	
	TestCase antiDAG2(){
		int[] profits=new int[]{10, 10, 10, 10, 10, 10};
		List<int[]> edges=new LinkedList<int[]>();
		edges.add(new int[]{0,1,8});
		edges.add(new int[]{1,2,9});
		edges.add(new int[]{3,1,0});
		edges.add(new int[]{3,2,0});
		edges.add(new int[]{1,4,9});
		edges.add(new int[]{2,4,0});
		edges.add(new int[]{4,5,0});
		

		return new TestCase(profits, edges, 31);	
	}
	
	TestCase antiDAG(){
		int[] profits=new int[]{10, 10, 10, 10, 10, 10};
		List<int[]> edges=new LinkedList<int[]>();
		edges.add(new int[]{0,1,3});
		edges.add(new int[]{1,2,3});
		edges.add(new int[]{2,3,0});
		edges.add(new int[]{5,4,0});
		edges.add(new int[]{4,3,0});
		

		return new TestCase(profits, edges, 34);
	}
	
	TestCase antiDAGBig(){
		int n=1000;
		int[] profits=new int[n];
		Arrays.fill(profits, 10);
		List<int[]> edges=new LinkedList<int[]>();
		for(int i=0;i<100;i++)
			edges.add(new int[]{i,i+1, 0});
		
		for(int i=100;i<n-1;i++)
			edges.add(new int[]{i+1, i, 0});
		
		return new TestCase(profits, edges, 1010);
	
	}
	
	TestCase antiBF(int branches){
		int depth = (MAXNODECNT-1) / branches;
		int[] profits=new int[depth * branches + 1];
		Arrays.fill(profits, MAXCOST);
		List<int[]> edges=new LinkedList<int[]>();

		for (int j=depth; j>1; --j) {
			for (int b = 0; b < branches; ++b) {
				int i = depth * b + j;
				edges.add(new int[]{i-1,i,0});
			}
		}

		for (int b = 0; b < branches; ++b)
			edges.add(new int[]{0, (depth*b)+1, 0});

		return new TestCase(profits, edges, (depth+1)*MAXCOST);
	}

	TestCase antiBFRandomNodeNr(int seed){
		Random r=new Random(seed);
		ArrayList<Integer> nodenr = new ArrayList<Integer>();
		for (int i = 1; i < MAXNODECNT; i++) {
			nodenr.add(i);
		}
		Collections.shuffle(nodenr, r);
		nodenr.add(nodenr.get(0));
		nodenr.set(0, 0);

		int[] profits=new int[MAXNODECNT];
		Arrays.fill(profits, MAXCOST);
		List<int[]> edges=new LinkedList<int[]>();
		for(int i=MAXNODECNT-1;i>=1;--i)
		  edges.add(new int[]{nodenr.get(i-1),nodenr.get(i),0});

		return new TestCase(profits, edges, MAXNODECNT*MAXCOST);
	}

	TestCase antiBFMultiEdge(){
		final int multi = MAXEDGECNT / MAXNODECNT;
		int[] profits=new int[MAXNODECNT];
		Arrays.fill(profits, MAXCOST);
		List<int[]> edges=new LinkedList<int[]>();
		for(int i=MAXNODECNT-1;i>=1;--i)
			for (int me = multi-1; me >= 0; --me)
				edges.add(new int[]{i-1,i,me});

		return new TestCase(profits, edges, MAXNODECNT*MAXCOST);
	}
	
	TestCase getMultiLine(int[] costs){
		int[] profits=new int[]{10, 10, 10, 10};
		List<int[]> edges=new LinkedList<int[]>();
		edges.add(new int[]{0,1,10});
		edges.add(new int[]{1,2,costs[0]});
		edges.add(new int[]{1,2,costs[1]});
		edges.add(new int[]{1,2,costs[2]});
		edges.add(new int[]{2,3,2});
		
		int min=Math.min(costs[0], Math.min(costs[1], costs[2]));

		return new TestCase(profits, edges, 28-min);
	}
	
	int[] randomProfits(int n, Random r){
		int[] profits=new int[n];
		for(int i=0;i<n;i++)
			profits[i]=r.nextInt(MAXCOST+1);
		return profits;
	}
	
	
	
	TestCase getMaxEdges(int seed, int m){
		Random r=new Random(seed);
		List<int[]>edges=new LinkedList<int[]>();
		int n=1;
		while(m>0){
			n++;
			for(int i=0;i<n-1&& 0<m--;i++)
				edges.add(new int[]{i,n-1, r.nextInt(MAXCOST+1)});
		}
		int[] profits=randomProfits(n, r);
		
		return new TestCase(profits, edges,findProfit(edges, profits));
	}
	
	
	TestCase getMaxNodes(int seed){
		Random r=new Random(seed);
		int[] profits=randomProfits(MAXNODECNT, r);
		List<int[]> edges=new LinkedList<int[]>();
		for(int i=0;i<MAXNODECNT-2;i++){
			edges.add(new int[]{i,i+1,r.nextInt(MAXCOST+1)});
			edges.add(new int[]{i, i+2, r.nextInt(MAXCOST+1)});
		}
		return new TestCase(profits, edges, findProfit(edges, profits));
		
	}
	

	
	
	TestCase getMaxNodesMaxEdges(int seed){
		Random r=new Random(seed);
		int[] profits=randomProfits(MAXNODECNT, r);
		List<int[]> edges=new LinkedList<int[]>();
		int m=MAXEDGECNT;
		for(int i=0;i<MAXNODECNT;i++){
			for(int j=1;j<20 && 0<m--;j++){
				edges.add(new int[]{i,i+j,r.nextInt(MAXCOST+1)});
			}		
		}
		return new TestCase(profits, edges, findProfit(edges, profits));
		
	}
	
	
	
	String path="";

	
	void produceFile(TestCase[] cases, String fileName) throws Exception{
		String fileInput=path+fileName+".in";
		String fileOutput=path+fileName+".out";
		PrintWriter printerInput = new  PrintWriter(new BufferedWriter(new FileWriter(fileInput)));

		
		printerInput.print(cases.length+"\n");
		for(TestCase tc: cases){
			printerInput.print(tc.profits.length+" "+tc.edges.size()+"\n");
			int c=0;
			for(int x:tc.profits)
				if(0==c++)
					printerInput.print(x);
				else
					printerInput.print(" "+x);
			printerInput.print("\n");
			for(int[] e:tc.edges)
				printerInput.print((e[0]+1)+" "+(e[1]+1)+" "+e[2]+"\n");
			
		}
printerInput.close();
		//print solution:

		BufferedReader reader=new BufferedReader(new FileReader(fileInput));
		PrintWriter printerOutput = new  PrintWriter( new BufferedWriter(new FileWriter(fileOutput)));
		
	    new CavesEgor().withStreams(reader, printerOutput);
	    reader.close();

		printerOutput.close();
		
	}
	

	TestCase[] getRandomCases(int cnt, int seed,int minN, int maxN, int minM, int maxM){
		return getRandomCases(cnt, seed, minN, maxN, minM, maxM, false);
	}
	
	TestCase[] getRandomCases(int cnt, int seed,int minN, int maxN, int minM, int maxM, boolean shuffle){
		TestCase[] res=new TestCase[cnt];
		Random r=new Random(seed);
		while(cnt>0){
			TestCase tc=getRandomTestCase(r, minN, maxN, minM, minM);
			if(shuffle)
			   Collections.shuffle(tc.edges, r);
			res[--cnt]=tc;
		}
		return res;
	}
	
	TestCase getRandomTestCase(Random r, int minN, int maxN, int minM, int maxM){
		return getRandomTestCase(r, minN, maxN, minM, maxM, true);
	}
	
	TestCase getRandomTestCase(Random r, int minN, int maxN, int minM, int maxM, boolean everyReachableFromOne){
		int n=minN+r.nextInt(maxN-minN+1);
		
		//graph
		List<Integer> list=new LinkedList<Integer>();
		for(int i=1;i<n;i++)
			list.add(i);
		Collections.shuffle(list,r);
		list.add(0,0);
		Integer[] map=list.toArray(new Integer[0]);
		
		List<int[]> edges=new LinkedList<int[]>();
		
		 
		for(int i=0;i<n;i++){
			if(i!=0 && everyReachableFromOne) 
				edges.add(new int[]{map[0], map[i], r.nextInt(MAXCOST+1)});
		    maxM=Math.max(minM, Math.min(maxM, n-i+1));
		    int mFromNode=minM+r.nextInt(maxM-minM+1);
		    int current=0;
		    if(i!=0){
		    	for(int k=0; k<mFromNode;k++){
		    		int step=1+r.nextInt((n-i)/mFromNode+1);
		    		current=step+current;
		    		//System.out.println(n+" "+map.length+" "+(i+current));
		    		if(i+current>=n) break;
		    		edges.add(new int[]{map[i], map[i+current], r.nextInt(MAXCOST+1)});
		    	}
		    }
		}
		//profit:
		int[] profits=new int[n];
		for(int i=0;i<n;i++)
			profits[i]=r.nextInt(MAXCOST+1);
		
		//result

		long profit=findProfit(edges, profits);
		return new TestCase(profits, edges, profit);
	}
	
	long findProfit(List<int[]> edges, int[] profits){
		int[] start=new int[edges.size()+1];
		int[] end=new int[edges.size()+1];
		int[] cost=new int[edges.size()+1];
		
		int cnt=0;
		for(int[] e:edges){
			start[cnt]=e[0]+1;
			end[cnt]=e[1]+1;
			cost[cnt]=e[2];
			cnt++;
		}
		start[edges.size()]=0;
	   	end[edges.size()]=1;
	   	cost[edges.size()]=0;
	   	CavesEgor cfe=new CavesEgor();
	   	long profit=cfe.makeDFS(cfe.iniGraph(start, end, cost, profits));
	   	return profit;
	}
	

	
	class TestCase{
		List<int[]> edges;
		int[] profits;
		long result;
		
		TestCase(int[] profits, List<int[]> edges,  long result){
			this.result=result;
			this.edges=edges;
			this.profits=profits;
			if(!checkProfit(profits))
				System.out.println("ProfitsWrong");
			
			if(!constrainsEdges(edges, profits.length))
				System.out.println("constrains edges wrong");
			
			if(!isDAG(edges, profits.length))
				System.out.println("Not a DAG");
			
		}
		
		boolean checkProfit(int[] profit){
			if(profit.length>MAXNODECNT && profit.length<1)
				return false;
			for(int i:profit)
				if(i<0 && i>MAXCOST)
					return false;
			return true;
		}
		
		boolean checkNode(int i, int n){
			return i>=0 && i<n;
		}
		
		//List<int[]> filterEdges(List<int[]> edges){
		//	
		//}
		boolean constrainsEdges(List<int[]> edges, int n){
			Set<Integer>[] goals=new Set[n];
			for(int i=0;i<n;goals[i++]=new HashSet<Integer>());
			for(int[] e:edges){
				if(!goals[e[0]].add(e[1])){//multi edges
					System.out.println(goals[e[0]]+" adding "+e[1]);
					return false;
				}
				if(!( checkNode(e[0],n) && checkNode(e[1],n) && (e[2]>=0 && e[2]<=MAXCOST))){
						return false;
				}
			}
			return true;
		}
		
		boolean isDAG(List<int[]> edges, int n){
			if(edges.size()>MAXEDGECNT && edges.size()<0)
				return false;
			List<Integer>[] graph=new List[n];
			for(int i=0;i<n;graph[i++]=new LinkedList<Integer>());
			
			int[] inCnts=new int[n];
			for(int[] e:edges){
				graph[e[0]].add(e[1]);
				inCnts[e[1]]++;
			}
			
			if(inCnts[0]!=0)
				return false;// a cave above
			
			Queue<List<Integer>> q=new LinkedList<List<Integer>>();
			int done=0;
			for(int i=0;i<n;i++)
				if(inCnts[i]==0)
					q.offer(graph[i]);
			
			while(!q.isEmpty()){
				List<Integer> outs=q.poll();
				done++;
				for(int next:outs){
					inCnts[next]--;
					if(inCnts[next]==0)
						q.offer(graph[next]);
				}
				
			}
			
			return done==n;
		}
		
		
		
	}
public class CavesEgor {
	
	
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
	        withStreams(reader, writer);
	        writer.flush();
	        writer.close();
	        reader.close();
	    }

}
		

}



