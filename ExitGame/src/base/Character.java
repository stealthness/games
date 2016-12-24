package base;

import res.DIR;
import res.ORDER;

public abstract class Character implements Move{
	
	//private String name;
	int[] position;
	int sightLength;
	DIR direction;
	
	public void moveTo(int i, int j) {
		position[0] = i;
		position[1] = j;		
	}
	
	@Override
	public void move(ORDER order) {
		switch(order){
		case BACKWARD:
			moveForward(DIR.turn(direction));
			break;
		case FORWARD:
			moveForward(direction);
			break;
		case LEFT:
			direction = DIR.turnLeft(direction);
			break;
		case RIGHT:
			direction = DIR.turnRight(direction);
			break;
		case TURN:
			direction = DIR.turn(direction);
			break;
		}
	}
	
	private void moveForward(DIR dir) {
		switch(dir){
		case NORTH:
			moveTo(this.position[0],this.position[1]+1);
			break;
		case SOUTH:
			moveTo(this.position[0],this.position[1]-1);
			break;
		case EAST:
			moveTo(this.position[0]+1,this.position[1]);
			break;
		case WEST:
			moveTo(this.position[0]-1,this.position[1]);
			break;
		}
		
	}

	public DIR getDirection() {
		return direction;
	}
	
	// Getter and Setters
	
	public int[] getPosition() {
		return position;
	}

	public boolean isAt(int i, int j) {
		return position[0]==i && position[1]==j;
	}
	
	public int getSightLength() {
		return sightLength;
	}

	public void setSightLength(int length) {
		sightLength  = length;
		
	}
}
