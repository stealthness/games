package core;

public class Game {
	
	
	public static final String PLAYER1_NAME_DEFAULT = "Player 1";
	public static final String PLAYER2_NAME_DEFAULT = "Player 2";
	public static final int DEFAULT_BOARD_SIZE = 4;
	public static final Player NONE = new Player("None");
	private static final String NO_WINNER = ("None");
	private static final String DRAW = "Draw";
	Player player1 = new Player(PLAYER1_NAME_DEFAULT,'X');
	Player player2 = new Player(PLAYER2_NAME_DEFAULT,'O');
	
	Board board;
	
	boolean hasStarted;
	String winner;
	private Player currentPlayer;
	private int NoMoves;
	
	/**
	 * Creates a new game using default values
	 */
	public Game(){
		this(new Board(DEFAULT_BOARD_SIZE,DEFAULT_BOARD_SIZE));
	}
	
	/**
	 * Creates a new game using
	 * @param board a given board in Board class
	 */
	public Game(Board board){
		this(board,PLAYER1_NAME_DEFAULT,PLAYER2_NAME_DEFAULT);
	}
	

	
	public Game(Board board,String player1Name,String player2Name){
		this.board = board;
		setPlayers(new Player(player1Name,'X'), new Player(player2Name,'O'));;
        resetGame();	 
    }	

	private void resetGame(){
		NoMoves= 0;
        currentPlayer = NONE;
        hasStarted = false;
        winner = NO_WINNER;		
	}
	

	
	// Game functions
	
	public void start() {
		board = new Board(this.getMaxNoCol(),this.getColumnMaxSize());
		NoMoves = 0;
		hasStarted = true;
		setCurrentPlayer(1);
	}	
	
	public void setWinner(String winner) {
		hasStarted = false;
		this.winner = winner;
	}

	public void end() {
		hasStarted = false;
		setWinner(NO_WINNER);		
	}
	
	private void changePlayer() {
		if (player1 == currentPlayer){
			currentPlayer = player2;
		}else{
			currentPlayer = player1;
		}
		
	}
	
	public void addMove(int col) {
		if (hasStarted){
			if (!board.isColFull(col)){
				board.addTo(this.currentPlayer,col);
				changePlayer();
				NoMoves++;
				testGame(col,board.getColumnSize(col)-1);
				
			}			
		}
	}
	


	public boolean checkValidMove(int col) {
		return this.getCurrentColumnSizeAt(col)<this.getColumnMaxSize();
	}
	
	private void testGame(int col, int pos) {
		Player test = this.getCol(col).getPlayerAt(pos);	
		//test vertical - test horizontal - test Diagonally
		if(testVertical(test, col, pos) || testHorizontal(test, col, pos) || 	
				testDiagonalUp(test, col, pos) || testDiagonaldown(test, col, pos)){
			setWinner(test.getName());
		};
				
		// test for a draw
		if (this.getNoMoves()==board.getSize()){
			winner = DRAW;
			hasStarted = false;
		}
		
	}
	
