package core;

public class Tile implements Comparable {
	
	Mark mark;
	char simpleCharDisplay;
	Coord coord;
	
	public static char emptyMarkChar = '.';
	public static char playerAchar = 'x';
	public static char	playerBchar = 'o';
	
	public Tile(Mark mark, char newChar, Coord coord){
		this.mark = mark;
		simpleCharDisplay = newChar;
		setCoord(coord);
	}
	
	public Tile(Coord coord){
		this.mark = Mark.EMPTY;
		simpleCharDisplay = emptyMarkChar;
		setCoord(coord);
	}

	public void setCoord(Coord coord) {
		if (Coord.isValid(coord)){
			this.coord = coord;
		}else{
			coord = null;
			this.mark = Mark.EMPTY;
		}
	}
	

	public Coord getCoord(){
		return coord;
	}

	public Mark getMark() {
		return mark;
	}

	public void setMark(Mark mark) {
		this.mark = mark;
		if (mark == Mark.EMPTY){
			this.simpleCharDisplay = emptyMarkChar;
		} else if (mark==Mark.PLAYER_A){
			this.simpleCharDisplay = playerAchar;
		} else if (mark==Mark.PLAYER_B){
			this.simpleCharDisplay = playerBchar;
		}
	}

	public char getSimpleCharDisplay() {
		return simpleCharDisplay;
	}

	public void setSimpleCharDisplay(char simpleCharDisplay) {
		this.simpleCharDisplay = simpleCharDisplay;
	}

	@Override
	public boolean equals(Object that){
		if (coord.getX() == (((Tile) that).getCoord()).getX()  && coord.getY() == (((Tile) that).getCoord()).getY()){
			if (mark == ((Tile)that).getMark()){
				return true;
			}
			
		}
		return false;
	}
	
	
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString(){
		return mark+" @ "+coord;
	}

}
