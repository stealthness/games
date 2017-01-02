package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author www.javadb.com
 */
public class TicTacToe {
	
	Board board2 = new Board();
    private char[][] board = new char[3][3];
    private Mark player1 = Mark.PLAYER_A;
    private Mark player2 = Mark.PLAYER_B;
    
    private int currentPlayer;
    private int plays;	
    
    private BufferedReader reader ;
    
	public TicTacToe(){
    	reader = new BufferedReader(new InputStreamReader(System.in));
	}


    TicTacToe(BufferedReader reader){
    	super();
    	this.reader = reader;
    }
           

    protected void init() {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {       	
                board[i][i1] = Character.forDigit(++counter, 10);
            }
        }
        currentPlayer = 1;
        plays = 0;
        
        
    }

    protected void switchPlayers() {
        if (getCurrentPlayer() == 1) {
            setCurrentPlayer(2);
        } else {
            setCurrentPlayer(1);
        }
        setPlays(getPlays() + 1);
    }

    protected boolean placeMarker(int play) {
        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                if (board[i][i1] == Character.forDigit(play, 10)) {
                   // board[i][i1] = (getCurrentPlayer() == 1) ? getMarker1() : getMarker2();
                    return true;
                }
            }
        }
        return false;
    }
    
    
    /**
     * Will place [mark] at [coord] return true if successful
     * @param mark
     * @param coord
     * @return
     */
    protected boolean placeMarkerAt(Mark mark, Coord coord){
    	Tile tile = board2.findTileAt(coord);
    	if (tile.getMark() == Mark.EMPTY || tile.getMark() != mark){
    		board2.setPlayerAt(mark, coord);
    	}
		return false;   	
    }

    protected boolean winner() {
    	char current = ' ';
        //Checking checkColumns(current)||
        return checkRows(current) ||  checkDiagonals(current);
    }
    
    private boolean checkRows(char current){
    	
        for (int i = 0; i < 3; i++) {
            int i1 = 0;
            for (i1 = 0; i1 < 3; i1++) {
            	
            	
/*                if (!Character.isLetter(board[i][i1])) {
                    break;
                }
                if (i1 == 0) {
                    current = board[i][i1];
                } else if (current != board[i][i1]) {
                    break;
                }
                if (i1 == 2) {
                    //Found winner
                    return true;
                }*/
                
                if (checkStraight(i,i1,current) || checkStraight(i1,i,current)){
                	return true;
                }else{
                	break;
                }
                
                
            }
        }
		return false;
    }
    
    private boolean checkStraight(int x,int y, char current){
    	
        if (!Character.isLetter(board[x][y])) {
        	return false;
        }
        if (y == 0) {
            current = board[x][y];
        } else if (current != board[x][y]) {
        	return false;
        }
        if (y == 2) {
            //Found winner
            return true;
        }
        return false;
    }
    
    private boolean checkColumns(char current){
            for (int i = 0; i < 3; i++) {
            current = ' ';
            int i1 = 0;
            for (i1 = 0; i1 < 3; i1++) {
                if (!Character.isLetter(board[i1][i])) {
                    break;
                }
                if (i1 == 0) {
                    current = board[i1][i];
                } else if (current != board[i1][i]) {
                    break;
                }
                if (i1 == 2) {
                    //Found winner
                    return true;
                }
            }
        }
		return false;    	
    }

    private boolean checkDiagonals(char current){
    	current = board[0][0];
        if (Character.isLetter(current) && board[1][1] == current && board[2][2] == current) {
            return true;
        }
        current = board[2][0];
        if (Character.isLetter(current) && board[1][1] == current && board[0][2] == current) {
            return true;
        }
        return false;
    }
    
    // 

    protected String getRules() {
        return R.rules;
    }

    protected String getPrompt() {
        String prompt = "";
        try {
            prompt = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prompt;
    }

    protected String drawBoard() {
        StringBuilder builder = new StringBuilder("Game board: \n");
        for (int i = 0; i < 3; i++) {
            for (int i1 = 0; i1 < 3; i1++) {
                builder.append("[" + board2.findTileAt(new Coord(i,i1)).getSimpleCharDisplay() + "]");
            }
            builder.append("\n");
        }
        builder.append("\n");
        return builder.toString();
    }
    
    public Mark getPlayer(int player){
    	return (player==1)?getPlayer1():getPlayer2();
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public Mark getPlayer1() {
        return player1;
    }

    public void setPlayer1(Mark player) {
        this.player1 = player;
    }

    public Mark getPlayer2() {
        return player2;
    }

    public void setPlayer2(Mark player2) {
        this.player2 = player2;
    }

	public void setMarker(int player, char c) {
		if (player==0){
			//setMarker1(c);
		}else{
			//setMarker2(c);
		}
		
	}


	public int size() {
		return board2.size();
	}
}


