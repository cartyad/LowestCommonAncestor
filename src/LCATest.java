import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LCATest {
	
	LCA emptyTree = new LCA(0);
	LCA binaryTree = new LCA(9);
	LCA acyclicGraph =new LCA(8);
	LCA cycleGraph = new LCA(9);
	LCA directAcyclicGraph = new LCA(9);
	LCA smallBinaryTree = new LCA(5);

	@Test
	public void testEmptyTree() 
	{
		assertEquals(emptyTree.V(),0);
		assertEquals(emptyTree.E(),0);
	}
	
	@Test
	public void testNegativeDAG() 
	{
		
		assertThrows(IllegalArgumentException.class, () -> {LCA negativeTest = new LCA(-2);});		
	}
	
	@Test
	public void testInDegreeErrorHandling()
	{
		LCA DAG = new LCA(5);
		assertEquals(-1, DAG.indegree(-3));
		
		acyclicGraphConstructor();
		assertEquals(-1, acyclicGraph.indegree(-3));
	}
	
	@Test
	public void testIndegreeFunctionality()
	{
		acyclicGraphConstructor();
		cycleGraphConstructor();
		assertEquals(1, acyclicGraph.indegree(5));
		assertEquals(1, acyclicGraph.indegree(5));
		assertEquals( 2, cycleGraph.indegree(8));
	}
	
	@Test
	public void testOutDegree()
	{
		LCA DAG = new LCA(5);
		assertEquals(-1, DAG.outdegree(8));	
		
		acyclicGraphConstructor();
		assertEquals(acyclicGraph.outdegree(9),-1);
	}
	
	@Test
	public void testOutdegreeFunctionality()
	{
		acyclicGraphConstructor();
		cycleGraphConstructor();
		assertEquals(1, acyclicGraph.outdegree(0));
		assertEquals(2, cycleGraph.outdegree(0));
	}
	
	@Test
	public void testAddEdgeFunctionality(){
		LCA DAG = new LCA(5);
		assertThrows(IllegalArgumentException.class, () -> {DAG.addEdge(3, 9);});
		assertThrows(IllegalArgumentException.class, () -> {DAG.addEdge(-2, -5);});
	}
	
	@Test
	public void testAddEdgeErrorHandlingFunctionality()
	{
		acyclicGraphConstructor();
		cycleGraphConstructor();
		createSmallBinaryTree();
		createSampleBinaryTree();
		assertEquals(7, acyclicGraph.E());
		acyclicGraph.addEdge(4, 6);
		assertEquals(8, acyclicGraph.E());
		assertThrows(IllegalArgumentException.class, () -> {acyclicGraph.addEdge(-1, -1);});
		assertEquals( 8, acyclicGraph.E());
		assertEquals( 4, smallBinaryTree.E());
		assertEquals( 8, binaryTree.E());
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
		assertEquals(smallBinaryTree.hasCycle(), false);
		assertFalse(directAcyclicGraph.hasCycle());
			
		cycleGraph.findCycle(0);
		assertTrue(cycleGraph.hasCycle());
	}
	
	@Test
	public void testLCAForNoCommonAncestors(){
		LCA DAG = new LCA(11);
		//-----1----5----
		//---0-|---/-----
		//-----2--3---4--
		DAG.addEdge(0, 1);
		DAG.addEdge(0, 2);
		DAG.addEdge(1, 2);
		DAG.addEdge(2, 3);
		DAG.addEdge(3, 4);
		DAG.addEdge(1, 5);
		DAG.addEdge(3, 5);
		assertEquals(DAG.findLCA(1, 3),1);
		assertEquals(DAG.findLCA(2, 3),2);
		assertEquals(DAG.findLCA(5, 4),1);

		assertEquals(-1, DAG.findLCA(7, 3));
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
	public void testLCA()
	{
		acyclicGraphConstructor();
		cycleGraphConstructor();
		directAcyclicGraphConstructor();
		createSampleBinaryTree();
		createSmallBinaryTree();
		
		assertEquals(binaryTree.findLCA(8, 1), 1);
		
		assertEquals(acyclicGraph.findLCA(2, 3), 2);
		assertEquals(directAcyclicGraph.findLCA(7, 8), 7);
		assertEquals(directAcyclicGraph.findLCA(7, 5), 5);
		
		assertEquals(binaryTree.findLCA(5, 2), 5);
		assertEquals(binaryTree.findLCA(5, 1), 3);
		assertEquals(binaryTree.findLCA(0, 1), 1);
		assertEquals(binaryTree.findLCA(7, 4), 2);
		assertEquals(binaryTree.findLCA(4, 6), 5);
		assertEquals(binaryTree.findLCA(6, 8), 3);
		
		assertEquals(smallBinaryTree.findLCA(1, 2), 0);
		assertEquals(smallBinaryTree.findLCA(3, 4), 1);
		assertEquals(smallBinaryTree.findLCA(4, 1), 1);
		assertEquals(smallBinaryTree.findLCA(3, 2), 0);

		assertEquals(0, directAcyclicGraph.findLCA(3, 4));
		assertEquals(0, directAcyclicGraph.findLCA(1, 4));
		assertEquals(0, directAcyclicGraph.findLCA(5, 2));
		assertEquals(0, directAcyclicGraph.findLCA(0, 2));
		assertEquals(1, directAcyclicGraph.findLCA(1, 3));
		assertEquals(1, directAcyclicGraph.findLCA(1, 5));
		assertEquals(1, directAcyclicGraph.findLCA(5, 1));
		assertEquals(3, directAcyclicGraph.findLCA(3, 3));
		
		assertEquals(2, acyclicGraph.findLCA(6, 2));
		assertEquals(3, acyclicGraph.findLCA(7, 3));
		assertEquals(1, acyclicGraph.findLCA(3, 1));
		assertEquals(0, acyclicGraph.findLCA(0, 1));
		
		assertEquals(3, acyclicGraph.findLCA(3, 3));
		assertEquals(3, directAcyclicGraph.findLCA(3, 3));
		assertEquals(3, binaryTree.findLCA(3, 3));
		
		LCA emptyGraph = new LCA(0);
		assertThrows(IllegalArgumentException.class, () -> {emptyGraph.findLCA(3, 3);});
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
		assertThrows(IllegalArgumentException.class, () -> {binaryTree.findLCA(1, 23);});
		assertThrows(IllegalArgumentException.class, () -> {binaryTree.findLCA(9, 10);});
		assertThrows(IllegalArgumentException.class, () -> {binaryTree.findLCA(-100, 400);});
	
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
		
		//Binary Tree Graph Error Tests
		assertThrows(IllegalArgumentException.class, () -> {binaryTree.findLCA(-1, 4);});
		assertThrows(IllegalArgumentException.class, () -> {binaryTree.findLCA(1, 40);});
		assertThrows(IllegalArgumentException.class, () -> {binaryTree.findLCA(100, 400);});
		
	}
	
	@Test
	public void testForDirectedGraph(){
		LCA DAG = new LCA(10);
		DAG.addEdge(1, 2);
		DAG.addEdge(1, 3);
		DAG.addEdge(3, 4);

		assertEquals("", 1, DAG.indegree(4));
		assertEquals("", 0, DAG.outdegree(4));
		assertEquals("", 3, DAG.E());
		assertEquals("", 10, DAG.V());
		assertEquals("","[]", DAG.adj(4).toString());
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
	
	public void createSmallBinaryTree()
	{
		smallBinaryTree.addEdge(0, 1);
		smallBinaryTree.addEdge(0, 2);
		smallBinaryTree.addEdge(1, 4);
		smallBinaryTree.addEdge(1, 3);
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
