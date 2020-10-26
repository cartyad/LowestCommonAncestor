import java.util.ArrayList; 
import java.util.List; 

public class LCA {
	
	private int V;//# of vertices in graph
	private int E;//# of edges in graph
	private int[][] adj; //adjacency list for vertex v - changed to 2D array
	private int[] outdegree;//outdegree of vertex v
	private int[] indegree; // indegree of vertex v
	private int[] visited;  //vertices that have been visited
	
	public LCA(int V){
		if(V<0){
			throw new IllegalArgumentException("Number of vertices in the DAG must be greater than 0.");
		}
		else{
			this.V = V;
			this.E = 0;
			indegree = new int[V];
			indegree = new int[V];
			outdegree = new int[V];
			visited = new int[V];
			adj = new int[V][V];
			for(int i = 0; i<V; i++){//sets up an empty graph in 2D array
				for(int j=0;j<V;j++){
					adj[i][j] = 0;
				}
			}
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
    private void validateVertex(int v)
    {
        if((v<0)||(v>=V))
        {
            throw new IllegalArgumentException("Edgesless than one means no verices are joined, and if there is a cycle it cannot be moved");
        }
    }
    
  //adds directed edge from v to w
    public void addEdge(int v, int w)
    {
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
    public int outdegree(int v)
    {
    	validateVertex(v);
    	return outdegree[v];
    }
    
    //returns the number of directed edges into vertex v
    public int indegree(int v)
    {
    	validateVertex(v);
    	return indegree[v];
    }
    
	public int findLCA(int v, int w) 
	{	
		path1.clear();
		path2.clear();
		return findLCA(root, v, w);
	}
	
	private int findLCA(Node root, int v, int w)
	{
		if (!findPath(root, v, path1) || !findPath(root, w, path2)) {
			return -1;
		}
		int i;
		for (i = 0; i < path1.size() && i < path2.size(); i++) {
			if (!path1.get(i).equals(path2.get(i)))
				break;
		}
		return path1.get(i-1);
	}
	
	private boolean findPath(Node root, int n, List<Integer> path)
	{
		//Handles inputs of negative numbers and returns false
		if(n<0){
			return false;
		}
		
		//Handles the input of a null root
		if (root == null) 
		{
			return false;
		}
		
		path.add(root.value);
		//If the node's value is found return as true
		if (root.value == n) 
		{
			return true;
		}
		//Reiterates the findPath function to search through nodes to the left of the current node
		if (root.left != null && findPath(root.left, n, path)) 
		{
			return true;
		}
		//Reiterates the findPath function to search through nodes to the right of the current node
		if (root.right != null && findPath(root.right, n, path)) 
		{
			return true;
		}
		path.remove(path.size()-1);
		return false;
	}
	
	
	
	public static void main(String[] args) {
		LCA tree = new LCA();
		tree.root = new Node(1); 
        tree.root.left = new Node(2); 
        tree.root.right = new Node(3); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(5); 
        tree.root.right.left = new Node(6); 
        tree.root.right.right = new Node(7); 
  
        System.out.println("LCA(4, 5): " + tree.findLCA(4,5)); 
        System.out.println("LCA(4, 6): " + tree.findLCA(4,6)); 
        System.out.println("LCA(3, 4): " + tree.findLCA(3,4)); 
        System.out.println("LCA(2, 4): " + tree.findLCA(2,4));

	}

}
