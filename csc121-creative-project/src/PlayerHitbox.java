import java.util.Objects;

import processing.core.PApplet;
import processing.event.KeyEvent;

/* represents a player's hitbox in Subway Surfers */
class PlayerHitbox extends PlayerComponent {
	boolean hasCollided = false; // whether or not the player has collided with an obstacle

	public PlayerHitbox() {
		super();
		this.pos = new Vector(SSConstants.tracks[currentTrack - 1].getX(), SSConstants.floorLvl - height / 2,
				SSConstants.PLAYER_Z);

		// defines the edges of the player box
		this.bounds = new Bounds(pos, width, height);
	}

	/* updates this player */
	public void update() {
		bounds = bounds.update(pos);
		pos = pos.translate(vel);
		pos = pos.newX(SSConstants.tracks[currentTrack - 1].getX());
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

	@Override
	public int hashCode() {
		return Objects.hash(bounds, currentTrack, floorLvl, gravity, hasCollided, height, pos, vel, width);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerHitbox other = (PlayerHitbox) obj;
		return Objects.equals(bounds, other.bounds) && currentTrack == other.currentTrack && floorLvl == other.floorLvl
				&& Objects.equals(gravity, other.gravity) && hasCollided == other.hasCollided && height == other.height
				&& Objects.equals(pos, other.pos) && Objects.equals(vel, other.vel)
				&& width == other.width;
	}

}
