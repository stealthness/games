package core;

import java.util.Random;

public class AI {
	
	static int mode = 1;
	
	
	public static int getMove(Game game){
		System.out.print("getMOve():");
		int col ;

		switch(mode){
		
		
		case 0:
			col = 0;
			while (!game.checkValidMove(col)){
				System.out.print("col="+col);
				col++;
			}
			
			return col;
		case 1:
			Random random = new Random();
			col = random.nextInt(game.getMaxNoCol());;
			while (!game.checkValidMove(col)){
				System.out.print("col="+col);
				col =random.nextInt(5);
			}
			return col;
			
		
		}
		return -1;		
	}

	public static int getMode() {
		return mode;
	}

	public static void setMode(int mode) {
		AI.mode = mode;
	}

}
