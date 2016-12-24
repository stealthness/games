import base.tile.SolidTile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmptyTileTest {

	private final SolidTile  tile = new SolidTile();


	@Test
	void testIsSolid() {
		assertTrue(tile.isSolid());
	}
	
	@Test
	void testIsHidden(){
		assertTrue(tile.isHidden());
	}
}
