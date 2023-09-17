import processing.core.PApplet;
import processing.event.KeyEvent;

/* represents a player in Subway Surfers */
class Player {

	Posn pos;                    // represents the position of the center of the player sprite
	int width = 75;              // width of player sprite (in px)
	int height = 125;            // height of player sprite (in px)
	Bounds bounds;
	
	boolean isJumping = false;   // whether or not the player is jumping
	boolean isFalling = false;   // whether or not the player is falling
	int floorLvl = 700;
	
	boolean hasCollided = false;   // whether or not the player has collided with an obstacle
	
	int currentTrack = 2;        // stores which track the player is on (one of 1, 2, 3)

	float maxGrav = 20;
	float minGrav = 0.5f;
	float gravity = minGrav;

	float minJumpSpd = 4;
	float maxJumpSpd = 15;
	float jumpSpd = maxJumpSpd;
	float jumpHeight = 300;

	public Player(Posn pos) {
		// sets the pos to the Posn given in the constructor
		this.pos = pos;

		// starts the player on the middle track
		this.currentTrack = 2;

		// defines the edges of the player box
		this.bounds = new Bounds(pos, width, height);
	}

	/* updates this player */
	void update() {
		updateBounds();
		gravity();
		jump();
	}

	/* renders the player as a red square in the correct lane */
	PApplet render(PApplet c) {

		// sets x based on the current track
		switch (currentTrack) {
		case 1:
			pos = pos.newX(200);
			break;
		case 2:
			pos = pos.newX(600);
			break;
		case 3:
			pos = pos.newX(1000);
			break;
		}

		// if the player is somehow below floorLvl fixes it
		if (bounds.bBound > floorLvl) {
			pos = pos.newY(floorLvl - (height / 2));
		}

		// renders the player
		c.fill(255, 0, 0);
		c.rectMode(3);       // rectangle is placed with (x,y) at center
		c.rect(pos.x, pos.y, width, height, 25);
		return c;
	}

	/* updates the bounds of the player */
	void updateBounds() {
		bounds = new Bounds(pos, width, height);
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
			isJumping = true;
		}
	}

	/*
	 * when the player isFalling, not jumping, and the bottom bound is above the
	 * floor level, applies the gravity
	 */
	void gravity() {
		if (!isJumping && isFalling && bounds.bBound < floorLvl) {
			pos = pos.newY(pos.y + gravity);
			if (gravity < maxGrav) {
				gravity += 0.5;
			}
		} else if (bounds.bBound >= floorLvl) {
			isFalling = false;
			gravity = minGrav;
		}
	}

	/*
	 * when the player is not falling, isJumping and the bottom bound is below the
	 * jump height relative to the floor, causes the player to jump
	 */
	void jump() {
		if (!isFalling && isJumping && bounds.bBound > (floorLvl - jumpHeight)) {
			pos = pos.newY(pos.y - jumpSpd);
			if (jumpSpd > minJumpSpd) {
				jumpSpd -= 0.1;
			}
		} else {
			isJumping = false;
			isFalling = true;
			jumpSpd = maxJumpSpd;
		}
	}

}
