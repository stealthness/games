

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToe {
	
	private static final char n = R.n;
	
	private Player player1;
	private Player player2;
	private static Display display;
	Board board;

	
	static BufferedReader br;

	
	
	public static void main(String[] args) {
		TicTacToe now = new TicTacToe();
		if (args != null){
			now.setMatch(0);
		} else{
			now.setMatch(1);
		}


	}

	public void setMatch(int numberAIOfPlayers){
		switch(numberAIOfPlayers){
			case 0:
				this.player1 = new Player(1, R.player1Name,false,R.player1Mark);
				this.player2 = new Player(2,R.player2Name,false,R.player2Mark);
				break;
			case 1:
				this.player1 = new Player(1,R.player1Name,false,R.player1Mark);
				this.player2 = new Player(2,R.player2Name,true,R.player2Mark);
				break;
			default:
				this.player1 = new Player(1,R.player1Name,true,R.player1Mark);
				this.player2 = new Player(2,R.player2Name,true,R.player2Mark);
		}

		startGame();
	}


	public void startMatch() {

		br = new BufferedReader(new InputStreamReader(System.in));
		display = new Display(System.out);
		if (antherGame(R.StartQuestion) == n ) {
			return;
		}

		board = new Board(player1,player2);
		
		display.out(R.instructions);
		display.out(R.numPad);
		display.out(R.begin);
	
		do {
			board.reset();		
			Player winningPlayer = startGame();	
			winningPlayer.addWin();
			display.score(winningPlayer,player1,player2);
			display.out(board.toString());
		} while (antherGame(R.startAnotherGame)!= R.n);

		display.out(R.gameOver);;
	}
	
	

	private char antherGame(String question) {
		display.out(question);		
		try {
			String antherGame = br.readLine();
			return (antherGame!=null)?antherGame.charAt(0):'n';
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 'n';
	}


	/**
	 * Starts a game of TicTacToe
	 * @return
	 */
	private Player startGame() {
		if (display==null){
            display = new Display(System.out);
        }
        if (board == null){
            board = new Board(player1,player2);
        }
		display.out(board.toString());
		Player winningPlayer = new Player(0,R.none);
		Player currentPlayer = player1;
		boolean isPlaying = true;
		
		while (isPlaying) {
			
			if (!currentPlayer.isAI()){
				board.addPlayerAt(playerMove(), currentPlayer);
			}else{
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}
				board.addPlayerAt(compMove(), currentPlayer);
			}
			if (board.testVictory()){
				isPlaying = false;
				winningPlayer = currentPlayer;
				display.out(String.format(R.winnerIs,winningPlayer.getName()));
			} else if (testDraw()){
				isPlaying = false;
				display.out(R.draw);
			} else {				
				currentPlayer = (currentPlayer==player1)?player2:player1;				
			}
			display.out(board.toString());
		}
		return winningPlayer;
	}

	
	private boolean testDraw() {
		return board.getMoves()==9;
	}
	
	/**
	 * Gets and returns a AI move
	 * @return
	 */
	private int compMove() {
		int compMove = CompAI.bestMove(board,player2,player1);
		while (!board.isEmptyAt(compMove)){
			compMove = CompAI.bestMove(board,player2,player1);
		}
		display.out(String.format(R.compPlays, compMove));
		return compMove;
	}


	/**
	 * Ask and returns for player for a move
	 * @return players move
	 */
	private int playerMove() {		
		display.out(R.playerMove);
		int move = -1;
        String line="";

        while (move == -1) {System.out.println("< >");
            try {
                line = br.readLine();
            } catch (Exception e) {

            }
            if (line == null || line.equals("") ){

                continue;
            } else {
                System.out.println("<1>");
                move = Integer.parseInt(line);
                System.out.println("<2>");
                if ((move < 1 || move > 9 || !board.isEmptyAt(move - 1))) {
                    System.out.println("<3>");
                    display.out(R.invalidMove);
                    move = -1;
                }else{
                    return move;
                }
            }
        }

		return move-1;
	}




}