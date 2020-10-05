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
	public void testTreeConstructor() {
		//Create a tree with only one single node, the root
		Node rootNode = new Node(0);
		assertEquals("Root Value is Zero",0 ,rootNode.value);
		assertEquals("Only node is root so null is expected",null ,rootNode.right);
		assertEquals("Only node is root so null is expected",null ,rootNode.left);
	}

}
