import processing.core.PApplet;

class Obstacle {
	
	Vector pos;
	Bounds bounds;
	int width = SSConstants.TRAIN_WIDTH + 60;
	int height = 150;
	int depth = 60;
	
	boolean offScreen = false;
	
	int track;
	
	float gameSpd;
	
	Obstacle(int track) {
		this.track = track;
		calcPos();
		//this.bounds = new Bounds(pos, width, height);
	}
	
	void update() {
		pos = pos.newZ(pos.z + SSConstants.gameSpd);
		offScreen = pos.z >= SSConstants.DELETE_POINT;
	}
	
	void draw(PApplet c) {
		c.pushMatrix();
		c.fill(70);
		c.translate(pos.x, SSConstants.ENVIRONMENT_Y-30, pos.z);
		c.box(width, 20, depth); // draws the base
		
		c.pushMatrix();
		c.fill(90, 60, 40);
		c.translate(-80, -75, 0);
		c.box(40, 130, 20); // draws the left post
		c.popMatrix();
		
		c.pushMatrix();
		c.fill(90, 60, 40);
		c.translate(80, -75, 0);
		c.box(40, 130, 20); // draws the right post
		c.popMatrix();
		
		c.pushMatrix();
		c.fill(150);
		c.translate(0, -120, 0);
		c.box(width-20, 60, 45); // draws the white bar
		c.popMatrix();
		
		c.pushMatrix();
		c.fill(90);
		c.strokeWeight(0.05f);
		c.translate(-80, -115, 0);
		c.sphere(30); // draws the left sphere
		c.strokeWeight(1);
		c.popMatrix();
		
		c.pushMatrix();
		c.fill(90);
		c.strokeWeight(0.05f);
		c.translate(80, -115, 0);
		c.sphere(30); // draws the right sphere
		c.strokeWeight(1);
		c.popMatrix();
		
		c.pushMatrix();  // draws the orange bars
		c.fill(190, 100, 0);
		c.translate(-100, -120, 24);
		c.rect(0, 0, 20, 60);
		c.rect(40, 0, 20, 60);
		c.rect(80, 0, 20, 60); 
		c.rect(120, 0, 20, 60);
		c.rect(160, 0, 20, 60);
		c.rect(200, 0, 20, 60);
		c.popMatrix();
		
		c.popMatrix();
	}
	
	void calcPos() {
		
		pos = new Vector(0, SSConstants.TRAIN_Y, SSConstants.TRAIN_INITIAL_Z);
		
		switch(track) {
		case 1:
			pos = pos.newX(SSConstants.TRACK_1);
			break;
		case 2:
			pos = pos.newX(SSConstants.TRACK_2);
			break;
		case 3:
			pos = pos.newX(SSConstants.TRACK_3);
			break;
		}
	}
	
	

}
