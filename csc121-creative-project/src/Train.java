import java.util.ArrayList;
import processing.core.PApplet;

/**
 * Represents a train obstacle that moves toward the player
 * 
 * A Train is made up of a list of TrainSprites (frames) that are rendered
 * behind each other
 */
public class Train {
	int length; // length of the train (in frames)
	int track;
	ArrayList<TrainSprite> frames; // stores a list of the frames that make up the train, the frame at index 0 is
								   // the last frame of the train
	Vector vel;
	boolean hasRamp; 			   // whether or not the train has a ramp on the front the player can run up (has
								   // no speed)
	boolean finishedSpawn = false; // returns true if the train has already been fully rendered

	public Train(int length, int track, float speed, boolean hasRamp) {
		this.track = track;
		this.length = length;

		frames = new ArrayList<TrainSprite>(length);
		
		this.hasRamp = hasRamp;
		this.vel = hasRamp ? new Vector(0, 0, SSConstants.gameSpd) : new Vector(0, 0, (SSConstants.gameSpd + speed));

		frames.add(calcFrame(SSConstants.TRAIN_INITIAL_Z));
	}

	/**
	 * Constructs the appropriate TrainSprite at given y position for this train
	 * based on which track it is on
	 */
	private TrainSprite calcFrame(float z) {
		Vector pos = new Vector(0, (SSConstants.floorLvl - SSConstants.TRAIN_HEIGHT/2), z);

		switch (track) {
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

		return new TrainSprite(pos, vel);
	}
	
	/**
	 * Renders the train on the given scene by drawing each of its frames
	 */
	PApplet draw(PApplet c) {
		frames.forEach(frame -> frame.draw(c));
		return c;
	}

	/**
	 * Adds a new train frame if the train length has not been reached yet
	 * 
	 * Updates the position of this train by shifting each frame based on the
	 * train's speed and track
	 */
	void update() {
		if (frames.size() < length) {
			for (int i = 0; i < vel.z && i < length; i++) {
				TrainSprite last = frames.get(frames.size() - 1);
				frames.add(calcFrame(last.pos.z + 4));
			}
		} else {
			frames.forEach(frame -> frame.update());
			frames.removeIf(frame -> frames.get(0).offScreen);
		}

	}
}
