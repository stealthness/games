import static org.junit.Assert.*;

import org.junit.*;

class TestEnumHANDS extends GroovyTestCase{
	
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	void testToString(){
		assertEquals("Rock",HANDS.ROCK)
	}

}