	public boolean testVertical(Player test,int col,int pos) {
		// cannot win vertically before 7 moves or if col is less than 4 items
		if (this.getNoMoves()>= 7 && pos>=3 ){
			for (int j=0;j<4;j++){
				int testPos = pos-3+j;
				if (testPos-j <0 || this.getCol(col).getPlayerAt(testPos).getMark() != test.getMark()){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean testHorizontal(Player test,int col, int pos){
		boolean foundWinner = false;
		if(this.getNoMoves()>3 ){
			for(int i = 0;i<3;i++){
				foundWinner = true;		
				for(int j= 0;j<4;j++){
					int testCol = col-3+j+i;
					if(testCol>=0 && testCol<this.getMaxNoCol()){
						if(this.getCol(testCol).getCurrentSize()>pos){
							if( this.getCol(testCol).getPlayerAt(pos).getMark()!=test.getMark()){
								foundWinner = false;
								break;
							}
						}else{
							foundWinner = false;
							break;	
						}
					}else{
						foundWinner = false;
						break;
					}				
				}
				if ( foundWinner){
					return true;						
				}				
			}
		}
		return false;
	}
	
	public boolean testDiagonalUp(Player test,int col, int pos){
		
		boolean foundWinner;
		
		// fail if less than 10 moves
		if(this.getNoMoves()>9 ){
			for(int i = 0;i<4;i++){
				foundWinner = true;				
				int testCol =col-3+i;
				int testPos =pos-3+i;
				
				// check range
				if(this.checkInRange(testCol, testPos, true)){				
						// check column is not return a null player				
					
					for(int j= 0;j<4;j++){
						// check range
						if( testCol<this.getColumnMaxSize()){
	
							if(this.getCol(testCol).getCurrentSize()>testPos){
							 	
								if( this.getCol(testCol).getPlayerAt(testPos).getMark()!=test.getMark()){
									foundWinner = false;
									i=i+j;
									j=4;
								}								
							}else{
								j=4;
								foundWinner = false;
							}
						testPos++;
						testCol++;
						}	
					}	
					if ( foundWinner){
						setWinner(test.getName());
						return true;						
					}	
				}		
			}
		}
		
		return false;
	}
	
	public boolean testDiagonaldown(Player test,int col, int pos){
		
		boolean foundWinner;
		// fail if less than 10 moves
		if(this.getNoMoves()>9 ){
			
			for(int i = 0;i<4;i++){
				foundWinner = true;				
				int testCol =col-3+i;
				int testPos =pos+3-i;
				
				// check range
				if(this.checkInRange(testCol, testPos, false)){				
									
					// check column is not return a null player	
					for(int j= 0;j<4;j++){
						
						// check range
					
						if(testPos>=0 && this.getCol(testCol).getCurrentSize()>testPos){
						 	
							// check mark is same as test player
							if( this.getCol(testCol).getPlayerAt(testPos).getMark()!=test.getMark()){
								foundWinner = false;
								i=i+j;
								j=4;
							}						
						}else{
							j=4;
							foundWinner = false;
						}
					
						testPos--;
						testCol++;
					}
					
					if ( foundWinner){
						setWinner(test.getName());
						return true;						
					}	
				}		
			}
		}
		
		return false;
	}
	
	private boolean checkInRange(int testColStart, int testPosStart, boolean isUp){
		if (isUp){		
			return (testColStart>=0  && testColStart+3<this.getMaxNoCol() && testPosStart>=0 && testPosStart+3<this.getColumnMaxSize());
		}else{		
			return (testColStart>=0  && testColStart+3<this.getMaxNoCol() && testPosStart-3>=0 && testPosStart<this.getColumnMaxSize());
		}
	}

	// Game getters
	
	public boolean isStarted() {
		return hasStarted;
	}	
	


	public String getWinner() {
		return winner;
	}

	public Player getPlayer(int player) {
		switch(player){
		case 1:
			return player1;
		case 2:
			return player2;
		}
		return NONE;
	}		

	public int getNoMoves() {
		return NoMoves;
	}
	
	public Player getCurrentPlayer() {
		if (!hasStarted){
			return NONE;
		}else{
			return currentPlayer ;
		}		
	}
	
	
	public int getBoardSize() {
		return board.getSize();
	}

	public int getColumnMaxSize() {
		return board.getColumnMaxSize();
	}
	public int getMaxNoCol() {
		return board.getNoColumns();
	}
	
	
	public Column getCol(int i) {
		return board.getColumn(i);
	}

    public int getCurrentColumnSizeAt(int col){
        return board.getColumnSize(col);
    }
    
	// Setters
    
    /**
     * 
     * @param player the winner of the game
     */
    private void setWinner(Player player){
    	this.winner = player.getName();
    	this.hasStarted = false;
    }

	private void setCurrentPlayer(int player) {
		switch(player){
		case 1:
			currentPlayer = player1;
			break;
		case 2:
			currentPlayer =  player2;
			break;
		}
	}	



	public void setPlayer(int choice, Player player){
		switch(choice){
		case 1:
			this.player1 = player;
			break;
		case 2:
			this.player2 = player;
			break;
		}
	}
	

	
	public void setPlayers(Player player1, Player player2) {
        setPlayer(1, player1);
        setPlayer(2, player2);	
	}


    // to string

    public String toString(){
        String newline = "\n";
        StringBuilder sb = new StringBuilder();
        sb.append("hasStarted = "+this.hasStarted+newline);
        sb.append("winner = "+this.getWinner()+newline);
        sb.append("currentPlayer = "+this.getCurrentPlayer().getName()+newline);
        sb.append("players 1 = "+this.getPlayer(1).getName()+", mark = "+this.getPlayer(1).getMark()+newline);
        sb.append("players 2 = "+this.getPlayer(2).getName()+", mark = "+this.getPlayer(2).getMark()+newline);
        for (int i = 0 ; i<this.getMaxNoCol();i++){
        	 sb.append("col "+i+" size = "+this.getCurrentColumnSizeAt(i)+" col -> "+this.getCol(i).toString()+newline);
        }
        sb.append("moves = "+this.getNoMoves()+newline);
        return sb.toString();
    }

	public Player getOtherPlayer() {
		if (player1.getMark()==this.getCurrentPlayer().getMark()){
			return player1;
		}else{
			return player2;
		}
	}




}
