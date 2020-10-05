import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LCATest {
	
	LCA tree = new LCA();

	@Test
	public void testNullTree() 
	{
		assertNull(tree.root);
	}
	
	

}
