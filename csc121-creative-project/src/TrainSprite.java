import processing.core.PApplet;

/**
 * A shape representing a frame in a train
 */
class TrainSprite {
	Posn pos;    // position of middle of this sprite
	int width = 50;
	int height = 55;
	Bounds bounds;

	//float speed; // the speed this frame moves across the screen, dependent on its size
	
	Boolean offScreen = false;
	
	TrainSprite(Posn pos/*, float speed*/) {
		this.pos = pos;
		//this.speed = speed;
		bounds = new Bounds(pos, width, height);
	}
	
	TrainSprite(float x, float y, int width, int height) {
		this.pos = new Posn(x,y);
		this.width = width;
		this.height = height;
		this.bounds = new Bounds(pos, width, height);
	}

	
	/**
	 * Draws this frame of the train on the screen
	 */
	void draw(PApplet c) {
		c.fill(0,0,255);
		c.rectMode(3); // problem
		c.rect(pos.x, pos.y, width, height);
	}
	
	/**
	 * Shifts and grows this frame based on the given speed and track
	 * 
	 * Returns the updated speed of the train that contains this frame, based on the frame's size
	 */
	void update(float y, int track) {
		
		pos = pos.newY(y);
		
		bounds = bounds.update(pos, width, height);

		offScreen = bounds.tBound > 800;
		
		switch (track) {
		case 1:
			pos = pos.newY(y);
			break;
		case 2:
			pos = pos.newY(y);
			break;
		case 3:
			pos = pos.newY(y);
			break; 
		}  
	}
}