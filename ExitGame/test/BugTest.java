import base.BugCharacter;
import org.junit.Test;
import res.DIR;
import res.ORDER;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class BugTest {
	
	private final BugCharacter bug = new BugCharacter(0,0);

    @Test
	void testCreate() {

		assertBug(DIR.NORTH,0,0,bug);
		
		bug.moveTo(2, 4);
		assertBug(DIR.NORTH,2,4,bug);
	}

	@Test
	void testSight(){
		assertEquals(1, bug.getSightLength());
		bug.setSightLength(3);
	}

	@Test
	void testDirection(){
		assertEquals(DIR.NORTH,bug.getDirection());
		bug.move(ORDER.LEFT);
		assertEquals(DIR.WEST,bug.getDirection());
		bug.move(ORDER.RIGHT);
		assertEquals(DIR.NORTH,bug.getDirection());
		bug.move(ORDER.RIGHT);
		assertEquals(DIR.EAST,bug.getDirection());
		bug.move(ORDER.TURN);
		assertEquals(DIR.WEST,bug.getDirection());
	}
	
	@Test 
	void testMovement(){
		assertBug(DIR.NORTH,0,0,bug);
		bug.move(ORDER.FORWARD);
		bug.move(ORDER.FORWARD);
		bug.move(ORDER.FORWARD);
		assertBug(DIR.NORTH,0,3,bug);
		bug.move(ORDER.RIGHT);
		bug.move(ORDER.FORWARD);
		bug.move(ORDER.FORWARD);
		bug.move(ORDER.FORWARD);
		assertBug(DIR.EAST,3,3,bug);
		bug.move(ORDER.BACKWARD);
		assertBug(DIR.EAST,2,3,bug);
		bug.move(ORDER.LEFT);
		bug.move(ORDER.BACKWARD);
		assertBug(DIR.NORTH,2,2,bug);
	}
	
	private void assertBug(DIR expDir, int expX, int expY, BugCharacter bug){
		assertNotNull(bug);
		assertEquals(expX, bug.getPosition()[0]);
		assertEquals(expY, bug.getPosition()[1]);
		assertEquals(expDir,bug.getDirection());
	}

}
