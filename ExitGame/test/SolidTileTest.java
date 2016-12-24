import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import base.tile.EmptyTile;

public class SolidTileTest {
	
	private EmptyTile tile = new EmptyTile();


	@Test
	public void testIsSolid() {
		assertFalse(tile.isSolid());
	}
	
	@Test
	public void testIsHidden(){
		assertTrue(tile.isHidden());
	}

}
