package base.tile;


public class SolidTile extends Tile {
	
	public static char MARK = 'X';

	@Override
	public boolean isSolid() {
		return true;

	}

	@Override
	public char getMark() {
		return MARK;
	}

}
