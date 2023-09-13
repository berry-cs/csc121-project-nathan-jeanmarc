import processing.core.PApplet;
import processing.event.KeyEvent;

/* represents a player */
class Player {

	Posn pos;
	int floorLvl;
	float bottomBound;
	float topBound;
	float rightBound;
	float leftBound;
	boolean isJumping;
	boolean isFalling;
	boolean collision;
	int width;
	int height;
	int currentTrack;

	float gravity;
	float maxGrav;
	float minGrav;

	float jumpSpd;
	float maxJumpSpd;
	float minJumpSpd;

	int jumpHeight;

	Player(Posn pos) {

		// sets the pos to the Posn given in the constructor
		this.pos = pos;

		// sets the initial ground level to 700
		this.floorLvl = 700;

		/*
		 * initially sets the booleans for if the player is jumping/falling or in a
		 * collision to false
		 */
		this.isJumping = false;
		this.isFalling = false;
		this.collision = false;

		// defines the size of the player
		this.width = 75;
		this.height = 125;

		// starts the player on the middle track
		this.currentTrack = 2;

		// defines the edges of the player box
		this.bottomBound = pos.y + (height / 2);
		this.topBound = pos.y - (height / 2);
		this.rightBound = pos.x + (width / 2);
		this.leftBound = pos.x - (width / 2);

		// defines the gravity, jump speed, and jump height
		this.gravity = 0.5f;
		this.maxGrav = 20;
		this.minGrav = 0.5f;

		this.jumpSpd = 15;
		this.minJumpSpd = 4;
		this.maxJumpSpd = 15;
		this.jumpHeight = 300;
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

		// sets x to the current track
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
		c.rectMode(3);
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
