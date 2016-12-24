import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CompAiTest {


	Player player1 = new Player(1, "Amy", false, 'x');;
	Player player2 = new Player(2, "Bob", true, 'o');;
	private Board board= new Board(player1, player2);



	@Test
	public void testWeightedMove(){
		board.addPlayerAt(CompAI.weightedMove(board), player2);
		assertFalse(board.isEmptyAt(4));
		board.addPlayerAt(CompAI.weightedMove(board), player2);
		assertFalse(board.isEmptyAt(0));
		board.addPlayerAt(CompAI.weightedMove(board), player2);
		assertFalse(board.isEmptyAt(2));
		board.addPlayerAt(CompAI.weightedMove(board), player2);
		assertFalse(board.isEmptyAt(6));
		board.addPlayerAt(CompAI.weightedMove(board), player2);
		assertFalse(board.isEmptyAt(8));
		board.addPlayerAt(CompAI.weightedMove(board), player2);
		assertFalse(board.isEmptyAt(1));
		board.addPlayerAt(CompAI.weightedMove(board), player2);
		assertFalse(board.isEmptyAt(3));
	}

}
