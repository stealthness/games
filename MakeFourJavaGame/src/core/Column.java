package core;

import java.util.ArrayList;

public class Column {

	private int maxSizeCol;
	ArrayList<Player> players = new ArrayList<Player>();

	public Column(int maxSizeCol) {
		this.maxSizeCol = maxSizeCol;
		players= new ArrayList<Player>();
	}

	public int getMaxSize() {
		return maxSizeCol;
	}

	public int getCurrentSize() {
		
		return players.size();
	}

	public ArrayList<Player> getHoles() {
		return players;
	}

	public void setHoles(ArrayList<Player> holes) {
		this.players = holes;
	}

	public void addPlayer(Player player) {
		if (getCurrentSize()<maxSizeCol){
			players.add(player);
		}	
	}

	public Player getPlayerAt(int i) {
		return players.get(i);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (Player p:players){
			sb.append(p.getMark());
		}
		for (int i = players.size();i<maxSizeCol;i++){
			sb.append("-");
		}
		return sb.toString();	
	}
}
