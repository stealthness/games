package test;

import java.util.ArrayList;

import core.Player;

public class GameTestData {
	
	int[] recs;
	String winner;
	int noMoves;
	boolean isStarted;
	int size;

	
	static ArrayList<int[]> data = new ArrayList<int[]>();
	static ArrayList<String> winners = new ArrayList<String>();
	static ArrayList<Integer> moves = new ArrayList<Integer>();
	static ArrayList<Integer> sizes =	new ArrayList<Integer>();
	static ArrayList<Boolean> starts = new ArrayList<Boolean>();
	
	public GameTestData(){
		
	}
	
	public GameTestData(int choice) {
		addData();
		setFields(data.get(choice),winners.get(choice),moves.get(choice),starts.get(choice),sizes.get(choice));
	}
	


	private void setFields(int[] rec, String winner,
			int noMoves, boolean isStarted, int size) {
		this.recs = rec;
		this.winner = winner;
		this.noMoves = noMoves;
		this.isStarted = isStarted;
		this.size = size;
		
	}
	

	public int[] getRecs() {
		return recs;
	}
	public String getWinner() {
		return winner;
	}
	public int getNoMoves() {
		return noMoves;
	}
	public boolean isStarted() {
		return isStarted;
	}
	public int getSize() {
		return size;
	}
	
	static public int getNoTests(){
		return 20;
	}
	
	// TODO Auto-generated method stub
	public Player PLAYER1;
	public Player PLAYER2 ;
	private static final Player AMY = new Player("Amy",'X');
	private static final Player BOB = new Player("Bob",'O');
		
		
	private void addData() {

		/*
		data.add(e);
		winners.add(e);
		moves.add(e);
		sizes.add(e);
		starts.add(e);
		*/
		// 0

		data.add(new int[]{1,1,1});
		winners.add("None");
		moves.add(0);
		sizes.add(4);
		starts.add(false);
		
		////   NO Winners   ////
		
		// 1 - testing 4x4 board
		data.add(new int[]{1,1,1,1});
		winners.add("None");
		moves.add(2);
		sizes.add(4);
		starts.add(true);	
		
		
		// 2 - testing 7x7 board
		data.add(new int[]{1,1,1,1});
			winners.add("None");
			moves.add(2);
			sizes.add(7);
			starts.add(true);	
		
		// 3  testing 10x10 board
			data.add(new int[]{1,1,1,1});
			winners.add("None");
			moves.add(2);
			sizes.add(10);
			starts.add(true);
			
		// 4 testing 10x10 board
			data.add(new int[]{1,1,1,1});
			winners.add("None");
			moves.add(9);
			sizes.add(10);
			starts.add(true);
			
		
		// 5 rec1
		
			data.add(new int[]{1,1,1,1});
			winners.add("Player 1");
			moves.add(7);
			sizes.add(4);
			starts.add(false);
		
		



	
		///     V   V  V     ///
		
		// 6 - v0 Bob win vertically on col 3
		
			data.add(new int[]{1,1,1,1});
			winners.add("Player 2");
			moves.add(8);
			sizes.add(4);
			starts.add(false);
			
		

		
		//7 - v1 amy wins vertically col 6
			
			
			data.add(new int[]{1,1,1,1});
			winners.add("Player 1");
			moves.add(9);
			sizes.add(7);
			starts.add(false);
			
		// 8 - 
			
			data.add(new int[]{1,1,1,1});
			winners.add("Player 2");
			moves.add(8);
			sizes.add(4);
			starts.add(false);
			
		// 9 - 
		
			data.add(new int[]{1,1,1,1});
			winners.add("Player 2");
			moves.add(8);
			sizes.add(7);
			starts.add(false);
			
			
		// 10 - 	
			data.add(new int[]{1,1,1,1});
			winners.add("Player 2");
			moves.add(8);
			sizes.add(7);
			starts.add(false);
		
			
		// 11
			
			data.add(new int[]{1,1,1,1});
			winners.add("Player 1");
			moves.add(9);
			sizes.add(7);
			starts.add(false);
		
		////     H    H    H    ////	
			
			
		// 12 Amy  win horizontally on row 0
			
			data.add(new int[]{1,1,1,1});
			winners.add("Player 1");
			moves.add(7);
			sizes.add(4);
			starts.add(false);
		
		// 13 Bob  win horizontally on row 2
		
			data.add(new int[]{1,1,1,1});
			winners.add("Player 2");
			moves.add(12);
			sizes.add(4);
			starts.add(false);
			
		// 14 -7 
			data.add(new int[]{1,1,1,1});
			winners.add("Player 2");
			moves.add(12);
			sizes.add(4);
			starts.add(false);
			
		// 15 -  Bob  win horizontally on row 2 for 7x7 board
			
			data.add(new int[]{1,1,1,1});
			winners.add("Player 2");
			moves.add(12);
			sizes.add(7);
			starts.add(false);	
			
		////   Diagonal ////
			
		// 16 - amy win diagonally up
			data.add(new int[]{1,1,1,1});
			winners.add("Player 1");
			moves.add(11);
			sizes.add(4);
			starts.add(false);
			
			
		// 17	
			
			data.add(new int[]{1,1,1,1});
				winners.add("Player 1");
				moves.add(17);
				sizes.add(7);
				starts.add(false);	
			
				
				// 18
				
				data.add(new int[]{1,1,1,1});
				moves.add(21);
				sizes.add(7);
				starts.add(false);	
		// 19 - diagonally down
			
				data.add(new int[]{1,1,1,1});
				winners.add("Player 2");
				moves.add(16);
				sizes.add(7);
				starts.add(false);

	}
	

}
