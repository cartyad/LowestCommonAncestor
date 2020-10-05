import java.util.ArrayList; 
import java.util.List; 

public class LCA {
	Node root;
	
	private List<Integer> path1 = new ArrayList<>();
	private List<Integer> path2 = new ArrayList<>();
	
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
	}
	
	private boolean findPath(Node root, int n, List<Integer> path)
	{
		
	}

}
