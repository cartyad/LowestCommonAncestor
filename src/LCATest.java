import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LCATest {
	
	LCA tree = new LCA();

	@Test
	public void testNullTreeError() 
	{
		assertNull(tree.root);
	}
	
	@Test
	public void testTreeConstructor() 
	{
		//Create a tree with only one single node, the root
		Node rootNode = new Node(0);
		assertEquals("Root Value is Zero",0 ,rootNode.value);
		assertEquals("Only node is root so null is expected",null ,rootNode.right);
		assertEquals("Only node is root so null is expected",null ,rootNode.left);
	}
	
	@Test
	public void testNonExistingNodes()
	{
		createTree();
		assertEquals("Element doesn't exist expect -1",-1 ,tree.findLCA(1, 23));
		assertEquals("Elements don't exist expect -1",-1,tree.findLCA(9,10));
		assertEquals("null",3 ,tree.findLCA(3, 3));
	}
	
	
	
	
	public void createTree()
	{
		tree.root = new Node(3);
		tree.root.left = new Node(5);
		tree.root.right = new Node(1);
		tree.root.left.left = new Node(6);
		tree.root.left.right = new Node(2);
		tree.root.right.left = new Node(0);
		tree.root.right.right = new Node(8);
		tree.root.left.right.left = new Node(7);
		tree.root.left.right.right = new Node(4);	
	}

}
