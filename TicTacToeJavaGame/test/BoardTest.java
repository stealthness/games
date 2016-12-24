import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {


	Player player1 = new Player(1, "Amy", false, 'x');;
	Player player2 = new Player(2, "Bob", true, 'o');;
	private Board board= new Board(player1, player2);

	@Test
	public void testInit(){
		assertFalse(board.hasEnded());
		for (int i = 0; i<9 ;i++){
			assertNotNull(board.getPlayerAt(i));
			assertEquals(0,board.getPlayerAt(i).getId());
		}
	}
	
	@Test
	public void testGetMoves(){
		assertEquals(0, board.getMoves());	
		
		for (int i = 0; i<9 ;i++){
			assertTrue(board.isEmptyAt(i));
			board.addPlayerAt(i,player1);
			assertEquals(i+1,board.getMoves());
		}
		// adding existing player1 to position should fail alter number of moves
		for (int i = 0; i<9 ;i++){
			board.addPlayerAt(i,player1);
			assertEquals(9,board.getMoves());
		}
		
		board.reset();
		assertEquals(0, board.getMoves());	
		
	}

	
	@Test 
	public void testAddMove(){
		for (int i = 0; i<9 ;i++){
			 board.addPlayerAt(i,player1);
			assertNotNull(board.getPlayerAt(i));
			assertEquals(1,board.getPlayerAt(i).getId());
			assertEquals(i+1,board.getMoves());
		}
		
		board.addPlayerAt(1, player2);
		// this should fail ass position is already assigned
		assertNotNull(board.getPlayerAt(1));
		assertEquals(1,board.getPlayerAt(1).getId());
	}
	
	@Test 
	public void testToString() {
		String emptyBoard = "---\n---\n---\n";
		assertEquals(emptyBoard,board.toString());
		
		String testString1 =  "x--\n---\n---\n";
		board.addPlayerAt(0, player1);
		assertEquals(testString1,board.toString());
		
		String testString2 =  "x--\n---\n--o\n";
		board.addPlayerAt(8, player2);
		assertEquals(testString2,board.toString());
		
	}
	
	@Test
	public void testVictoryConditions(){
		
		// horizontal
		testVictoryConditions(board,player1,0,1,2);
		testVictoryConditions(board,player1,3,4,5);
		testVictoryConditions(board,player1,6,7,8);
		
		// vertical 
		testVictoryConditions(board,player1,0,3,6);
		testVictoryConditions(board,player1,1,4,7);
		testVictoryConditions(board,player1,2,5,8);
		
		// diagonals
		testVictoryConditions(board,player1,0,4,8);
		testVictoryConditions(board,player1,2,4,6);
	}
	
	private void testVictoryConditions(Board board, Player player,int pos1,int pos2, int pos3){
		assertFalse(board.hasEnded());
		board.addPlayerAt(pos1, player);
		board.addPlayerAt(pos2, player);
		board.addPlayerAt(pos3, player);
		board.testVictory();
		assertTrue(board.hasEnded());
		board.reset();
	}
	
	

	
}
