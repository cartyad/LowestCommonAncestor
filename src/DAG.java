
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
	

}
