public class CompAI {
	
	public static int randomMove(){
		return (int) (Math.random()*9);
	}
	
	public static int weightedMove(Board board) {
		int[] weight = {2,1,2,1,3,1,2,1,2};
		int[] choiceScore = new int[9];
		// add weights
		for (int i = 0;i<9;i++){
			if (board.isEmptyAt(i)){
				choiceScore[i] = weight[i];
			}else{
				choiceScore[i] = -1;
			}			
		}
		// find highest weight
		int move = 0;
		for (int i = 0;i<9;i++){
			if (choiceScore[i]>choiceScore[move]){
				move = i;
			}
		}	
		return move;
	}
	
	public static int bestMove(Board board, Player currPlayer, Player oppPlayer) {
		int[] weight = {2,1,2,1,3,1,2,1,2};		
		int[] choiceScore = new int[9];
		
		// find any winning move
		for (int i = 0;i<9;i++){
			if (board.isEmptyAt(i)){
				Board copyOfBoard = board;
				copyOfBoard .addPlayerAt(i, currPlayer);
				if (copyOfBoard.testVictory()){
					return i;
				}
			}		
		}
		
		System.out.println("no win move");
			
		
		// block opponents winning move
		
		for (int i = 0;i<9;i++){
			if (board.isEmptyAt(i)){
				Board copyOfBoard = board;
				copyOfBoard .addPlayerAt(i, oppPlayer);
				if (copyOfBoard.testVictory()){
					return i;
				}
			}		
		}
		
		System.out.println("no opp win move");
		
		//
		
		for (int i = 0;i<9;i++){
			if (board.isEmptyAt(i)){
				choiceScore[i] = weight[i];
			}		
		}
		
		

		// add weights
		for (int i = 0;i<9;i++){
			if (board.isEmptyAt(i)){
				choiceScore[i] = weight[i];
			}else{
				choiceScore[i] = -1;
			}	
			System.out.print("choice = "+ choiceScore[i] +", ");
		}
		// find highest weight
		int move = 0;
		for (int i = 0;i<9;i++){
			if (choiceScore[i]>choiceScore[move]){
				move = i;
				
			}
			System.out.print("move = "+ move +", ");
		}	
		System.out.println(", final move = "+ move);
		System.out.println(board.toString());
		return move;
	}

}
