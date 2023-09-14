import processing.core.PApplet;

/**
 * A shape representing a frame in a train
 */
class TrainSprite {
	Posn pos;    // position of middle of this sprite
	int width = 50;
	int height = 55;
	float growSpd = 1;
	
	TrainSprite(Posn pos) {
		this.pos = pos;

	}
	
	TrainSprite(float x, float y) {
		this.pos = new Posn(x, y);

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
	void update(float overallSpd, int track) {
		
		float currentGrowSpd = growSpd * (overallSpd/2);
		
		switch (track) {
		case 1:
			width += currentGrowSpd;
			height += currentGrowSpd;
			pos = pos.posnSum(new Posn(-overallSpd/2, overallSpd));
			break;
		case 2:
			width += currentGrowSpd;
			height += currentGrowSpd;
			pos = pos.newY((pos.y + overallSpd));

			break;
		case 3:
			width += currentGrowSpd;
			height += currentGrowSpd;
			pos = pos.posnSum(new Posn(overallSpd/2, overallSpd));
			break;
		}
	}
}