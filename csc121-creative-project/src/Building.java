import processing.core.PApplet;

class Building {

	int size;

	int width = 600;
	int height;
	int depth = 600;

	boolean onRight;
	boolean offScreen;
	
	static int spawnPoint = SSConstants.TRAIN_INITIAL_Z - 1000;

	Vector pos;

	Building(float z, int size, boolean onRight) {
		this.size = size;
		this.onRight = onRight;
		choosePos();
		pos = pos.newZ(z);
	}

	void draw(PApplet c) {
		c.fill(60);
		c.pushMatrix();
		c.translate(pos.x, pos.y, pos.z);
		c.box(width, height, depth);
		c.popMatrix();
	}
	
	void update() {
		pos = pos.newZ(pos.z + SSConstants.gameSpd);
		
		offScreen = pos.z >= SSConstants.DELETE_POINT;
		
		if (offScreen) {
			pos = pos.newZ(spawnPoint);
		}
	}

	void choosePos() {
		pos = new Vector(0, 0, 0);

		switch(size) {
		case 1:
			height = 850;
			pos = pos.newY(425);
			break;
		case 2:
			height = 1050;
			pos = pos.newY(325);
			break;
		case 3:
			height = 1200;
			pos = pos.newY(250);
			break;
		}
		
		if (onRight) {
			pos = pos.newX(2000);
		} else {
			pos = pos.newX(-800);
		}
	}
}
