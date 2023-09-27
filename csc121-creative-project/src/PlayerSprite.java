import processing.core.PApplet;

/**
 * Represents the rendered object of a player
 */
public class PlayerSprite extends PlayerComponent {
	// creates a new sprite at the parent hitbox's position and with the parent's bounds
	public PlayerSprite(Vector pos, Bounds bounds) {
		super(pos, bounds);
	}
	
	/**
	 * draws this player sprite on the given canvas
	 * @param c - the canvas to draw on
	 * @return the updated canvas
	 */
	PApplet draw(PApplet c) {
		c.pushMatrix();
		c.translate(0, 0, pos.z);
		c.fill(50, 0, 80);
		c.rectMode(3); // rectangle is placed with (x,y) at center
		c.rect(pos.x, pos.y, width, height, 25);
		c.popMatrix();
		return c;
	}
	
	public void update() {
		bounds = bounds.update(pos);
		pos = pos.translate(vel);
		pos = pos.newX(SSConstants.tracks[currentTrack - 1].getxPos());
		gravity();
	}
}
