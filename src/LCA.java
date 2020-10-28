import java.util.ArrayList; 
import java.util.List; 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class LCA {
	
	private int V;           // number of vertices in this digraph
	private int E;                 // number of edges in this digraph
	private ArrayList<Integer>[] adj;    // adj[v] = adjacency list for vertex v
	private int[] indegree;        // indegree[v] = indegree of vertex v
	private boolean marked[];		//Boolean List to track visited vertices
	private boolean stack[];
	private boolean hasCycle;	//True if cycle in graph//Order that vertices were visited
	private int[] edgeTo;      // edgeTo[v] = last edge on shortest s->v path
    private int[] distTo;      // distTo[v] = length of shortest s->v path

	
	public LCA(int V)
	{
		if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
	    this.V = V;
	    this.E = 0;
	    indegree = new int[V];
	    marked = new boolean[V];
	    stack = new boolean[V];
	    adj = (ArrayList<Integer>[]) new ArrayList[V];
	    for (int v = 0; v < V; v++) {
	        adj[v] = new ArrayList<Integer>();
	    }              
	}
	
	
	 //returns number of vertices in DAG
    public int V()
    {
        return V;
    }
    
    //returns number of edges in DAG
    public int E(){
        return E;
    }
    
    //throws illegal exception if the vertex put in, is out of bounds
    private int validateVertex(int v) 
    {
        if (v < 0 || v >= V)
        {
        	return -1;        	
        }
        else
        {
        	return 1;        	
        }
    }
    
  //adds directed edge from v to w
    public void addEdge(int v, int w)
    {
    	if((validateVertex(v)>0)&&(validateVertex(w)>0))
	    {
	    	adj[v].add(w);
	    	indegree[w]++;
	    	E++;
	    }
	    else
	    {
	    	throw new IllegalArgumentException("Invalid Vertices");
	    }
    	
    }
    
  //Removes an edge from v to w
   /* public void removeEdge(int v, int w){
    	validateVertex(v);
    	validateVertex(w);
    	adj[v][w]=0;
    	indegree[w]--;
    	outdegree[v]--;
    	E--;
    }*/
    
  //returns the number of directed edges out of vertex v
    public int outdegree(int v)
    {
    	if(validateVertex(v)<0){
			return -1;
		}
		else{
			return adj[v].size();
		}
    }
    
    //returns the number of directed edges into vertex v
    public int indegree(int v)
    {
    	if(validateVertex(v)<0){
			return -1;
		}
		else{
			return indegree[v];
		}
    }
    
  //returns the vertices adjacent from vertex v
    public int[] adj(int v)
    {
    	validateVertex(v);
    	int[] temp = new int[outdegree[v]];
    	int count =0;
    	for(int i=0;i<V;i++){
    		if(adj[v][i]==1){
    			temp[count]=i;
    			count++;
    		}
    	}
    	return temp;
    }
    
    
    
  //This public function is used to find the lowest common ancestor in a directed acyclic graph
    public int findLCA(int v, int w){
    	validateVertex(v);
    	validateVertex(w);
    	boolean cyclicGraph = hasCycle();
    	if(E>0 && !cyclicGraph)
    	{
    		return LCAUtil(v,w);
    	}
    	else{
    		throw new IllegalArgumentException("This graph is not an acyclic graph.");
    	}
    }
    
    public boolean hasCycle()  
    { 
          
        // Mark all the vertices as not visited and 
        // not part of recursion stack 
        boolean[] visited = new boolean[V]; 
        boolean[] recStack = new boolean[V]; 
          
          
        // Call the recursive helper function to 
        // detect cycle in different DFS trees 
        for (int i = 0; i < V; i++) 
            if (isCyclicUtil(i, visited, recStack)) 
                return true; 
  
        return false; 
    } 
    
    public boolean isCyclicUtil(int i, boolean[] visited, 
                                      boolean[] recStack)  
    { 
          
        // Mark the current node as visited and 
        // part of recursion stack 
        if (recStack[i]) 
            return true; 
  
        if (visited[i]) 
            return false; 
              
        visited[i] = true; 
  
        recStack[i] = true; 
        int [] children = adj(i); 
      
        for (int j=0; j<children.length; j++)
        {
        	if (isCyclicUtil(children[j], visited, recStack)) 
                return true; 
        }
             
        recStack[i] = false; 
  
        return false; 
    } 
    
    private int LCAUtil(int v, int w){
    	int[] vArr = new int[E];
    	int[] wArr = new int[E];
    	boolean[] vMarked = new boolean[V];
    	boolean[] wMarked = new boolean[V];
    	int vCount =0;
    	int wCount = 0;
    	vArr[vCount]=v;
    	wArr[wCount]=w;
    	for(int j=0; j<V;j++)
    	{
    		vMarked[j]=false;
    		wMarked[j]=false;
    	}
    	
    	vMarked[v] =true;
		wMarked[w] =true;
    	
    	for(int i =0;i<V;i++)
    	{
    		
    		for(int j = 0; j<V;j++)
    		{
    			if(adj[i][j]==1 && vMarked[i])
    			{
    				vCount++;
    				vArr[vCount]=j;
    				vMarked[j]=true;
    			}
    			if(adj[i][j]==1 && wMarked[i])
    			{
    				wCount++;
    				wArr[wCount]=j;
    				wMarked[j]=true;
    			}
    			if(wArr[wCount]==vArr[vCount])
    			{
    				return wArr[wCount];
    			}
    		}
    	}
    	return -1;
    }  
    
}
