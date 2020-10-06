
public class DAG {
	private int V;
	private int E;
	private int[][] adj;
	private int[] outdegree;
	private int[] indegree;
	private int[] visited;
	
	public DAG(int V)
	{
		if(V<0)
		{
			throw new IllegalArgumentException("Number of vertices in the DAG must be greater than 0.");
		}

		else{
			this.V = V;
			this.E = 0;
			//indegree = new boolean[V];
			indegree = new int[V];
			outdegree = new int[V];
			visited = new int[V];
			adj = new int[V][V];
			for(int i = 0; i<V; i++)
			{
				for(int j=0;j<V;j++)
				{
					adj[i][j] = 0;
				}
			}
		}
	}
	
	public int V()
	{
        return V;
    }
	public int E()
	{
        return E;
    }
	
	
	private void validateVertex(int v){
        if((v<0)||(v>=V))
        {
            throw new IllegalArgumentException("Number of Edges is less than 1");
        }
    }
	
	
	public void addEdge(int v, int w){
        validateVertex(v);
	validateVertex(w);
	adj[v][w]=1;
	indegree[w]++;
	outdegree[v]++;
	E++;
    }
    
    //Removes an edge from v to w
    public void removeEdge(int v, int w){
        validateVertex(v);
	validateVertex(w);
	adj[v][w]=0;
	indegree[w]--;
	outdegree[v]--;
	E--;
    }
    
    //returns the number of directed edges out of vertex v
    public int outdegree(int v){
    	validateVertex(v);
	return outdegree[v];
    }
    
    //returns the number of directed edges into vertex v
    public int indegree(int v){
   	validateVertex(v);
	return indegree[v];
    }
    
    //returns the vertices adjacent from vertex v
    public int[] adj(int v){
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
    
    //returns true if the graph contains acycle, else false
    public boolean hasCycle()
    {
    	boolean hasCycleVar=false;
    	int count = 0;
    	for(int i =0;i<V;i++){
    		visited[count]=i;
    		for(int j = 0; j<V;j++){
    			for(int k=0;k<V;k++){
    				if(visited[k]==j && adj[i][j]==1){
    					hasCycleVar=true;
    					return hasCycleVar;
    				}
    			}	
    		}
    		count++;
    	}
    	return hasCycleVar;
    }
    
    //This public function is used to find the lowest common ancestor in a directed acyclic graph
    public int findLCA(int v, int w)
    {
    	validateVertex(v);
    	validateVertex(w);
    	boolean hasCycleVar=hasCycle();
    	if(E>0 && !hasCycleVar){
    		return LCAUtil(v,w);
    	}
    	else{
    		throw new IllegalArgumentException("This graph is not an acyclic graph.");
    	}
    }
    
    //helper function for LCA
    private int LCAUtil(int v, int w)
    {
    	int[] vArr = new int[E];
    	int[] wArr = new int[E];
    	boolean[] vMarked = new boolean[V];
    	boolean[] wMarked = new boolean[V];
    	int vCount =0;
    	int wCount = 0;
    	vArr[vCount]=v;
    	wArr[wCount]=w;
    	for(int j=0; j<V;j++){//mark all vertices as not been visited yet
    		vMarked[j]=false;
    		wMarked[j]=false;
    	}
    	for(int i =0;i<V;i++){
    		vMarked[v] =true;
    		wMarked[w] =true;
    		for(int j = 0; j<V;j++){
    			if(adj[i][j]==1 && vMarked[i]){
    				vCount++;
    				vArr[vCount]=j;
    				vMarked[j]=true;
    			}
    			if(adj[i][j]==1 && wMarked[i]){
    				wCount++;
    				wArr[wCount]=j;
    				wMarked[j]=true;
    			}
    			if(wArr[wCount]==vArr[vCount]){
    				return wArr[wCount];
    			}
    		}
    	}
    	return -1;//returns -1 if no ancestor found
    }  

}
