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
	boolean hasRamp; // whether or not the train has a ramp on the front the player can run up (has
						// no speed)
	boolean finishedSpawn = false; // returns true if the train has already been fully rendered

	public Train(int length, int track, float speed, boolean hasRamp) {
		this.track = track;
		this.length = length;

		frames = new ArrayList<TrainSprite>(length);
		

		this.hasRamp = hasRamp;
		this.vel = hasRamp ? new Vector(0, 0, SSConstants.gameSpd) : new Vector(0, 0, SSConstants.gameSpd + speed);

		for (int i = 0; i < length; i++) {
			frames.add(i, calcFrame());
		}
	}

	/**
	 * Constructs the appropriate TrainSprite at given y position for this train
	 * based on which track it is on
	 */
	private TrainSprite calcFrame() {
		Vector pos = new Vector(0, 500, 0);

		switch (track) {
		case 1:
			pos = pos.newX(300);
			break;
		case 2:
			pos = pos.newX(600);
			break;
		case 3:
			pos = pos.newX(900);
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
		
		//frames.forEach(frame -> frame.update(vel));

		// holds the "first frame" of the train (last frame for rendering purposes
		TrainSprite firstFrame = frames.get(length-1);

		// used to tell how many frames to update before train has moved at least its length 
		int spawnNum = (int) firstFrame.pos.z + 1;


		// iterates over every necessary element in the trains list 
		// if i is the first frame moves it down by the speed 
		// if i is another frame moves it 2 pixels up from the frame directly in front of it 
		for (int i = 0; i < Math.min(length, spawnNum); i++) {
			if (i == 0) {
				firstFrame.update();
			} else {
				frames.get(length - 1 - i).update();
			}
		}



		frames.removeIf(frame -> frames.get(length-1).offScreen);
	}
}
