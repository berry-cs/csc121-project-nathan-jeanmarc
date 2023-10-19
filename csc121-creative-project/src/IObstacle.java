import processing.core.PApplet;

/**
 * Represents something that the player will interact with that affects the game
 */
public interface IObstacle {
	
	/**
	 * updates the obstacles position and bounds
	 */
	void update();
	
	/**
	 * renders the obstacle
	 */
	void draw(PApplet c);
	
	/**
	 * handles collision with the given player, returns true if bounds intersect
	 */
	Boolean handleCollision(Player p);

}
