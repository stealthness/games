package core;

public class R {

	static final String welcome = "Welcome! Tic Tac Toe is a two player game.";
	static final String enterPlayer1 ="Enter player one's name: ";
	static final String enterPlayer2 ="Enter player two's name: ";
	static final String rules = "Players take turns marking a square. Only squares \n"+
								"not already marked can be picked. Once a player has \n"+
    							"marked three squares in a row, they win! If all squares \n"+
    							"are marked and no three squares are the same, a tied game is declared.\n"+
    							"Have Fun! \n\n";
	static final String playAgain = "Play again? (Y/N): ";
	static final String selectAnotherSquare  ="Square can not be selected. Retry";
	static final String invalidMarker = "Invalid marker, try again";
	static final String gameOver = "Game Over";
	static final String gameDraw = "Draw";
	static final String gameWin = "Wins!!";
	
	static String nextPlayerTurn(String player){
		return "It is " + player + "'s turn. Pick a square: ";
	}
	static String selesctAnotherLetter(String player){
		return "Select any letter as " + player + "'s marker: ";
	}
}