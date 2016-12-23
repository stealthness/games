package core;

public class Player {
	
	String name;
	private char mark;
	boolean isAI;

	public Player(String name) {
		this.name = name;
		isAI = false;
	}
	
	public boolean isAI() {
		return isAI;
	}

	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}

	public Player(String name, char mark){
		this(name);
		this.mark = mark;
	}
	
	public Player(String name, char mark, boolean isAI){
		this(name, mark);
		this.isAI = isAI;		
	}

	public String getName() {
		
		return name;
	}

	public char getMark() {
		return mark;		
	}

}
