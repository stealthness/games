import org.junit.Before
import org.junit.Test

class TestEnumHANDS extends GroovyTestCase{
	
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	void testThatHANDSdotROCKtoString(){
		assertEquals("Rock",HANDS.ROCK.toString())
	}

    @Test
    void testThatHANDSdotPAPERtoString(){
        assertEquals("Paper",HANDS.PAPER.toString())
    }

    @Test
    void testThatHNADSdotSCISSORStoString(){
        assertEquals("Scissors",HANDS.SCISSORS.toString())
    }

}
