package base;

import res.DIR;

public class BugCharacter extends Character{
	
	static final char MARK = 'b';
	

	public BugCharacter(int i, int j) {
		position = new int[2];
		position[0] = i;
		position[1] = j;
		setSightLength(1);
		direction = DIR.NORTH;
	}

	public char getMark() {
		return MARK;
	}


}
