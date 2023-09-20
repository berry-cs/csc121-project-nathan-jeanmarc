import processing.core.PApplet;

/**
 * A shape representing a frame in a train
 */
class TrainSprite {
	Vector pos;    // position of middle of this sprite
	Vector vel;
	int width = 50;
	int height = 55;
	Bounds bounds;

	//float speed; // the speed this frame moves across the screen, dependent on its size
	
	Boolean offScreen = false;
	
	TrainSprite(Vector pos, Vector vel) {
		this.pos = pos;
		this.vel = vel;
		bounds = new Bounds(pos, width, height);
	}
	
	/**
	 * Draws this frame of the train on the screen
	 */
	void draw(PApplet c) {
		c.pushMatrix();
		c.translate(0, 0, pos.z);
		c.fill(0,0,255);
		c.rectMode(3); // problem
		c.rect(pos.x, pos.y, width, height);
		c.popMatrix();
	}
	
	/**
	 * Moves this frame by adding its velocity to its position
	 */
	void update() {
		pos = pos.translate(vel);
		
		bounds = bounds.update(pos, width, height);

		offScreen = pos.z > SSConstants.CAMERA_Z;
		
	}
}