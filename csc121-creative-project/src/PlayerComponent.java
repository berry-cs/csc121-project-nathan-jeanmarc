
public abstract class PlayerComponent {
	int currentTrack = 2; // stores which track the player is on (one of 1, 2, 3)
	Vector pos; // represents the position of the center of the player sprite
	Vector vel = new Vector(0, 0, 0);
	Vector gravity = new Vector(0, 1, 0);
	
	int width = 75; // width of player sprite (in px)
	int height = 125; // height of player sprite (in px)
	Bounds bounds;
	
	int floorLvl = SSConstants.floorLvl; // will change when jumping on top of trains
	
	public PlayerComponent() {}
	
	public PlayerComponent(Vector pos, Bounds b) {
		this.pos = pos;
		this.bounds = b;
	}
	
	/**
	 * Changes this player as needed
	 */
	public abstract void update();
	
	/**
	 * Gives the player an upward change in velocity to simulate jumping
	 */
	public void jump() {
		if (bounds.bBound >= SSConstants.floorLvl) {
			vel = new Vector(0, -14, 0);
		}
	}
	
	/**
	 * Update this sprite's position based on gravity
	 */
	public void gravity() {
		if (bounds.bBound < SSConstants.floorLvl) {
			vel = vel.translate(gravity);
		} else if (bounds.bBound > SSConstants.floorLvl) {
			vel = new Vector(0, 0, 0);
			pos = new Vector(pos.x, SSConstants.floorLvl - height / 2, SSConstants.PLAYER_Z);
		}
	}
}
