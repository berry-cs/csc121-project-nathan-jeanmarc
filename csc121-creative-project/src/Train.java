import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Represents a train obstacle that moves toward the player
 * 
 * A Train is made up of a list of TrainSprites (frames) that are rendered behind each other
 */
public class Train extends Entity {
	int length; // length of the train (in frames)
	ArrayList<TrainSprite> frames; // stores a list of the frames that make up the train, the frame at index 0 is the last frame of the train
	float initialSpdSclr; // the amount the game speed should be scaled initially to produce a train that moves faster than the game
	float overallSpd; // the current speed of the train with the gameSpd factored in
	boolean hasRamp; // whether or not the train has a ramp on the front the player can run up (has
						// no speed)
	boolean finishedSpawn = false; // returns true if the train has already been fully rendered

	public Train(int length, int track, float initialSpdSclr, float gameSpd, boolean hasRamp) {
		super(track);
		this.length = length;

		frames = new ArrayList<TrainSprite>(length);
		frames.add(calcFrame(200));

		this.hasRamp = hasRamp;
		this.initialSpdSclr = hasRamp ? 0 : initialSpdSclr;
		this.overallSpd = initialSpdSclr * gameSpd;
	}
	
	/**
	 * Constructs the appropriate TrainSprite at given y position for this train based on which
	 * track it is on
	 */
	private TrainSprite calcFrame(float y) {
		Posn pos = new Posn(0, y); 
		
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

		return new TrainSprite(pos);
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
		int currentLen = frames.size();

		if (currentLen < length && !finishedSpawn) {
			frames.add(0, calcFrame(frames.get(0).pos.y - overallSpd/2)); // change to just - overallSpd for old train behavior
		} else {
			finishedSpawn = true;
		}
		
		// update all frames
		frames.forEach(frame -> frame.update(overallSpd, track));
		// remove frames when they are off of the screen and the train has finished spawning
		frames.removeIf(frame -> frame.offScreen && finishedSpawn);
		// update speed of this train based on size of first frame, kind of fixes "accordion" issue
		if (frames.size() != 0) overallSpd += initialSpdSclr * 0.175;
	}
}
