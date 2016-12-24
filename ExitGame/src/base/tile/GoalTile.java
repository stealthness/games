package base.tile;


public class GoalTile extends Tile {
	
	public static char MARK = 'O';

	@Override
	public boolean isSolid() {
		return false;
	}

	public char getMark() {
		return MARK;
	}

}
