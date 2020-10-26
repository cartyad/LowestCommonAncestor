import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LCATest {
	
	LCA binaryTree = new LCA(9);
	LCA acyclicGraph =new LCA(8);
	LCA cycleGraph = new LCA(9);
	LCA directacyclicGraphGraph = new LCA(9);

	/*@Test
	public void testNullTreeError() 
	{
		assertNull(tree.root);
	}*/
	
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
	public void testNonExistingNodesWithinTheTree()
	{
		createSampleTree();
		assertEquals("Element doesn't exist expect -1",-1 ,tree.findLCA(1, 23));
		assertEquals("Elements don't exist expect -1",-1,tree.findLCA(9,10));
		assertEquals("null",3 ,tree.findLCA(3, 3));
	}
	
	@Test
	public void testIdenticalNodeInput()
	{
		createSampleTree();
		assertEquals(3 ,tree.findLCA(3, 3));
		assertEquals(3 ,tree.findLCA(3, 5));
		assertEquals(3 ,tree.findLCA(1, 3));
		assertEquals(1 ,tree.findLCA(1, 1));
		assertEquals(5 ,tree.findLCA(5, 5));
	}
	
	@Test
	public void testSmallTree() {
		tree.root = new Node(3);
		tree.root.left = new Node(5);
		assertEquals(3 ,tree.root.value);
		assertEquals(5 ,tree.root.left.value);
		assertNull(tree.root.right);
		assertEquals(3 ,tree.findLCA(3, 5));
		assertEquals(3 ,tree.findLCA(5, 3));
	}
	
	
	@Test
	public void testTree() 
	{
		createSampleTree();
		assertEquals(3 ,tree.findLCA(6, 1));
		assertEquals(2 ,tree.findLCA(7, 4));
		assertEquals(1 ,tree.findLCA(0, 8));
		assertEquals(3 ,tree.findLCA(1, 5));
		assertEquals(3 ,tree.findLCA(5, 1));
	}
	
	
	public void acyclicGraphConstructor(){
		//1->2->3->4->5->6->7
		acyclicGraph.addEdge(0, 1);
		acyclicGraph.addEdge(1, 2);
		acyclicGraph.addEdge(2, 3);
		acyclicGraph.addEdge(3, 4);
		acyclicGraph.addEdge(4, 5);
		acyclicGraph.addEdge(5, 6);
		acyclicGraph.addEdge(6, 7);
	}
	
	
	public void createSampleBinaryTree()
	{
		binaryTree.addEdge(1, 0);
		binaryTree.addEdge(1, 8);
		binaryTree.addEdge(3, 1);
		binaryTree.addEdge(3, 5);
		binaryTree.addEdge(5, 6);
		binaryTree.addEdge(5, 2);
		binaryTree.addEdge(2, 7);
		binaryTree.addEdge(2, 4);

	}

}
