import java.util.Objects;

import processing.core.PApplet;

/**
 * Represents a train obstacle that moves toward the player
 * 
 * A Train is made up of a list of TrainSprites (frames) that are rendered
 * behind each other
 */
public class Train {
	int track;

	int length; // length of the train
	int width = SSConstants.TRAIN_WIDTH;
	int height = SSConstants.TRAIN_HEIGHT;
	Vector vel;
	boolean hasRamp; 			   // whether or not the train has a ramp on the front the player can run up (has
	// no speed)
	Vector pos;
	
	Bounds3D bounds;

	float rearZ;				   // z for the rear of the train
	float frontZ;				   // z for the front of the train

	public Train(int length, int track, float speed, boolean hasRamp) {
		this.track = track;
		this.length = length;
		this.pos = new Vector(SSConstants.tracks[track - 1].getX(), SSConstants.TRAIN_Y, SSConstants.TRAIN_INITIAL_Z-length/2);

		this.hasRamp = hasRamp;
		this.vel = hasRamp ? new Vector(0, 0, SSConstants.gameSpd) : new Vector(0, 0, (SSConstants.gameSpd + speed));
		
		this.bounds = new Bounds3D(pos, width, height, length);
	}

	/**
	 * Renders the train on the given scene by drawing each of its frames
	 */
	PApplet draw(PApplet c) {
		c.fill(100,0,0);
		c.pushMatrix();

		c.translate(pos.x, pos.y, pos.z); // draws train body
		c.box(width, height, length);

		c.pushMatrix();					  // draws front bumper
		c.fill(60);
		c.translate(0, height-185, length/2);
		c.box(width+10, 60, 50);
		c.popMatrix();

		c.pushMatrix();					  // draws right headlight
		c.fill(255, 255, 100);
		c.translate(width/2 - 30, height-240, length/2+1);
		c.circle(0, 0, 40);
		c.popMatrix();

		c.pushMatrix();					  // draws left headlight
		c.fill(255, 255, 100);
		c.translate(-width/2 + 30, height-240, length/2+1);
		c.circle(0, 0, 40);
		c.popMatrix();

		c.pushMatrix();					  // draws bottom middle headlight
		c.fill(255, 255, 100);
		c.translate(0, height-243, length/2+1);
		c.circle(0, 0, 25);
		c.popMatrix();

		c.pushMatrix();					  // draws top middle headlight
		c.fill(255, 255, 100);
		c.translate(0, -20, length/2+1);
		c.circle(0, 0, 25);
		c.popMatrix();

		c.pushMatrix();					  // draws left window pane
		c.fill(0, 60, 100);
		c.translate(-width/2 + 58, -75, length/2+1);
		c.rect(0, 0, (width-25)/2, 65);
		c.popMatrix();

		c.pushMatrix();				   	  // draws right window pane
		c.fill(0, 60, 100);
		c.translate(width/2 - 58, -75, length/2+1);
		c.rect(0, 0, (width-25)/2, 65);
		c.popMatrix();

		c.popMatrix(); 


		return c;
	}

	/**
	 * Updates the position of the train by adding the velocity, also updates the bounds
	 */
	void update() {
		pos = pos.newZ(pos.z + vel.z);
		bounds = bounds.update(pos);
		frontZ = bounds.frontZ;
		rearZ = bounds.backZ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bounds, frontZ, hasRamp, height, length, pos, rearZ, track, vel, width);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Train other = (Train) obj;
		return Objects.equals(bounds, other.bounds)
				&& Float.floatToIntBits(frontZ) == Float.floatToIntBits(other.frontZ) && hasRamp == other.hasRamp
				&& height == other.height && length == other.length && Objects.equals(pos, other.pos)
				&& Float.floatToIntBits(rearZ) == Float.floatToIntBits(other.rearZ) && track == other.track
				&& Objects.equals(vel, other.vel) && width == other.width;
	}
	
	
}

