package test;


import core.Board;
import core.Game;
import core.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest  {
	
	private static final String PLAYER1_NAME = "Player 1";
	private static final String PLAYER2_NAME = "Player 2";
	public Player PLAYER1;
	public Player PLAYER2 ;
	static final Player N_ONE = new Player("None");
	public static final String NONE = "None";
	private static final String DRAW = "Draw";
	private static final Player NO_ONE = new Player(NONE);
	Game game,largeGame;

	@BeforeEach
	protected void setUp() throws Exception {
		game = new Game();
		largeGame = new Game(new Board(7,7));
		PLAYER1 = new Player(PLAYER1_NAME,'X');
		PLAYER2 = new Player(PLAYER2_NAME,'O');
	}
	
	
	public void testGameStart(){
		testGameState(game, PLAYER1, PLAYER2, NONE, false,0);
		game.start();
		testGameState(game, PLAYER1, PLAYER2, NONE, true,0);
		game.end();
		testGameState(game, PLAYER1, PLAYER2, NONE, false,0);
	}

	@Test
	public void testSetPlayerName(){
		assertEquals(PLAYER1_NAME,game.getPlayer(1).getName());
		assertEquals(PLAYER2_NAME,game.getPlayer(2).getName());
		assertEquals('X',game.getPlayer(1).getMark());
		assertEquals('O',game.getPlayer(2).getMark());
		Player amy = new Player("Amy");
		Player bob = new Player("Bob");
		testGameState(game, PLAYER1, PLAYER2);
		game.setPlayer(1,new Player (amy.getName()));
		testGameState(game, amy, PLAYER2);
		game.setPlayer(2, new Player(bob.getName()));
		testGameState(game, amy, bob);
		
		
	}
	
	@Test
	public void testAddMove(){
		
		// before and after start game
		testGameState(game,NONE,false,0,NO_ONE);
		game.start();
		testGameState(game,NONE,true,0,PLAYER1);
				
		/// add moves
		for (int col=0; col<game.getMaxNoCol();col++){
			game = new Game();
			game.start();
			testGameState(game,NONE,true,0,PLAYER1);
			for (int i=0;i<game.getColumnMaxSize();i+=2){
				game.addMove(col);
				testGameState(game,NONE,true,i+1,PLAYER2);
				
				if(i+1<game.getColumnMaxSize()){
					game.addMove(col);
					testGameState(game,NONE,true,i+2,PLAYER1);					
				}
			}				
			// column 0 should be full attempting to add to column should not change state.
			game.addMove(col);
			testGameState(game,NONE,true,game.getColumnMaxSize(),PLAYER1);
		}		
	}

	

	@Test
	public void testFullMoveGame(){	
		game.start();	
		testGameState(game,NONE,true,0,PLAYER1);
	
		for (int i = 0;i<game.getMaxNoCol();i+=2){
			game.addMove(i);
			assertEquals(NONE,game.getWinner());
			game.addMove(i+1);
			game.addMove(i);
			game.addMove(i+1);
			game.addMove(i);
			game.addMove(i+1);
			assertEquals(NONE,game.getWinner());		
		}
		
		assertEquals(12,game.getNoMoves());
		testGameState(game,NONE,true,12);
		testVisual(game,new String[]{"XXX-","OOO-","XXX-","OOO-"});
		
		
		game.addMove(1);
		testGameState(game,NONE,true,13);
		assertEquals(13,game.getNoMoves());
		testVisual(game,new String[]{"XXX-","OOOX","XXX-","OOO-"});
		
		
		
		game.addMove(0);
		testVisual(game,new String[]{"XXXO","OOOX","XXX-","OOO-"});
		testGameState(game,NONE,true,14);	
		assertEquals(14,game.getNoMoves());
		
		
		game.addMove(3);
		testGameState(game,NONE,true);	
		assertEquals(15,game.getNoMoves());
		testVisual(game,new String[]{"XXXO","OOOX","XXX-","OOOX"});
		
		game.addMove(2);
		assertEquals(16,game.getNoMoves());
		testVisual(game,new String[]{"XXXO","OOOX","XXXO","OOOX"});
		
		testGameState(game,DRAW,false,game.getBoardSize());	
		
		// end of game
		game.addMove(2);
		assertEquals(16,game.getNoMoves());
		testVisual(game,new String[]{"XXXO","OOOX","XXXO","OOOX"});
		
			
	}
	
	public void testVisual(Game game, String[] strings){
		if(game.getColumnMaxSize()==4){
			assertEquals(strings[0],game.getCol(0).toString());
			assertEquals(strings[1],game.getCol(1).toString());
			assertEquals(strings[2],game.getCol(2).toString());
			assertEquals(strings[3],game.getCol(3).toString());	
		}

	}
	

	
	public void testGetCurrentColSize(){
		game.start();
		// 0
		PlayerTest.assertPlayers(PLAYER1, game.getCurrentPlayer());
		assertEquals(0,game.getCurrentColumnSizeAt(0));
		
		//1
		game.addMove(0);		
		assertEquals(1,game.getCurrentColumnSizeAt(0));
		PlayerTest.assertPlayers(PLAYER2, game.getCurrentPlayer());
		
		//2
		game.addMove(0);
		assertEquals(2,game.getCurrentColumnSizeAt(0));
		PlayerTest.assertPlayers(PLAYER1, game.getCurrentPlayer());

		//3
		game.addMove(0);	
		assertEquals(3,game.getCurrentColumnSizeAt(0));
		PlayerTest.assertPlayers(PLAYER2, game.getCurrentPlayer());
		
		//4
		game.addMove(0);
		assertEquals(4,game.getCurrentColumnSizeAt(0));
		PlayerTest.assertPlayers(PLAYER1, game.getCurrentPlayer());
		game.addMove(0);
		assertEquals(4,game.getCurrentColumnSizeAt(0));
		PlayerTest.assertPlayers(PLAYER1, game.getCurrentPlayer());
		
		for (int i = 1 ; i<game.getMaxNoCol()-1;i++){
			assertEquals(0,game.getCurrentColumnSizeAt(i));
			game.addMove(i);
			assertEquals(1,game.getCurrentColumnSizeAt(i));
			game.addMove(i);
			assertEquals(2,game.getCurrentColumnSizeAt(i));
			
		}
	}
	
	
	public void testVictoryStraightdown(){
		 testVictoryStraightdown(new Game(new Board(7,7)));
		 testVictoryStraightdown(new Game(new Board(10,10)));
		 testVictoryStraightdown(new Game(new Board(4,4)));
		 
	}
	
	public void testVictoryStraightdown(Game game){
		game.start();
		for(int i = 0;i<3;i++){
			game.addMove(0);
			game.addMove(1);
		}
		testVisual(game,new String[]{
				"XXX-",
				"OOO-",
				"----",
				"----"});	
		testGameState(game,NONE,true,6);
		assertEquals(PLAYER1.getMark(),game.getPlayer(1).getMark());
		
		
		
		game.addMove(0);
		testVisual(game,new String[]{
				"XXXX",
				"OOO-",
				"----",
				"----"});
		//game should be won now
		testGameState(game,PLAYER1_NAME,false,7);
		
		// add move should not change state
		game.addMove(0);
		testGameState(game,PLAYER1_NAME,false,7);
	}
	
	
	public void testHorizontalVictory(){
		 testHorizontalVictory(new Game(new Board(4,4)));
		 testHorizontalVictory(new Game(new Board(7,7)));
		 testHorizontalVictory(new Game(new Board(10,10)));
	}
	
	
	public void testHorizontalVictory(Game game){
		game.start();
		for(int i =0;i<3;i++){
			game.addMove(i);
			game.addMove(i);
		}
		game.addMove(3);
		
		//game should be won now
		testVisual(game,new String[]{
				"XO--",
				"XO--",
				"XO--",
				"X---"});
		
		testGameState(game,PLAYER1_NAME,false,7);
		
		// add move should not change state
		game.addMove(0);
		testGameState(game,PLAYER1_NAME,false,7);
		
		game = new Game();
		game.start();
		for(int i =0;i<3;i++){
			game.addMove(i);
			game.addMove(i);
		}

		
		game.addMove(0);
		testGameState(game,NONE,true,7);
		testVisual(game,new String[]{
				"XOX-",
				"XO--",
				"XO--",
				"----"});
		game.addMove(3); //o
		game.addMove(2); //xox	
		game.addMove(0); //xoxo
		game.addMove(1); //xox
		game.addMove(1); //xoxo
		game.addMove(2); //xox
		game.addMove(3); //oo
			
		//game should be won now
		testVisual(game,new String[]{
				"XOXO",
				"XOXO",
				"XOXX",
				"OO--"});
		testGameState(game,PLAYER2_NAME,false,14);
		// adding move should not change game state
		
		game.addMove(3);
		testVisual(game,new String[]{
				"XOXO",
				"XOXO",
				"XOXX",
				"OO--"});
		testGameState(game,PLAYER2_NAME,false,14);
	}
	
	public void testVictoryDiagonalUp(){
		largeGame.start();
		largeGame.addMove(0); // x
		largeGame.addMove(1); // o
		largeGame.addMove(1); // ox
		largeGame.addMove(2); // o
		largeGame.addMove(3); // x
		
		largeGame.addMove(2); //oo
		largeGame.addMove(2); //oox
		
		largeGame.addMove(3); //xo
		largeGame.addMove(0); //xx
		largeGame.addMove(3); //xoo
		largeGame.addMove(3); //xoox

		//largeGame should be won now
		assertEquals("XX-----",largeGame.getCol(0).toString());
		assertEquals("OX-----",largeGame.getCol(1).toString());
		assertEquals("OOX----",largeGame.getCol(2).toString());
		assertEquals("XOOX---",largeGame.getCol(3).toString());
		
		testGameState(largeGame,PLAYER1_NAME,false,11);
		
		// add move should not change state
		largeGame.addMove(0);
		testGameState(largeGame,PLAYER1_NAME,false,11);
		
		largeGame.start();
		largeGame.addMove(0); // X
		largeGame.addMove(1); // O
		largeGame.addMove(1); // oX
		largeGame.addMove(2); // O
		largeGame.addMove(3); // X
		
		largeGame.addMove(2); //oo
		largeGame.addMove(3); //XX
		
		largeGame.addMove(2); //ooO
		largeGame.addMove(2); //oooX
		largeGame.addMove(3); //xxO
		largeGame.addMove(3); //xxoX
		largeGame.addMove(2); //oooxO
		largeGame.addMove(3); //xxoxX
		largeGame.addMove(3); //xxoxxO
		largeGame.addMove(1); //oxX
		largeGame.addMove(1);
		largeGame.addMove(0);

		//largeGame should be won now
		assertEquals("XX-----",largeGame.getCol(0).toString());
		assertEquals("OXXO---",largeGame.getCol(1).toString());
		assertEquals("OOOXO--",largeGame.getCol(2).toString());
		assertEquals("XXOXXO-",largeGame.getCol(3).toString());
		
		testGameState(largeGame,PLAYER1_NAME,false,17);
		
		// add move should not change state
		largeGame.addMove(0);

	}
	
	public void testVictoryDiagonalDown(){
/*		largeGame.start();
		largeGame.addMove(0); // x
		largeGame.addMove(1); // o
		largeGame.addMove(1); // ox
		largeGame.addMove(2); // o
		largeGame.addMove(3); // x
		
		largeGame.addMove(2); //oo
		largeGame.addMove(2); //oox
		
		largeGame.addMove(3); //xo
		largeGame.addMove(0); //xx
		largeGame.addMove(3); //xoo
		largeGame.addMove(3); //xoox

		//largeGame should be won now
		assertEquals("XX-----",largeGame.getCol(0).toString());
		assertEquals("OX-----",largeGame.getCol(1).toString());
		assertEquals("OOX----",largeGame.getCol(2).toString());
		assertEquals("XOOX---",largeGame.getCol(3).toString());
		
		testGameState(largeGame,PLAYER1_NAME,false,11);
		
		// add move should not change state
		largeGame.addMove(0);
		testGameState(largeGame,PLAYER1_NAME,false,11);
		
		largeGame.start();
		largeGame.addMove(0); // X
		largeGame.addMove(1); // O
		largeGame.addMove(1); // oX
		largeGame.addMove(2); // O
		largeGame.addMove(3); // X
		
		largeGame.addMove(2); //oo
		largeGame.addMove(3); //XX
		
		largeGame.addMove(2); //ooO
		largeGame.addMove(2); //oooX
		largeGame.addMove(3); //xxO
		largeGame.addMove(3); //xxoX
		largeGame.addMove(2); //oooxO
		largeGame.addMove(3); //xxoxX
		largeGame.addMove(3); //xxoxxO
		largeGame.addMove(1); //oxX
		largeGame.addMove(1);
		largeGame.addMove(0);

		//largeGame should be won now
		assertEquals("XX-----",largeGame.getCol(0).toString());
		assertEquals("OXXO---",largeGame.getCol(1).toString());
		assertEquals("OOOXO--",largeGame.getCol(2).toString());
		assertEquals("XXOXXO-",largeGame.getCol(3).toString());
		
		testGameState(largeGame,PLAYER1_NAME,false,17);
		
		// add move should not change state
		largeGame.addMove(0);*/
	
	}
	



	
	/// private methods
	

	
	static void testGameState(Game testGame,String expWinner, boolean hasStarted, int expNoMOves, Player expPlayerMove){
		ColumnTest.assertPlayer(expPlayerMove, testGame.getCurrentPlayer());
		testGameState(testGame, expWinner, hasStarted, expNoMOves);
	}
	
	public static void testGameState(Game testGame,Player expPlayer1, Player expPlayer2, String expWinner, boolean hasStarted,int expNoMoves){
		testGameState(testGame, expPlayer1, expPlayer2, expWinner, hasStarted);
		assertEquals(expNoMoves,testGame.getNoMoves());
	}
	
	static void testGameState(Game testGame,Player expPlayer1, Player expPlayer2, String expWinner, boolean hasStarted){
		assertEquals(hasStarted,testGame.isStarted());
		if (expWinner != NONE){
			assertEquals(expWinner,testGame.getWinner());
		}
		testGameState(testGame, expPlayer1, expPlayer2);
	}
	
	static void testGameState(Game testGame,String expWinner, boolean hasStarted, int expNoMoves){
		assertEquals(expNoMoves,testGame.getNoMoves());
		testGameState(testGame,expWinner,hasStarted);
	}
	
	static void testGameState(Game testGame,String expWinner, boolean hasStarted){
		if (expWinner != NONE){
			assertEquals(expWinner,testGame.getWinner());
		}
		assertEquals(hasStarted,testGame.isStarted());
	}
	
	static void testGameState(Game testGame,Player expPlayer1, Player expPlayer2){
		ColumnTest.assertPlayer(expPlayer1, testGame.getPlayer(1));
		ColumnTest.assertPlayer(expPlayer2, testGame.getPlayer(2));
	}
	
	
	
	

}
