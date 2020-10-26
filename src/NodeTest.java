import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeTest {

	Node a = new Node(1);
	Node b = new Node(2);
	Node c = new Node(3);
	Node d = new Node(4);

	@Test
	public void constructorTest(){
		assertEquals("Test the value in node a", 1 ,a.value);
		/*assertEquals("Test the value in node b", 2 ,b.value);
		assertEquals("Confirm a is not connected to anything yet",0 ,a.outdegree);
		assertEquals("Confirm b is not connected to anything yet",0 ,b.outdegree);
		assertEquals("Confirm a is not connected to anything yet",0 ,a.indegree);
		assertEquals("Confirm b is not connected to anything yet",0 ,b.indegree);
		assertEquals("Confirm a is not connected to anything yet",true ,a.edgesTo.isEmpty());
		assertEquals("Confirm b is not connected to anything yet",true ,b.edgesTo.isEmpty())*/;
	}

}
