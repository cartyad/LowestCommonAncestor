
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
		
		
	}

}
