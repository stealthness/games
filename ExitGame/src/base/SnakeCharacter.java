package base;

import res.DIR;
import res.ORDER;


public class SnakeCharacter extends Character {

	public SnakeCharacter(int i, int j) {
		super();
		position = new int[2];
		this.position[0] = i;
		this.position[1] = j;
		this.direction = DIR.NORTH;
	}
	
	/**
	 * A snake with move forwards at double the rate
	 */
	public void move(ORDER order){
		super.move(order);
		if (order == ORDER.FORWARD){
			super.move(order);
		}
	}

}
