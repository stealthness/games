package test;

import core.Board;
import core.Column;
import core.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ColumnTest  {

	private static final String AMY = "Amy";
	private static final String BOB = "Bob";
	private Player player1, player2;
	private Column defaultCol;
	private Column nullCol;
	private Column largeCol;
	private Column superCol;
	private Column[] columns;

	@BeforeEach
	protected void setUp() throws Exception {
		defaultCol = new Column(Board.DEFAULT_BOARD_SIZE_OF_COLUMNS);
		largeCol = new Column(Board.LARGE_BOARD_SIZE_OF_COLUMNS);
		superCol = new Column(Board.SUPER_BOARD_SIZE_OF_COLUMNS);
		
		columns = new Column[] { defaultCol, largeCol, superCol };
		
		player1 = new Player(AMY, 'X');
		player2 = new Player(BOB, 'O');
	}

	@Test
	public void testSetUp() {
		testSetup(defaultCol, Board.DEFAULT_BOARD_NO_COLUMNS);
		testSetup(largeCol, Board.LARGE_BOARD_NO_COLUMNS);
		testSetup(superCol, Board.SUPER_BOARD_NO_COLUMNS);
	}

	private void testSetup(Column col, int expSizeCol) {
		assertEquals(expSizeCol, col.getMaxSize());
	}

	@Test
	public void testAddMove() {
		for (Column col:columns){
			testAddMove(col);
		}
	}
	
	private void testAddMove(Column col){
		int count = 0;
		assertEquals(count, col.getCurrentSize());
		col.addPlayer(player2);
		assertEquals(++count, col.getCurrentSize());
		while(count<col.getMaxSize()-1){
			count++;
			col.addPlayer(player1);
		}		
		col.addPlayer(player2);
		assertEquals(++count, col.getCurrentSize());
		col.addPlayer(player1);
		assertEquals(count, col.getCurrentSize());
		
	}
	
	public void testFindPlayerAt(){
		testFindPlayerAt(defaultCol);
	}

	public void testFindPlayerAt(Column col) {
		boolean caughtException = false;
		testAddMove();
		assertPlayer(player2, defaultCol.getPlayerAt(0));
		assertPlayer(player1, defaultCol.getPlayerAt(1));
		assertPlayer(player1, defaultCol.getPlayerAt(col.getMaxSize()-2));
		assertPlayer(player2, defaultCol.getPlayerAt(col.getMaxSize()-1));
		try {
			assertPlayer(player2, defaultCol.getPlayerAt(col.getMaxSize()));
		} catch (Exception e) {
			caughtException = true;

		} finally {
			assertTrue(caughtException);
		}

	}

	public void testNewCol() {
		assertNull(nullCol);
		boolean caughtException = false;
		try {
			assertEquals(0, nullCol.getCurrentSize());
		} catch (Exception e) {
			caughtException = true;

		} finally {
			assertTrue(caughtException);
		}
		nullCol = new Column(8);
		assertEquals(8, nullCol.getMaxSize());
		assertEquals(0, nullCol.getCurrentSize());
	}

	public void testToString() {
		assertEquals("----", defaultCol.toString());
		defaultCol.addPlayer(player1);
		assertEquals("X---", defaultCol.toString());
		defaultCol.addPlayer(player1);
		assertEquals("XX--", defaultCol.toString());
		defaultCol.addPlayer(player2);
		assertEquals("XXO-", defaultCol.toString());
		defaultCol.addPlayer(player1);
		assertEquals("XXOX", defaultCol.toString());
		defaultCol.addPlayer(player1);
		assertEquals("XXOX", defaultCol.toString());
		
		testAddMove(largeCol);
		assertEquals("OXXXXXO", largeCol.toString());
		
		testAddMove(superCol);
		assertEquals("OXXXXXXXXO", superCol.toString());
	}

	public static void assertPlayer(Player expPlayer, Player objPlayer) {
		assertEquals(expPlayer.getName(), objPlayer.getName());
	}

}
