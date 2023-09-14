import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Represents a train obstacle that moves toward the player
 */
public class Train implements IEntity {
	int length; // length of the train (in frames)
	ArrayList<TrainSprite> frames; // stores a list of the frames that make up the train
	int track; // the track the train is on: one of 1, 2, 3
	float speed; // the speed of the train relative to the rest of the stage
	float gameSpd; // the current speed of the game (needed for calculating
	float overallSpd; // the current speed of the train with the gameSpd factored in
	boolean hasRamp; // whether or not the train has a ramp on the front the player can run up (has
						// no speed)
	boolean finishedSpawn = false; // returns true if the train length has already been fully rendered

	/**
	 * For constructing a completely new train
	 */
	public Train(int length, int track, float speed, float gameSpd, boolean hasRamp) {
		this.length = length;
		this.track = track;

		frames = new ArrayList<TrainSprite>(length);
		frames.add(calcFirstFrame());

		this.hasRamp = hasRamp;
		this.gameSpd = gameSpd;
		this.overallSpd = speed * gameSpd;

		if (hasRamp)
			this.speed = 0;
		else
			this.speed = speed;
	}

	/**
	 * For "reconstructing" an existing train
	 */
	Train(int length, ArrayList<TrainSprite> frames, int track, float speed, float gameSpd, boolean hasRamp) {
		this.length = length;
		this.track = track;

		this.frames = frames;

		this.hasRamp = hasRamp;
		this.gameSpd = gameSpd;

		if (hasRamp)
			this.speed = 0;
		else
			this.speed = speed;
	}

	/**
	 * Constructs the appropriate first TrainSprite for this train based on which
	 * track it is on
	 */
	private TrainSprite calcFrame() {
		Posn pos = new Posn(0, 200);

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
	 * Constructs the appropriate first TrainSprite for this train based on which
	 * track it is on
	 */
	private TrainSprite calcFirstFrame() {
		Posn pos = new Posn(0, 200);

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
	PApplet render(PApplet c) {
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
		if (frames.size() < length && !finishedSpawn) {
			frames.add(1, calcFrame());
		} else {
			finishedSpawn = true;
		}
		
		frames.forEach(frame -> frame.update(overallSpd, track));

		frames.removeIf(frame -> frame.offScreen);
	}
}
