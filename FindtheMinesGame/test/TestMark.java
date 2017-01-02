import static org.junit.Assert.*;
import org.junit.*;

import core.Mark;

public class TestMark {
	
	Mark mark0,mark1,mark2,mark11;
	String playerA,playerB,empty;

	@Before
	public void setUp() throws Exception {
		mark1 = Mark.PLAYER_A;
		mark11 = Mark.PLAYER_A;
		mark2 = Mark.PLAYER_B;
		mark0 = Mark.EMPTY;
		
		playerA = "player A";
		playerB = "player B";
		empty = "empty";
	}

	
	@Test
	public void testCreate(){
		// are all marks created equal
		assertEquals(mark1,Mark.PLAYER_A);
		assertEquals(mark2,Mark.PLAYER_B);
		assertEquals(mark0,Mark.EMPTY);
	}
	
	@Test
	public void testChangeValue(){
		// testing that we can change mark value
		mark1 = Mark.EMPTY;
		assertEquals(mark1,Mark.EMPTY);
		mark2 = Mark.PLAYER_A;
		assertEquals(mark2,Mark.PLAYER_A);
		mark0 = Mark.PLAYER_B;
		assertEquals(mark0,Mark.PLAYER_B);
	}
	
	@Test
	public void testEquality(){
		assertEquals(mark1,mark11);
	}
	
	@Test
	public void testToString(){
		assertEquals(playerA,mark1.toString());
		assertEquals(playerB,mark2.toString());
		assertEquals(empty,mark0.toString());
	}
	
	public void testSetMarkNames(){
		String name1 = "Amy";
		String name2 = "Bob";
		mark1.setName(name1);
		mark2.setName(name2);
		assertEquals(name1,mark1.toString());
		assertEquals(name2,mark2.toString());
		assertEquals(empty,mark1.toString());
	};

}
