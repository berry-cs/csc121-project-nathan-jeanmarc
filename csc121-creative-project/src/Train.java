import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Represents a train obstacle that moves toward the player
 * 
 * A Train is made up of a list of TrainSprites (frames) that are rendered behind each other
 */
public class Train implements IEntity {
	int length; // length of the train (in frames)
	ArrayList<TrainSprite> frames; // stores a list of the frames that make up the train, the frame at index 0 is the last frame of the train
	int track; // the track the train is on: one of 1, 2, 3
	float speed; // the speed of the train relative to the rest of the stage
	float gameSpd; // the current speed of the game, passed from SubwaySurfers (needed for calculating how much a train should grow and shift)
	float overallSpd; // the current speed of the train with the gameSpd factored in
	boolean hasRamp; // whether or not the train has a ramp on the front the player can run up (has
						// no speed)
	boolean finishedSpawn = false; // returns true if the train has already been fully rendered

	public Train(int length, int track, float speed, float gameSpd, boolean hasRamp) {
		this.length = length;
		this.track = track;

		frames = new ArrayList<TrainSprite>(length);
		frames.add(calcFrame());

		this.hasRamp = hasRamp;
		this.gameSpd = gameSpd;
		this.speed = hasRamp ? 0 : speed;
		this.overallSpd = speed * gameSpd;
	}
	
	/**
	 * Constructs the appropriate first TrainSprite for this train based on which
	 * track it is on
	 */
	private TrainSprite calcFrame() {
		Posn pos = new Posn(0, 200); // will be replaced by above when fixed
		
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
		if (frames.size() < length && !finishedSpawn) {
			// duplicate last frame in this train
			frames.add(0, calcFrame());
			// update all but the last frame of this train
			for (int i = 1; i < frames.size(); i++) {
				frames.get(i).update(overallSpd, track);
			}
			
			/*
			// add extra frames to make train more smooth (fixes choppiness with faster trains)
			if (frames.get(frames.size() - 2).pos.y - frames.get(frames.size() - 1).pos.y > 19) {
				length *= 2;
				int oldSize = frames.size();
				for (int i = 0; i < oldSize - 1; i++) {
					TrainSprite hiFrame = frames.get(i);
					TrainSprite loFrame = frames.get(i+1);
					frames.add(i, new TrainSprite( (hiFrame.pos.x + loFrame.pos.x) / 2, 
													(hiFrame.pos.y + loFrame.pos.y) / 2, 
													(hiFrame.width + loFrame.width) / 2, 
													(hiFrame.height + loFrame.height) / 2));
				}
			}
			*/
		} else {
			finishedSpawn = true;
			// update all frames
			frames.forEach(frame -> frame.update(overallSpd, track));
		}

		// remove frames when they are off of the screen and the train has finished spawning
		frames.removeIf(frame -> frame.offScreen && finishedSpawn);
	}
}
