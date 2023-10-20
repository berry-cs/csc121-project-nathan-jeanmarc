import java.util.Objects;
import processing.core.PApplet;
import processing.event.KeyEvent;

/** represents a player's hitbox in Subway Surfers */
class Player {
	
	private int currentTrack = 2; // stores which track the player is on (one of 1, 2, 3)
	private Vector pos; // represents the position of the center of the player sprite
	private Vector vel = new Vector(0, 0, 0);
	private Vector gravity = new Vector(0, 0.75f, 0);

	private int width = 90; // width of player sprite (in px)
	private int height = 180; // height of player sprite (in px)
	private Bounds bounds;

	private int floorLvl = SSConstants.floorLvl; // will change when jumping on top of trains

	private boolean onTrain = false;
	
	public Player() {
		this.pos = new Vector(SSConstants.tracks[currentTrack - 1].getX(), SSConstants.floorLvl - height / 2,
				SSConstants.PLAYER_Z);

		// defines the edges of the player box
		this.bounds = new Bounds(pos, width, height);
	}
	
	PApplet draw(PApplet c) {
		c.pushMatrix();
		c.translate(0, 0, pos.getZ());
		c.fill(50, 0, 80);
		c.imageMode(3);
		SSConstants.playerSprite.display(pos.getX(), pos.getY(), width, height);
		c.popMatrix();
		return c;
	}

	/* updates this player */
	public void update() {
		bounds.update(pos);
		pos.translate(vel);

		if (pos.getX() < SSConstants.tracks[currentTrack - 1].getX() - 40) {
			pos.translate(new Vector(40, 0, 0));
		} else if (pos.getX() > SSConstants.tracks[currentTrack - 1].getX() + 40) {
			pos.translate(new Vector(-40, 0, 0));
		} else
			pos.newX(SSConstants.tracks[currentTrack - 1].getX());

		gravity();
	}

	/* moves the player */
	public void move(KeyEvent kev) {
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

	/**
	 * Gives the player an upward change in velocity to simulate jumping
	 */
	public void jump() {
		if (bounds.getbBound() >= floorLvl) {
			vel = new Vector(0, -16, 0);
		}
	}

	/**
	 * Update this sprite's position based on gravity
	 */
	public void gravity() {
		if (bounds.getbBound() < floorLvl) {
			vel.translate(gravity);
		} else if (bounds.getbBound() > floorLvl) {
			vel = new Vector(0, 0, 0);
			pos = new Vector(pos.getX(), floorLvl - height / 2, SSConstants.PLAYER_Z);
		}
	}
	
	/**
	 *  returns the variable that tracks whether or not the player is on a train
	 */
	public Boolean isOnTrain() {
		return this.onTrain;
	}
	
	/**
	 * changes the floor level to be the top of the train and sets the
	 * boolean to reflect that  
	 */
	public void onTrain() {
		this.onTrain = true;
		floorLvl = SSConstants.TRAIN_TOP;
	}
	
	/**
	 * changes the floor level to be the ground and sets the
	 * boolean to reflect that  
	 */
	public void offTrain() {
		this.onTrain = false;
		floorLvl = SSConstants.floorLvl;
	}

	public Vector getPos() {
		return pos;
	}

	public Vector getVel() {
		return vel;
	}

	public Bounds getBounds() {
		return bounds;
	}
	
	public int getFloorLvl() {
		return floorLvl;
	}

	public int getCurrentTrack() {
		return currentTrack;
	}

	public Vector getGravity() {
		return gravity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bounds, currentTrack, floorLvl, gravity, height, onTrain, pos, vel, width);
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
				&& Objects.equals(gravity, other.gravity) && height == other.height && onTrain == other.onTrain
				&& Objects.equals(pos, other.pos) && Objects.equals(vel, other.vel) && width == other.width;
	}

	

}
