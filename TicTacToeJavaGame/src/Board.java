import java.util.ArrayList;

public class Board {
	
	public ArrayList<Player> playersMarks;
	Player player1;
	Player player2;
	Player playerNone = new Player(0,"none");
	boolean hasEnded;
	
	
	
	
	
	public Board(Player player1, Player player2) {
		super();
		reset();
		this.player1 = player1;
		this.player2 = player2;
	}


	
	public Player getPlayerAt(int i){
		return playersMarks.get(i);
	}
	
	public int getMoves(){
		int count = 0;
		for (Player player : playersMarks){
			if (player.getId()!=0) count++;
		}	
		return count;
	}


	public boolean hasEnded() {
		return hasEnded;
	}


	public void addPlayerAt(int i, Player player) {
		if (playersMarks.get(i).getId()==0){
			playersMarks.set(i, player);
		}				
	}
	
	public void reset() {
		playersMarks = new ArrayList<Player>();
		for (int i= 0;i<9;i++){
			playersMarks.add(playerNone);
		}
		this.hasEnded = false;
	}

	public boolean testVictory() {
		// horizontal
		if (isConnectedLine(0,1,2)||isConnectedLine(3,4,5)||isConnectedLine(6,7,8)) return true;
				
		// vertical 
		if (isConnectedLine(0,3,6)||isConnectedLine(1,4,7)||isConnectedLine(2,5,8)) return true;
	
		// diagonals
		if (isConnectedLine(0,4,8)||isConnectedLine(2,4,6)) return true;				
		return false;		
	}
	
	public boolean isConnectedLine(int pos1, int pos2, int pos3){
		int id = playersMarks.get(pos1).getId();
		if (id == 0) return false;
		if (id != playersMarks.get(pos2).getId()) return false;
		if (id != playersMarks.get(pos3).getId()) return false;
		
		return hasEnded = true;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		int count =0;
		for (Player player:playersMarks){
			sb.append(player.getMark());
			if (count == 2|| count ==5 || count == 8){
				sb.append("\n");
			}
			count++;
		}
		return sb.toString();		
	}

	public boolean isEmptyAt(int move) {
		return playersMarks.get(move).getName()=="none";
	}

}
