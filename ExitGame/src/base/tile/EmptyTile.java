package base.tile;

public class EmptyTile extends Tile {
	
	public static char MARK = '.';

	@Override
	public boolean isSolid() {
		return false;
	}

	@Override
	public char getMark() {
		return MARK;
	}
	


}
