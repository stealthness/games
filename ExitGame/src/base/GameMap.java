package base;

import base.tile.EmptyTile;
import base.tile.GoalTile;
import base.tile.SolidTile;
import base.tile.Tile;

import java.util.Arrays;

public class GameMap {

	public static final int DEFAULT_LENGTH = 10;
	public static final int DEFAULT_WIDTH = 10;

	enum MOVE {UP,DOWN,LEFT,RIGHT};
	

	Tile[][] gameMap  = new Tile[10][10];
	BugCharacter bug ;
	int length = DEFAULT_LENGTH;
	int width = DEFAULT_WIDTH;
	
	public GameMap(int length, int width){
		this.length = length;
		this.width = width;
		gameMap  = new Tile[length][width];
		init();
	}

	private void init() {
        for (int i =0;i<length;i++){
			for (int j=0;j<width;j++){
				gameMap[i][j] = new EmptyTile();
			}
		}
	}

	public GameMap() {
		this(10,10);
	}

	public int getSize() {
		return gameMap.length*gameMap[0].length;
	}

	/**
	 * @return a string representation on the GameMap
	 */
	public String toString(){
		StringBuilder output = new StringBuilder();
		for (int i =0;i<length;i++){
			for (int j=0;j<width;j++){
				if (bug!=null && bug.isAt(i,j)){
					output.append(bug.getMark());
				}else{								
					if (gameMap[i][j]!=null && !gameMap[i][j].isHidden()){
						output.append(gameMap[i][j].getMark());	
					}else{
						output.append("#");
					}				
				}			
			}
			output.append(System.lineSeparator());
		}
		return output.toString();
	}


	public String showAll() {		
		StringBuilder output = new StringBuilder();
		for (int i =0;i<length;i++){
			for (int j=0;j<width;j++){
				if (gameMap[i][j]!=null){
					output.append(gameMap[i][j].getMark());
				}
				
			}
			output.append(System.lineSeparator());
		}
		return output.toString();
	}


	public void createBug(int sightLength){
		bug = new BugCharacter(-1,-1);
		bug.setSightLength(sightLength);
	}

	public void setBugAt(int i, int j) {
		if (bug!=null){
			bug.moveTo(i,j);
		}else{
			bug = new BugCharacter(i,j);
		}
		reveal(i,j);
		
	}

	private void reveal(int i, int j) {
		int adj = bug.getSightLength();
		for (int k = i-adj;k<i+adj+1;k++){
			for (int m=j-adj;m<j+adj+1;m++){
				if (isValid(k,m)){
					if (adj==1){
						gameMap[k][m].reveal();
					} if (adj==2){
						int maxSquare = Math.max(Math.abs(i-k), Math.abs(j-m));
						if (maxSquare==1){
							gameMap[k][m].reveal();
						}else{
							if (Math.abs(i-k)== Math.abs(j-m)){
								//deals with corners
								gameMap[k][m].reveal();
							}else{
								// deals with sides
								gameMap[k][m].reveal();
							}
						}
					} else{
						gameMap[k][m].reveal();
					}
					
				}
				
			}
		}
		
	}

	private boolean isValid(int k, int m) {
		return k>=0 && k<length && m>=0 && m<width;
	}

	public BugCharacter getBug() {
		if (bug!=null){
			return bug;
		}else{
			return new BugCharacter(-1, -1);
		}
		
	}


	public void setBlockAT(int i, int j) {
		gameMap[i][j]= new SolidTile();
		
	}

	public void setGoalAT(int i, int j) {
		gameMap[i][j] = new GoalTile();
		
	}

	public Tile getTileAT(int i, int j) {
		return gameMap[i][j] ;
	}

	public char getMarkAT(int i, int j) {
		if (gameMap[i][j].isHidden()){
			return Tile.MARK;
		}else if(bug.isAt(i, j)){
			return BugCharacter.MARK;
		}else{
			return gameMap[i][j].getMark();
		}		
	}

	// Getter and setter

	public int getLength() {
		return gameMap.length;
	}


	public int getWidth() {
		return gameMap[0].length;
	}

}
