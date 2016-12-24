package base;

import res.ORDER;

/**
 * Adds behaviour of movement
 * @author steve
 *
 */
public interface Move {
	/**
	 * Moves a character based on order given
	 * @param order 
	 */
	public void move(ORDER order);

}
