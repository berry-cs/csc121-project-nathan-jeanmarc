import processing.core.PApplet;
import processing.event.KeyEvent;

/* represents a player in Subway Surfers */
class Player {

	Vector pos; // represents the position of the center of the player sprite
	Vector vel = new Vector(0, 0, 0);
	int width = 75; // width of player sprite (in px)
	int height = 125; // height of player sprite (in px)
	Bounds bounds;

	int floorLvl = SSConstants.floorLvl;

	boolean hasCollided = false; // whether or not the player has collided with an obstacle

	int currentTrack = 2; // stores which track the player is on (one of 1, 2, 3)

	Vector gravity = new Vector(0, 0.7f, 0);

	boolean inPos = false;

	public Player() {
		// sets the position
		this.pos = new Vector(SSConstants.tracks[currentTrack - 1].getxPos(), SSConstants.floorLvl - height / 2,
				SSConstants.PLAYER_Z);

		// defines the edges of the player box
		this.bounds = new Bounds(pos, width, height);

	}

	/* updates this player */
	void update() {
		bounds = bounds.update(pos);
		pos = pos.translate(vel);
		gravity();
	}

	/* renders the player as a red square in the correct lane */
	PApplet draw(PApplet c) {

		pos = pos.newX(SSConstants.tracks[currentTrack - 1].getxPos());

		// renders the player
		c.pushMatrix();
		c.translate(0, 0, pos.z);
		c.fill(50, 0, 80);
		c.rectMode(3); // rectangle is placed with (x,y) at center
		c.rect(pos.x, pos.y, width, height, 25);
		c.popMatrix();
		return c;
	}

	/* moves the player */
	void move(KeyEvent kev) {
		if (kev.getKey() == 'a' || kev.getKey() == 'A') {
			if (currentTrack > 1) {
				currentTrack -= 1;
			}
		} else if (kev.getKey() == 'd' || kev.getKey() == 'D') {
			if (currentTrack < 3) {
				currentTrack += 1;
			}
		} else if (kev.getKey() == 'w' || kev.getKey() == 'W') {
			jump();
		}
	}

	/*
	 * when the player isFalling, not jumping, and the bottom bound is above the
	 * floor level, applies the gravity
	 */
	void gravity() {

		if (bounds.bBound < SSConstants.floorLvl) {
			vel = vel.translate(gravity);
		} else if (bounds.bBound > SSConstants.floorLvl) {
			vel = new Vector(0, 0, 0);
			pos = new Vector(0, SSConstants.floorLvl - height / 2, SSConstants.PLAYER_Z);
		}
	}

	void jump() {

		if (bounds.bBound >= SSConstants.floorLvl) {
			vel = new Vector(0, -16, 0);
		}
	}

}
