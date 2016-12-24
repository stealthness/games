package res;

public enum DIR {

	NORTH, SOUTH, EAST, WEST;

	public static DIR turnLeft(DIR dir) {
		switch(dir){
		case NORTH:
			return WEST;
		case WEST:
			return SOUTH;
		case SOUTH:
			return EAST;
		case EAST:
			return NORTH;
		}
		return dir;
	}

	public static DIR turnRight(DIR dir) {
		return turnLeft(turn(dir));
	}

	public static DIR turn(DIR dir) {
		return turnLeft(turnLeft(dir));
	}
}
