import processing.core.PApplet;

class TrackBoards {
	int width = 200;
	int height = 15;
	int depth = 50;
	
	Vector pos = new Vector(0, 820, -1000);
	
	TrackBoards() {
		
	}
	
	void draw(PApplet c) {
		c.fill(40, 20, 0);
		
		c.pushMatrix();
		c.translate(100, pos.y, pos.z);
		c.box(width, height, depth);
		c.popMatrix();
		
		c.pushMatrix();
		c.translate(600, pos.y, pos.z);
		c.box(width, height, depth);
		c.popMatrix();
		
		c.pushMatrix();
		c.translate(1100, pos.y, pos.z);
		c.box(width, height, depth);
		c.popMatrix();
	}
	
	void update() {
		pos = pos.newZ(pos.z + SSConstants.gameSpd);
	}

}
