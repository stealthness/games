import static org.junit.Assert.*;
import org.junit.*;
import core.TicTacToe;

public class TestTicTacToe {
	
	TicTacToe ttt ;
	
	
	@Before
	public void setUp() throws Exception {
		ttt = new TicTacToe();
		
	}
	
	@Test
	public void testCreate(){
		assertEquals(9,ttt.size());
	}

}
