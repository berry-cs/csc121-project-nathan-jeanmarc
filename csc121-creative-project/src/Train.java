import java.util.ArrayList;

import processing.core.PApplet;
import processing.event.KeyEvent;

/**
 * Represents a train obstacle that moves toward the player
 */
public class Train implements IEntity {
	int length;                     // length of the train (in frames)
	ArrayList<TrainSprite> frames;  // stores a list of the frames that make up the train
	int track;						// the track the train is on: one of 1, 2, 3
	float speed;					// the speed of the train relative to the rest of the stage
	float gameSpd;					// the current speed of the game (needed for calculating 
	boolean hasRamp;				// whether or not the train has a ramp on the front the player can run up (has no speed)
	
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
		
		if (hasRamp) this.speed = 0;
		else this.speed = speed;
	}
	
	/**
	 * For "reconstructing" an existing train
	 */
	Train(int length, ArrayList<TrainSprite> frames, int track, float speed, float gameSpd, boolean hasRamp) {
		this.length = length;
		this.frames = frames;
		this.track = track;
		this.hasRamp = hasRamp;
		this.gameSpd = gameSpd;
		
		if (hasRamp) this.speed = 0;
		else this.speed = speed;
	}
	
	/**
	 * Constructs the appropriate first TrainSprite for this train based
	 * on which track it is on
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
	 * Updates the position of this train by shifting each frame based on the train's speed
	 * and track
	 */
	void update() {
		frames.forEach(frame -> frame.update(speed, gameSpd, track));
	}
}

/**
 * A shape representing a frame in a train
 */
class TrainSprite {
	Posn pos;    // position of middle of this sprite
	int width;
	int height;
	
	TrainSprite(Posn pos) {
		this.pos = pos;
		this.width = 50;
		this.height = 100;
	}
	
	TrainSprite(float x, float y) {
		this.pos = new Posn(x, y);
		this.width = 50;
		this.height = 100;
	}
	
	TrainSprite(float x, float y, int width, int height) {
		this.pos = new Posn(x, y);
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Draws this frame of the train on the screen
	 */
	void draw(PApplet c) {
		c.fill(0,0,255);
		c.rectMode(3);
		c.rect(pos.x, pos.y, width, height);
	}
	
	/**
	 * Shifts and grows this frame based on the given speed and track
	 */
	void update(float trainSpd, float gameSpd, int track) {
		switch (track) {
		case 1:
			pos = pos.newY(pos.y + (trainSpd * gameSpd));
			break;
		case 2:
			pos = pos.newY(pos.y + (trainSpd * gameSpd));
			break;
		case 3:
			pos = pos.newY(pos.y + (trainSpd * gameSpd));
			break;
		}
	}
}


