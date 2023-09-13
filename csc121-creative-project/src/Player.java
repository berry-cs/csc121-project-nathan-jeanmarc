import processing.core.PApplet;
import processing.event.KeyEvent;

/* represents a player in Subway Surfers */
class Player {

	Posn pos;                    // represents the position of the center of the player sprite
	int floorLvl = 700;
	float bottomBound;           // stores the y value of the bottom edge of the player sprite
	float topBound;				 // stores the y value of the top edge of the player sprite
	float rightBound;            // stores the x value of the right edge of the player sprite
	float leftBound;             // stores the x value of the left edge of the player sprite
	boolean isJumping = false;   // whether or not the player is jumping
	boolean isFalling = false;   // whether or not the player is falling
	boolean collision = false;   // whether or not the player has collided with an obstacle
	int width = 75;              // width of player sprite (in px)
	int height = 125;            // height of player sprite (in px)
	int currentTrack = 2;        // stores which track the player is on (one of 1, 2, 3)

<<<<<<< HEAD
	float gravity = 0.5f;
	float maxGrav = 20;
	float minGrav = 0.5f;
	
	float jumpSpd = 15;
	float minJumpSpd = 4;
	float maxJumpSpd = 15;
	float jumpHeight = 300;
=======
	float gravity;
	float maxGrav;
	float minGrav;

	float jumpSpd;
	float maxJumpSpd;
	float minJumpSpd;

	int jumpHeight;

	Player(Posn pos) {
>>>>>>> 8f5382674869c762fb8ae1a7ad0475cdb711388f

	public Player(Posn pos) {
		// sets the pos to the Posn given in the constructor
		this.pos = pos;

		// starts the player on the middle track
		this.currentTrack = 2;

		// defines the edges of the player box
		this.bottomBound = pos.y + (height / 2);
		this.topBound = pos.y - (height / 2);
		this.rightBound = pos.x + (width / 2);
		this.leftBound = pos.x - (width / 2);
	}

	/* updates this player */
	void update(PApplet c) {
		this.render(c);
		this.updateBounds();
		this.gravity();
		this.jump();

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
		if (bottomBound > floorLvl) {
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
		bottomBound = pos.y + (height / 2);
		topBound = pos.y - (height / 2);
		rightBound = pos.x + (width / 2);
		leftBound = pos.x - (width / 2);
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
		if (!isJumping && isFalling && bottomBound < floorLvl) {
			pos = pos.newY(pos.y + gravity);
			if (gravity < maxGrav) {
				gravity += 0.5;
			}
		} else if (bottomBound >= floorLvl) {
			isFalling = false;
			gravity = minGrav;
		}
	}

	/*
	 * when the player is not falling, isJumping and the bottom bound is below the
	 * jump height relative to the floor, causes the player to jump
	 */
	void jump() {
		if (!isFalling && isJumping && bottomBound > (floorLvl - jumpHeight)) {
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
