import processing.core.PApplet;
import processing.event.KeyEvent;

/* represents a player in Subway Surfers */
class Player {

	Vector pos;                    // represents the position of the center of the player sprite
	Vector vel = new Vector(0,0,0);
	int width = 75;              // width of player sprite (in px)
	int height = 125;            // height of player sprite (in px)
	Bounds bounds;
	
	//PlayerState state = PlayerState.FALLING;
//	boolean isJumping = false;   // whether or not the player is jumping
//	boolean isFalling = false;   // whether or not the player is falling
	
	int floorLvl = SSConstants.floorLvl;
	
	boolean hasCollided = false;   // whether or not the player has collided with an obstacle
	
	int currentTrack = 2;        // stores which track the player is on (one of 1, 2, 3)

	Vector gravity = new Vector(0, 0.7f, 0);
	
	boolean inPos = false;

//	float minJumpSpd = 4;
//	float maxJumpSpd = 15;
//	float jumpSpd = maxJumpSpd;
//	float jumpHeight = 220;

	public Player() {
		// sets the pos to the Posn given in the constructor
		this.pos = new Vector(0, SSConstants.floorLvl - height/2, SSConstants.PLAYER_Z);

		// starts the player on the middle track
		this.currentTrack = 2;

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

		// sets x based on the current track
		switch (currentTrack) {
		case 1:
			pos = pos.newX(SSConstants.TRACK_1);
			break;
		case 2:
			pos = pos.newX(SSConstants.TRACK_2);
			break;
		case 3:
			pos = pos.newX(SSConstants.TRACK_3);
			break;
		}
		

//		// if the player is somehow below floorLvl fixes it
//		if (bounds.bBound > floorLvl) {
//			pos = pos.newY(floorLvl - (height / 2));
//		}

		// renders the player
		c.pushMatrix();
		c.translate(0, 0, pos.z);
		c.fill(50, 0, 80);
		c.rectMode(3);       // rectangle is placed with (x,y) at center
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
			vel = new Vector(0,0,0);
			pos = new Vector(0, SSConstants.floorLvl - height/2, SSConstants.PLAYER_Z);
		}
		
		//System.out.println("vel: " + vel.toString());
	}


	void jump() {

		if (bounds.bBound >= SSConstants.floorLvl) {
			vel = new Vector(0, -16, 0);
			//System.out.println("vel: " + vel.toString());
		}
	}

}
