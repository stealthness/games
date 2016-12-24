import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import base.SnakeCharacter;
import res.DIR;
import res.ORDER;

public class SnakeCharacterTest {

	SnakeCharacter snake = new SnakeCharacter(0,0);;


	@Test
	public void testMovement() {
		assertSnake(DIR.NORTH,0,0,snake);
		snake.move(ORDER.FORWARD);
		snake.move(ORDER.FORWARD);
		snake.move(ORDER.FORWARD);
		assertSnake(DIR.NORTH,0,6,snake);
		snake.move(ORDER.RIGHT);
		snake.move(ORDER.FORWARD);
		snake.move(ORDER.FORWARD);
		snake.move(ORDER.FORWARD);
		assertSnake(DIR.EAST,6,6,snake);
		snake.move(ORDER.BACKWARD);
		assertSnake(DIR.EAST,5,6,snake);
		snake.move(ORDER.LEFT);
		snake.move(ORDER.BACKWARD);
		assertSnake(DIR.NORTH,5,5,snake);
	}
	
	private void assertSnake(DIR expDir, int expX, int expY, SnakeCharacter snake){
		assertNotNull(snake);
		assertEquals(expX, snake.getPosition()[0]);
		assertEquals(expY, snake.getPosition()[1]);
		assertEquals(expDir,snake.getDirection());
	}


}
