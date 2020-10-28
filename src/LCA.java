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
    public Iterable<Integer> adj(int v)
	{ 
    	return adj[v]; 
    }
    
    
    
  //This public function is used to find the lowest common ancestor in a directed acyclic graph
    public int findLCA(int v, int w){
    	int valid1 = validateVertex(v);
		int valid2 = validateVertex(w);
		if(valid1==-1||valid2==-1)
		{
			throw new IllegalArgumentException("Invalid Vertices");
		}
		findCycle(0);
		if(hasCycle)
		{
			//Graph is not a DAG
			throw new IllegalArgumentException("Graph has a Cycle");
		}
		//Reverse the dag, allows easier traversal
		DAG backwards = reverse();
		
		//Locate the two points in the graph
		ArrayList<Integer> vPath = backwards.BFS(v);
		ArrayList<Integer> wPath = backwards.BFS(w);
		ArrayList<Integer> commonAncestors = new ArrayList<Integer>();
		boolean found = false;
		
		//cycle through the BFS paths, adding all common ancestors to the arrayList
		//return the first one found, as it is the closest to the nodes.
		for(int i = 0; i<vPath.size(); i++){
				for(int t = 0; t<wPath.size(); t++){		
					if(vPath.get(i)==wPath.get(t)){
						commonAncestors.add(vPath.get(i));	
						found = true;
					}
			}
		}
		//return -1 in any case where no lca is found (empty dag etc)
		if(found)
			return commonAncestors.get(0);
		else
			return -1;
    }
    
    public LCA reverse() 
    {
        LCA reverse = new LCA(V); //new dag of same parameter
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v); //reverse the direction of the edges
            }
        }
        return reverse;
    }
    
    public ArrayList<Integer> BFS(int s)
    {
        // Mark all the vertices as not visited(By default set as false)
        boolean visited[] = new boolean[V];
 
        LinkedList<Integer> queue = new LinkedList<Integer>();
        ArrayList<Integer> order= new ArrayList<Integer>();
 
        visited[s]=true;
        queue.add(s);
        
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();           
            order.add(s);
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        
        return order;
        
    }
    
    public boolean hasCycle()  
    {   
    	return hasCycle;
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
