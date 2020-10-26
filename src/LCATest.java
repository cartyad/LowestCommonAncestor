import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LCATest {
	
	LCA emptyTree = new LCA(0);
	LCA binaryTree = new LCA(9);
	LCA acyclicGraph =new LCA(8);
	LCA cycleGraph = new LCA(9);
	LCA directAcyclicGraph = new LCA(9);

	@Test
	public void testEmptyTree() 
	{
		assertNull(emptyTree.V());
		assertNull(emptyTree.E());
	}
	
	@Test
	public void testNegativeDAG() 
	{
		
		assertThrows(IllegalArgumentException.class, () -> {LCA negativeTest = new LCA(-2);});		
	}
	
	@Test
	public void testIndegreeFunctionality()
	{
		acyclicGraphConstructor();
		cycleGraphConstructor();
		assertEquals(1, acyclicGraph.indegree(5));
		assertThrows(IllegalArgumentException.class, () -> {acyclicGraph.indegree(-3);});
		assertEquals(1, acyclicGraph.indegree(5));
		assertEquals( 2, cycleGraph.indegree(8));
	}
	
	@Test
	public void testOutdegreeFunctionality()
	{
		acyclicGraphConstructor();
		cycleGraphConstructor();
		assertThrows(IllegalArgumentException.class, () -> {acyclicGraph.outdegree(9);});
		assertEquals(1, acyclicGraph.outdegree(0));
		assertEquals(2, cycleGraph.outdegree(0));
	}
	
	@Test
	public void testAdjancyArrayFunctionality()
	{
		acyclicGraphConstructor();
		cycleGraphConstructor();
		assertArrayEquals(new int[]{5}, acyclicGraph.adj(4));
		assertArrayEquals(new int[]{1,2}, cycleGraph.adj(0));
	}
	
	@Test
	public void testEdgesInConstructedGraph()
	{
		acyclicGraphConstructor();
		cycleGraphConstructor();
		directAcyclicGraphConstructor();
		createSampleBinaryTree();
		assertEquals( 7, acyclicGraph.E());
		assertEquals(9, cycleGraph.E());
		assertEquals(9, directAcyclicGraph.E());
		assertEquals(8 ,binaryTree.E());
	}
	
	@Test
	public void testCycle()
	{
		LCA emptyGraph = new LCA(0);
		assertFalse(emptyGraph.hasCycle());
		acyclicGraphConstructor();
		cycleGraphConstructor();
		directAcyclicGraphConstructor();
		createSampleBinaryTree();
		assertEquals(acyclicGraph.hasCycle(), false);
		assertEquals(binaryTree.hasCycle(), false);
		assertTrue(cycleGraph.hasCycle());
		assertFalse(directAcyclicGraph.hasCycle());
	}
	
	@Test
	public void testV()
	{
		acyclicGraphConstructor();
		cycleGraphConstructor();
		directAcyclicGraphConstructor();
		createSampleBinaryTree();
		assertEquals(8, acyclicGraph.V());
		assertEquals(9, cycleGraph.V());
		assertEquals(9, directAcyclicGraph.V());
		assertEquals(9 ,binaryTree.V());
	}
	
	@Test
	public void testValidVertex()
	{
		LCA validTestGraph = new LCA(3);

		assertThrows(IllegalArgumentException.class, () -> {validTestGraph.addEdge(-1, 2);});
		assertThrows(IllegalArgumentException.class, () -> {validTestGraph.addEdge(1, -2);});
		assertThrows(IllegalArgumentException.class, () -> {validTestGraph.addEdge(-1, -2);});
		assertEquals(0, validTestGraph.E());
		validTestGraph.addEdge(1, 2);
		assertEquals(1, validTestGraph.E());
	}
	
	@Test
	public void testBinaryTreeConstructor() 
	{
		createSampleBinaryTree();
		assertEquals(9 ,binaryTree.V());
		assertEquals(8 ,binaryTree.E());
		
	}
	
	@Test
	public void testNonExistingNodesWithinTheBinaryTree()
	{
		createSampleBinaryTree();
		assertEquals("Element doesn't exist expect -1",-1 ,binaryTree.findLCA(1, 23));
		assertEquals("Elements don't exist expect -1",-1,binaryTree.findLCA(9,10));
		assertEquals("null",3 ,binaryTree.findLCA(3, 3));
	}
	
	@Test
	public void testAddEdgeFunctionality()
	{
		acyclicGraphConstructor();
		cycleGraphConstructor();
		assertEquals(7, acyclicGraph.E());
		acyclicGraph.addEdge(4, 6);
		assertEquals(8, acyclicGraph.E());
		assertThrows(IllegalArgumentException.class, () -> {acyclicGraph.addEdge(-1, -1);});
		assertEquals( 8, acyclicGraph.E());
	}
	
	@Test
	public void testLCAError()
	{
		acyclicGraphConstructor();
		cycleGraphConstructor();
		directAcyclicGraphConstructor();
		createSampleBinaryTree();
		
		//Cyclic Graph Error Tests
		assertThrows(IllegalArgumentException.class, () -> {cycleGraph.findLCA(1, 4);});
		assertThrows(IllegalArgumentException.class, () -> {cycleGraph.findLCA(100, 40);});
		assertThrows(IllegalArgumentException.class, () -> {cycleGraph.findLCA(-1, 4);});
		
		//Acyclic Graph Error Tests
		assertThrows(IllegalArgumentException.class, () -> {acyclicGraph.findLCA(-1, 4);});
		assertThrows(IllegalArgumentException.class, () -> {acyclicGraph.findLCA(1, 40);});
		assertThrows(IllegalArgumentException.class, () -> {acyclicGraph.findLCA(100, 400);});
		
		//Direct Acyclic Graph Error Tests
		assertThrows(IllegalArgumentException.class, () -> {directAcyclicGraph.findLCA(-1, 4);});
		assertThrows(IllegalArgumentException.class, () -> {directAcyclicGraph.findLCA(1, 40);});
		assertThrows(IllegalArgumentException.class, () -> {directAcyclicGraph.findLCA(100, 400);});
		
		//Direct Acyclic Graph Error Tests
		assertThrows(IllegalArgumentException.class, () -> {binaryTree.findLCA(-1, 4);});
		assertThrows(IllegalArgumentException.class, () -> {binaryTree.findLCA(1, 40);});
		assertThrows(IllegalArgumentException.class, () -> {binaryTree.findLCA(100, 400);});
		
	}
	
	public void acyclicGraphConstructor(){
		
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
	
	public void directAcyclicGraphConstructor(){
   
		directAcyclicGraph.addEdge(0, 1);
		directAcyclicGraph.addEdge(0, 2);
		directAcyclicGraph.addEdge(1, 3);
		directAcyclicGraph.addEdge(2, 4);
		directAcyclicGraph.addEdge(3, 5);
		directAcyclicGraph.addEdge(4, 6);
		directAcyclicGraph.addEdge(5, 7);
		directAcyclicGraph.addEdge(6, 7);
		directAcyclicGraph.addEdge(7, 8);
	}
	
	public void cycleGraphConstructor()
	{
		
		cycleGraph.addEdge(0, 1);
		cycleGraph.addEdge(0, 2);
		cycleGraph.addEdge(1, 2);
		cycleGraph.addEdge(2, 4);
		cycleGraph.addEdge(4, 3);
		cycleGraph.addEdge(3, 1);
		cycleGraph.addEdge(3, 6);
		cycleGraph.addEdge(6, 8);
		cycleGraph.addEdge(7, 8);
	}	

}
