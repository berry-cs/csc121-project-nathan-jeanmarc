import processing.core.PApplet;

class TrackBoards {
	int width = 200;
	int height = 15;
	int depth = 50;
	
	Vector pos;
	
	boolean offScreen = false;
	
	TrackBoards(float z) {
		pos = new Vector(0, SSConstants.ENVIRONMENT_Y, z);
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
		
		reset();
	}
	
	void update() {
		pos = pos.newZ(pos.z + SSConstants.gameSpd);
		offScreen = pos.z >= SSConstants.DELETE_POINT;
	}
	
	void reset() {
		if (offScreen) {
			pos = pos.newZ(SSConstants.BOARD_INITIAL_Z);
			offScreen = false;
		}
	}

}
