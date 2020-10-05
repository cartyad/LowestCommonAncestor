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
		tree.root = new Node(3);
		tree.root.left = new Node(5);
		tree.root.right = new Node(1);
		tree.root.left.left = new Node(6);
		tree.root.left.right = new Node(2);
		tree.root.right.left = new Node(0);
		tree.root.right.right = new Node(8);
		tree.root.left.right.left = new Node(7);
		tree.root.left.right.right = new Node(4);
		
		System.out.println("LCA(4, 5): " + tree.findLCA(4,5));
		System.out.println("LCA(4, 6): " + tree.findLCA(4,6));
		System.out.println("LCA(3, 4): " + tree.findLCA(3,4));
		System.out.println("LCA(2, 4): " + tree.findLCA(2,4));
		System.out.println(tree.root.right.right.value);

	}

}
