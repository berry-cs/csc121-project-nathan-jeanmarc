import processing.core.PApplet;

/**
 * A shape representing a frame in a train
 */
class TrainSprite {
	Posn pos;    // position of middle of this sprite
	int width = 50;
	int height = 55;
	Bounds bounds;
	
	float growSpd = 1;
	

	Boolean offScreen = false;
	
	TrainSprite(Posn pos) {
		this.pos = pos;
		bounds = new Bounds(pos, width, height);

	}
	
	TrainSprite(float x, float y) {
		this.pos = new Posn(x, y);
		bounds = new Bounds(pos, width, height);

	}
	
	TrainSprite(float x, float y, int width, int height) {
		this.pos = new Posn(x, y);
		this.width = width;
		this.height = height;
		bounds = new Bounds(pos, width, height);
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
	void update(float overallSpd, int track) {
		
		bounds.tBound = pos.y - height/2;
		
		offScreen = bounds.tBound > 800;
		
		float currentGrowSpd = growSpd * (overallSpd/2);
		
		switch (track) {
		case 1:
			width += currentGrowSpd;
			height += currentGrowSpd;
			pos = pos.posnSum(new Posn(-overallSpd/2, overallSpd + currentGrowSpd));
			break;
		case 2:
			width += currentGrowSpd;
			height += currentGrowSpd;
			pos = pos.newY((pos.y + overallSpd + currentGrowSpd));

			break;
		case 3:
			width += currentGrowSpd;
			height += currentGrowSpd;
			pos = pos.posnSum(new Posn(overallSpd/2, overallSpd + currentGrowSpd));
			break;
		}
	}
}