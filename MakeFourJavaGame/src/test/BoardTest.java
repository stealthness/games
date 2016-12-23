package test;

import core.Board;
import core.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
	
	

	private static final int BOARD_SIZE = Board.DEFAULT_BOARD_NO_COLUMNS*Board.DEFAULT_BOARD_SIZE_OF_COLUMNS;
	private static final int LARGE_BOARD_SIZE = Board.LARGE_BOARD_NO_COLUMNS*Board.LARGE_BOARD_SIZE_OF_COLUMNS;
	private static final int SUPER_BOARD_SIZE = Board.SUPER_BOARD_NO_COLUMNS*Board.SUPER_BOARD_SIZE_OF_COLUMNS;
	private static final String AMY = "Amy";
		
	Player player1;
	
	Board defaultBoard;	
	Board largeBoard;	
	Board superBoard;
	
	Board[] boards;


	@BeforeEach
	protected void setUp() throws Exception {
		defaultBoard = new Board();
		largeBoard = new Board(Board.LARGE_BOARD_NO_COLUMNS,Board.LARGE_BOARD_SIZE_OF_COLUMNS);
		superBoard = new Board(Board.SUPER_BOARD_NO_COLUMNS,Board.SUPER_BOARD_SIZE_OF_COLUMNS);
		boards = new Board[] {defaultBoard,largeBoard,superBoard};
		player1 = new Player(AMY);
	}

	@Test
	public void testBoardSetUp(){
		assertEquals(BOARD_SIZE,defaultBoard.getSize());
		assertEquals(LARGE_BOARD_SIZE,largeBoard.getSize());
		assertEquals(SUPER_BOARD_SIZE,superBoard.getSize());
	}
	@Test
	public void testColumns(){
		testColumns(defaultBoard,Board.DEFAULT_BOARD_NO_COLUMNS,Board.DEFAULT_BOARD_SIZE_OF_COLUMNS);
		testColumns(largeBoard,Board.LARGE_BOARD_NO_COLUMNS,Board.LARGE_BOARD_SIZE_OF_COLUMNS);
		testColumns(superBoard,Board.SUPER_BOARD_NO_COLUMNS,Board.SUPER_BOARD_SIZE_OF_COLUMNS);
	}

	private void testColumns(Board board, int expNoColumns, int expMaxSizeCol){
		assertEquals(expNoColumns,board.getNoColumns());
		assertEquals(expMaxSizeCol,board.getColumnMaxSize());
		
		for (int i = 0;i<board.getNoColumns();i++){
			assertEquals(0,board.getColumnSize(i));
		}
	}
	
	// testing adding
	@Test
	public void testAddToColumn(){
		for (Board board:boards){
			testAddToColumn(board);
		}
	}
	@Test
	private void testAddToColumn(Board board){
		for (int i = 0;i<board.getNoColumns();i++){
			board = new Board(board.getNoColumns(),board.getColumnMaxSize()); // reset the board
			board.addTo(player1, i);
			testRestColumnsZero(board,i);
			assertEquals(1,board.getColumnSize(i));
			
			for (int pos=1;pos<board.getColumnMaxSize()-1;pos++){
				board.addTo(player1, i);
				assertEquals(pos+1,board.getColumnSize(i));
			}

			assertFalse(board.isColFull(i));
			board.addTo(player1, i);
			assertEquals(board.getColumnMaxSize(),board.getColumnSize(i));
			assertTrue(board.isColFull(i));		
			board.addTo(player1, i);
			assertEquals(board.getColumnMaxSize(),board.getColumnSize(i));	

		}
	}
		
	private void testRestColumnsZero(Board board,int skippedColumn){
		for (int i = 0;i<board.getNoColumns();i++){
			if (i != skippedColumn){
				assertEquals(0,board.getColumnSize(i));
			}else{
				assertNotSame(0,board.getColumnSize(i));
			}
			
		}
	}
	
	

}
