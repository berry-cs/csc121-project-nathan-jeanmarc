import java.util.Objects;

import processing.core.PApplet;
import processing.event.KeyEvent;

/* represents a player in Subway Surfers */
class Player {

	Vector pos; // represents the position of the center of the player sprite
	Vector vel = new Vector(0, 0, 0);
	
	int width = 75; // width of player sprite (in px)
	int height = 125; // height of player sprite (in px)
	
	Bounds bounds;

	int floorLvl = SSConstants.floorLvl; // will change when jumping on top of trains

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
		pos = pos.newX(SSConstants.tracks[currentTrack - 1].getxPos());
		gravity();
	}

	/* renders the player as a red square in the correct lane */
	PApplet draw(PApplet c) {
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

	void gravity() {

		if (bounds.bBound < SSConstants.floorLvl) {
			vel = vel.translate(gravity);
		} else if (bounds.bBound > SSConstants.floorLvl) {
			vel = new Vector(0, 0, 0);
			pos = new Vector(pos.x, SSConstants.floorLvl - height / 2, SSConstants.PLAYER_Z);
		}
	}

	void jump() {

		if (bounds.bBound >= SSConstants.floorLvl) {
			vel = new Vector(0, -16, 0);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(bounds, currentTrack, floorLvl, gravity, hasCollided, height, inPos, pos, vel, width);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(bounds, other.bounds) && currentTrack == other.currentTrack && floorLvl == other.floorLvl
				&& Objects.equals(gravity, other.gravity) && hasCollided == other.hasCollided && height == other.height
				&& inPos == other.inPos && Objects.equals(pos, other.pos) && Objects.equals(vel, other.vel)
				&& width == other.width;
	}

}
