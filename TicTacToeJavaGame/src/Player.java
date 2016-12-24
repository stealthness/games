public class Player {

	int id;
	String Name;
	boolean isAI;
	char mark;
	int wins;

	// constructors
	
	public Player(int id, String name, boolean isAI, char mark) {
		super();
		this.id = id;
		Name = name;
		this.isAI = isAI;
		this.mark = mark;
		wins = 0;
	}

	public Player(int id, String name) {
		super();
		this.id = id;
		Name = name;
		this.mark = '-';
	}
	
	// methods
	
	public void addWin() {
		this.wins++;		
	}
	
	// getter and setters
	
	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public boolean isAI() {
		return isAI;
	}

	public char getMark() {
		return mark;
	}



}
