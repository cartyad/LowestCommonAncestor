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
		assertEquals(1 ,a.value);
		assertEquals(2 ,b.value);
	}

}
